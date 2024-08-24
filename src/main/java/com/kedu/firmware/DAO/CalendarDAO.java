package com.kedu.firmware.DAO;

import com.kedu.firmware.DTO.CalendarDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CalendarDAO {

    @Autowired
    private SqlSession mybatis;

    public CalendarDTO getCalendarById(Long calendarsSeq) {
        return mybatis.selectOne("Calendar.getCalendarById", calendarsSeq);
    }

    public List<CalendarDTO> getAllCalendars() {
        return mybatis.selectList("Calendar.getAllCalendars");
    }

    public void insertCalendar(CalendarDTO calendar) {
        // 캘린더 코드가 없다면 UUID로 생성
        if (calendar.getCalendarsCode() == null) {
            calendar.setCalendarsCode(UUID.randomUUID().toString());
        }
        mybatis.insert("Calendar.insertCalendar", calendar);
    }

    public void updateCalendar(CalendarDTO calendar) {
        mybatis.update("Calendar.updateCalendar", calendar);
    }

    public void deleteCalendar(Long calendarsSeq) {
        // 1. 캘린더와 관련된 모든 이벤트를 삭제
        mybatis.delete("Calendar.deleteEventsByCalendarSeq", calendarsSeq);

        // 2. 캘린더 삭제
        mybatis.delete("Calendar.deleteCalendar", calendarsSeq);
    }

    public boolean exists(Long calendarsSeq) {
        Integer count = mybatis.selectOne("Calendar.exists", calendarsSeq);
        return count != null && count > 0;
    }
}

