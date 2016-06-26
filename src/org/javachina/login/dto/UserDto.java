package org.javachina.login.dto;

public class UserDto {
	private String userId;
	private String pwd;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public UserDto(String userId, String pwd) {
		super();
		this.userId = userId;
		this.pwd = pwd;
	}
	public UserDto() {
		super();
	}
	
}
