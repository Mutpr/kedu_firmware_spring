package com.kedu.firmware.DAO;

import com.kedu.firmware.DTO.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAO {
    @Autowired
    private SqlSession mybatis;

    public void insert(BoardDTO dto) {
        mybatis.insert("board.insert", dto);
    }

    public List<BoardDTO> selectAll() {
        return mybatis.selectList("board.selectAll");
    }

    public BoardDTO selectBySeq(int seq) {
        return mybatis.selectOne("board.selectBySeq", seq);
    }

    public int deleteBySeq(int seq) {
        return mybatis.delete("board.deleteBySeq", seq);
    }

    public int updateBySeq(BoardDTO dto) {
        return mybatis.update("board.updateBySeq", dto);
    }

}
