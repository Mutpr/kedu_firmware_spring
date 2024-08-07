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

    public List<BoardDTO> getBoards() {
        return boardDAO.selectAll();
    }

    public BoardDTO getBoard(int seq) {
        return boardDAO.selectBySeq(seq);
    }

    public int deleteBySeq(int seq) {
        return boardDAO.deleteBySeq(seq);
    }

    public int updateBySeq(BoardDTO dto) {
        return boardDAO.updateBySeq(dto);
    }



}
