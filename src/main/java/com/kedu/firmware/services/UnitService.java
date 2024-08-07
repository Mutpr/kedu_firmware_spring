package com.kedu.firmware.services;

import com.kedu.firmware.DAO.UnitDAO;
import com.kedu.firmware.DTO.UnitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    @Autowired
    private UnitDAO unitDAO;

    // 모든 부서 조회
    public List<UnitDTO> getAllUnits() {
        return unitDAO.selectAllUnits();
    }

    // 부서 코드에 따른 부서 조회
    public List<UnitDTO> getUnitsByDepartmentCode(String departmentCode) {
        return unitDAO.selectUnitsByDepartmentCode(departmentCode);
    }

    // 부서 고유 번호를 통해 부서 정보 조회
    public UnitDTO getUnitBySeq(int unitSeq) {
        return unitDAO.selectUnitBySeq(unitSeq);
    }

    // 새로운 부서 추가
    public void addUnit(UnitDTO unitDTO) {
        unitDAO.insertUnit(unitDTO);
    }

    // 부서 정보 업데이트
    public void updateUnit(UnitDTO unitDTO) {
        unitDAO.updateUnit(unitDTO);
    }

    // 부서 고유 번호를 통해 부서 삭제
    public void deleteUnit(int unitSeq) {
        unitDAO.deleteUnit(unitSeq);
    }
}
