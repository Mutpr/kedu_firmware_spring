package com.kedu.firmware.controllers;

import com.kedu.firmware.DTO.RankDTO;
import com.kedu.firmware.services.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranks")
public class RankController {

    @Autowired
    private RankService rankService;

    // 새로운 Rank를 추가하는 메서드
    // 클라이언트로부터 RankDTO 객체를 받아 서비스를 통해 Rank를 추가
    @PostMapping
    public void addRank(@RequestBody RankDTO rank) {
        rankService.addRank(rank);
    }

    // ID(rank_seq)를 통해 특정 Rank를 조회하는 메서드
    // URL 경로에서 rank_seq를 받아 해당 Rank 정보를 반환
    @GetMapping("/{rank_seq}")
    public RankDTO getRankById(@PathVariable int rank_seq) {
        return rankService.getRankById(rank_seq);
    }

    // 모든 Rank 목록을 조회하는 메서드
    // RankService를 통해 모든 Rank를 리스트로 반환
    @GetMapping
    public List<RankDTO> getAllRanks() {
        return rankService.getAllRanks();
    }

    // 특정 Rank를 업데이트하는 메서드
    // URL 경로에서 rank_seq를 받아 해당 Rank 정보를 수정
    @PutMapping("/{rank_seq}")
    public void updateRank(@PathVariable int rank_seq, @RequestBody RankDTO rank) {
        rank.setRank_seq(rank_seq);
        rankService.updateRank(rank);
    }

    // 특정 Rank를 삭제하는 메서드
    // URL 경로에서 rank_seq를 받아 해당 Rank를 삭제
    @DeleteMapping("/{rank_seq}")
    public void deleteRank(@PathVariable int rank_seq) {
        rankService.deleteRank(rank_seq);
    }
}
