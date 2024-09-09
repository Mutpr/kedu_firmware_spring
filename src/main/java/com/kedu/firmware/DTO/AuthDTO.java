package com.kedu.firmware.DTO;


public class AuthDTO {
	private int usersSeq;
	private String loginID;
	private String usersName;
	private String employeeId;
	private String rank;
	private int isAdmin;

	public AuthDTO () {}
	public AuthDTO (int usersSeq, String loginID, String usersName, String employeeId, String rank, int isAdmin)
	{
		this.usersSeq = usersSeq;
		this.loginID = loginID;
		this.usersName = usersName;
		this.employeeId = employeeId;
		this.rank = rank;
		this.isAdmin = isAdmin;
	}


	public void setUsersSeq(int usersSeq) {
		this.usersSeq = usersSeq;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}



	public int getUsersSeq() {
		return usersSeq;
	}

	public String getLoginID() {
		return loginID;
	}

	public String getUsersName() {
		return usersName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getRank() {
		return rank;
	}


	public int getIsAdmin() {
		return isAdmin;
	}




}
