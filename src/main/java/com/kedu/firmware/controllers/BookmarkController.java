package com.kedu.firmware.controllers;

import com.kedu.firmware.DTO.BookmarkDTO;
import com.kedu.firmware.DTO.UsersDTO;
import com.kedu.firmware.services.BookmarkService;
import com.kedu.firmware.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/bookmark")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private UsersService usersService;

    // 특정 게시물의 북마크 여부를 확인하는 엔드포인트
    @GetMapping("/{seq}")
    public ResponseEntity<Boolean> getBookmark(HttpServletRequest request, @PathVariable Integer seq) {
        HttpSession session = request.getSession();
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UsersDTO user = usersService.findUserByCode(loginID);

        boolean isBookmarked = bookmarkService.isBookmarked(user.getUsers_seq(), seq);
        return ResponseEntity.ok(isBookmarked);
    }

    // 북마크를 추가하는 엔드포인트
    @PostMapping
    public ResponseEntity<Void> addBookmark(HttpServletRequest request, @RequestBody BookmarkDTO bookmarkDTO) {
        HttpSession session = request.getSession();
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UsersDTO user = usersService.findUserByCode(loginID);
        bookmarkDTO.setUser_seq(user.getUsers_seq());
        bookmarkService.addBookmark(bookmarkDTO);

        return ResponseEntity.ok().build();
    }

    // 북마크를 넣는 엔드포인트
    @PostMapping("/{board_seq}")
    public ResponseEntity<Boolean> addBookmark(HttpServletRequest request, @PathVariable int board_seq) {
        HttpSession session = request.getSession();
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UsersDTO user = usersService.findUserByCode(loginID);
        int user_seq = user.getUsers_seq();

        BookmarkDTO bookmarkDTO = new BookmarkDTO();
        bookmarkDTO.setBoard_seq(board_seq);
        bookmarkDTO.setUser_seq(user_seq);

        bookmarkService.addBookmark(bookmarkDTO);
        return ResponseEntity.ok().build();
    }

    // 북마크를 삭제하는 엔드포인트
    @DeleteMapping("/{board_seq}")
    public ResponseEntity<Void> deleteBookmark(HttpServletRequest request, @PathVariable int board_seq) {
        HttpSession session = request.getSession();
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UsersDTO user = usersService.findUserByCode(loginID);
        int user_seq = user.getUsers_seq();
        System.out.println(user_seq);

        bookmarkService.deleteBookmark(board_seq, user_seq);
        return ResponseEntity.ok().build();
    }


    // 현재 사용자의 북마크 리스트를 조회하는 엔드포인트
    @GetMapping
    public ResponseEntity<List<BookmarkDTO>> getBookmarks(HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UsersDTO user = usersService.findUserByCode(loginID);

        String user_name = user.getUsers_name();

        List<BookmarkDTO> bookmarks = bookmarkService.getBookmarksByUsername(user_name);
        return ResponseEntity.ok(bookmarks);
    }

    // 특정 게시물이 사용자의 북마크인지 확인하는 엔드포인트
    @GetMapping("/check/{board_seq}")
    public ResponseEntity<Boolean> checkBookmark(HttpServletRequest request, @PathVariable int board_seq) {
        HttpSession session = request.getSession();
        String loginID = (String) session.getAttribute("loginID");
        if (loginID == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UsersDTO user = usersService.findUserByCode(loginID);
        int user_seq = user.getUsers_seq();

        boolean isBookmarked = bookmarkService.isBookmarked(user_seq, board_seq);
        return ResponseEntity.ok(isBookmarked);
    }
}
