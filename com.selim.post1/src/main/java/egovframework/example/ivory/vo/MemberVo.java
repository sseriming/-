package egovframework.example.ivory.vo;

import java.util.Date;

public class MemberVo {
	private String userId;
	private String userPw;
	private String userEmail;
	private Date enterDate;
	private String userName;
	

	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserPw() {
		return userPw;
	}
	
	public void setUserPw(String userPw) {
		this.userPw= userPw;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public Date getEnterDate() {
		return enterDate;
	}
	
	public void setEnterDate(Date enterDate) {
		this.enterDate= enterDate;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String toString() {
		return "MemberVo [userId=" + userId+ ", userPw=" + userPw + ", userEmail=" + userEmail+", enterDate="+ enterDate+",userName=" + userName+ "]";            
	}
	
	//비밀번호 확인
	public boolean matchPassword(String pw) {
		return this.userPw.equals(pw);
	}


	
}