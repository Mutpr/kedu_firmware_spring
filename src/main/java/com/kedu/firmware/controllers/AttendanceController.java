package com.kedu.firmware.controllers;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kedu.firmware.DTO.AttendanceDTO;
import com.kedu.firmware.services.AttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceServ;

    @PostMapping
    public ResponseEntity<String> handleAttendance(@RequestBody AttendanceDTO attendancedto) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

            if (attendancedto.getCheck_in_time() != null) {
                // check_in_time을 Timestamp로 직접 설정
                Timestamp checkInTimestamp = Timestamp.valueOf(attendancedto.getCheck_in_time().toString());
                System.out.println(checkInTimestamp);
                attendancedto.setCheck_in_time(checkInTimestamp);

                attendanceServ.insertCheckInTime(attendancedto);
                return ResponseEntity.ok("출석 기록 성공");
            }

            if (attendancedto.getCheck_out_time() != null) {
                // check_out_time을 Timestamp로 직접 설정
                Timestamp checkOutTimestamp = Timestamp.valueOf(attendancedto.getCheck_out_time().toString());
                attendancedto.setCheck_out_time(checkOutTimestamp);

                attendanceServ.insertCheckOutTime(attendancedto);
                return ResponseEntity.ok("퇴근 기록 성공");
            }

            return ResponseEntity.badRequest().body("Invalid request");

        } catch (Exception e) {
            e.printStackTrace(); // 서버 로그에 예외 메시지를 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에서 오류가 발생했습니다: " + e.getMessage());
        }
    }
    
    
    // 근태관리 event 출력 처리
    @GetMapping("/events")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceEvents(
            @RequestParam("users_seq") int usersSeq,
            @RequestParam("start_date") String startDate,
            @RequestParam("end_date") String endDate) {
        List<AttendanceDTO> events = attendanceServ.getAttendanceEvents(usersSeq, startDate, endDate);
        
        //event 제대로 가져와지는지 확인을 위한 테스트 코드
//        for (AttendanceDTO event : events) {
//            System.out.println("Attendance ID: " + event.getAttendance_id());
//            System.out.println("User Seq: " + event.getUsers_seq());
//            System.out.println("Attendance Date: " + event.getAttendance_date());
//            System.out.println("Check-In Time: " + event.getCheck_in_time());
//            System.out.println("Check-Out Time: " + event.getCheck_out_time());
//            System.out.println("Status: " + event.getStatus());
//            System.out.println("-------------------------------");
//        }
        
        return ResponseEntity.ok(events);
    }
    
    // 출석 결석 시간 가져오기위한 get
    @GetMapping("/check")
    public ResponseEntity<Map<String, Object>> checkAttendanceStatus(@RequestParam("users_seq") int usersSeq) {
        AttendanceDTO attendance = attendanceServ.checkIfAlreadyCheckedIn(usersSeq);
        
        Map<String, Object> response = new HashMap<>();
        if (attendance != null) {
            response.put("checkIn", attendance.getCheck_in_time());
            response.put("checkOut", attendance.getCheck_out_time());
        } else {
            response.put("message", "No attendance record found.");
        }        
        return ResponseEntity.ok(response);
    }
    
    
 // 근태 정보 출력
    @GetMapping("/checkAttendanceSummary")
    public ResponseEntity<Map<String, Object>> getAttendanceSummary(
        @RequestParam("usersSeq") int usersSeq, // 자바 컨벤션에 맞게 usersSeq로 수정
        @RequestParam("month") String month) {

        // 해당 월의 시작일과 종료일 계산
        YearMonth yearMonth = YearMonth.parse(month); // YYYY-MM 형식으로 들어온 month를 파싱
        LocalDate startDate = yearMonth.atDay(1); // 월의 첫날
        LocalDate endDate = yearMonth.atEndOfMonth(); // 월의 마지막 날
        System.out.println("Month: " + month);

        // 서비스 레이어에서 데이터를 가져옴
        List<AttendanceDTO> monthlyRecords = attendanceServ.getMonthlyAttendance(usersSeq, startDate.toString(), endDate.toString());
        System.out.println("Monthly Records Size: " + monthlyRecords.size());

        // 요약 데이터를 담을 맵을 준비
        Map<String, Object> summary = new HashMap<>();
        int daysPresent = 0;
        int daysLate = 0;
        int daysAbsent = 0;
        int earlyLeave = 0;

        // 해당 월의 모든 날짜에 대해 출석 기록을 체크
        for (int day = 1; day <= endDate.getDayOfMonth(); day++) { // 실제 월의 마지막 날까지만 반복
            LocalDate currentDate = yearMonth.atDay(day);
            boolean isAbsent = true;

            for (AttendanceDTO record : monthlyRecords) {
                LocalDate recordDate = record.getAttendance_date().toLocalDate();

                if (recordDate.equals(currentDate)) {
                    isAbsent = false;

                    // 정상 퇴근한 날만 출석 일수에 포함
                    if (record.getStatus().equalsIgnoreCase("퇴근")) {
                        daysPresent++;
                    }

                    // 조퇴 일수 증가
                    if (record.getStatus().equalsIgnoreCase("조퇴")) {
                        earlyLeave++;
                    }

                    // 지각 처리
                    if (record.getCheck_in_time() != null) {
                        LocalDateTime checkInDateTime = record.getCheck_in_time().toLocalDateTime();
                        LocalTime checkInTime = checkInDateTime.toLocalTime();

                        if (checkInTime.isAfter(LocalTime.of(9, 10))) {
                            daysLate++;
                        }
                    }
                }
            }

            // 결근 처리: 해당 날짜에 출석 기록이 없으면 결근으로 간주
            if (isAbsent) {
                daysAbsent++;
            }
        }

        summary.put("daysPresent", daysPresent); // 출석 일수 (정상 퇴근한 날만 포함)
        summary.put("daysLate", daysLate); // 지각 일수
        summary.put("daysAbsent", daysAbsent); // 결근 일수
        summary.put("earlyLeave", earlyLeave); // 조퇴 일수

        return ResponseEntity.ok(summary);
    }


    // 근태관리 event 출력 처리 (하루 일정 조회)
    @GetMapping("/departmentEvents")
    public ResponseEntity<List<AttendanceDTO>> getDepartmentEvents(
            @RequestParam("users_seq") int usersSeq,  
            @RequestParam("date") String date) { 

        // 클라이언트에서 받은 날짜를 "yyyy-MM-dd" 형식으로 파싱한 후 "yy/MM/dd" 형식으로 변환
        String formattedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                        .format(DateTimeFormatter.ofPattern("yy/MM/dd"));
        System.out.println("파싱된 시간은" + formattedDate);
        // 변환된 날짜를 사용하여 서비스 호출
        List<AttendanceDTO> events = attendanceServ.getDepartmentEventsForDate(usersSeq, formattedDate);

        if (events.isEmpty()) {
            System.out.println("부서 인원들의 일정 없음");
            return ResponseEntity.noContent().build();  
        }
        return ResponseEntity.ok(events);  
    }



    
    
    
}
