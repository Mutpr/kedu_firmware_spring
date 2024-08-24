package com.kedu.firmware.DTO;

import java.sql.Timestamp;

public class EventDTO {
    private Long eventsSeq;
    private Long calendarsSeq;
    private String eventsTitle;
    private String eventsDescription;
    private String eventsLocation;
    private Timestamp eventsStartDate;
    private Timestamp eventsEndDate;
    private String eventsIsDraggable;
    private String eventsColor;
    private Timestamp eventsCreatedAt;
    private Timestamp eventsUpdatedAt;

    // 기본 생성자
    public EventDTO() {}

    // 모든 필드를 포함하는 생성자
    public EventDTO(Long eventsSeq, Long calendarsSeq, String eventsTitle, String eventsDescription, 
                    String eventsLocation, Timestamp eventsStartDate, Timestamp eventsEndDate, 
                    String eventsIsDraggable, String eventsColor, Timestamp eventsCreatedAt, 
                    Timestamp eventsUpdatedAt) {
        this.eventsSeq = eventsSeq;
        this.calendarsSeq = calendarsSeq;
        this.eventsTitle = eventsTitle;
        this.eventsDescription = eventsDescription;
        this.eventsLocation = eventsLocation;
        this.eventsStartDate = eventsStartDate;
        this.eventsEndDate = eventsEndDate;
        this.eventsIsDraggable = eventsIsDraggable;
        this.eventsColor = eventsColor;
        this.eventsCreatedAt = eventsCreatedAt;
        this.eventsUpdatedAt = eventsUpdatedAt;
    }

    // Getters와 Setters
    public Long getEventsSeq() {
        return eventsSeq;
    }

    public void setEventsSeq(Long eventsSeq) {
        this.eventsSeq = eventsSeq;
    }

    public Long getCalendarsSeq() {
        return calendarsSeq;
    }

    public void setCalendarsSeq(Long calendarsSeq) {
        this.calendarsSeq = calendarsSeq;
    }

    public String getEventsTitle() {
        return eventsTitle;
    }

    public void setEventsTitle(String eventsTitle) {
        this.eventsTitle = eventsTitle;
    }

    public String getEventsDescription() {
        return eventsDescription;
    }

    public void setEventsDescription(String eventsDescription) {
        this.eventsDescription = eventsDescription;
    }

    public String getEventsLocation() {
        return eventsLocation;
    }

    public void setEventsLocation(String eventsLocation) {
        this.eventsLocation = eventsLocation;
    }

    public Timestamp getEventsStartDate() {
        return eventsStartDate;
    }

    public void setEventsStartDate(Timestamp eventsStartDate) {
        this.eventsStartDate = eventsStartDate;
    }

    public Timestamp getEventsEndDate() {
        return eventsEndDate;
    }

    public void setEventsEndDate(Timestamp eventsEndDate) {
        this.eventsEndDate = eventsEndDate;
    }

    public String getEventsIsDraggable() {
        return eventsIsDraggable;
    }

    public void setEventsIsDraggable(String eventsIsDraggable) {
        this.eventsIsDraggable = eventsIsDraggable;
    }

    public String getEventsColor() {
        return eventsColor;
    }

    public void setEventsColor(String eventsColor) {
        this.eventsColor = eventsColor;
    }

    public Timestamp getEventsCreatedAt() {
        return eventsCreatedAt;
    }

    public void setEventsCreatedAt(Timestamp eventsCreatedAt) {
        this.eventsCreatedAt = eventsCreatedAt;
    }

    public Timestamp getEventsUpdatedAt() {
        return eventsUpdatedAt;
    }

    public void setEventsUpdatedAt(Timestamp eventsUpdatedAt) {
        this.eventsUpdatedAt = eventsUpdatedAt;
    }
}

