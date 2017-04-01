package com.zx.Pojo;

public class Model {
	private Integer id;
	private String modelName;
	private Integer parentId;
	private Integer modelLevel;
	private Integer isOrder;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getModelLevel() {
		return modelLevel;
	}
	public void setModelLevel(Integer modelLevel) {
		this.modelLevel = modelLevel;
	}
	
	public Integer getIsOrder() {
		return isOrder;
	}
	public void setIsOrder(Integer isOrder) {
		this.isOrder = isOrder;
	}
	public Model(Integer id, String modelName, Integer parentId,
			Integer modelLevel) {
		super();
		this.id = id;
		this.modelName = modelName;
		this.parentId = parentId;
		this.modelLevel = modelLevel;
	}
	public Model() {
		super();
	}
	
	
}
