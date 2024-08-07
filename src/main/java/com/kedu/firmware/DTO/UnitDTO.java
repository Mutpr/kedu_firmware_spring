package com.kedu.firmware.DTO;

public class UnitDTO {
    private int unit_seq;
    private String unit_title;
    private int unit_captain_user_seq;
    private String unit_code;
    
    
    public UnitDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    

	public UnitDTO(int unit_seq, String unit_title, int unit_captain_user_seq, String unit_code) {
		super();
		this.unit_seq = unit_seq;
		this.unit_title = unit_title;
		this.unit_captain_user_seq = unit_captain_user_seq;
		this.unit_code = unit_code;
	}


	// Getters and Setters
    public int getUnit_seq() {
        return unit_seq;
    }

    public void setUnit_seq(int unit_seq) {
        this.unit_seq = unit_seq;
    }

    public String getUnit_title() {
        return unit_title;
    }

    public void setUnit_title(String unit_title) {
        this.unit_title = unit_title;
    }

    public int getUnit_captain_user_seq() {
        return unit_captain_user_seq;
    }

    public void setUnit_captain_user_seq(int unit_captain_user_seq) {
        this.unit_captain_user_seq = unit_captain_user_seq;
    }

    public String getUnit_code() {
        return unit_code;
    }

    public void setUnit_code(String unit_code) {
        this.unit_code = unit_code;
    }
}

