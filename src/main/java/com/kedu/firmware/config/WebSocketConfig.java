package com.kedu.firmware.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.kedu.firmware.handlers.ChatWebSocketHandler;
import com.kedu.firmware.interceptors.HttpSessionInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getChatHandler(), "/ws/chat")
                .setAllowedOrigins(
                    "http://192.168.1.11:3000",
                    "http://192.168.1.10:3000",
                    "http://192.168.1.36:3000",
                    "http://192.168.1.172:3000",
                    "http://192.168.1.43:3000",
                    "http://172.30.1.29:3000",
                    "http://localhost:3000"
                )
                .addInterceptors(new HttpSessionInterceptor());
    }

    @Bean
    public ChatWebSocketHandler getChatHandler() {
        return new ChatWebSocketHandler();
    }
}
