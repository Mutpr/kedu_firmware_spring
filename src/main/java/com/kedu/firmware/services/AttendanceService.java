package com.kedu.firmware.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.firmware.DAO.AttendanceDAO;
import com.kedu.firmware.DTO.AttendanceDTO;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceDAO attendancedao;
	
	public void insertCheckInTime(AttendanceDTO attendancedto) {
		attendancedao.insertCheckInTime(attendancedto);
	}

	public void insertCheckOutTime(AttendanceDTO attendancedto) {
		attendancedao.insertCheckOutTime(attendancedto);
	}
	
	public AttendanceDTO checkIfAlreadyCheckedIn(int users_seq) {
		return attendancedao.checkIfAlreadyCheckedIn(users_seq);
	}
	
	public List<AttendanceDTO> getAttendanceEvents(int usersSeq, String startDate, String endDate) {
        return attendancedao.getAttendanceEvents(usersSeq, startDate, endDate);
    }

	public List<AttendanceDTO> getMonthlyAttendance(int usersSeq, String startDate, String endDate) {
	    return attendancedao.getMonthlyAttendance(usersSeq, startDate, endDate);
	}
	
	// 특정 사용자의 특정 날짜에 대한 이벤트를 조회하는 메서드
    public List<AttendanceDTO> getDepartmentEventsForDate(int usersSeq, String date) {
        return attendancedao.findEventsByUserSeqAndDate(usersSeq, date);
    }

    // 근태 일정 삽입(휴가)
    public void insertAttendance(AttendanceDTO attendanceDTO) {
        attendancedao.insertAttendance(attendanceDTO);
    }
    
}
