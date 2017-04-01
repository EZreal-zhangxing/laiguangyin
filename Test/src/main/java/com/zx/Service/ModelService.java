package com.zx.Service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zx.Dao.ModelDaoImp;
import com.zx.Pojo.Model;

public class ModelService {
	private ModelDaoImp modelDaoImp;
	public List<Model> getOneLevelModel()
	{
		return modelDaoImp.getOnelevelModel();
	}
	
	public List<Model> getArtModel(List<Integer> list)
	{
		return modelDaoImp.getArtModel(list);
	}
	public List<Model> getOthLevelModel(String modelid)
	{
		return modelDaoImp.getOthlevelModel(modelid);
	}
	
	public boolean modelisorder(String modelid)
	{
		Model model=modelDaoImp.modelisOrder(modelid);
		if(model == null || (model != null && model.getIsOrder() == 0))
		{
			return false;
		}else
		{
			return true;
		}
	}
	public ModelDaoImp getModelDaoImp() {
		return modelDaoImp;
	}
	public void setModelDaoImp(ModelDaoImp modelDaoImp) {
		this.modelDaoImp = modelDaoImp;
	}
	
	public boolean ischildNode(String modelid)
	{
		return modelDaoImp.ischildNode(modelid);
	}
	
	public List getchildModel(String modelid)
	{
		return modelDaoImp.getchildModel(modelid);
	}
	
}
