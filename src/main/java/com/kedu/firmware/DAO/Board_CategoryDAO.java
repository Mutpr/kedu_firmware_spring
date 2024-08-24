package com.kedu.firmware.DAO;

import com.kedu.firmware.DTO.BoardDTO;
import com.kedu.firmware.DTO.Board_CategoryDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Board_CategoryDAO {

    @Autowired
    private SqlSession mybatis;

    public List<Board_CategoryDTO> getAllCategories() {
        return mybatis.selectList("category.selectAll");
    }

    public List<BoardDTO> getPostsByCategory(int category_seq, int user_seq) {
        System.out.println("category_seq::::: "+category_seq);
        Map<String, Integer> params = new HashMap<>();
        params.put("category_seq", category_seq);
        if(user_seq != 999999999) {
            params.put("user_seq", user_seq);
        }
        return mybatis.selectList("category.selectByCategory", params);
    }

    public Board_CategoryDTO getDefaultCategory() {
        return mybatis.selectOne("category.selectDefaultCategory");
    }
}
