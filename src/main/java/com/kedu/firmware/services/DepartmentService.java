package com.kedu.firmware.services;

import com.kedu.firmware.DAO.DepartmentDAO;
import com.kedu.firmware.DTO.DepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    // 모든 부서를 가져오는 메서드
    public List<DepartmentDTO> getDepartments() {
        return departmentDAO.getDepartments();
    }

    // 특정 유닛 코드에 속한 부서들을 가져오는 메서드
    public List<DepartmentDTO> getDepartmentsByUnitCode(String unitCode) {
        return departmentDAO.getDepartmentsByUnitCode(unitCode);
    }

    // 특정 부서 코드를 기반으로 부서 정보를 가져오는 메서드
    public DepartmentDTO getDepartmentByCode(String departmentCode) {
        return departmentDAO.getDepartmentByCode(departmentCode);
    }

    // 특정 부서 제목을 기반으로 부서 정보를 가져오는 메서드
    public DepartmentDTO getDepartmentByTitle(String departmentTitle) {
        return departmentDAO.getDepartmentByTitle(departmentTitle);
    }

    // 새로운 부서를 추가하는 메서드
    public void insertDepartment(DepartmentDTO dto) {
        departmentDAO.insertDepartment(dto);
    }
}
