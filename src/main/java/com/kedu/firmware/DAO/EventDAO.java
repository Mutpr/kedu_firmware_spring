package com.kedu.firmware.DAO;

import com.kedu.firmware.DTO.EventDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventDAO {

    @Autowired
    private SqlSession mybatis;

    // 특정 이벤트 ID로 이벤트를 조회하는 DAO 메소드
    public EventDTO getEventById(Long eventsSeq) {
        return mybatis.selectOne("Event.getEventById", eventsSeq);
    }

    // 특정 캘린더 ID로 이벤트 목록을 조회하는 DAO 메소드
    public List<EventDTO> getEventsByCalendarId(Long calendarsSeq) {
        return mybatis.selectList("Event.getEventsByCalendarId", calendarsSeq);
    }

    // 새로운 이벤트를 삽입하는 DAO 메소드
    public void insertEvent(EventDTO event) {
        try {
            mybatis.insert("Event.insertEvent", event);
        } catch (Exception e) {
            throw new RuntimeException("이벤트 삽입 중 오류 발생: " + e.getMessage(), e);
        }
    }

    // 기존 이벤트를 업데이트하는 DAO 메소드
    public void updateEvent(EventDTO event) {
        try {
            mybatis.update("Event.updateEvent", event);
        } catch (Exception e) {
            throw new RuntimeException("이벤트 업데이트 중 오류 발생: " + e.getMessage(), e);
        }
    }

    // 특정 이벤트 ID로 이벤트를 삭제하는 DAO 메소드
    public void deleteEvent(Long eventsSeq) {
        try {
            mybatis.delete("Event.deleteEvent", eventsSeq);
        } catch (Exception e) {
            throw new RuntimeException("이벤트 삭제 중 오류 발생: " + e.getMessage(), e);
        }
    }
    
    // 모든 이벤트를 조회하는 DAO 메소드
    public List<EventDTO> getAllEvents() {
        return mybatis.selectList("Event.getAllEvents");
    }
    
}

