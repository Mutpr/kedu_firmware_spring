package com.kedu.firmware.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kedu.firmware.DAO.CalendarDAO;
import com.kedu.firmware.DAO.EventDAO;
import com.kedu.firmware.DTO.EventDTO;

@Service
public class EventService {

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private CalendarDAO calendarDAO;

    // 특정 이벤트 ID로 이벤트를 조회하는 서비스 메소드
    public EventDTO getEventById(Long eventsSeq) {
        return eventDAO.getEventById(eventsSeq);
    }

    // 특정 캘린더 ID로 이벤트 목록을 조회하는 서비스 메소드
    public List<EventDTO> getEventsByCalendarId(Long calendarsSeq) {
        return eventDAO.getEventsByCalendarId(calendarsSeq);
    }

    // 새로운 이벤트를 생성하는 서비스 메소드
    public void createEvent(EventDTO event) {
        if (event.getEventsTitle() == null || event.getEventsTitle().isEmpty()) {
            throw new IllegalArgumentException("이벤트 제목은 필수입니다.");
        }
        
        if (event.getCalendarsSeq() == null) {
            throw new IllegalArgumentException("캘린더 ID는 필수입니다.");
        }
        
        if (event.getEventsStartDate() == null || event.getEventsEndDate() == null) {
            throw new IllegalArgumentException("시작 날짜와 종료 날짜는 필수입니다.");
        }
        
        // 캘린더가 존재하는지 확인
        if (!calendarDAO.exists(event.getCalendarsSeq())) {
            throw new IllegalArgumentException("해당 캘린더가 존재하지 않습니다. calendarsSeq: " + event.getCalendarsSeq());
        }

        eventDAO.insertEvent(event);
    }

    // 기존 이벤트를 업데이트하는 서비스 메소드
    public void updateEvent(EventDTO event) {
        if (event.getEventsTitle() == null || event.getEventsTitle().isEmpty()) {
            throw new IllegalArgumentException("이벤트 제목은 필수입니다.");
        }

        if (event.getCalendarsSeq() == null) {
            throw new IllegalArgumentException("캘린더 ID는 필수입니다.");
        }

        if (event.getEventsStartDate() == null || event.getEventsEndDate() == null) {
            throw new IllegalArgumentException("시작 날짜와 종료 날짜는 필수입니다.");
        }

        // 캘린더가 존재하는지 확인
        if (!calendarDAO.exists(event.getCalendarsSeq())) {
            throw new IllegalArgumentException("해당 캘린더가 존재하지 않습니다.");
        }

        eventDAO.updateEvent(event);
    }

    // 특정 이벤트 ID로 이벤트를 삭제하는 서비스 메소드
    public void deleteEvent(Long eventsSeq) {
        eventDAO.deleteEvent(eventsSeq);
    }
    
    // 모든 이벤트를 조회하는 서비스 메소드
    public List<EventDTO> getAllEvents() {
        return eventDAO.getAllEvents();
    }
}
