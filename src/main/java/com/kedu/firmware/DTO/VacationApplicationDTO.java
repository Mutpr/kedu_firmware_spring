package com.kedu.firmware.DTO;

import java.sql.Timestamp;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationApplicationDTO {
	private int vacation_application_seq;
	private int vacation_drafter_user_seq;
	private int vacation_type_seq;
	private int vacation_permission_user_seq;
	private Timestamp vacation_start_date;
	private Timestamp vacation_end_date;
	private Timestamp vacation_application_date;
	private char vacation_application_status;
	private String vacation_application_reason;
	
	public VacationApplicationDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getVacation_application_seq() {
		return vacation_application_seq;
	}

	public void setVacation_application_seq(int vacation_application_seq) {
		this.vacation_application_seq = vacation_application_seq;
	}

	public int getVacation_drafter_user_seq() {
		return vacation_drafter_user_seq;
	}

	public void setVacation_drafter_user_seq(int vacation_drafter_user_seq) {
		this.vacation_drafter_user_seq = vacation_drafter_user_seq;
	}

	public int getVacation_type_seq() {
		return vacation_type_seq;
	}

	public void setVacation_type_seq(int vacation_type_seq) {
		this.vacation_type_seq = vacation_type_seq;
	}

	public int getVacation_permission_user_seq() {
		return vacation_permission_user_seq;
	}

	public void setVacation_permission_user_seq(int vacation_permission_user_seq) {
		this.vacation_permission_user_seq = vacation_permission_user_seq;
	}

	public Timestamp getVacation_start_date() {
		return vacation_start_date;
	}

	public void setVacation_start_date(Timestamp vacation_start_date) {
		this.vacation_start_date = vacation_start_date;
	}

	public Timestamp getVacation_end_date() {
		return vacation_end_date;
	}

	public void setVacation_end_date(Timestamp vacation_end_date) {
		this.vacation_end_date = vacation_end_date;
	}

	public Timestamp getVacation_application_date() {
		return vacation_application_date;
	}

	public void setVacation_application_date(Timestamp vacation_application_date) {
		this.vacation_application_date = vacation_application_date;
	}

	public char getVacation_application_status() {
		return vacation_application_status;
	}

	public void setVacation_application_status(char vacation_application_status) {
		this.vacation_application_status = vacation_application_status;
	}

	public String getVacation_application_reason() {
		return vacation_application_reason;
	}

	public void setVacation_application_reason(String vacation_application_reason) {
		this.vacation_application_reason = vacation_application_reason;
	}

	public VacationApplicationDTO(int vacation_application_seq, int vacation_drafter_user_seq, int vacation_type_seq,
			int vacation_permission_user_seq, Timestamp vacation_start_date, Timestamp vacation_end_date,
			Timestamp vacation_application_date, char vacation_application_status, String vacation_application_reason) {
		super();
		this.vacation_application_seq = vacation_application_seq;
		this.vacation_drafter_user_seq = vacation_drafter_user_seq;
		this.vacation_type_seq = vacation_type_seq;
		this.vacation_permission_user_seq = vacation_permission_user_seq;
		this.vacation_start_date = vacation_start_date;
		this.vacation_end_date = vacation_end_date;
		this.vacation_application_date = vacation_application_date;
		this.vacation_application_status = vacation_application_status;
		this.vacation_application_reason = vacation_application_reason;
	}
	
	
	
	
}
