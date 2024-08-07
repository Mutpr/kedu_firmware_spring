package com.kedu.firmware.DTO;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class Board_ReplyDTO {
    private int reply_seq;
    private String reply_userName;
    private String reply_contents;
    private Timestamp reply_reg_date;
    private int board_seq;

    public Board_ReplyDTO(int reply_seq, String reply_userName, String reply_contents, Timestamp reply_reg_date, int board_seq) {
        this.reply_seq = reply_seq;
        this.reply_userName = reply_userName;
        this.reply_contents = reply_contents;
        this.reply_reg_date = reply_reg_date;
        this.board_seq = board_seq;
    }

    public int getBoard_seq() {
        return board_seq;
    }

    public void setBoard_seq(int board_seq) {
        this.board_seq = board_seq;
    }

    public Timestamp getReply_reg_date() {
        return reply_reg_date;
    }

    public void setReply_reg_date(Timestamp reply_reg_date) {
        this.reply_reg_date = reply_reg_date;
    }

    public String getReply_contents() {
        return reply_contents;
    }

    public void setReply_contents(String reply_contents) {
        this.reply_contents = reply_contents;
    }

    public String getReply_userName() {
        return reply_userName;
    }

    public void setReply_userName(String reply_userName) {
        this.reply_userName = reply_userName;
    }

    public int getReply_seq() {
        return reply_seq;
    }

    public void setReply_seq(int reply_seq) {
        this.reply_seq = reply_seq;
    }

    public Board_ReplyDTO() {
    }
}