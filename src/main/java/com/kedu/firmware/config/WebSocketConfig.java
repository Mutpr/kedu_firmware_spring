package com.kedu.firmware.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // 메세지를 브로드캐스트할 엔드포인트
        config.setApplicationDestinationPrefixes("/app"); // 클라이언트가 메세지를 전송할 경로
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
            .setAllowedOrigins(
                "http://192.168.1.11:3000",
                "http://192.168.1.10:3000",
                "http://192.168.1.36:3000",
                "http://192.168.1.172:3000",
                "http://192.168.1.43:3000",
                "http://localhost:3000"
            ) // 프론트엔드 출처와 동일한 출처로 설정
            .withSockJS(); // SockJS를 사용할 경우 추가 설정
    }
}
