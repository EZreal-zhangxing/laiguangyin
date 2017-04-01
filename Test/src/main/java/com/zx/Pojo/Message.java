package com.zx.Pojo;

import java.util.Date;

public class Message {
	private String code;
	private String message;
	private Date date;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Message(String code, String message, Date date) {
		super();
		this.code = code;
		this.message = message;
		this.date = date;
	}
	public Message() {
		super();
	}
	
}
