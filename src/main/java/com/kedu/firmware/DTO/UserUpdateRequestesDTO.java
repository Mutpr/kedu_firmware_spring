package com.kedu.firmware.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;

public class UserUpdateRequestesDTO {

	private Long usersUpdateRequestSeq; // 사용자 업데이트 요청 시퀀스
    private Long usersSeq;              // 사용자 시퀀스
    private String phoneNumber;         // 전화번호
    private String email;               // 이메일
    private String address;             // 주소
    private String zipCode;             // 우편번호
    private String detailedAddress;     // 상세 주소
    private String requestReason;       // 요청 사유
    private String requestStatus;       // 요청 상태

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp requestTimestamp; // 요청 타임스탬프

    private String profileImage;        // 프로필 이미지 URL
    private String rank;                // 직급
    private String employeeId;          // 직원 ID
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp joinDate;         // 입사일
    
    private String approver;            // 승인자
    private String userName;            // 사용자 이름 (추가 필드)

    // 기본 생성자
    public UserUpdateRequestesDTO() {
        super();
    }

    // 매개변수가 있는 생성자
    public UserUpdateRequestesDTO(Long usersUpdateRequestSeq, Long usersSeq, String phoneNumber, String email,
                                String address, String zipCode, String detailedAddress, String requestReason, 
                                String requestStatus, Timestamp requestTimestamp, String profileImage, 
                                String rank, String employeeId, Timestamp joinDate, String approver) {
        super();
        this.usersUpdateRequestSeq = usersUpdateRequestSeq;
        this.usersSeq = usersSeq;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.zipCode = zipCode;
        this.detailedAddress = detailedAddress;
        this.requestReason = requestReason;
        this.requestStatus = requestStatus;
        this.requestTimestamp = requestTimestamp;
        this.profileImage = profileImage;
        this.rank = rank;
        this.employeeId = employeeId;
        this.joinDate = joinDate;
        this.approver = approver;
    }

    // Getter 및 Setter 메서드

    public Long getUsersUpdateRequestSeq() {
        return usersUpdateRequestSeq;
    }

    public void setUsersUpdateRequestSeq(Long usersUpdateRequestSeq) {
        this.usersUpdateRequestSeq = usersUpdateRequestSeq;
    }

    public Long getUsersSeq() {
        return usersSeq;
    }

    public void setUsersSeq(Long usersSeq) {
        this.usersSeq = usersSeq;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Timestamp getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Timestamp requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // UserProfileDTO로 변환하는 메서드
    public UserProfileDTO toUserProfileDTO() {
        UserProfileDTO userProfile = new UserProfileDTO();
        userProfile.setUserSeq(this.usersSeq);
        userProfile.setPhoneNumber(this.phoneNumber);
        userProfile.setEmail(this.email);
        userProfile.setAddress(this.address);
        userProfile.setZipCode(this.zipCode);
        userProfile.setDetailedAddress(this.detailedAddress);
        userProfile.setProfilePictureUrl(this.profileImage);
        userProfile.setRank(this.rank);
        userProfile.setEmployeeId(this.employeeId);
        userProfile.setJoinDate(this.joinDate);
        return userProfile;
    }
}
