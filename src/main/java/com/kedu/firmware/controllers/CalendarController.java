package com.kedu.firmware.controllers;

import com.kedu.firmware.DTO.CalendarDTO;
import com.kedu.firmware.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendars")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/{id}")
    public CalendarDTO getCalendarById(@PathVariable Long id) {
        return calendarService.getCalendarById(id);
    }

    @GetMapping
    public List<CalendarDTO> getAllCalendars() {
        return calendarService.getAllCalendars();
    }

    @PostMapping
    public Long createCalendar(@RequestBody CalendarDTO calendar) {
        calendarService.createCalendar(calendar);
        return calendar.getCalendarsSeq();  // 생성된 캘린더의 시퀀스 반환
    }

    @PutMapping("/{id}")
    public void updateCalendar(@PathVariable Long id, @RequestBody CalendarDTO calendar) {
        calendar.setCalendarsSeq(id);
        calendarService.updateCalendar(calendar);
    }

    @DeleteMapping("/{id}")
    public void deleteCalendar(@PathVariable Long id) {
        calendarService.deleteCalendar(id);
    }
}

