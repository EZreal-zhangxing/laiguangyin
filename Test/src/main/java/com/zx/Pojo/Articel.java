package com.zx.Pojo;

import java.util.Date;
import java.util.List;


public class Articel {
	private Integer id;
	private String title;
	private String articelDesc;
	private String content;
	private Integer modelId;
	private int[] modelids;
	private String articelPicName;
	private String articelPicPath;
	private String outUrl;//文章外链
	private Date date;
	private Integer likeNum;
	private Integer orderNum; //已经预定数
	private Integer allUsernum;//所有人员数
	private Integer isOrder;
	private Integer haslike; //用户是否已经点赞 0标识没有 1表示有
	private Integer MaxMannum;  //最大预约人员
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getArticelPicName() {
		return articelPicName;
	}
	public void setArticelPicName(String articelPicName) {
		this.articelPicName = articelPicName;
	}
	public String getArticelPicPath() {
		return articelPicPath;
	}
	public void setArticelPicPath(String articelPicPath) {
		this.articelPicPath = articelPicPath;
	}
	
	public Integer getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
	public Articel(Integer id, String title, String content, Integer modelId,
			String articelPicName, String articelPicPath, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.modelId = modelId;
		this.articelPicName = articelPicName;
		this.articelPicPath = articelPicPath;
		this.date = date;
	}
	public Articel() {
		super();
	}
	public Integer getIsOrder() {
		return isOrder;
	}
	public void setIsOrder(Integer isOrder) {
		this.isOrder = isOrder;
	}
	public Integer getHaslike() {
		return haslike;
	}
	public void setHaslike(Integer haslike) {
		this.haslike = haslike;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getAllUsernum() {
		return allUsernum;
	}
	public void setAllUsernum(Integer allUsernum) {
		this.allUsernum = allUsernum;
	}
	public String getOutUrl() {
		return outUrl;
	}
	public void setOutUrl(String outUrl) {
		this.outUrl = outUrl;
	}
	public int[] getModelids() {
		return modelids;
	}
	public void setModelids(int[] modelids) {
		this.modelids = modelids;
	}
	public String getArticelDesc() {
		return articelDesc;
	}
	public void setArticelDesc(String articelDesc) {
		this.articelDesc = articelDesc;
	}
	public Integer getMaxMannum() {
		return MaxMannum;
	}
	public void setMaxMannum(Integer maxMannum) {
		MaxMannum = maxMannum;
	}
	
}
