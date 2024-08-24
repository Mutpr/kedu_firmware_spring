package com.kedu.firmware.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.kedu.firmware.DTO.UserProfileDTO;
import com.kedu.firmware.services.UserProfileService;

@RestController
@RequestMapping("/user-profile")
public class UserProfileController {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<UserProfileDTO> getUserProfile(@RequestParam String userCode) {
        try {
            UserProfileDTO userProfile = userProfileService.getUserProfileByUserCode(userCode);
            if (userProfile != null) {
                return ResponseEntity.ok(userProfile);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error getting user profile for user code: {}", userCode, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getUserProfileById(@PathVariable("id") Long userProfileSeq) {
        try {
            UserProfileDTO userProfile = userProfileService.getUserProfileById(userProfileSeq);
            if (userProfile != null) {
                return ResponseEntity.ok(userProfile);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error getting user profile for id: {}", userProfileSeq, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Void> createUserProfile(@RequestBody UserProfileDTO userProfile) {
        try {
            if (userProfile.getUserSeq() != null && userProfile.getEmail() != null) {
                userProfileService.createUserProfile(userProfile);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            logger.error("Error creating user profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateUserProfile(@RequestBody UserProfileDTO userProfile) {
        try {
            if (userProfile.getUserProfileSeq() != null) {
                userProfileService.updateUserProfile(userProfile);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            logger.error("Error updating user profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable("id") Long userProfileSeq) {
        try {
            userProfileService.deleteUserProfile(userProfileSeq);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting user profile", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // 사용자 시퀀스를 기반으로 프로필을 삭제하는 엔드포인트 추가
    @DeleteMapping("/userSeq/{userSeq}")
    public ResponseEntity<Void> deleteUserProfileByUserSeq(@PathVariable("userSeq") Long userSeq) {
        try {
            userProfileService.deleteUserProfileByUserSeq(userSeq);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting user profile for userSeq: {}", userSeq, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

