package com.kedu.firmware.DTO;

import org.springframework.web.bind.annotation.RestController;


import java.sql.Timestamp;

@RestController
public class CalendarDTO {
	private int calendar_seq;
	private int group_seq;
	private String calendar_title;
	private String calendar_writer;
	private String calendar_content;
	private Timestamp calendar_start_date;
	private Timestamp calendar_end_date;
	private char calendar_allday;
	private String calendar_textcolor;
	private String calendar_background_color;
	private String calendar_border_color;
	public int getCalendar_seq() {
		return calendar_seq;
	}
	public void setCalendar_seq(int calendar_seq) {
		this.calendar_seq = calendar_seq;
	}
	public int getGroup_seq() {
		return group_seq;
	}
	public void setGroup_seq(int group_seq) {
		this.group_seq = group_seq;
	}
	public String getCalendar_title() {
		return calendar_title;
	}
	public void setCalendar_title(String calendar_title) {
		this.calendar_title = calendar_title;
	}
	public String getCalendar_writer() {
		return calendar_writer;
	}
	public void setCalendar_writer(String calendar_writer) {
		this.calendar_writer = calendar_writer;
	}
	public String getCalendar_content() {
		return calendar_content;
	}
	public void setCalendar_content(String calendar_content) {
		this.calendar_content = calendar_content;
	}
	public Timestamp getCalendar_start_date() {
		return calendar_start_date;
	}
	public void setCalendar_start_date(Timestamp calendar_start_date) {
		this.calendar_start_date = calendar_start_date;
	}
	public Timestamp getCalendar_end_date() {
		return calendar_end_date;
	}
	public void setCalendar_end_date(Timestamp calendar_end_date) {
		this.calendar_end_date = calendar_end_date;
	}
	public char getCalendar_allday() {
		return calendar_allday;
	}
	public void setCalendar_allday(char calendar_allday) {
		this.calendar_allday = calendar_allday;
	}
	public String getCalendar_textcolor() {
		return calendar_textcolor;
	}
	public void setCalendar_textcolor(String calendar_textcolor) {
		this.calendar_textcolor = calendar_textcolor;
	}
	public String getCalendar_background_color() {
		return calendar_background_color;
	}
	public void setCalendar_background_color(String calendar_background_color) {
		this.calendar_background_color = calendar_background_color;
	}
	public String getCalendar_border_color() {
		return calendar_border_color;
	}
	public void setCalendar_border_color(String calendar_border_color) {
		this.calendar_border_color = calendar_border_color;
	}
	
	
}
