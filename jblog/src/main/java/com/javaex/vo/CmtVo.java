package com.javaex.vo;

public class CmtVo {
	private int cmtNo;
	private int postNo;
	private String cmtContent;
	private String regDate;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public CmtVo(int cmtNo, int postNo, String cmtContent, String regDate) {
		super();
		this.cmtNo = cmtNo;
		this.postNo = postNo;
		this.cmtContent = cmtContent;
		this.regDate = regDate;
	}
	public CmtVo() {
		super();
	}
	@Override
	public String toString() {
		return "CmtVo [cmtNo=" + cmtNo + ", postNo=" + postNo + ", cmtContent=" + cmtContent + ", regDate=" + regDate
				+ "]";
	}
	
	
}
