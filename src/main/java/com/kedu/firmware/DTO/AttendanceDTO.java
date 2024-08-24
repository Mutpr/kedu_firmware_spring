package com.kedu.firmware.DTO;

import java.sql.Date;
import java.sql.Timestamp;

public class AttendanceDTO {

	private int attendance_id;
	private int users_seq;
	private Date attendance_date;
	private Timestamp check_in_time;
	private Timestamp check_out_time;
	private String status;	
	
	//
	// 조인을 위해 추가된 필드
    private String vacation_application_status;  

    public String getVacation_application_status() {
		return vacation_application_status;
	}

	public void setVacation_application_status(String vacation_application_status) {
		this.vacation_application_status = vacation_application_status;
	}
	
    public AttendanceDTO(int attendance_id, int users_seq, Date attendance_date, Timestamp check_in_time,
			Timestamp check_out_time, String status, String vacation_application_status) {
		super();
		this.attendance_id = attendance_id;
		this.users_seq = users_seq;
		this.attendance_date = attendance_date;
		this.check_in_time = check_in_time;
		this.check_out_time = check_out_time;
		this.status = status;
		this.vacation_application_status = vacation_application_status;
	}

	//
    
    
    
	

	public AttendanceDTO(int attendance_id, int users_seq, Date attendance_date, Timestamp check_in_time,
			Timestamp check_out_time, String status) {
		super();
		this.attendance_id = attendance_id;
		this.users_seq = users_seq;
		this.attendance_date = attendance_date;
		this.check_in_time = check_in_time;
		this.check_out_time = check_out_time;
		this.status = status;
	}

	public AttendanceDTO(){
		
	}

	public int getAttendance_id() {
		return attendance_id;
	}

	public void setAttendance_id(int attendance_id) {
		this.attendance_id = attendance_id;
	}

	public int getUsers_seq() {
		return users_seq;
	}

	public void setUsers_seq(int users_seq) {
		this.users_seq = users_seq;
	}

	public Date getAttendance_date() {
		return attendance_date;
	}

	public void setAttendance_date(Date attendance_date) {
		this.attendance_date = attendance_date;
	}

	public Timestamp getCheck_in_time() {
		return check_in_time;
	}

	public void setCheck_in_time(Timestamp check_in_time) {
		this.check_in_time = check_in_time;
	}

	public Timestamp getCheck_out_time() {
		return check_out_time;
	}

	public void setCheck_out_time(Timestamp check_out_time) {
		this.check_out_time = check_out_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
}
