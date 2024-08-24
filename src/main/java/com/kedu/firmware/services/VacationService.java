package com.kedu.firmware.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kedu.firmware.DAO.VacationDAO;
import com.kedu.firmware.DTO.AnnualVacationManagementDTO;
import com.kedu.firmware.DTO.AttendanceDTO;
import com.kedu.firmware.DTO.VacationApplicationDTO;

import jakarta.servlet.http.HttpSession;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class VacationService {

    @Autowired
    private VacationDAO vacationDAO;
    
    @Autowired
    private AttendanceService attendanceService;

    
    // 해당 사용자가 이미 연차를 부여받았는지 확인
    public boolean hasVacation(int usersSeq) {
        AnnualVacationManagementDTO vacationInfo = vacationDAO.getAnnualVacationInfo(usersSeq);
        if (vacationInfo == null) {
            System.out.println("연차 정보가 없습니다. usersSeq: " + usersSeq);
            return false;
        }
        boolean hasVacation = vacationInfo.getRemain_annual_vacation_days() > 0;
        System.out.println("연차 정보 확인: " + hasVacation + ", 남은 연차: " + vacationInfo.getRemain_annual_vacation_days());
        return hasVacation;
    }



    // 연차 지급
    @Transactional
    public void allocateVacation(int usersSeq, LocalDate joinDate) {
        // 연차 지급 전에 기존 연차 데이터 확인
        AnnualVacationManagementDTO vacationInfo = vacationDAO.getAnnualVacationInfo(usersSeq);
        if (vacationInfo != null) {
            // 이미 연차가 존재한다면 중복 지급을 방지
            System.out.println("이미 연차가 지급되었습니다. 중복 지급 방지");
            return;
        }

        int baseAnnualLeave = 15;  // 기본 연차 15일
        LocalDate now = LocalDate.now();  // 현재 날짜

        // 입사일과 현재 날짜 사이의 근무 일수 계산
        long daysWorked = ChronoUnit.DAYS.between(joinDate, now);

        // 근무 년차 계산
        int yearsWorked = (int) (daysWorked / 365);

        int totalLeave;
        if (yearsWorked >= 1) {
            // 1년 이상 근무한 경우
            int additionalLeave = Math.min(yearsWorked - 1, 10);  // 2년차부터 매년 1일 추가, 최대 10일
            totalLeave = baseAnnualLeave + additionalLeave;
        } else {
            // 1년 미만 근무한 경우 비례 연차 계산
            totalLeave = calculateProRatedLeave(joinDate, now, baseAnnualLeave);
        }

        // 연차 정보를 데이터베이스에 삽입
        vacationDAO.insertAnnualVacation(usersSeq, totalLeave);
        
        System.out.println("연차가 지급되었습니다. usersSeq: " + usersSeq + ", 총 연차: " + totalLeave);
        AnnualVacationManagementDTO updatedInfo = vacationDAO.getAnnualVacationInfo(usersSeq);
        System.out.println("업데이트된 연차 정보: 남은 연차: " + updatedInfo.getRemain_annual_vacation_days());
    }


    private int calculateProRatedLeave(LocalDate joinDate, LocalDate now, int totalAnnualLeave) {
        long daysWorked = ChronoUnit.DAYS.between(joinDate, now);
        int proRatedLeave = (int) ((daysWorked / 365.0) * totalAnnualLeave);
        return Math.max(proRatedLeave, 1);  // 최소 1일의 연차 보장
    }



 // 휴가 신청 및 근태 일정 등록
    @Transactional
    public void applyForVacation(VacationApplicationDTO vacationApplication) {
        // 1. 휴가 신청 삽입
        vacationDAO.insertVacationApplication(vacationApplication);

        // 2. 휴가 기간 동안 근태 일정 등록
        LocalDate startDate = vacationApplication.getVacation_start_date().toLocalDateTime().toLocalDate();
        LocalDate endDate = vacationApplication.getVacation_end_date().toLocalDateTime().toLocalDate();

        // 각 날짜별로 근태 일정 등록
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            AttendanceDTO attendanceDTO = new AttendanceDTO();
            attendanceDTO.setUsers_seq(vacationApplication.getVacation_drafter_user_seq());
            attendanceDTO.setAttendance_date(Date.valueOf(date));  // java.sql.Date로 변환
            attendanceDTO.setCheck_in_time(Timestamp.valueOf(date.atTime(9, 0)));  // 오전 9시
            attendanceDTO.setCheck_out_time(Timestamp.valueOf(date.atTime(18, 0))); // 오후 6시
            attendanceDTO.setStatus("연차");

            // 근태 일정 삽입
            attendanceService.insertAttendance(attendanceDTO);
        }
     // 3. 연차 사용 업데이트
        updateVacationUsage(vacationApplication);
    }

    // 연차 사용 업데이트
    private void updateVacationUsage(VacationApplicationDTO vacationApplication) {
        LocalDate startDate = vacationApplication.getVacation_start_date().toLocalDateTime().toLocalDate();
        LocalDate endDate = vacationApplication.getVacation_end_date().toLocalDateTime().toLocalDate();
        int usedDays = (int) (ChronoUnit.DAYS.between(startDate, endDate) + 1);

        vacationDAO.updateVacationUsage(vacationApplication.getVacation_drafter_user_seq(), usedDays);
    }

    // 휴가 삭제 처리
    @Transactional
    public void deleteVacation(int vacationApplicationSeq) {
        VacationApplicationDTO vacationApplication = vacationDAO.getVacationApplicationById(vacationApplicationSeq);

        // 1. ANNUAL_VACATION_APPLICATION 테이블에서 연차 사용량 업데이트
        int usedDays = (int) ((vacationApplication.getVacation_end_date().getTime() - vacationApplication.getVacation_start_date().getTime()) / (1000 * 60 * 60 * 24)) + 1;
        vacationDAO.revertVacationUsage(vacationApplication.getVacation_drafter_user_seq(), usedDays);

        // 2. ATTENDANCE 테이블에서 해당 휴가와 관련된 레코드 삭제
        Date startDate = new Date(vacationApplication.getVacation_start_date().getTime());
        Date endDate = new Date(vacationApplication.getVacation_end_date().getTime());
        
        vacationDAO.deleteAttendanceByVacationApplication(vacationApplication.getVacation_drafter_user_seq(), startDate, endDate);

        // 3. VACATION_APPLICATION 테이블에서 휴가 신청 삭제
        vacationDAO.deleteVacationApplication(vacationApplicationSeq);
    }


    // 특정 유저의 휴가 신청 내역 조회
    public List<VacationApplicationDTO> getVacationApplicationsByUser(int userSeq) {
        return vacationDAO.getVacationApplicationsByUser(userSeq);
    }
    
    // 모든 유저의 휴가 신청 내역 조회 (관리자)
    public List<VacationApplicationDTO> getAllVacationApplications() {
        return vacationDAO.getAllVacationApplications();  // 모든 유저의 휴가 신청 내역 조회
    }
    
    // 휴가 승인 처리
    public void approveVacation(int vacationApplicationSeq, int userSeq) {
    	

        vacationDAO.approveVacation(vacationApplicationSeq, userSeq);

        // 휴가 승인 후 연차 사용 업데이트
        VacationApplicationDTO vacationApplication = vacationDAO.getVacationApplicationById(vacationApplicationSeq);
        
        // 휴가 신청을 할때 연차가 소모되는 방향으로 노선을 바꿈 (휴가 신청 취소되면 휴가 사용 복구 됌)
//        int usedDays = (int) ((vacationApplication.getVacation_end_date().getTime() - vacationApplication.getVacation_start_date().getTime()) / (1000 * 60 * 60 * 24)) + 1;
//        vacationDAO.updateVacationUsage(vacationApplication.getVacation_drafter_user_seq(), usedDays);
    }

//    // 특정 기간 동안의 휴가 조회
//    public List<VacationApplicationDTO> getVacationByDateRange(int userSeq, String startDate, String endDate) {
//        return vacationDAO.getVacationByDateRange(userSeq, startDate, endDate);
//    }
//    
    // 특정 사용자의 연차 정보 조회
    public AnnualVacationManagementDTO getAnnualVacationInfo(int usersSeq) {
        return vacationDAO.getAnnualVacationInfo(usersSeq);
    }
}
