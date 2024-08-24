package com.kedu.firmware.DAO;

import com.kedu.firmware.DTO.PrivateMessageDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PrivateMessageDAO {

    @Autowired
    private SqlSession mybatis;

    // 메시지 저장
    public void saveMessage(PrivateMessageDTO message) {
        mybatis.insert("privateMessage.insert", message);
    }

    // 참여자 간 메시지 가져오기
    public List<PrivateMessageDTO> selectByParticipants(String sender, String receiver) {
        Map<String, Object> params = new HashMap<>();
        params.put("sender", sender);
        params.put("receiver", receiver);
        return mybatis.selectList("privateMessage.selectByParticipants", params);
    }

    // 읽지 않은 메시지 가져오기
    public List<PrivateMessageDTO> getUnreadMessagesByReceiver(String receiver) {
        return mybatis.selectList("privateMessage.selectUnreadByReceiver", receiver);
    }

    // 메시지 읽음 상태 업데이트
    public void updateMessagesReadStatus(String sender, String receiver, boolean read) {
        Map<String, Object> params = new HashMap<>();
        params.put("sender", sender);
        params.put("receiver", receiver);
        params.put("read", read ? 1 : 0);
        mybatis.update("privateMessage.updateMessagesReadStatus", params);
    }
}

