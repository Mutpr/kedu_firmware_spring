package com.kedu.firmware.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kedu.firmware.DTO.MailDTO;
import com.kedu.firmware.DTO.UsersDTO;
import com.kedu.firmware.services.UsersService;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private HttpSession httpSession;

    // 사용자 정보 출력 (디버깅 목적)
    // 사용자 생성
    // 생성된 사용자 정보 반환
    @PostMapping
    public ResponseEntity<UsersDTO> post(@RequestBody UsersDTO usersdto) { 
        System.out.println(usersdto.getUsers_name() + " : " + usersdto.getUsers_password() + " : " + usersdto.getUsers_full_name());  
        usersService.post(usersdto);
        return ResponseEntity.ok(usersdto); 
    }

    // 사용자 삭제 엔드포인트
    // 사용자 삭제
    @DeleteMapping("/{userSeq}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userSeq) {
        // userSeq를 이용해 사용자 조회
        UsersDTO user = usersService.findUserBySeq(userSeq);
        // 사용자가 관리자일 경우 삭제 제한
        if (user != null && user.getUsers_is_admin() == 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 관리자 삭제 제한
        }
        // 관리자가 아닌 경우 사용자 삭제
        usersService.memberOut(String.valueOf(userSeq));  
        return ResponseEntity.ok().build();
    }

    // 마이페이지 조회 엔드포인트
    // 현재 세션에서 로그인 ID 가져오기
    // 로그인된 사용자 정보 조회
    // 사용자 정보 반환
    // 로그인되지 않은 경우 401 Unauthorized 응답 반환
    @GetMapping
    public ResponseEntity<UsersDTO> getMypage() {      
        String id = (String) httpSession.getAttribute("loginID");
        if (id != null) {          
            UsersDTO usersdto = usersService.getMemberById(id);         
            return ResponseEntity.ok(usersdto);
        }      
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // 모든 사용자 정보를 반환하는 엔드포인트 추가
    // 데이터베이스에 저장된 모든 사용자 정보를 조회하여 반환
    @GetMapping("/all")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<UsersDTO> users = usersService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{loginID}/departmentmemberinfo")
    public ResponseEntity<List<Map<String, String>>> getDepartmentMemberInfo(@PathVariable String loginID){
    	System.out.println(loginID);
    	List<Map<String, String>> Info = usersService.getDepartmentMemberInfoByLoginID(loginID);
    	
    	System.out.println("부서 정보 찾는데 성공했음");
        if (Info.isEmpty()) {
        	System.out.println("부서 사람이 없음");
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(Info);
    	
    }
    
    
    
    
}
