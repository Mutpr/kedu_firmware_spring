package com.kedu.firmware.DTO;

public class DepartmentDTO {

    private int department_seq;
    private String department_unit_seq; // VARCHAR2 -> String
    private int department_captain_user_seq;
    private String department_title;
    private String department_code; // VARCHAR2 -> String
    private String department_description;

    public DepartmentDTO() {
        super();
    }

    public DepartmentDTO(int department_seq, String department_unit_seq, int department_captain_user_seq,
                         String department_title, String department_code, String department_description) {
        super();
        this.department_seq = department_seq;
        this.department_unit_seq = department_unit_seq;
        this.department_captain_user_seq = department_captain_user_seq;
        this.department_title = department_title;
        this.department_code = department_code;
        this.department_description = department_description;
    }

    public int getDepartment_seq() {
        return department_seq;
    }

    public void setDepartment_seq(int department_seq) {
        this.department_seq = department_seq;
    }

    public String getDepartment_unit_seq() {
        return department_unit_seq;
    }

    public void setDepartment_unit_seq(String department_unit_seq) {
        this.department_unit_seq = department_unit_seq;
    }

    public int getDepartment_captain_user_seq() {
        return department_captain_user_seq;
    }

    public void setDepartment_captain_user_seq(int department_captain_user_seq) {
        this.department_captain_user_seq = department_captain_user_seq;
    }

    public String getDepartment_title() {
        return department_title;
    }

    public void setDepartment_title(String department_title) {
        this.department_title = department_title;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public String getDepartment_description() {
        return department_description;
    }

    public void setDepartment_description(String department_description) {
        this.department_description = department_description;
    }
}
