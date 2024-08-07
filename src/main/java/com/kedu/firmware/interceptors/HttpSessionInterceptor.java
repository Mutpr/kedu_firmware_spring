package com.kedu.firmware.interceptors;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import jakarta.servlet.http.HttpSession;

public class HttpSessionInterceptor implements HandshakeInterceptor {

    // 핸드쉐이크 전 호출되는 메서드
    // WebSocket 연결 전에 HTTP 세션을 WebSocket 핸들러에 전달
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {
        ServletServerHttpRequest sReq = (ServletServerHttpRequest) request;
        HttpSession session = sReq.getServletRequest().getSession();
        attributes.put("hSession", session); // 세션 정보를 WebSocket 핸들러의 속성에 추가
        return true; // 핸드쉐이크 진행 허용
    }
    
    // 핸드쉐이크 후 호출되는 메서드
    // 핸드쉐이크 후 추가적인 작업은 없음
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Exception exception) {
        // 핸드쉐이크 후 작업이 필요 없는 경우 비워둠
    }
}
