package com.kedu.firmware.DAO;

import com.kedu.firmware.DTO.EmployeeDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

    @Autowired
    private SqlSession mybatis;  

    // 새로운 직원 정보를 삽입
    public void insert(EmployeeDTO employeeDTO) {
        mybatis.insert("Employee.insertEmployee", employeeDTO);
    }

    // 직원 고유 번호를 통해 특정 직원을 조회
    public EmployeeDTO selectBySeq(int employeeSeq) {
        return mybatis.selectOne("Employee.selectBySeq", employeeSeq);
    }

    // 직원 정보를 업데이트
    public void update(EmployeeDTO employeeDTO) {
        mybatis.update("Employee.updateEmployee", employeeDTO);
    }

    // 직원 고유 번호를 통해 특정 직원을 삭제
    public void delete(int employeeSeq) {
        mybatis.delete("Employee.deleteEmployee", employeeSeq);
    }
}
