package com.kedu.firmware.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kedu.firmware.interceptors.LoginValidator;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private LoginValidator loginValidator;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginValidator).
		addPathPatterns("/**").
		excludePathPatterns("/auth");
	}
}
