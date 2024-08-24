package com.kedu.firmware.DTO;


public class AnnualVacationManagementDTO {
    private int annual_vacation_manage_seq;
    private int user_seq;
    private int total_annual_vacation_days;
    private int used_annual_vacation_days;
    private int remain_annual_vacation_days;

    public AnnualVacationManagementDTO() {

    }

    public AnnualVacationManagementDTO(int annual_vacation_manage_seq, int user_seq, int total_annual_vacation_days,
                                       int used_annual_vacation_days, int remain_annual_vacation_days) {
        super();
        this.annual_vacation_manage_seq = annual_vacation_manage_seq;
        this.user_seq = user_seq;
        this.total_annual_vacation_days = total_annual_vacation_days;
        this.used_annual_vacation_days = used_annual_vacation_days;
        this.remain_annual_vacation_days = remain_annual_vacation_days;
    }

    public int getAnnual_vacation_manage_seq() {
        return annual_vacation_manage_seq;
    }
    public void setAnnual_vacation_manage_seq(int annual_vacation_manage_seq) {
        this.annual_vacation_manage_seq = annual_vacation_manage_seq;
    }
    public int getUser_seq() {
        return user_seq;
    }
    public void setUser_seq(int user_seq) {
        this.user_seq = user_seq;
    }
    public int getTotal_annual_vacation_days() {
        return total_annual_vacation_days;
    }
    public void setTotal_annual_vacation_days(int total_annual_vacation_days) {
        this.total_annual_vacation_days = total_annual_vacation_days;
    }
    public int getUsed_annual_vacation_days() {
        return used_annual_vacation_days;
    }
    public void setUsed_annual_vacation_days(int used_annual_vacation_days) {
        this.used_annual_vacation_days = used_annual_vacation_days;
    }
    public int getRemain_annual_vacation_days() {
        return remain_annual_vacation_days;
    }
    public void setRemain_annual_vacation_days(int remain_annual_vacation_days) {
        this.remain_annual_vacation_days = remain_annual_vacation_days;
    }
}
