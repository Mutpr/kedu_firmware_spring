package com.kedu.firmware.DAO;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.kedu.firmware.DTO.RankDTO;

@Repository
public class RankDAO {

    @Autowired
    private SqlSession mybatis;
    
    // 모든 직급 정보를 조회
    public List<RankDTO> getAllRanks() {
        return mybatis.selectList("Rank.selectAllRanks");
    }
    
    // 직급 고유 번호를 통해 특정 직급을 조회
    public RankDTO getRankById(int rankSeq) {
        return mybatis.selectOne("Rank.selectRankById", rankSeq);
    }

    // 새로운 직급을 삽입
    public int insertRank(RankDTO rank) {
        return mybatis.insert("Rank.insertRank", rank);
    }
    
    // 직급 정보를 업데이트
    public int updateRank(RankDTO rank) {
        return mybatis.update("Rank.updateRank", rank);
    }
    
    // 직급 고유 번호를 통해 특정 직급을 삭제
    public int deleteRank(int rankSeq) {
        return mybatis.delete("Rank.deleteRank", rankSeq);
    }
}
