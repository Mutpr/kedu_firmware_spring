package com.kedu.firmware.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kedu.firmware.DAO.CalendarDAO;
import com.kedu.firmware.DTO.CalendarDTO;

@Service
public class CalendarService {

    @Autowired
    private CalendarDAO calendarDAO;

    @Transactional
    public CalendarDTO getCalendarById(Long calendarsSeq) {
        return calendarDAO.getCalendarById(calendarsSeq);
    }

    @Transactional
    public List<CalendarDTO> getAllCalendars() {
        return calendarDAO.getAllCalendars();
    }

    @Transactional
    public void createCalendar(CalendarDTO calendar) {
        if (calendar.getCalendarsName() == null || calendar.getCalendarsName().isEmpty()) {
            calendar.setCalendarsName("Unnamed Calendar");
        }
        if (calendar.getCalendarsColor() == null || calendar.getCalendarsColor().isEmpty()) {
            calendar.setCalendarsColor("#000000");
        }
        if (calendar.getCalendarsIsInterest() == null) {
            calendar.setCalendarsIsInterest("N");
        }

        if (calendar.getCalendarsCode() == null) {
            calendar.setCalendarsCode(UUID.randomUUID().toString());
        }

        if (calendar.getUsersSeq() == null) {
            throw new IllegalArgumentException("UsersSeq는 필수입니다.");
        }

        calendarDAO.insertCalendar(calendar);
    }

    @Transactional
    public void updateCalendar(CalendarDTO calendar) {
        calendarDAO.updateCalendar(calendar);
    }

    @Transactional
    public void deleteCalendar(Long calendarsSeq) {
        calendarDAO.deleteCalendar(calendarsSeq);
    }
}

