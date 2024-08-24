package com.kedu.firmware.controllers;

import com.kedu.firmware.DTO.BoardDTO;
import com.kedu.firmware.DTO.Board_CategoryDTO;
import com.kedu.firmware.services.Board_CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board_category")
public class Board_CategoryController {

    @Autowired
    private Board_CategoryService board_categoryService;

    @GetMapping
    public List<Board_CategoryDTO> getAllCategories() {
        return board_categoryService.getAllCategories();
    }

    @GetMapping("/{category_seq}")
    public void getPostsByCategory(@PathVariable int category_seq) {
        List<BoardDTO>posts = board_categoryService.getPostsByCategory(category_seq, 999999999);
        for(BoardDTO category : posts){
            System.out.println(category.getBoard_title());
        }
    }
}
