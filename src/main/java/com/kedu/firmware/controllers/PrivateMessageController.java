package com.kedu.firmware.controllers;

import com.kedu.firmware.DTO.PrivateMessageDTO;
import com.kedu.firmware.services.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/messages")
public class PrivateMessageController {

    private static final Logger logger = Logger.getLogger(PrivateMessageController.class.getName());

    @Autowired
    private PrivateMessageService privateMessageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody PrivateMessageDTO message) {
        try {
            privateMessageService.saveMessage(message);
            messagingTemplate.convertAndSend("/topic/public", message);
            return ResponseEntity.ok("메시지가 성공적으로 저장되고 브로드캐스트되었습니다.");
        } catch (Exception e) {
            logger.severe("메시지 저장 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("메시지 저장 오류: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PrivateMessageDTO>> getAllMessages() {
        List<PrivateMessageDTO> messages = privateMessageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrivateMessageDTO> getMessageById(@PathVariable int id) {
        PrivateMessageDTO message = privateMessageService.getMessageById(id);
        if (message != null) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/chat/{sender}/{receiver}")
    public ResponseEntity<List<PrivateMessageDTO>> getMessagesBetweenParticipants(
            @PathVariable String sender,
            @PathVariable String receiver) {

        List<PrivateMessageDTO> messages = privateMessageService.getMessagesByParticipants(sender, receiver);
        return ResponseEntity.ok(messages);
    }
}
