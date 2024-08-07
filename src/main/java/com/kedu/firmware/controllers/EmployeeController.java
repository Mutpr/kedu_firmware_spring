package com.kedu.firmware.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kedu.firmware.DTO.EmployeeDTO;
import com.kedu.firmware.services.EmployeeService;
import com.kedu.firmware.services.DepartmentService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;  // DepartmentService 주입

    // 새로운 사원 등록 기능
    @PostMapping("/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            // department_title을 이용해 department_seq 값을 설정
            if (employeeDTO.getDepartment_seq() == null && employeeDTO.getDepartment_title() != null) {
                Integer departmentSeq = departmentService.getDepartmentByTitle(employeeDTO.getDepartment_title()).getDepartment_seq();
                employeeDTO.setDepartment_seq(departmentSeq);
            }

            employeeService.register(employeeDTO); // EmployeeService를 통해 사원 등록
            return ResponseEntity.ok("사원 등록 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사원 등록 실패");
        }
    }

    // 사원 정보 조회 (사원 ID로 조회)
    @GetMapping("/{employeeSeq}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("employeeSeq") int employeeSeq) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeBySeq(employeeSeq);
        if (employeeDTO != null) {
            return ResponseEntity.ok(employeeDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 사원 정보 업데이트
    @PutMapping("/{employeeSeq}")
    public ResponseEntity<String> updateEmployee(@PathVariable("employeeSeq") int employeeSeq, @RequestBody EmployeeDTO employeeDTO) {
        try {
            employeeDTO.setEmployee_seq(employeeSeq);
            employeeService.updateEmployee(employeeDTO);
            return ResponseEntity.ok("사원 정보 업데이트 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사원 정보 업데이트 실패");
        }
    }

    // 사원 삭제
    @DeleteMapping("/{employeeSeq}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeSeq") int employeeSeq) {
        try {
            employeeService.deleteEmployee(employeeSeq);
            return ResponseEntity.ok("사원 삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사원 삭제 실패");
        }
    }
}
