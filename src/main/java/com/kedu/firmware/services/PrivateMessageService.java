package com.kedu.firmware.services;

import com.kedu.firmware.DAO.PrivateMessageDAO;
import com.kedu.firmware.DTO.PrivateMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PrivateMessageService {

    @Autowired
    private PrivateMessageDAO privateMessageDAO;

    // 메시지 저장
    public void saveMessage(PrivateMessageDTO message) {
        if (message.getSend_date() == null) {
            message.setSend_date(new Timestamp(System.currentTimeMillis()));
        }
        if (message.getRead() == null) {
            message.setRead(false);
        }
        privateMessageDAO.saveMessage(message);
    }

    // 참여자 간 메시지 가져오기
    public List<PrivateMessageDTO> getMessagesByParticipants(String sender, String receiver) {
        return privateMessageDAO.selectByParticipants(sender, receiver);
    }

    // 메시지 읽음 상태 업데이트
    public void updateMessagesReadStatus(String sender, String receiver, boolean read) {
        privateMessageDAO.updateMessagesReadStatus(sender, receiver, read);
    }

    // 사용자별 읽지 않은 메시지 수 가져오기
    public Map<String, Integer> getUnreadMessagesCount(String username) {
        List<PrivateMessageDTO> unreadMessages = privateMessageDAO.getUnreadMessagesByReceiver(username);
        Map<String, Integer> unreadCounts = new HashMap<>();

        for (PrivateMessageDTO message : unreadMessages) {
            String sender = message.getSender_username();
            unreadCounts.put(sender, unreadCounts.getOrDefault(sender, 0) + 1);
        }
        return unreadCounts;
    }
}

