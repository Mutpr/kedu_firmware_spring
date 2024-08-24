package com.kedu.firmware.DTO;

import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class UsersDTO {
    private Integer users_seq;
    private String users_code;
    private String users_name;
    private String users_password;
    private String users_email;
    private String users_full_name;
    private String department_code;  // 추가된 필드: 부서 코드
    private String unit_code;  // 추가된 필드: 유닛 코드
    private Timestamp users_created_at;
    private Timestamp users_updated_at;
    private Integer users_is_admin;

    public UsersDTO() {}

    public UsersDTO(Integer users_seq, String users_code, String users_name, String users_password, String users_email, 
                    String users_full_name, String department_code, String unit_code, 
                    Timestamp users_created_at, Timestamp users_updated_at, Integer users_is_admin) {
        this.users_seq = users_seq;
        this.users_code = users_code;
        this.users_name = users_name;
        this.users_password = users_password;
        this.users_email = users_email;
        this.users_full_name = users_full_name;
        this.department_code = department_code;
        this.unit_code = unit_code;
        this.users_created_at = users_created_at;
        this.users_updated_at = users_updated_at;
        this.users_is_admin = users_is_admin;
    }

    // Getters and Setters

    public Integer getUsers_seq() {
        return users_seq;
    }

    public void setUsers_seq(Integer users_seq) {
        this.users_seq = users_seq;
    }

    public String getUsers_code() {
        return users_code;
    }

    public void setUsers_code(String users_code) {
        this.users_code = users_code;
    }

    public String getUsers_name() {
        return users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public String getUsers_password() {
        return users_password;
    }

    public void setUsers_password(String users_password) {
        this.users_password = users_password;
    }

    public String getUsers_email() {
        return users_email;
    }

    public void setUsers_email(String users_email) {
        this.users_email = users_email;
    }

    public String getUsers_full_name() {
        return users_full_name;
    }

    public void setUsers_full_name(String users_full_name) {
        this.users_full_name = users_full_name;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public String getUnit_code() {
        return unit_code;
    }

    public void setUnit_code(String unit_code) {
        this.unit_code = unit_code;
    }

    public Timestamp getUsers_created_at() {
        return users_created_at;
    }

    public void setUsers_created_at(Timestamp users_created_at) {
        this.users_created_at = users_created_at;
    }

    public Timestamp getUsers_updated_at() {
        return users_updated_at;
    }

    public void setUsers_updated_at(Timestamp users_updated_at) {
        this.users_updated_at = users_updated_at;
    }

    public Integer getUsers_is_admin() {
        return users_is_admin;
    }

    public void setUsers_is_admin(Integer users_is_admin) {
        this.users_is_admin = users_is_admin;
    }

    public boolean isAdmin() {
        return users_is_admin != null && users_is_admin == 1;
    }
}
