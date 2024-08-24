package com.kedu.firmware.services;

import com.kedu.firmware.DAO.EmployeeDAO;
import com.kedu.firmware.DTO.EmployeeDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    // 새로운 직원 등록
    public void register(EmployeeDTO employeeDTO) {
        employeeDAO.insert(employeeDTO);
    }

    // 직원 고유 번호를 통해 직원 정보 조회
    public EmployeeDTO getEmployeeBySeq(int employeeSeq) {
        return employeeDAO.selectBySeq(employeeSeq);
    }

    // 직원 정보 업데이트
    public void updateEmployee(EmployeeDTO employeeDTO) {
        employeeDAO.update(employeeDTO);
    }

    // 직원 고유 번호를 통해 직원 삭제
    public void deleteEmployee(int employeeSeq) {
        employeeDAO.delete(employeeSeq);
    }
    
    
 // EmployeeService에서 호출된 전체 직원 정보를 반환하는 메소드
    public List<EmployeeDTO> getAllEmployees() {
        return employeeDAO.selectAllEmployees();  // employeeMapper 호출 -> employeeDAO로 변경
    }
}
