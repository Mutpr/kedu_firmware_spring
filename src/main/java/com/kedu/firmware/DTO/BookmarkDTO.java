package com.kedu.firmware.DTO;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookmarkDTO {
    private int bookmark_seq;
    private int user_seq;
    private int board_seq;

    public BookmarkDTO() {}
    public BookmarkDTO(int bookmark_seq, int user_seq, int board_seq) {
        this.bookmark_seq = bookmark_seq;
        this.user_seq = user_seq;
        this.board_seq = board_seq;
    }



    public int getBookmark_seq() {
        return bookmark_seq;
    }

    public void setBookmark_seq(int bookmark_seq) {
        this.bookmark_seq = bookmark_seq;
    }

    public int getUser_seq() {
        return user_seq;
    }

    public void setUser_seq(int user_seq) {
        this.user_seq = user_seq;
    }

    public int getBoard_seq() {
        return board_seq;
    }

    public void setBoard_seq(int board_seq) {
        this.board_seq = board_seq;
    }
}
