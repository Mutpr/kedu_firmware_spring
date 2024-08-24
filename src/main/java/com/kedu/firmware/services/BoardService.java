package com.kedu.firmware.services;

import com.kedu.firmware.DAO.BoardDAO;
import com.kedu.firmware.DTO.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin
public class BoardService {

    @Autowired
    private BoardDAO boardDAO;

    public void post(BoardDTO dto) {
        boardDAO.insert(dto);
    }

    //List
    public List<BoardDTO> getBoards() {
        return boardDAO.selectAll();
    }

    //    seq List
    public List<BoardDTO> getSeqBoard(int seq) {
        return boardDAO.selectAll(seq);
    }


    public BoardDTO getBoard(int seq) {
        return boardDAO.selectBySeq(seq);
    }

    //삭제
    public int deleteBySeq(int seq) {
        return boardDAO.deleteBySeq(seq);
    }

    //업데이트
    public int updateBySeq(BoardDTO dto) {
        return boardDAO.updateBySeq(dto);
    }

    public void incrementViewCount(int board_Seq) {
        boardDAO.incrementViewCount(board_Seq);
    }
}