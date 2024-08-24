package com.kedu.firmware.services;

import com.kedu.firmware.DAO.Board_ReplyDAO;
import com.kedu.firmware.DTO.Board_ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin
public class Board_ReplyService {

    @Autowired

    private Board_ReplyDAO board_ReplyDAO;

    //입력
    public void post(Board_ReplyDTO dto) {
        board_ReplyDAO.insert(dto);
    }

//    출력
    public List<Board_ReplyDTO> getBoard_ReplyList(int boardSeq) {
        return board_ReplyDAO.selectAll(boardSeq);
    }

//    업데이트
    public int updateBySeq(Board_ReplyDTO dto ) {
        return board_ReplyDAO.updateBySeq(dto);
    }

    // 삭제 메서드
    public int deleteBySeq(int seq) {
        return board_ReplyDAO.deleteBySeq(seq);
    }


}
