package com.kedu.firmware.controllers;

import com.kedu.firmware.DTO.PrivateMessageDTO;
import com.kedu.firmware.services.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/messages")
public class PrivateMessageController {

    @Autowired
    private PrivateMessageService privateMessageService;

    // 메시지 전송 API
    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody PrivateMessageDTO message) {
        if (message.getContent() == null || message.getContent().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("빈 메시지는 전송할 수 없습니다.");
        }

        try {
            privateMessageService.saveMessage(message);
            return ResponseEntity.ok("메시지가 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메시지 저장 오류: " + e.getMessage());
        }
    }

    // 채팅 내역 불러오기 API
    @GetMapping("/chat/{sender}/{receiver}")
    public ResponseEntity<List<PrivateMessageDTO>> getMessagesBetweenParticipants(
            @PathVariable String sender,
            @PathVariable String receiver) {
        try {
            List<PrivateMessageDTO> messages = privateMessageService.getMessagesByParticipants(sender, receiver);
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 메시지 읽음 상태 업데이트 API
    @PostMapping("/read/{sender}/{receiver}")
    public ResponseEntity<String> markMessagesAsRead(
            @PathVariable String sender,
            @PathVariable String receiver) {
        try {
            privateMessageService.updateMessagesReadStatus(sender, receiver, true);
            return ResponseEntity.ok("메시지 읽음 상태가 업데이트되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메시지 읽음 상태 업데이트 오류: " + e.getMessage());
        }
    }

    // 읽지 않은 메시지 수를 반환하는 API
    @GetMapping("/unread/{username}")
    public ResponseEntity<Map<String, Integer>> getUnreadMessageCount(@PathVariable String username) {
        try {
            Map<String, Integer> unreadCounts = privateMessageService.getUnreadMessagesCount(username);
            return ResponseEntity.ok(unreadCounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
