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
	 //(Mail 테이블에는 해당 칼럼 없음)
	private String sender_name; // 발신자 이름
	private String sender_email; // 발신자 이메일
	private String sender_department_name; // 발신자 부서 이름
	
	private String receiver_email; // 받는 사람 이메일
	private String receiver_name;
	private String receiver_department_name;
	private String copyType;	// 회신 or 수신 타입 구분을 위한 칼럼
	
	//---각 메일에 첨부파일이 있는지 여부를 판단하기위한 DTO 추가 내용
	
	private boolean has_attachments;

    public boolean isHas_attachments() {
        return has_attachments;
    }

    public void setHas_attachments(boolean has_attachments) {
        this.has_attachments = has_attachments;
    }

	
	//
	public MailDTO(int mail_seq, int mail_sender_user_seq, int mailbox_seq, String mail_title, String mail_content,
			Timestamp mail_send_date, Timestamp mail_received_date, Timestamp mail_deleted_date, char mail_read_status,
			char mail_deleted_status, char mail_sent_status, String sender_name, String sender_email,
			String sender_department_name, String receiver_email, String receiver_name, String receiver_department_name,
			String copyType) {
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
		this.sender_name = sender_name;
		this.sender_email = sender_email;
		this.sender_department_name = sender_department_name;
		this.receiver_email = receiver_email;
		this.receiver_name = receiver_name;
		this.receiver_department_name = receiver_department_name;
		this.copyType = copyType;
	}


	public String getSender_name() {
		return sender_name;
	}


	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}


	public String getSender_email() {
		return sender_email;
	}


	public void setSender_email(String sender_email) {
		this.sender_email = sender_email;
	}


	public String getSender_department_name() {
		return sender_department_name;
	}


	public void setSender_department_name(String sender_department_name) {
		this.sender_department_name = sender_department_name;
	}


	public String getReceiver_email() {
		return receiver_email;
	}


	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}


	public String getReceiver_name() {
		return receiver_name;
	}


	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}


	public String getReceiver_department_name() {
		return receiver_department_name;
	}


	public void setReceiver_department_name(String receiver_department_name) {
		this.receiver_department_name = receiver_department_name;
	}


	public String getCopyType() {
		return copyType;
	}


	public void setCopyType(String copyType) {
		this.copyType = copyType;
	}
	


	

	//-----------------------------------------

	//전체 메일 출력 할때 sender_name 포함
	public MailDTO(int mail_seq, int mail_sender_user_seq, int mailbox_seq, String mail_title, String mail_content,
			Timestamp mail_send_date, Timestamp mail_received_date, Timestamp mail_deleted_date, char mail_read_status,
			char mail_deleted_status, char mail_sent_status, String sender_name) {
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
		this.sender_name = sender_name;
	}
	

	//-----------------------------------------------
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
