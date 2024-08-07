package com.kedu.firmware.DTO;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
//@RestController
public class MailDTO {

	private int mail_seq;
	private int mail_sender_user_seq;
	private int mailbox_seq;
	private String mail_title;
	private String mail_content;
	private Timestamp mail_send_date;
	private Timestamp mail_received_date;
	private Timestamp mail_deleted_date;
	private char mail_read_status;
	private char mail_deleted_status;
	private char mail_sent_status;

	//--------메일 보낸 사람 받아오기위한 DTO 추가 내용----------
	private String sender_Name; //추가된 변수(DB에는 해당 칼럼 없음)
	
	public String getSender_Name() {
		return sender_Name;
	}

	public void setSender_Name(String sender_Name) {
		this.sender_Name = sender_Name;
	}

	public MailDTO(int mail_seq, int mail_sender_user_seq, int mailbox_seq, String mail_title, String mail_content,
			Timestamp mail_send_date, Timestamp mail_received_date, Timestamp mail_deleted_date, char mail_read_status,
			char mail_deleted_status, char mail_sent_status, String sender_Name) {
		super();
		this.mail_seq = mail_seq;
		this.mail_sender_user_seq = mail_sender_user_seq;
		this.mailbox_seq = mailbox_seq;
		this.mail_title = mail_title;
		this.mail_content = mail_content;
		this.mail_send_date = mail_send_date;
		this.mail_received_date = mail_received_date;
		this.mail_deleted_date = mail_deleted_date;
		this.mail_read_status = mail_read_status;
		this.mail_deleted_status = mail_deleted_status;
		this.mail_sent_status = mail_sent_status;
		this.sender_Name = sender_Name;
	}
	//-----------------------------------------

	public MailDTO(int mail_seq, int mail_sender_user_seq, int mailbox_seq, String mail_title, String mail_content,
			Timestamp mail_send_date, Timestamp mail_received_date, Timestamp mail_deleted_date, char mail_read_status,
			char mail_deleted_status, char mail_sent_status) {
		super();
		this.mail_seq = mail_seq;
		this.mail_sender_user_seq = mail_sender_user_seq;
		this.mailbox_seq = mailbox_seq;
		this.mail_title = mail_title;
		this.mail_content = mail_content;
		this.mail_send_date = mail_send_date;
		this.mail_received_date = mail_received_date;
		this.mail_deleted_date = mail_deleted_date;
		this.mail_read_status = mail_read_status;
		this.mail_deleted_status = mail_deleted_status;
		this.mail_sent_status = mail_sent_status;
	}
	
	
	public int getMail_seq() {
		return mail_seq;
	}

	public void setMail_seq(int mail_seq) {
		this.mail_seq = mail_seq;
	}

	public int getMail_sender_user_seq() {
		return mail_sender_user_seq;
	}

	public void setMail_sender_user_seq(int mail_sender_user_seq) {
		this.mail_sender_user_seq = mail_sender_user_seq;
	}

	public int getMailbox_seq() {
		return mailbox_seq;
	}

	public void setMailbox_seq(int mailbox_seq) {
		this.mailbox_seq = mailbox_seq;
	}

	public String getMail_title() {
		return mail_title;
	}

	public void setMail_title(String mail_title) {
		this.mail_title = mail_title;
	}

	public String getMail_content() {
		return mail_content;
	}

	public void setMail_content(String mail_content) {
		this.mail_content = mail_content;
	}

	public Timestamp getMail_send_date() {
		return mail_send_date;
	}

	public void setMail_send_date(Timestamp mail_send_date) {
		this.mail_send_date = mail_send_date;
	}

	public Timestamp getMail_received_date() {
		return mail_received_date;
	}

	public void setMail_received_date(Timestamp mail_received_date) {
		this.mail_received_date = mail_received_date;
	}

	public Timestamp getMail_deleted_date() {
		return mail_deleted_date;
	}

	public void setMail_deleted_date(Timestamp mail_deleted_date) {
		this.mail_deleted_date = mail_deleted_date;
	}

	public char getMail_read_status() {
		return mail_read_status;
	}

	public void setMail_read_status(char mail_read_status) {
		this.mail_read_status = mail_read_status;
	}

	public char getMail_deleted_status() {
		return mail_deleted_status;
	}

	public void setMail_deleted_status(char mail_deleted_status) {
		this.mail_deleted_status = mail_deleted_status;
	}

	public char getMail_sent_status() {
		return mail_sent_status;
	}

	public void setMail_sent_status(char mail_sent_status) {
		this.mail_sent_status = mail_sent_status;
	}

	public MailDTO() {
	}
}
