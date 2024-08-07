package com.kedu.firmware.DAO;

import com.kedu.firmware.DTO.UnitDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnitDAO {

    @Autowired
    private SqlSession mybatis;

    // 모든 단위 정보를 조회
    public List<UnitDTO> selectAllUnits() {
        return mybatis.selectList("Unit.selectAllUnits"); 
    }

    // 특정 부서 코드에 해당하는 단위들을 조회
    public List<UnitDTO> selectUnitsByDepartmentCode(String departmentCode) {
        return mybatis.selectList("Unit.selectUnitsByDepartmentCode", departmentCode);
    }

    // 단위 고유 번호를 통해 특정 단위를 조회
    public UnitDTO selectUnitBySeq(int unitSeq) {
        return mybatis.selectOne("Unit.selectUnitBySeq", unitSeq); 
    }

    // 새로운 단위를 삽입
    public void insertUnit(UnitDTO unitDTO) {
        mybatis.insert("Unit.insertUnit", unitDTO);
    }

    // 단위 정보를 업데이트
    public void updateUnit(UnitDTO unitDTO) {
        mybatis.update("Unit.updateUnit", unitDTO);
    }

    // 단위 고유 번호를 통해 특정 단위를 삭제
    public void deleteUnit(int unitSeq) {
        mybatis.delete("Unit.deleteUnit", unitSeq);
    }
}
