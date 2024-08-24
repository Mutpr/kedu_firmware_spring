package com.kedu.firmware.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kedu.firmware.DTO.PrivateMessageDTO;
import com.kedu.firmware.services.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private PrivateMessageService privateMessageService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // 세션을 저장하는 Set
    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // 메시지를 파싱하여 DTO 객체로 변환
        PrivateMessageDTO privateMessageDTO = parseMessage(payload);

        if (privateMessageDTO != null) {
            // 데이터베이스에 메시지를 저장
            privateMessageService.saveMessage(privateMessageDTO);

            // 모든 연결된 클라이언트로 메시지 전송 (Broadcasting)
            for (WebSocketSession webSocketSession : sessions) {
                if (webSocketSession.isOpen()) {
                    String jsonResponse = objectMapper.writeValueAsString(privateMessageDTO);
                    webSocketSession.sendMessage(new TextMessage(jsonResponse));
                }
            }
        } else {
            System.out.println("Failed to parse message.");
        }
    }

    private PrivateMessageDTO parseMessage(String payload) {
        try {
            return objectMapper.readValue(payload, PrivateMessageDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("WebSocket connection established with session id: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("WebSocket connection closed with session id: " + session.getId());
    }
}
