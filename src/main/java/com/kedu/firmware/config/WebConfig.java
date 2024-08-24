package com.kedu.firmware.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kedu.firmware.interceptors.LoggingInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/**");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 업로드된 파일을 서빙하기 위한 핸들러
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///absolute/path/to/upload/directory/");
        
        // C:/images/ 디렉토리의 파일을 서빙하기 위한 핸들러
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///C:/images/");
    }
}
