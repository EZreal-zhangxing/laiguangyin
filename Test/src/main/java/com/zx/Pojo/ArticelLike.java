package com.zx.Pojo;

import java.util.Date;

public class ArticelLike {
	private Integer id;
	private Integer articelId;
	private Integer likeUser;
	private Date likeDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getArticelId() {
		return articelId;
	}
	public void setArticelId(Integer articelId) {
		this.articelId = articelId;
	}
	public Integer getLikeUser() {
		return likeUser;
	}
	public void setLikeUser(Integer likeUser) {
		this.likeUser = likeUser;
	}
	public Date getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
	public ArticelLike(Integer id, Integer articelId, Integer likeUser,
			Date likeDate) {
		super();
		this.id = id;
		this.articelId = articelId;
		this.likeUser = likeUser;
		this.likeDate = likeDate;
	}
	public ArticelLike() {
		super();
	}
	
}
