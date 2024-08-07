package com.kedu.firmware.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginValidator implements HandlerInterceptor {
    
    @Autowired
    private HttpSession session;

    // 요청이 핸들러에 전달되기 전에 호출
    // CORS 프리플라이트 요청 (OPTIONS 메서드) 처리
    // 사용자가 로그인된 상태인지 확인
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        // CORS 프리플라이트 요청 처리
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        
        System.out.println("인터셉터 동작 확인");
        String loginID = (String) session.getAttribute("loginID");
        if (loginID != null) {
            System.out.println("LoginId : " + loginID);
            return true; // 로그인된 사용자의 경우 요청을 계속 처리
        }
        System.out.println("NoLoginId");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 로그인되지 않은 경우 401 Unauthorized 상태 반환
        return false; // 요청 처리 중단

    }

    // 핸들러가 실행된 후 호출
    // 현재는 비어 있음, 필요시 구현 가능
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
    }
}
