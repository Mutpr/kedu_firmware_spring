package com.kedu.firmware.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kedu.firmware.DTO.UsersDTO;
import com.kedu.firmware.services.UsersService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private HttpSession session;

    // 사용자의 로그인 요청을 처리하는 메서드
    // 클라이언트로부터 UsersDTO 객체를 받아서 로그인 시도
    // usersService를 통해 사용자가 회원인지 확인
    // 회원이 아닐 경우 401 Unauthorized 응답을 반환
    // 회원일 경우 세션에 로그인 정보를 저장하고, 사용자 정보를 반환
    @PostMapping
    public ResponseEntity<UsersDTO> login(@RequestBody UsersDTO usersdto) {
        System.out.println("usercode:::"+usersdto.getUsers_code());
        boolean result = usersService.isMember(usersdto);
        if (!result) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }        
        
        session.setAttribute("loginID", usersdto.getUsers_code()); 
        UsersDTO user = usersService.findUserByCode(usersdto.getUsers_code());  
        
        if (user == null) {
            // 사용자가 null인 경우에 대한 추가 로그
            System.out.println("로그인 실패: 사용자를 찾을 수 없음");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        System.out.println("로그인 성공: " + user.getUsers_code());
        return ResponseEntity.ok(user);
    }

    // 현재 세션 무효화
    // 클라이언트에 성공 응답 전송
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();  
        return ResponseEntity.ok().build();  
    }
}
