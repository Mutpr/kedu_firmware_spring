package com.kedu.firmware.services;

import com.kedu.firmware.DAO.Board_CategoryDAO;
import com.kedu.firmware.DTO.BoardDTO;
import com.kedu.firmware.DTO.Board_CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@CrossOrigin
public class Board_CategoryService {

    @Autowired
    private Board_CategoryDAO board_CategoryDAO;

    public List<Board_CategoryDTO> getAllCategories() {
        return board_CategoryDAO.getAllCategories();
    }

    public List<BoardDTO> getPostsByCategory(int category_seq, int user_seq ) {
        return board_CategoryDAO.getPostsByCategory(category_seq, user_seq);
    }

    public Board_CategoryDTO getDefaultCategory() {
        return board_CategoryDAO.getDefaultCategory();
    }
}
