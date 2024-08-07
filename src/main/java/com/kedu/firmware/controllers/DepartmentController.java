package com.kedu.firmware.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kedu.firmware.DTO.DepartmentDTO;
import com.kedu.firmware.services.DepartmentService;

@RestController
@RequestMapping("/admin")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 모든 부서를 가져오는 메서드
    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDTO>> getDepartments() {
        List<DepartmentDTO> departments = departmentService.getDepartments();
        return ResponseEntity.ok(departments);
    }

    // 특정 유닛 코드에 속한 부서들을 가져오는 메서드
    @GetMapping("/units/{unitCode}/departments")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByUnitCode(@PathVariable String unitCode) {
        List<DepartmentDTO> departments = departmentService.getDepartmentsByUnitCode(unitCode);
        return ResponseEntity.ok(departments);
    }

    // 특정 부서 코드를 기반으로 부서 정보를 가져오는 메서드 추가
    @GetMapping("/departments/{departmentCode}")
    public ResponseEntity<DepartmentDTO> getDepartmentByCode(@PathVariable String departmentCode) {
        DepartmentDTO department = departmentService.getDepartmentByCode(departmentCode);
        if (department != null) {
            return ResponseEntity.ok(department);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 새로운 부서를 생성하는 메서드
    @PostMapping("/departments")
    public ResponseEntity<Void> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.insertDepartment(departmentDTO);
        return ResponseEntity.ok().build();
    }
}
