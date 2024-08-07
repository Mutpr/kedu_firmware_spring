package com.kedu.firmware.controllers;

import com.kedu.firmware.DTO.UnitDTO;
import com.kedu.firmware.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UnitController {

    @Autowired
    private UnitService unitService;

    // 모든 유닛을 가져오는 메서드
    @GetMapping("/units")
    public ResponseEntity<List<UnitDTO>> getAllUnits() {
        List<UnitDTO> units = unitService.getAllUnits();
        return ResponseEntity.ok(units);
    }

    // 특정 유닛을 가져오는 메서드 추가
    @GetMapping("/units/{unitSeq}")
    public ResponseEntity<UnitDTO> getUnitBySeq(@PathVariable int unitSeq) {
        UnitDTO unit = unitService.getUnitBySeq(unitSeq);
        if (unit == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(unit);
    }

    // 부서 코드를 기반으로 유닛 목록을 가져오는 메서드
    @GetMapping("/departments/{departmentCode}/units")
    public ResponseEntity<List<UnitDTO>> getUnitsByDepartmentCode(@PathVariable String departmentCode) {
        List<UnitDTO> units = unitService.getUnitsByDepartmentCode(departmentCode);
        if (units.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(units);
    }

    // 유닛을 생성하는 메서드
    @PostMapping("/units")
    public ResponseEntity<Void> createUnit(@RequestBody UnitDTO unitDTO) {
        unitService.addUnit(unitDTO);
        return ResponseEntity.ok().build();
    }

    // 유닛을 업데이트하는 메서드
    @PutMapping("/units/{unitSeq}")
    public ResponseEntity<Void> updateUnit(@PathVariable int unitSeq, @RequestBody UnitDTO unitDTO) {
        unitDTO.setUnit_seq(unitSeq);
        unitService.updateUnit(unitDTO);
        return ResponseEntity.ok().build();
    }

    // 유닛을 삭제하는 메서드
    @DeleteMapping("/units/{unitSeq}")
    public ResponseEntity<Void> deleteUnit(@PathVariable int unitSeq) {
        unitService.deleteUnit(unitSeq);
        return ResponseEntity.ok().build();
    }
}
