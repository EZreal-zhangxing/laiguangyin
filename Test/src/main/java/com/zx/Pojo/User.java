package com.zx.Pojo;

public class User {
	private Integer id;
	private String UserName;
	private String Password;
	private String code;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public User(Integer id, String userName, String password) {
		super();
		this.id = id;
		UserName = userName;
		Password = password;
	}
	public User() {
		super();
	}
	
}
