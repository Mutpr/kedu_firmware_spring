package com.kedu.firmware.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    // 요청이 핸들러에 전달되기 전에 호출
    // 요청 URI, 메서드, 헤더 정보를 로그로 출력
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request Headers: ");
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> 
            System.out.println(headerName + ": " + request.getHeader(headerName))
        );
        return true; // 요청을 계속 처리하도록 허용
    }

    // 핸들러가 실행된 후 호출
    // 추가적인 작업이 필요할 경우 구현할 수 있음 (현재는 비어 있음)
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    // 요청 처리 완료 후 호출
    // 응답 상태 코드를 로그로 출력
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Response Status: " + response.getStatus());
    }
}
