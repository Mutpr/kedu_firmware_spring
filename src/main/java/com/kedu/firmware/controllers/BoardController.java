package com.kedu.firmware.controllers;

import com.kedu.firmware.DTO.BoardDTO;
import com.kedu.firmware.DTO.Board_CategoryDTO;
import com.kedu.firmware.DTO.UsersDTO;
import com.kedu.firmware.services.BoardService;
import com.kedu.firmware.services.Board_CategoryService;
import com.kedu.firmware.services.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private Board_CategoryService board_categoryService;

    @Autowired
    private HttpSession session;
    @Autowired
    private UsersService usersService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<Void> post(@RequestBody BoardDTO dto) {
        // 세션에서 loginID를 가져옴
        String loginID = (String) session.getAttribute("loginID");

        if (loginID == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 로그인되어 있지 않으면 Unauthorized 응답
        }

        // loginID를 사용자 ID로 변환
        // 예를 들어, loginID가 사용자 코드이고 이를 정수형 ID로 변환해야 한다면:\

        //loginID가 어떻게 int로 바뀜 string인데, loginID가 user_code잖아 ??? 여기 코드 이상하잖음.

        int userSeq;
        try {
            userSeq = usersService.findUserByCode(loginID).getUsers_seq();
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 변환 실패 시 Unauthorized 응답
        }

        // 변환된 userSeq를 DTO에 설정
        dto.setUser_seq(userSeq);

        boardService.post(dto);
        return ResponseEntity.ok().build();
    }

    // 게시글 리스트 조회
    @GetMapping
    public ResponseEntity<List<BoardDTO>> get() {
        List<BoardDTO> boards = boardService.getBoards();
        return ResponseEntity.ok(boards);
    }

    // 특정 카테고리의 게시글 조회
    @GetMapping("/{seq}")
    public ResponseEntity<List<BoardDTO>> getPostByCategory(@PathVariable int seq) {

        String loginID = (String) session.getAttribute("loginID");

        UsersDTO usersDto = usersService.findUserByCode(loginID);
        if (usersDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 사용자 정보가 없으면 Unauthorized 응답
        }
        int user_seq = ((UsersDTO) usersDto).getUsers_seq();

        List<BoardDTO> boardDTOList = board_categoryService.getPostsByCategory(seq, user_seq );
        return ResponseEntity.ok(boardDTOList);
    }

    // 게시글 상세 조회
    @GetMapping("/detail/{seq}")
    public ResponseEntity<BoardDTO> getBySeq(@PathVariable int seq) {
        BoardDTO board = boardService.getBoard(seq);
        long user_seq = board.getUser_seq();
        String user_name = usersService.getUserNameBySeq(user_seq);
        board.setUsers_name(user_name);
        if (board != null) {
            return ResponseEntity.ok(board);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 게시글 삭제
    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> deleteBySeq(@PathVariable int seq) {
        // 세션에서 loginID를 가져옴
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 로그인되어 있지 않으면 Unauthorized 응답
        }

        // 게시글 삭제 작업을 위한 추가적인 권한 확인 로직이 필요할 수 있습니다.

        boardService.deleteBySeq(seq);
        return ResponseEntity.ok().build();
    }

    // 게시글 업데이트
    @PutMapping("/{seq}")
    public ResponseEntity<Void> updateBySeq(@RequestBody BoardDTO dto, @PathVariable int seq) {
        // 세션에서 loginID를 가져옴
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 로그인되어 있지 않으면 Unauthorized 응답
        }

        // seq 값을 dto에 설정
        dto.setBoard_seq(seq);

        // loginID를 사용자 ID로 변환
        int userSeq;
        try {
            userSeq = usersService.findUserByCode(loginID).getUsers_seq(); // 문자열을 정수형으로 변환
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 변환 실패 시 Unauthorized 응답
        }

        // 변환된 userSeq를 DTO에 설정
        dto.setUser_seq(userSeq);

        boardService.updateBySeq(dto);
        return ResponseEntity.ok().build();
    }

    // 게시글 조회수 증가
    @PutMapping("/viewCount")
    public ResponseEntity<Void> incrementViewCount(@RequestBody Map<String, Integer> body) {
        int board_Seq = body.get("board_Seq");
        boardService.incrementViewCount(board_Seq);
        return ResponseEntity.ok().build();
    }

    // 기본 카테고리의 게시글 조회
    @GetMapping("/default")
    public ResponseEntity<List<BoardDTO>> getDefaultPosts() {
        Board_CategoryDTO defaultCategory = board_categoryService.getDefaultCategory();
        if (defaultCategory != null) {
            List<BoardDTO> defaultPosts = board_categoryService.getPostsByCategory(defaultCategory.getCategory_seq(), 999999999);
            return ResponseEntity.ok(defaultPosts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
