package com.kedu.firmware.DTO;

import java.sql.Date;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailAttachmentDTO {
	private int mail_attachment_seq;
	private int mail_seq;
	private String original_file_name;
	private String stored_file_name;
	private long file_size;
	private String file_type;
	private Date upload_date;
	
	
	public MailAttachmentDTO() {
		
	}
	
	public MailAttachmentDTO(int mail_attachment_seq, int mail_seq, String original_file_name, String stored_file_name,
			long file_size, String file_type, Date upload_date) {
		super();
		this.mail_attachment_seq = mail_attachment_seq;
		this.mail_seq = mail_seq;
		this.original_file_name = original_file_name;
		this.stored_file_name = stored_file_name;
		this.file_size = file_size;
		this.file_type = file_type;
		this.upload_date = upload_date;
	}




	public int getMail_attachment_seq() {
		return mail_attachment_seq;
	}




	public void setMail_attachment_seq(int mail_attachment_seq) {
		this.mail_attachment_seq = mail_attachment_seq;
	}




	public int getMail_seq() {
		return mail_seq;
	}




	public void setMail_seq(int mail_seq) {
		this.mail_seq = mail_seq;
	}




	public String getOriginal_file_name() {
		return original_file_name;
	}




	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}




	public String getStored_file_name() {
		return stored_file_name;
	}




	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}




	public long getFile_size() {
		return file_size;
	}




	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}




	public String getFile_type() {
		return file_type;
	}




	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}




	public Date getUpload_date() {
		return upload_date;
	}




	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	
	
}
