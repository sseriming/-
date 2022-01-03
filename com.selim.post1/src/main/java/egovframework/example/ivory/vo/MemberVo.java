package egovframework.example.ivory.vo;

import java.util.Date;

public class MemberVo {
	private String userId;
	private String userPw;
	private String userEmail;
	private Date enterDate;
	
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
	
	public String toString() {
		return "MemberVo [userId=" + userId+ ", userPw=" + userPw + ", userEmail=" + userEmail+", enterDate="+ enterDate+"]";
	}
	
}