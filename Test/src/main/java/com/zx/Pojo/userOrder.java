package com.zx.Pojo;

public class userOrder {
	private Integer id;
	private String name;
	private String userTel;
	private String userAddress;
	private String comAddress;
	private Integer articelId;
	private Integer userId;
	private Integer timeArea;
	private Integer statue; //0 已取消 1已预约 2已过期
	private String orderDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getComAddress() {
		return comAddress;
	}
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
	
	public Integer getArticelId() {
		return articelId;
	}
	public void setArticelId(Integer articelId) {
		this.articelId = articelId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getTimeArea() {
		return timeArea;
	}
	public void setTimeArea(Integer timeArea) {
		this.timeArea = timeArea;
	}
	
	public Integer getStatue() {
		return statue;
	}
	public void setStatue(Integer statue) {
		this.statue = statue;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public userOrder(Integer id, String name, String userTel,
			String userAddress, String comAddress, Integer articelId,
			Integer userId, Integer timeArea) {
		super();
		this.id = id;
		this.name = name;
		this.userTel = userTel;
		this.userAddress = userAddress;
		this.comAddress = comAddress;
		this.articelId = articelId;
		this.userId = userId;
		this.timeArea = timeArea;
	}
	public userOrder() {
		super();
	}
	
}
