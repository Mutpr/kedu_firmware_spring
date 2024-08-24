package com.kedu.firmware.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.firmware.DAO.UserProfileDAO;
import com.kedu.firmware.DAO.UserUpdateRequestesDAO;
import com.kedu.firmware.DTO.UserProfileDTO;
import com.kedu.firmware.DTO.UserUpdateRequestesDTO;

@Service
public class UserUpdateRequestesService {

    @Autowired
    private UserUpdateRequestesDAO userUpdateRequestesDAO;

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Autowired
    private UsersService usersService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${server.url}")
    private String serverUrl;

    // 특정 ID로 사용자 업데이트 요청을 가져오는 메서드
    public UserUpdateRequestesDTO getUserUpdateRequestById(Long userUpdateRequestSeq) {
        UserUpdateRequestesDTO request = userUpdateRequestesDAO.getUserUpdateRequestById(userUpdateRequestSeq);
        if (request != null) {
            String userName = usersService.getUserNameBySeq(request.getUsersSeq());
            request.setUserName(userName);  // 사용자 이름 설정
        }
        return request;
    }

    // 모든 사용자 업데이트 요청을 가져오는 메서드
    public List<UserUpdateRequestesDTO> getAllUserUpdateRequests() {
        List<UserUpdateRequestesDTO> requests = userUpdateRequestesDAO.getAllUserUpdateRequests();
        for (UserUpdateRequestesDTO request : requests) {
            String userName = usersService.getUserNameBySeq(request.getUsersSeq());
            request.setUserName(userName);  // 사용자 이름 설정
        }
        return requests;
    }

    // 사용자 업데이트 요청을 생성하는 메서드
    @Transactional
    public void createUserUpdateRequest(UserUpdateRequestesDTO userUpdateRequest, MultipartFile profileImage) throws IOException {
        if (profileImage != null && !profileImage.isEmpty()) {
            String profileImageUrl = saveProfileImage(profileImage);
            userUpdateRequest.setProfileImage(profileImageUrl);
        }

        UserUpdateRequestesDTO existingRequest = userUpdateRequestesDAO.getUserUpdateRequestByUserSeq(userUpdateRequest.getUsersSeq());
        if (existingRequest != null) {
            existingRequest.setPhoneNumber(userUpdateRequest.getPhoneNumber());
            existingRequest.setEmail(userUpdateRequest.getEmail());
            existingRequest.setAddress(userUpdateRequest.getAddress());
            existingRequest.setZipCode(userUpdateRequest.getZipCode());
            existingRequest.setDetailedAddress(userUpdateRequest.getDetailedAddress());
            existingRequest.setRequestReason(userUpdateRequest.getRequestReason());
            existingRequest.setRequestStatus(userUpdateRequest.getRequestStatus());
            existingRequest.setRequestTimestamp(userUpdateRequest.getRequestTimestamp());
            existingRequest.setProfileImage(userUpdateRequest.getProfileImage());
            existingRequest.setRank(userUpdateRequest.getRank());
            existingRequest.setEmployeeId(userUpdateRequest.getEmployeeId());
            existingRequest.setJoinDate(userUpdateRequest.getJoinDate());
            existingRequest.setApprover(userUpdateRequest.getApprover());

            userUpdateRequestesDAO.updateUserUpdateRequest(existingRequest);
        } else {
            userUpdateRequestesDAO.insertUserUpdateRequest(userUpdateRequest);
        }
    }

    // 사용자 업데이트 요청을 수정하는 메서드
    @Transactional
    public void updateUserUpdateRequest(UserUpdateRequestesDTO userUpdateRequest) {
        userUpdateRequestesDAO.updateUserUpdateRequest(userUpdateRequest);
    }

    // 사용자 업데이트 요청을 삭제하는 메서드
    @Transactional
    public void deleteUserUpdateRequest(Long userUpdateRequestSeq) {
        userUpdateRequestesDAO.deleteUserUpdateRequest(userUpdateRequestSeq);
    }

    // 사용자 업데이트 요청을 승인하는 메서드
    @Transactional
    public void approveUserUpdateRequest(Long userUpdateRequestSeq) {
        UserUpdateRequestesDTO request = userUpdateRequestesDAO.getUserUpdateRequestById(userUpdateRequestSeq);
        if (request != null) {
            request.setRequestStatus("승인됨");

            UserProfileDTO userProfile = request.toUserProfileDTO();
            UserProfileDTO existingProfile = userProfileDAO.getUserProfileByUserSeq(request.getUsersSeq());
            if (existingProfile != null) {
                userProfile.setUserProfileSeq(existingProfile.getUserProfileSeq());
                userProfileDAO.updateUserProfile(userProfile);
            } else {
                userProfileDAO.insertUserProfile(userProfile);
            }

            userUpdateRequestesDAO.updateUserUpdateRequest(request);
        } else {
            throw new IllegalArgumentException("Invalid request ID: " + userUpdateRequestSeq);
        }
    }

    // 사용자 업데이트 요청을 거부하는 메서드
    @Transactional
    public void rejectUserUpdateRequest(Long userUpdateRequestSeq) {
        UserUpdateRequestesDTO request = userUpdateRequestesDAO.getUserUpdateRequestById(userUpdateRequestSeq);
        if (request != null) {
            request.setRequestStatus("거부됨");
            userUpdateRequestesDAO.updateUserUpdateRequest(request);

            // 이전 승인된 프로필로 롤백
            UserProfileDTO approvedProfile = userProfileDAO.getUserProfileByUserSeq(request.getUsersSeq());
            if (approvedProfile != null) {
                request.setPhoneNumber(approvedProfile.getPhoneNumber());
                request.setEmail(approvedProfile.getEmail() != null ? approvedProfile.getEmail() : request.getEmail());
                request.setAddress(approvedProfile.getAddress());
                request.setZipCode(approvedProfile.getZipCode());
                request.setDetailedAddress(approvedProfile.getDetailedAddress());
                request.setProfileImage(approvedProfile.getProfilePictureUrl());
                request.setRank(approvedProfile.getRank());
                request.setEmployeeId(approvedProfile.getEmployeeId());

                if (approvedProfile.getJoinDate() != null) {
                    request.setJoinDate(new Timestamp(approvedProfile.getJoinDate().getTime()));
                } else {
                    request.setJoinDate(null);
                }

                userUpdateRequestesDAO.updateUserUpdateRequest(request);
            }
        } else {
            throw new IllegalArgumentException("Invalid request ID: " + userUpdateRequestSeq);
        }
    }

    // 프로필 이미지를 저장하고 웹 경로를 반환하는 메서드
    public String saveProfileImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + File.separator + fileName);

        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        return serverUrl + "/uploads/" + fileName;
    }
}
