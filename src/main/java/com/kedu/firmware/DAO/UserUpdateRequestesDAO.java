package com.kedu.firmware.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kedu.firmware.DTO.UserUpdateRequestesDTO;

@Repository
public class UserUpdateRequestesDAO {

    @Autowired
    private SqlSession mybatis;

    // 특정 ID로 사용자 업데이트 요청을 가져오는 메서드
    public UserUpdateRequestesDTO getUserUpdateRequestById(Long userUpdateRequestSeq) {
        return mybatis.selectOne("UserUpdateRequest.getUserUpdateRequestById", userUpdateRequestSeq);
    }

    // 특정 사용자 시퀀스로 사용자 업데이트 요청을 가져오는 메서드
    public UserUpdateRequestesDTO getUserUpdateRequestByUserSeq(Long usersSeq) {
        return mybatis.selectOne("UserUpdateRequest.getUserUpdateRequestByUserSeq", usersSeq);
    }

    // 모든 사용자 업데이트 요청을 가져오는 메서드
    public List<UserUpdateRequestesDTO> getAllUserUpdateRequests() {
        return mybatis.selectList("UserUpdateRequest.getAllUserUpdateRequests");
    }

    // 새로운 사용자 업데이트 요청을 삽입하는 메서드
    public void insertUserUpdateRequest(UserUpdateRequestesDTO userUpdateRequest) {
        mybatis.insert("UserUpdateRequest.insertUserUpdateRequest", userUpdateRequest);
    }

    // 기존 사용자 업데이트 요청을 수정하는 메서드
    public void updateUserUpdateRequest(UserUpdateRequestesDTO userUpdateRequest) {
        mybatis.update("UserUpdateRequest.updateUserUpdateRequest", userUpdateRequest);
    }

    // 특정 사용자 업데이트 요청을 삭제하는 메서드
    public void deleteUserUpdateRequest(Long userUpdateRequestSeq) {
        mybatis.delete("UserUpdateRequest.deleteUserUpdateRequest", userUpdateRequestSeq);
    }

    // 특정 사용자 시퀀스를 기반으로 모든 업데이트 요청을 삭제하는 메서드
    public void deleteUserUpdateRequestsByUserSeq(Long usersSeq) {
        mybatis.delete("UserUpdateRequest.deleteUserUpdateRequestsByUserSeq", usersSeq);
    }
}
