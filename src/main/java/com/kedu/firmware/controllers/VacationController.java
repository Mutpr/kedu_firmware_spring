package com.kedu.firmware.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kedu.firmware.DTO.AnnualVacationManagementDTO;
import com.kedu.firmware.DTO.UsersDTO;
import com.kedu.firmware.DTO.VacationApplicationDTO;
import com.kedu.firmware.services.UsersService;
import com.kedu.firmware.services.VacationService;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vacation")
public class VacationController {

    @Autowired
    private VacationService vacationService;

    @Autowired
	private UsersService usersServ;
    
    @Autowired
    private HttpSession session;
    
    // 로그인 후 휴가 상태 체크 및 휴가 지급 처리
    @PostMapping("/check")
    public ResponseEntity<Map<String, String>> checkVacation(@RequestBody Map<String, Object> payload) {
        Map<String, String> response = new HashMap<>();
        
        int usersSeq = (Integer) payload.get("users_seq");
        String joinDateStr = (String) payload.get("joinDate");

        LocalDate joinDate = joinDateStr != null ? LocalDate.parse(joinDateStr.substring(0, 10)) : null;  // 문자열로 받은 입사일을 LocalDate로 변환

        // 해당 사용자가 이미 연차를 부여받았는지 확인
        boolean hasVacation = vacationService.hasVacation(usersSeq);

        if (!hasVacation && joinDate != null) {
            // 첫 로그인 시 연차 부여
            vacationService.allocateVacation(usersSeq, joinDate);
            response.put("message", "휴가가 지급되었습니다");
        } else if (hasVacation) {
            // 이미 연차가 있을 경우 잔여 연차 확인
            response.put("message", "잔여 휴가 체크 완료");
        } else {
            // 연차 지급 정보가 없고, joinDate가 null인 경우에 대한 처리
            response.put("message", "연차 지급 정보가 없습니다.");
        }

        return ResponseEntity.ok(response);
    }




 // 휴가 신청 처리
    @PostMapping("/apply")
    public ResponseEntity<String> applyVacation(@RequestBody VacationApplicationDTO vacationApplication) {
    	// 시작일이 종료일보다 이후일 경우 에러 메시지 반환
        if (vacationApplication.getVacation_start_date().after(vacationApplication.getVacation_end_date())) {
            return ResponseEntity.badRequest().body("휴가 시작일이 종료일보다 늦을 수 없습니다.");
        }
    	
    	try {
            // 휴가 신청 등록
            vacationService.applyForVacation(vacationApplication);
            
            // 성공 메시지 반환
            return ResponseEntity.ok("휴가 신청이 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("휴가 신청에 실패했습니다.");
        }
    }

	 // 휴가 삭제 처리 (추가된 메서드)
	    @DeleteMapping("/delete/{vacationApplicationSeq}")
	    public ResponseEntity<String> deleteVacation(@PathVariable int vacationApplicationSeq) {
	        try {
	            vacationService.deleteVacation(vacationApplicationSeq);
	            return ResponseEntity.ok("휴가가 삭제되었습니다.");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("휴가 삭제에 실패했습니다.");
	        }
	    }
    
    
    // 휴가 신청 내역 조회
    @GetMapping("/applications/{userSeq}")
    public ResponseEntity<List<VacationApplicationDTO>> getVacationApplications(@PathVariable int userSeq) {
        List<VacationApplicationDTO> applications = vacationService.getVacationApplicationsByUser(userSeq);
        return ResponseEntity.ok(applications);
    }

    // 모든 유저의 휴가 신청 내역 조회 (관리자 전용)
    @GetMapping("/applications")
    public ResponseEntity<List<VacationApplicationDTO>> getAllVacationApplications() {
        List<VacationApplicationDTO> applications = vacationService.getAllVacationApplications();
        return ResponseEntity.ok(applications);
    }

    
    // 휴가 승인 처리
    @PostMapping("/approve/{vacationApplicationSeq}")
    public ResponseEntity<String> approveVacation(@PathVariable int vacationApplicationSeq) {
    	
    	// 세션에 저장된 유저코드(로그인 아이디)로 유저 seq찾아서 저장
        String user_code = (String) session.getAttribute("loginID");
        UsersDTO usersdto = usersServ.getMemberById(user_code);
        int userSeq = usersdto.getUsers_seq();
    	
        vacationService.approveVacation(vacationApplicationSeq, userSeq);
        return ResponseEntity.ok("휴가가 승인되었습니다.");
    }

//    // 특정 기간 동안의 휴가 조회
//    @GetMapping("/byDateRange")
//    public ResponseEntity<List<VacationApplicationDTO>> getVacationByDateRange(
//            @RequestParam int userSeq, 
//            @RequestParam String startDate, 
//            @RequestParam String endDate) {
//        List<VacationApplicationDTO> vacations = vacationService.getVacationByDateRange(userSeq, startDate, endDate);
//        return ResponseEntity.ok(vacations);
//    }
    
    // 사용자의 연차 정보 조회
    @GetMapping("/annual/{usersSeq}")
    public ResponseEntity<AnnualVacationManagementDTO> getAnnualVacationInfo(@PathVariable int usersSeq) {
        AnnualVacationManagementDTO annualVacationInfo = vacationService.getAnnualVacationInfo(usersSeq);
        if (annualVacationInfo != null) {
            return ResponseEntity.ok(annualVacationInfo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
