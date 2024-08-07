package com.kedu.firmware.controllers;

import com.kedu.firmware.DTO.BoardDTO;
import com.kedu.firmware.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    //    입력
    @PostMapping
    public ResponseEntity<Void> post(@RequestBody BoardDTO dto) {
        boardService.post(dto);
        return ResponseEntity.ok().build();
    }

    //    리스트 
    @GetMapping
    public ResponseEntity<List<BoardDTO>> get() {
        List<BoardDTO> boards = boardService.getBoards();
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/detail/{seq}")
    public ResponseEntity<BoardDTO> getBySeq(@PathVariable int seq) {
        BoardDTO board = boardService.getBoard(seq);
        if (board != null) {
            return ResponseEntity.ok(board);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    삭제
    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> deleteBySeq(@PathVariable int seq) {
        boardService.deleteBySeq(seq);
        return ResponseEntity.ok().build();
    }

//    업데이트
    @PutMapping("/{seq}")
    public ResponseEntity<Void> updateBySeq(@RequestBody BoardDTO dto, @PathVariable int seq) {
        // seq 값을 dto에 설정 (DTO에 seq 필드가 있어야 합니다)
        dto.setBoard_seq(seq);
        boardService.updateBySeq(dto);
        return ResponseEntity.ok().build();
    }

}
