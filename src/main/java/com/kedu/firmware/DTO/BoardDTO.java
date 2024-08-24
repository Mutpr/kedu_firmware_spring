package com.kedu.firmware.DTO;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController

public class BoardDTO extends BookmarkDTO {
	private int board_seq;
	private String board_title;
	private String board_contents;
	private Timestamp board_write_date;
	private int board_view_count;
	private int category_seq;
	private int user_seq;
	private String users_name;

	public int getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}

	public BoardDTO() {}
	public BoardDTO(int board_seq, String board_title, String board_contents, Timestamp board_write_date, int board_view_count, int category_seq, int user_seq) {
		this.board_seq = board_seq;
		this.board_title = board_title;
		this.board_contents = board_contents;
		this.board_write_date = board_write_date;
		this.board_view_count = board_view_count;
		this.category_seq = category_seq;
		this.user_seq = user_seq;
	}

	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_contents() {
		return board_contents;
	}

	public void setBoard_contents(String board_contents) {
		this.board_contents = board_contents;
	}

	public Timestamp getBoard_write_date() {
		return board_write_date;
	}

	public void setBoard_write_date(Timestamp board_write_date) {
		this.board_write_date = board_write_date;
	}

	public int getBoard_view_count() {
		return board_view_count;
	}

	public void setBoard_view_count(int board_view_count) {
		this.board_view_count = board_view_count;
	}

	public int getCategory_seq() {
		return category_seq;
	}

	public void setCategory_seq(int category_seq) {
		this.category_seq = category_seq;
	}

	public String getUsers_name() {
		return users_name;
	}

	public void setUsers_name(String user_name) {
		this.users_name = user_name;
	}

}
