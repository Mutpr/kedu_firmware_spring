package com.kedu.firmware.DAO;

import java.sql.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kedu.firmware.DTO.AnnualVacationManagementDTO;
import com.kedu.firmware.DTO.VacationApplicationDTO;

@Repository
public class VacationDAO {

    @Autowired
    private SqlSession mybatis;

    // 연차 관리 테이블에서 사용자 연차 정보 조회
    public AnnualVacationManagementDTO getAnnualVacationInfo(int usersSeq) {
        try {
            return mybatis.selectOne("Vacation.getAnnualVacationInfo", usersSeq);
        } catch (Exception e) {
            // 로깅 추가
            System.err.println("Error fetching vacation info for userSeq: " + usersSeq);
            e.printStackTrace();
            return null;
        }
    }

    // 연차 지급
    public void insertAnnualVacation(int usersSeq, int totalVacationDays) {
        Map<String, Object> params = new HashMap<>();
        params.put("usersSeq", usersSeq);
        params.put("totalAnnualVacationDays", totalVacationDays);
        params.put("usedAnnualVacationDays", 0);  // 처음 지급할 때는 사용된 연차가 없으므로 0으로 설정
        params.put("remainAnnualVacationDays", totalVacationDays);
        mybatis.insert("Vacation.insertAnnualVacation", params);
    }

    // 휴가 신청 삽입
    public void insertVacationApplication(VacationApplicationDTO vacationApplication) {
        mybatis.insert("Vacation.insertVacationApplication", vacationApplication);
    }

    // 휴가 신청 내역 조회
    public List<VacationApplicationDTO> getVacationApplicationsByUser(int userSeq) {
        return mybatis.selectList("Vacation.getVacationApplicationsByUser", userSeq);
    }

    // 모든 유저의 휴가 신청 내역 조회
    public List<VacationApplicationDTO> getAllVacationApplications() {
        return mybatis.selectList("Vacation.getAllVacationApplications");
    }

    
 // 휴가 승인 처리
    public void approveVacation(int vacationApplicationSeq, int userSeq) {
        Map<String, Object> params = new HashMap<>();
        params.put("vacationApplicationSeq", vacationApplicationSeq);
        params.put("approverSeq", userSeq);

        // 휴가 승인 및 승인자의 SEQ 업데이트
        mybatis.update("Vacation.approveVacation", params);
    }


    // 연차 사용 업데이트
    public void updateVacationUsage(int usersSeq, int usedDays) {
        Map<String, Object> params = new HashMap<>();
        params.put("usersSeq", usersSeq);
        params.put("usedDays", usedDays);
        mybatis.update("Vacation.updateVacationUsage", params);
    }

    // 연차 사용량 복구
    public void revertVacationUsage(int usersSeq, int usedDays) {
        Map<String, Object> params = new HashMap<>();
        params.put("usersSeq", usersSeq);
        params.put("usedDays", usedDays);
        mybatis.update("Vacation.revertVacationUsage", params);
    }

    // 휴가 신청 삭제
    public void deleteVacationApplication(int vacationApplicationSeq) {
        mybatis.delete("Vacation.deleteVacationApplication", vacationApplicationSeq);
    }

    // 휴가 일정 삭제
    public void deleteAttendanceByVacationApplication(int usersSeq, Date startDate, Date endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("usersSeq", usersSeq);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        
        mybatis.delete("Vacation.deleteAttendanceByVacationApplication", params);
    }

    
    
//    // 특정 기간 동안의 휴가 조회
//    public List<VacationApplicationDTO> getVacationByDateRange(int userSeq, String startDate, String endDate) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("userSeq", userSeq);
//        params.put("startDate", startDate);
//        params.put("endDate", endDate);
//        return mybatis.selectList("Vacation.getVacationByDateRange", params);
//    }
//
    // 휴가 신청 단건 조회 (ID 기준) (삭제 기능에서 사용)
    public VacationApplicationDTO getVacationApplicationById(int vacationApplicationSeq) {
        return mybatis.selectOne("Vacation.getVacationApplicationById", vacationApplicationSeq);
    }
}
