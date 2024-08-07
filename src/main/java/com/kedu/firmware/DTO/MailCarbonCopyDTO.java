package com.kedu.firmware.DTO;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailCarbonCopyDTO {
	private int mail_carbon_copy_seq;
	private String mail_carbon_receiver;
	private int mail_seq;
	private int mail_sender_user_seq;
	private int mailbox_seq;
	private String carbon_copy_type;
	private String carbon_copy_status;
	
	public MailCarbonCopyDTO(int mail_carbon_copy_seq, String mail_carbon_receiver, int mail_seq,
			int mail_sender_user_seq, int mailbox_seq, String carbon_copy_type, String carbon_copy_status) {
		super();
		this.mail_carbon_copy_seq = mail_carbon_copy_seq;
		this.mail_carbon_receiver = mail_carbon_receiver;
		this.mail_seq = mail_seq;
		this.mail_sender_user_seq = mail_sender_user_seq;
		this.mailbox_seq = mailbox_seq;
		this.carbon_copy_type = carbon_copy_type;
		this.carbon_copy_status = carbon_copy_status;
	}
	
	public MailCarbonCopyDTO() {
		
	}
	
	
	
	public int getMail_carbon_copy_seq() {
		return mail_carbon_copy_seq;
	}
	public void setMail_carbon_copy_seq(int mail_carbon_copy_seq) {
		this.mail_carbon_copy_seq = mail_carbon_copy_seq;
	}
	public String getMail_carbon_receiver() {
		return mail_carbon_receiver;
	}
	public void setMail_carbon_receiver(String mail_carbon_receiver) {
		this.mail_carbon_receiver= mail_carbon_receiver;
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
	public String getCarbon_copy_type() {
		return carbon_copy_type;
	}
	
	public void setCarbon_copy_type(String carbon_copy_type) {
		this.carbon_copy_type = carbon_copy_type;
	}
	public String getCarbon_copy_status() {
		return carbon_copy_status;
	}
	public void setCarbon_copy_status(String carbon_copy_status) {
		this.carbon_copy_status = carbon_copy_status;
	}
	
	
}
