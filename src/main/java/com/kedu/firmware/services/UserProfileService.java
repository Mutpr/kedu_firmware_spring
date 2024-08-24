package com.kedu.firmware.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kedu.firmware.DAO.UserProfileDAO;
import com.kedu.firmware.DTO.UserProfileDTO;

@Service
public class UserProfileService {

	 @Autowired
	    private UserProfileDAO userProfileDAO;

	    // 기본값을 유지하며 사용자의 프로필을 가져오는 메서드
	    @Transactional(readOnly = true)
	    public UserProfileDTO getUserProfileByUserCode(String userCode) {
	        UserProfileDTO userProfile = userProfileDAO.getUserProfileByUserCode(userCode);
	        if (userProfile == null) {
	            // 프로필이 없으면 기본값 설정
	            userProfile = new UserProfileDTO();
	            userProfile.setUserSeq(userProfileDAO.getUserSeqByUserCode(userCode));
	            userProfile.setPhoneNumber("010-"); // 기본값
	            userProfile.setAddress("주소를 선택해주세요"); // 기본값
	            userProfile.setProfilePictureUrl("/images/image.png"); // 기본값
	            // 데이터베이스에 새 프로필 삽입
	            userProfileDAO.insertUserProfile(userProfile);
	            // 다시 한번 조회하여 완전한 객체를 반환
	            userProfile = userProfileDAO.getUserProfileByUserCode(userCode);
	        }
	        return userProfile;
	    }

	    // 사용자 프로필 ID로 프로필을 조회하는 메서드
	    @Transactional(readOnly = true)
	    public UserProfileDTO getUserProfileById(Long userProfileSeq) {
	        return userProfileDAO.getUserProfileById(userProfileSeq);
	    }

	    // 새로운 사용자 프로필을 생성하는 메서드
	    @Transactional
	    public void createUserProfile(UserProfileDTO userProfile) {
	        userProfileDAO.insertUserProfile(userProfile);
	    }

	    // 사용자 프로필을 업데이트하는 메서드
	    @Transactional
	    public void updateUserProfile(UserProfileDTO userProfile) {
	        userProfileDAO.updateUserProfile(userProfile);
	    }

	    // 사용자 프로필을 삭제하는 메서드
	    @Transactional
	    public void deleteUserProfile(Long userProfileSeq) {
	        userProfileDAO.deleteUserProfile(userProfileSeq);
	    }

	    // userUpdateRequest 데이터를 기반으로 프로필을 업데이트하는 메서드 추가
	    @Transactional
	    public void updateUserProfileFromRequest(UserProfileDTO userProfile) {
	        UserProfileDTO existingProfile = userProfileDAO.getUserProfileByUserSeq(userProfile.getUserSeq());
	        if (existingProfile != null) {
	            // 각 필드가 null이 아닌 경우에만 업데이트
	            if (userProfile.getPhoneNumber() != null) {
	                existingProfile.setPhoneNumber(userProfile.getPhoneNumber());
	            }
	            if (userProfile.getAddress() != null) {
	                existingProfile.setAddress(userProfile.getAddress());
	            }
	            if (userProfile.getZipCode() != null) {
	                existingProfile.setZipCode(userProfile.getZipCode());
	            }
	            if (userProfile.getDetailedAddress() != null) {
	                existingProfile.setDetailedAddress(userProfile.getDetailedAddress());
	            }
	            if (userProfile.getProfilePictureUrl() != null) {
	                existingProfile.setProfilePictureUrl(userProfile.getProfilePictureUrl());
	            }
	            if (userProfile.getRank() != null) {
	                existingProfile.setRank(userProfile.getRank());
	            }
	            if (userProfile.getEmployeeId() != null) {
	                existingProfile.setEmployeeId(userProfile.getEmployeeId());
	            }
	            if (userProfile.getJoinDate() != null) {
	                existingProfile.setJoinDate(userProfile.getJoinDate());
	            }

	            userProfileDAO.updateUserProfile(existingProfile);
	        } else {
	            throw new IllegalArgumentException("UserProfile not found for userSeq: " + userProfile.getUserSeq());
	        }
	    }
	    
	 // 사용자 시퀀스를 기반으로 프로필을 삭제하는 메서드 추가
	    @Transactional
	    public void deleteUserProfileByUserSeq(Long userSeq) {
	        UserProfileDTO userProfile = userProfileDAO.getUserProfileByUserSeq(userSeq);
	        if (userProfile != null) {
	            userProfileDAO.deleteUserProfile(userProfile.getUserProfileSeq());
	        } else {
	            throw new IllegalArgumentException("UserProfile not found for userSeq: " + userSeq);
	        }
	    }
}
