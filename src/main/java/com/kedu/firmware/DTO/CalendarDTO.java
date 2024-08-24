package com.kedu.firmware.DTO;

import java.sql.Timestamp;

public class CalendarDTO {
    private Long calendarsSeq;
    private Long usersSeq;
    private String calendarsCode;
    private String calendarsName;
    private String calendarsColor;
    private String calendarsIsInterest;
    private Timestamp calendarsCreatedAt;
    private Timestamp calendarsUpdatedAt;

    public CalendarDTO() {}

    public CalendarDTO(Long calendarsSeq, Long usersSeq, String calendarsCode, String calendarsName,
                       String calendarsColor, String calendarsIsInterest, Timestamp calendarsCreatedAt, 
                       Timestamp calendarsUpdatedAt) {
        this.calendarsSeq = calendarsSeq;
        this.usersSeq = usersSeq;
        this.calendarsCode = calendarsCode;
        this.calendarsName = calendarsName;
        this.calendarsColor = calendarsColor;
        this.calendarsIsInterest = calendarsIsInterest;
        this.calendarsCreatedAt = calendarsCreatedAt;
        this.calendarsUpdatedAt = calendarsUpdatedAt;
    }

    public Long getCalendarsSeq() {
        return calendarsSeq;
    }

    public void setCalendarsSeq(Long calendarsSeq) {
        this.calendarsSeq = calendarsSeq;
    }

    public Long getUsersSeq() {
        return usersSeq;
    }

    public void setUsersSeq(Long usersSeq) {
        this.usersSeq = usersSeq;
    }

    public String getCalendarsCode() {
        return calendarsCode;
    }

    public void setCalendarsCode(String calendarsCode) {
        this.calendarsCode = calendarsCode;
    }

    public String getCalendarsName() {
        return calendarsName;
    }

    public void setCalendarsName(String calendarsName) {
        this.calendarsName = calendarsName;
    }

    public String getCalendarsColor() {
        return calendarsColor;
    }

    public void setCalendarsColor(String calendarsColor) {
        this.calendarsColor = calendarsColor;
    }

    public String getCalendarsIsInterest() {
        return calendarsIsInterest;
    }

    public void setCalendarsIsInterest(String calendarsIsInterest) {
        this.calendarsIsInterest = calendarsIsInterest;
    }

    public Timestamp getCalendarsCreatedAt() {
        return calendarsCreatedAt;
    }

    public void setCalendarsCreatedAt(Timestamp calendarsCreatedAt) {
        this.calendarsCreatedAt = calendarsCreatedAt;
    }

    public Timestamp getCalendarsUpdatedAt() {
        return calendarsUpdatedAt;
    }

    public void setCalendarsUpdatedAt(Timestamp calendarsUpdatedAt) {
        this.calendarsUpdatedAt = calendarsUpdatedAt;
    }
}
