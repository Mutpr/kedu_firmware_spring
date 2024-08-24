package com.kedu.firmware.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kedu.firmware.DTO.AttendanceDTO;
import com.kedu.firmware.DTO.MailBoxDTO;

@Repository
public class AttendanceDAO {

	@Autowired
	private SqlSession mybatis;
	
//	//메일함 생성 (메일 작성될때)
//	public void insertMailBox(MailBoxDTO dto) {
//		mybatis.insert("MailBox.insertMailBox", dto);
//	}
	
	public void insertCheckInTime(AttendanceDTO attendancedto) {
		mybatis.insert("Attendance.insertCheckInTime", attendancedto);
	}
	
	public void insertCheckOutTime(AttendanceDTO attendancedto) {
		mybatis.insert("Attendance.insertCheckOutTime", attendancedto);
	}
	
	public AttendanceDTO checkIfAlreadyCheckedIn(int users_seq) {
		return mybatis.selectOne("Attendance.checkIfAlreadyCheckedIn", users_seq);
	}
	
	public List<AttendanceDTO> getAttendanceEvents(int usersSeq, String startDate, String endDate){
		Map<String, Object> params = new HashMap<>();
        params.put("usersSeq", usersSeq);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
		return mybatis.selectList("Attendance.getAttendanceEvents",params);
	}

	public List<AttendanceDTO> getMonthlyAttendance(int usersSeq, String startDate, String endDate) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("usersSeq", usersSeq);
	    params.put("startDate", startDate);
	    params.put("endDate", endDate);
	    return mybatis.selectList("Attendance.getMonthlyAttendance", params);
	}
	
	public List<AttendanceDTO> findEventsByUserSeqAndDate(int usersSeq, String date) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("usersSeq", usersSeq);
	    params.put("date", date);

	    return mybatis.selectList("Attendance.findAttendanceEventsByDepartment", params);
	}

	// 근태 일정 삽입(휴가)
    public void insertAttendance(AttendanceDTO attendanceDTO) {
        mybatis.insert("Attendance.insertAttendance", attendanceDTO);
    }
    
}
