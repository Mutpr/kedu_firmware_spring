package com.kedu.firmware.services;

import com.kedu.firmware.DAO.BookmarkDAO;
import com.kedu.firmware.DTO.BookmarkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.SQLException;
import java.util.List;

@Service
@CrossOrigin
public class BookmarkService {

    @Autowired
    BookmarkDAO bookmarkDAO;

    // 북마크 추가 메서드
    public void addBookmark(BookmarkDTO dto) {
        bookmarkDAO.insert(dto);
    }

    // 특정 사용자의 북마크 리스트 조회 메서드
    public List<BookmarkDTO> getBookmarksByUsername(String username) throws SQLException {
        return bookmarkDAO.getBookmarksByUsername(username);
    }

    // 북마크 삭제 메서드
    public int deleteBookmark(int board_seq, int user_seq) {
        return bookmarkDAO.deleteBookmark(board_seq, user_seq);
    }

    // 특정 사용자와 게시물의 북마크 여부 확인 메서드
    public boolean isBookmarked(int user_seq, int board_seq) {
        return bookmarkDAO.checkBookmark(user_seq, board_seq) > 0;
    }
}
