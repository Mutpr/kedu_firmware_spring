package com.kedu.firmware.DAO;

import com.kedu.firmware.DTO.UsersDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;

@Repository
public class UsersDAO {

    @Autowired
    private SqlSession mybatis;

    // 로깅 기능을 제공하는 Logger 객체 생성
    private static final Logger logger = LoggerFactory.getLogger(UsersDAO.class);

    // 사용자 삽입
    public void insert(UsersDTO dto) {
        if (dto.getUsers_is_admin() == null) {
            dto.setUsers_is_admin(0);  // 관리자 여부가 설정되지 않은 경우 기본값 0 (일반 사용자)로 설정
        }
        mybatis.insert("Users.insertUser", dto);
        int usersSeq = mybatis.selectOne("Users.getLastInsertedUserSeq");
        dto.setUsers_seq(usersSeq);  // 자동 증가된 SEQ를 DTO에 설정
        logger.info("Inserted user with users_seq: {}", usersSeq);
    }

    // 사용자 시퀀스 ID를 통해 사용자 조회
    public UsersDTO findUserBySeq(int users_seq) {
        return mybatis.selectOne("Users.findUserBySeq", users_seq);
    }

    // 사용자 코드를 통해 사용자 조회
    public UsersDTO findUserByCode(String users_code) {
        logger.info("findUserByCode 호출: users_code={}", users_code);
        UsersDTO user = mybatis.selectOne("Users.findUserByCode", users_code);
        if (user == null) {
            logger.warn("사용자를 찾을 수 없습니다: {}", users_code);
        } else {
            logger.info("사용자 찾기 성공: {}", user);
        }
        return user;
    }

    // 사용자 고유 번호를 통해 사용자 삭제
    public void deleteById(int users_seq) {
        mybatis.delete("Users.deleteUser", users_seq);
    }
    
    // 사용자 코드로 사용자 삭제
    // 사용자 코드를 통해 사용자를 삭제하는 메서드
    public void deleteByCode(String users_code) {
        logger.info("deleteByCode 호출: users_code={}", users_code);
        int affectedRows = mybatis.delete("Users.deleteUserByCode", users_code);
        logger.info("deleteByCode 완료: {} 행이 삭제됨", affectedRows);
    }

    // ID를 통해 사용자 조회
    public UsersDTO selectById(String id) {
        return mybatis.selectOne("Users.selectById", id);
    }

    // 특정 부서에 속한 직원 수를 계산
    public int countEmployeesInDepartment(String departmentCode) {
        return mybatis.selectOne("Users.countEmployeesInDepartment", departmentCode);
    }

    // 모든 사용자 정보를 조회하는 메서드 추가
    // 전체 사용자 정보를 조회하여 리스트로 반환
    public List<UsersDTO> getAllUsers() {
        return mybatis.selectList("Users.getAllUsers");
    }
    
    // 사용자 시퀀스로 사용자 이름 조회
    public String findUserNameBySeq(Long usersSeq) {
        logger.info("findUserNameBySeq 호출: usersSeq={}", usersSeq);
        String userName = mybatis.selectOne("Users.findUserNameBySeq", usersSeq);
        if (userName == null) {
            logger.warn("사용자 이름을 찾을 수 없습니다: usersSeq={}", usersSeq);
        } else {
            logger.info("사용자 이름 찾기 성공: usersSeq={}, userName={}", usersSeq, userName);
        }
        return userName;
    }
    
    // 유저코드로 본인의 부서 인원들의 정보를 조회
    // excludeLoginID는 조회에서 제외시킬 ID이다(본인)
    public List<Map<String, String>> findDepartmentInfoByUserCode(String departmentPrefix, String excludeLoginID) {
        return mybatis.selectList("Users.findDepartmentInfoByUserCode", Map.of("departmentPrefix", departmentPrefix, "excludeLoginID", excludeLoginID));
    }
    
    public UsersDTO selectUserByEmail(String Email) {
    	return mybatis.selectOne("Users.selectUserByEmail", Email);

    }
 
    // 부서 내 모든 구성원 정보를 조회하는 메서드 (프로필 포함)
    public List<Map<String, Object>> findDepartmentInfoWithProfile(String departmentPrefix, String excludeLoginID) {
        return mybatis.selectList("Users.findDepartmentInfoWithProfileByUserCode", 
            Map.of("departmentPrefix", departmentPrefix, "excludeLoginID", excludeLoginID));
    }
    
}
