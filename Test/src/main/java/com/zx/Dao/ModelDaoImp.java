package com.zx.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.zx.Pojo.Model;

public class ModelDaoImp extends SqlSessionDaoSupport{
	
	public List<Model> getOnelevelModel()
	{
		SqlSession session=this.getSqlSession();
		List<Model> list=session.selectList("selectOneLevelModel");
		return list;
	}
	
	public List<Model> getArtModel(List<Integer> modellist)
	{
		SqlSession session=this.getSqlSession();
		List<Model> list=session.selectList("selectModel",modellist);
		return list;
	}
	
	public List<Model> getOthlevelModel(String modelid)
	{
		SqlSession session=this.getSqlSession();
		List<Model> list=session.selectList("selectOthLevelModel",modelid);
		return list;
	}
	
	public Model modelisOrder(String modelid)
	{
		SqlSession session=this.getSqlSession();
		List<Model> list=session.selectList("modelisOrder",modelid);
		return list.size()>0?list.get(0):null;
	}
	
	public boolean ischildNode(String modelid)
	{
		SqlSession session=this.getSqlSession();
		List list=session.selectList("isChildNode",modelid);
		Integer result= list.size()>0?(Integer)list.get(0):0;
		if(result>0)
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public List getchildModel(String modelid)
	{
		SqlSession session=this.getSqlSession();
		List list=session.selectList("getChildModel",modelid);
		return list;
	}
	
}
