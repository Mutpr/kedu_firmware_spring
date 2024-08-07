package com.kedu.firmware.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://192.168.1.11:3000",
                "http://192.168.1.10:3000",
                "http://192.168.1.36:3000",
                "http://192.168.1.172:3000",
                "http://192.168.1.43:3000",
                "http://localhost:3000"
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true); // allowCredentials(true)와 함께 '*'를 사용하면 오류가 발생할 수 있음
    }

}
