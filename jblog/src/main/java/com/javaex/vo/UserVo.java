package com.javaex.vo;

public class UserVo {
	private int userNo;
	private String id;
	private String userName;
	private String passWord;
	private String date;
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public UserVo(int userNo, String id, String userName, String passWord, String date) {
		super();
		this.userNo = userNo;
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.date = date;
	}
	
	public UserVo(String id, String userName, String passWord) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public UserVo(String id) {
		super();
		this.id = id;
	}

	public UserVo() {
		super();
	}
	
	@Override
	public String toString() {
		return "UserVo [userNo=" + userNo + ", id=" + id + ", userName=" + userName + ", passWord=" + passWord
				+ ", date=" + date + "]";
	}
	
	

}
