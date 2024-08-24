package com.kedu.firmware.DTO;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class Board_CategoryDTO {

    private int category_seq;
    private String category_name;
    private String category_status;
    private Timestamp category_reg_date;

    public Board_CategoryDTO() {}

    public int getCategory_seq() {
        return category_seq;
    }

    public void setCategory_seq(int category_seq) {
        this.category_seq = category_seq;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_status() {
        return category_status;
    }

    public void setCategory_status(String category_status) {
        this.category_status = category_status;
    }

    public Timestamp getCategory_reg_date() {
        return category_reg_date;
    }

    public void setCategory_reg_date(Timestamp category_reg_date) {
        this.category_reg_date = category_reg_date;
    }

    public Board_CategoryDTO(int category_seq, String category_name, String category_status, Timestamp category_reg_date) {
        this.category_seq = category_seq;
        this.category_name = category_name;
        this.category_status = category_status;
        this.category_reg_date = category_reg_date;
    }
}
