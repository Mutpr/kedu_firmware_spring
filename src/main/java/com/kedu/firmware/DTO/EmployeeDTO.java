package com.kedu.firmware.DTO;

import java.sql.Timestamp;

public class EmployeeDTO {
    
    private Integer employee_seq;           // 사원 고유 번호 (기본 키)
    private Integer user_seq;               // 사용자 고유 번호 (외래 키)
    private Integer department_seq;         // 부서 고유 번호 (외래 키)
    private String department_title;        // 부서 이름
    private Integer unit_seq;               // 유닛 고유 번호 (외래 키)
    private String unit_title;              // 유닛 이름
    private Integer rank_seq;               // 직급 고유 번호 (외래 키)
    private String rank_title;              // 직급 이름
    private String users_name;              // 사용자 이름
    private String employee_code;           // 사원 코드
    private Timestamp employee_hire_date;   // 입사 날짜
    private Timestamp employee_leave_date;  // 퇴사 날짜
    private String is_employee_validate;    // 직원 유효성 여부

    // 기본 생성자
    public EmployeeDTO() {}

    // 모든 필드를 포함한 생성자
    public EmployeeDTO(Integer employee_seq, Integer user_seq, Integer department_seq, String department_title, 
                       Integer unit_seq, String unit_title, Integer rank_seq, String rank_title, 
                       String users_name, String employee_code, Timestamp employee_hire_date, 
                       Timestamp employee_leave_date, String is_employee_validate) {
        this.employee_seq = employee_seq;
        this.user_seq = user_seq;
        this.department_seq = department_seq;
        this.department_title = department_title;
        this.unit_seq = unit_seq;
        this.unit_title = unit_title;
        this.rank_seq = rank_seq;
        this.rank_title = rank_title;
        this.users_name = users_name;
        this.employee_code = employee_code;
        this.employee_hire_date = employee_hire_date;
        this.employee_leave_date = employee_leave_date;
        this.is_employee_validate = is_employee_validate;
    }

    // Getters and Setters

    public Integer getEmployee_seq() {
        return employee_seq;
    }

    public void setEmployee_seq(Integer employee_seq) {
        this.employee_seq = employee_seq;
    }

    public Integer getUser_seq() {
        return user_seq;
    }

    public void setUser_seq(Integer user_seq) {
        this.user_seq = user_seq;
    }

    public Integer getDepartment_seq() {
        return department_seq;
    }

    public void setDepartment_seq(Integer department_seq) {
        this.department_seq = department_seq;
    }

    public String getDepartment_title() {
        return department_title;
    }

    public void setDepartment_title(String department_title) {
        this.department_title = department_title;
    }

    public Integer getUnit_seq() {
        return unit_seq;
    }

    public void setUnit_seq(Integer unit_seq) {
        this.unit_seq = unit_seq;
    }

    public String getUnit_title() {
        return unit_title;
    }

    public void setUnit_title(String unit_title) {
        this.unit_title = unit_title;
    }

    public Integer getRank_seq() {
        return rank_seq;
    }

    public void setRank_seq(Integer rank_seq) {
        this.rank_seq = rank_seq;
    }

    public String getRank_title() {
        return rank_title;
    }

    public void setRank_title(String rank_title) {
        this.rank_title = rank_title;
    }

    public String getUsers_name() {
        return users_name;
    }

    public void setUsers_name(String users_name) {
        this.users_name = users_name;
    }

    public String getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(String employee_code) {
        this.employee_code = employee_code;
    }

    public Timestamp getEmployee_hire_date() {
        return employee_hire_date;
    }

    public void setEmployee_hire_date(Timestamp employee_hire_date) {
        this.employee_hire_date = employee_hire_date;
    }

    public Timestamp getEmployee_leave_date() {
        return employee_leave_date;
    }

    public void setEmployee_leave_date(Timestamp employee_leave_date) {
        this.employee_leave_date = employee_leave_date;
    }

    public String getIs_employee_validate() {
        return is_employee_validate;
    }

    public void setIs_employee_validate(String is_employee_validate) {
        this.is_employee_validate = is_employee_validate;
    }
}
