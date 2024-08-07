package com.kedu.firmware.controllers;

import com.kedu.firmware.DTO.Board_ReplyDTO;
import com.kedu.firmware.services.Board_ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board_reply")
public class Board_ReplyController {

    @Autowired
    private Board_ReplyService board_replyService;

//    입력
    @PostMapping
    public ResponseEntity<Void> post(@RequestBody Board_ReplyDTO dto) {
        board_replyService.post(dto);
        return ResponseEntity.ok().build();
    }

//    출력
    @GetMapping("/{seq}")
    public ResponseEntity<List<Board_ReplyDTO>> getReplies(@PathVariable int seq) {
        List<Board_ReplyDTO> replyList = board_replyService.getBoard_ReplyList(seq);

        if (replyList != null && !replyList.isEmpty()) {
            return ResponseEntity.ok(replyList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // 삭제 엔드포인트
    @DeleteMapping("/replies/{reply_seq}")
    public ResponseEntity<Void> deleteBySeq(@PathVariable int reply_seq) {
        board_replyService.deleteBySeq(reply_seq);
        return ResponseEntity.ok().build();
    }

    // 업데이트 엔드포인트
    @PutMapping("/replies/{reply_seq}")
    public ResponseEntity<Void> updateBySeq(@RequestBody Board_ReplyDTO dto, @PathVariable int reply_seq) {
        dto.setReply_seq(reply_seq); // 댓글 ID를 DTO에 설정
        board_replyService.updateBySeq(dto);
        return ResponseEntity.ok().build();
    }


}
