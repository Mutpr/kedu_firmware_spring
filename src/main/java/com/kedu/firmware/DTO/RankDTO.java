package com.kedu.firmware.DTO;

public class RankDTO {

    private int rank_seq;       // 직급 시퀀스
    private String rank_code;   // 직급 코드
    private String rank_title;  // 직급 명칭

    // 기본 생성자
    public RankDTO() {}
    

    public RankDTO(int rank_seq, String rank_code, String rank_title) {
		super();
		this.rank_seq = rank_seq;
		this.rank_code = rank_code;
		this.rank_title = rank_title;
	}

	// Getter와 Setter 메서드
    public int getRank_seq() {
        return rank_seq;
    }

    public void setRank_seq(int rank_seq) {
        this.rank_seq = rank_seq;
    }

    public String getRank_code() {
        return rank_code;
    }

    public void setRank_code(String rank_code) {
        this.rank_code = rank_code;
    }

    public String getRank_title() {
        return rank_title;
    }

    public void setRank_title(String rank_title) {
        this.rank_title = rank_title;
    }
}

