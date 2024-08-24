package com.kedu.firmware.controllers;


import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.firmware.DTO.UserUpdateRequestesDTO;
import com.kedu.firmware.services.UserUpdateRequestesService;

@RestController
@RequestMapping("/user-update-request")
public class UserUpdateRequestesController {

	 @Autowired
	    private UserUpdateRequestesService userUpdateRequestesService;

	    // 특정 ID로 사용자 업데이트 요청을 가져오는 엔드포인트
	    @GetMapping("/{id}")
	    public UserUpdateRequestesDTO getUserUpdateRequestById(@PathVariable("id") Long usersUpdateRequestSeq) {
	        return userUpdateRequestesService.getUserUpdateRequestById(usersUpdateRequestSeq);
	    }

	    // 모든 사용자 업데이트 요청을 가져오는 엔드포인트
	    @GetMapping("/approval-list")
	    public List<UserUpdateRequestesDTO> getAllUserUpdateRequests() {
	        return userUpdateRequestesService.getAllUserUpdateRequests();
	    }

	    // 새로운 사용자 업데이트 요청을 생성하는 엔드포인트
	    @PostMapping
	    public ResponseEntity<String> createUserUpdateRequest(
	            @RequestBody UserUpdateRequestesDTO userUpdateRequest, 
	            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) {
	        if (userUpdateRequest.getUsersSeq() == null) {
	            throw new IllegalArgumentException("users_seq 값이 설정되지 않았습니다.");
	        }
	        try {
	            userUpdateRequestesService.createUserUpdateRequest(userUpdateRequest, profileImage);
	            return ResponseEntity.status(HttpStatus.CREATED).body("User update request created successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user update request.");
	        }
	    }

	    // 사용자 업데이트 요청을 수정하는 엔드포인트
	    @PutMapping
	    public void updateUserUpdateRequest(@RequestBody UserUpdateRequestesDTO userUpdateRequest) {
	        if (userUpdateRequest.getUsersSeq() == null) {
	            System.out.println("Update request received with null users_seq.");
	            throw new IllegalArgumentException("users_seq 값이 설정되지 않았습니다.");
	        }
	        userUpdateRequestesService.updateUserUpdateRequest(userUpdateRequest);
	    }

	    // 사용자 업데이트 요청을 삭제하는 엔드포인트
	    @DeleteMapping("/{id}")
	    public void deleteUserUpdateRequest(@PathVariable("id") Long usersUpdateRequestSeq) {
	        userUpdateRequestesService.deleteUserUpdateRequest(usersUpdateRequestSeq);
	    }

	    // 사용자 업데이트 요청을 승인하는 엔드포인트
	    @PostMapping("/approve/{id}")
	    public void approveUserUpdateRequest(@PathVariable("id") Long usersUpdateRequestSeq) {
	        userUpdateRequestesService.approveUserUpdateRequest(usersUpdateRequestSeq);
	    }

	    // 사용자 업데이트 요청을 거부하는 엔드포인트
	    @PostMapping("/reject/{id}")
	    public void rejectUserUpdateRequest(@PathVariable("id") Long usersUpdateRequestSeq) {
	        userUpdateRequestesService.rejectUserUpdateRequest(usersUpdateRequestSeq);
	    }

	    // 프로필 이미지를 업로드하는 엔드포인트
	    @PostMapping("/upload-profile-image")
	    public ResponseEntity<String> uploadProfileImage(@RequestParam("file") MultipartFile file) {
	        try {
	            String filePath = userUpdateRequestesService.saveProfileImage(file);
	            return ResponseEntity.ok(filePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed.");
	        }
	    }
}
