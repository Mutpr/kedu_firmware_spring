package com.kedu.firmware.DTO;

import java.util.Date;

public class UserProfileDTO {
    private Long userProfileSeq;        // 사용자 프로필 고유 번호
    private Long userSeq;               // 사용자 고유 번호
    private String phoneNumber;         // 전화번호
    private String address;             // 주소
    private String zipCode;             // 우편번호
    private String detailedAddress;     // 상세 주소
    private String profilePictureUrl;   // 프로필 사진 URL
    private String rank;                // 직책
    private String employeeId;          // 사번
    private Date joinDate;              // 입사일
    private String email;               // 이메일

    // 기본 생성자
    public UserProfileDTO() {
    }
    

    public UserProfileDTO(Long userProfileSeq, Long userSeq, String phoneNumber, String address, String zipCode,
			String detailedAddress, String profilePictureUrl, String rank, String employeeId, Date joinDate,
			String email) {
		super();
		this.userProfileSeq = userProfileSeq;
		this.userSeq = userSeq;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.zipCode = zipCode;
		this.detailedAddress = detailedAddress;
		this.profilePictureUrl = profilePictureUrl;
		this.rank = rank;
		this.employeeId = employeeId;
		this.joinDate = joinDate;
		this.email = email;
	}


	// 전체 필드에 대한 게터와 세터

    public Long getUserProfileSeq() {
        return userProfileSeq;
    }

    public void setUserProfileSeq(Long userProfileSeq) {
        this.userProfileSeq = userProfileSeq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Long userSeq) {
        this.userSeq = userSeq;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
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

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
