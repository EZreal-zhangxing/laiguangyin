package com.zx.Pojo;

import java.util.List;

public class Pageinfo {
	private Integer firstpage;//第一页
	private Integer prev;//上一页
	private Integer next;//下一页
	private Integer pagenum;//当前页
	private Integer allpage;//总页面
	private Integer lastpage;//最后一页
	private Integer shownum;//每页展示数
	private String[] pageStr;
	private int begin;
	private int end;
	private List<Articel> artList; //本页文章信息
	private Articel condarticel; //查询文章条件对象
	private List result; //用户预定信息列表
	public Integer getFirstpage() {
		return firstpage;
	}
	public void setFirstpage(Integer firstpage) {
		this.firstpage = firstpage;
	}
	public Integer getPrev() {
		return prev;
	}
	public void setPrev(Integer prev) {
		this.prev = prev;
	}
	public Integer getNext() {
		return next;
	}
	public void setNext(Integer next) {
		this.next = next;
	}
	public Integer getPagenum() {
		return pagenum;
	}
	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}
	public Integer getAllpage() {
		return allpage;
	}
	public void setAllpage(Integer allpage) {
		this.allpage = allpage;
	}
	public Integer getLastpage() {
		return lastpage;
	}
	public void setLastpage(Integer lastpage) {
		this.lastpage = lastpage;
	}
	public Integer getShownum() {
		return shownum;
	}
	public void setShownum(Integer shownum) {
		this.shownum = shownum;
	}
	
	public String[] getPageStr() {
		return pageStr;
	}
	public void setPageStr(String[] pageStr) {
		this.pageStr = pageStr;
	}
	public Articel getCondarticel() {
		return condarticel;
	}
	public void setCondarticel(Articel condarticel) {
		this.condarticel = condarticel;
	}
	
	public Pageinfo(Integer firstpage, Integer prev, Integer next,
			Integer pagenum, Integer allpage, Integer lastpage,
			Integer shownum, String[] pageStr, int begin, int end,
			List<Articel> artList, Articel condarticel) {
		super();
		this.firstpage = firstpage;
		this.prev = prev;
		this.next = next;
		this.pagenum = pagenum;
		this.allpage = allpage;
		this.lastpage = lastpage;
		this.shownum = shownum;
		this.pageStr = pageStr;
		this.begin = begin;
		this.end = end;
		this.artList = artList;
		this.condarticel = condarticel;
	}
	public List<Articel> getArtList() {
		return artList;
	}
	public void setArtList(List<Articel> artList) {
		this.artList = artList;
	}
	public Pageinfo() {
		super();
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public List getResult() {
		return result;
	}
	public void setResult(List result) {
		this.result = result;
	}
	
}
