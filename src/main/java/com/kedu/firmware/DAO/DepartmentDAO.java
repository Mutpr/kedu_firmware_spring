package com.kedu.firmware.DAO;

import com.kedu.firmware.DTO.DepartmentDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAO {

    @Autowired
    private SqlSession mybatis;

    // 모든 부서 정보를 조회
    public List<DepartmentDTO> getDepartments() {
        return mybatis.selectList("Department.getDepartments");
    }

    // 특정 단위 코드에 해당하는 부서들을 조회
    public List<DepartmentDTO> getDepartmentsByUnitCode(String unitCode) {
        return mybatis.selectList("Department.getDepartmentsByUnitCode", unitCode);
    }

    // 부서 코드를 통해 특정 부서를 조회
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        return mybatis.selectOne("Department.getDepartmentByCode", departmentCode);
    }

    // 부서명을 통해 특정 부서를 조회
    public DepartmentDTO getDepartmentByTitle(String departmentTitle) {
        return mybatis.selectOne("Department.getDepartmentByTitle", departmentTitle);
    }

    // 새로운 부서를 삽입
    public void insertDepartment(DepartmentDTO dto) {
        mybatis.insert("Department.insertDepartment", dto);
    }
}
