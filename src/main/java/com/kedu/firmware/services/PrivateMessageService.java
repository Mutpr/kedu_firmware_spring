package com.kedu.firmware.services;

import com.kedu.firmware.DAO.PrivateMessageDAO;
import com.kedu.firmware.DTO.PrivateMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PrivateMessageService {

    @Autowired
    private PrivateMessageDAO privateMessageDAO;

    public void saveMessage(PrivateMessageDTO message) {
        if (message.getSend_date() == null) {
            message.setSend_date(new Timestamp(System.currentTimeMillis())); // 현재 시간으로 전송 날짜 설정
        }
        // 메세지를 저장하기 전에 모든 필드가 올바르게 설정되어 있는지 확인
        privateMessageDAO.saveMessage(message);
    }

    public List<PrivateMessageDTO> getAllMessages() {
        return privateMessageDAO.selectAll();
    }

    public PrivateMessageDTO getMessageById(int id) {
        return privateMessageDAO.selectById(id);
    }

    public List<PrivateMessageDTO> getMessagesByParticipants(String sender, String receiver) {
        return privateMessageDAO.selectByParticipants(sender, receiver);
    }
}
