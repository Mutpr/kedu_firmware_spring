package com.kedu.firmware.services;

import com.kedu.firmware.DAO.RankDAO;
import com.kedu.firmware.DTO.RankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {

    @Autowired
    private RankDAO rankDAO;

    // 새로운 직급 추가
    public void addRank(RankDTO rank) {
        rankDAO.insertRank(rank);  
    }

    // 직급 고유 번호를 통해 직급 정보 조회
    public RankDTO getRankById(int rank_seq) {
        return rankDAO.getRankById(rank_seq); 
    }

    // 모든 직급 정보 조회
    public List<RankDTO> getAllRanks() {
        return rankDAO.getAllRanks(); 
    }

    // 직급 정보 업데이트
    public void updateRank(RankDTO rank) {
        rankDAO.updateRank(rank);  
    }

    // 직급 고유 번호를 통해 직급 삭제
    public void deleteRank(int rank_seq) {
        rankDAO.deleteRank(rank_seq);  
    }
}
