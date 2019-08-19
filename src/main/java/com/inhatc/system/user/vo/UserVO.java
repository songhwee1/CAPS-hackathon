package com.inhatc.system.user.vo;


public class UserVO {
	
	private String id;	
	private String pw;
	private String loginType;
	private String manager_belong;
	private String manager_name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	public String getManager_belong() {
		return manager_belong;
	}
	public void setManager_belong(String manager_belong) {
		this.manager_belong = manager_belong;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	
}