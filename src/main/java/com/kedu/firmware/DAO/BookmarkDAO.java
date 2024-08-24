package com.kedu.firmware.DAO;

import com.kedu.firmware.DTO.BookmarkDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookmarkDAO {

    @Autowired
    private SqlSession mybatis;

    // 북마크 추가 메서드
    public void insert(BookmarkDTO dto) {
        mybatis.insert("bookmark.insert", dto);
    }

    // 특정 사용자의 북마크 리스트 조회 메서드
    public List<BookmarkDTO> getBookmarksByUsername(String username) throws SQLException {
        return mybatis.selectList("bookmark.getBookmarksByUsername", username);
    }

    // 북마크 삭제 메서드
    public int deleteBookmark(int board_seq, int user_seq) {
        Map<String, Integer> params = new HashMap<>();
        params.put("board_seq", board_seq);
        params.put("user_seq", user_seq);
        return mybatis.delete("bookmark.delete", params);
    }

    // 특정 사용자와 게시물의 북마크 여부 확인 메서드
    public int checkBookmark(int user_seq, int board_seq) {
        Map<String, Integer> params = new HashMap<>();
        params.put("user_seq", user_seq);  // user_seq 대소문자 일치 확인
        params.put("board_seq", board_seq);  // board_seq 대소문자 일치 확인
        return mybatis.selectOne("bookmark.checkBookmark", params);
    }
}
