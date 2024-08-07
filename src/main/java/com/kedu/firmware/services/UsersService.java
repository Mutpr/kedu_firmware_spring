package com.kedu.firmware.services;


import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kedu.firmware.DAO.DepartmentDAO;
import com.kedu.firmware.DAO.UnitDAO;
import com.kedu.firmware.DAO.UsersDAO;
import com.kedu.firmware.DTO.DepartmentDTO;
import com.kedu.firmware.DTO.MailDTO;
import com.kedu.firmware.DTO.UnitDTO;
import com.kedu.firmware.DTO.UsersDTO;

@Service
public class UsersService {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private UnitDAO unitDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    // 사용자 등록 및 데이터베이스에 삽입
    // 원자성 유지 하기 위함
    @Transactional
    public void post(UsersDTO dto) {
        // 프론트엔드에서 전송된 사용자 정보 로깅
        logger.info("DB 삽입 전: users_code={}, department_code={}, unit_code={}", 
                    dto.getUsers_code(), dto.getDepartment_code(), dto.getUnit_code());

        // 사용자 데이터를 DB에 삽입
        usersDAO.insert(dto);

        // 데이터베이스에서 삽입된 사용자 조회
        UsersDTO insertedUser = usersDAO.findUserByCode(dto.getUsers_code());
        logger.info("DB 삽입 후: users_code={}, department_code={}, unit_code={}", 
                    insertedUser.getUsers_code(), insertedUser.getDepartment_code(), insertedUser.getUnit_code());

        // 생성된 사용자 시퀀스 로깅
        Integer generatedUserSeq = dto.getUsers_seq();
        logger.info("생성된 user_seq: {}", generatedUserSeq);
    }

    // 사용자 시퀀스 ID를 이용해 사용자를 조회하는 메서드
    public UsersDTO findUserBySeq(int userSeq) {
        return usersDAO.findUserBySeq(userSeq);  // UsersDAO에서 실제 쿼리를 수행하여 결과 반환
    }

    // 부서 코드에 따른 유닛 코드 조회
    @Transactional
    private String getUnitCode(String departmentCode) {
        if (departmentCode == null || departmentCode.isEmpty()) {
            logger.error("부서 코드가 유효하지 않습니다: {}", departmentCode);
            throw new RuntimeException("유효한 부서 코드가 필요합니다.");
        }

        try {
            List<UnitDTO> units = unitDAO.selectUnitsByDepartmentCode(departmentCode);
            if (units.isEmpty()) {
                throw new RuntimeException("해당 부서 코드에 대한 유닛을 찾을 수 없습니다.");
            }
            String unitCode = units.get(0).getUnit_code(); // 첫 번째 유닛 사용
            logger.info("가져온 unitCode: {}", unitCode);
            return unitCode;
        } catch (Exception e) {
            logger.error("유닛 코드를 가져오는 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("유닛 코드를 가져오는 중 오류가 발생했습니다.", e);
        }
    }

    // 유닛 코드에 따른 부서 정보 조회
    @Transactional
    private DepartmentDTO getDepartmentByUnitCode(String unitCode) {
        if (unitCode == null || unitCode.isEmpty()) {
            logger.error("유닛 코드가 유효하지 않습니다: {}", unitCode);
            throw new RuntimeException("유효한 유닛 코드가 필요합니다.");
        }

        try {
            List<DepartmentDTO> departments = departmentDAO.getDepartmentsByUnitCode(unitCode);
            if (departments.isEmpty()) {
                logger.error("해당 유닛 코드에 대한 부서를 찾을 수 없습니다: {}", unitCode);
                throw new RuntimeException("해당 유닛 코드에 대한 부서를 찾을 수 없습니다.");
            }
            DepartmentDTO department = departments.get(0); // 첫 번째 부서 사용
            logger.info("가져온 department: {}", department);
            return department;
        } catch (Exception e) {
            logger.error("부서 코드를 가져오는 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("부서 코드를 가져오는 중 오류가 발생했습니다.", e);
        }
    }

    // 사용자 로그인 검증
    @Transactional
    public boolean isMember(UsersDTO dto) {
        logger.info("로그인 시도: users_code={}, users_password={}", dto.getUsers_code(), dto.getUsers_password());
        UsersDTO storedUser = usersDAO.findUserByCode(dto.getUsers_code());
        if (storedUser != null) {
            boolean result = dto.getUsers_password().equals(storedUser.getUsers_password());
            logger.info("사용자 찾음: {}", storedUser);
            logger.info("입력된 비밀번호: {}, 저장된 비밀번호: {}", dto.getUsers_password(), storedUser.getUsers_password());
            logger.info("isMember 결과: {}", result);
            return result;
        }
        logger.info("isMember 결과: false");
        return false;
    }

    // 사용자 코드로 사용자 정보 조회
    public UsersDTO findUserByCode(String users_code) {
        return usersDAO.findUserByCode(users_code);
    }

    // 사용자 ID로 사용자 삭제
    public void memberOut(String id) {
        usersDAO.deleteById(Integer.parseInt(id));
    }

    // 사용자 ID로 사용자 정보 조회
    public UsersDTO getMemberById(String id) {
        return usersDAO.selectById(id);
    }

    // 모든 사용자를 조회하는 메서드 추가
    // 데이터베이스의 모든 사용자를 조회하여 반환
    public List<UsersDTO> getAllUsers() {
        return usersDAO.getAllUsers();
    }
    
    // 로그인ID(유저 코드)로 부서내 인원들의 정보(이메일, 이름)를 가져옴
    // 본인의 유저코드를 잘라서 사용해 유사 부서에 있는 인원들의 정보를 찾아오고 본인의 정보는 제외함
    public List<Map<String, String>> getDepartmentMemberInfoByLoginID(String loginID){

    	
    	String departmentPrefix = loginID.split("-")[0];
    	
    	
        return usersDAO.findDepartmentInfoByUserCode(departmentPrefix,loginID);
    }
    
    // 이메일로 유저의 정보 찾아오기
    public UsersDTO selectUserByEmail(String Email) {
    	return usersDAO.selectUserByEmail(Email);
    }
    
    
}
