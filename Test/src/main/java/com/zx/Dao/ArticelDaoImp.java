package com.zx.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.zx.Pojo.Articel;
import com.zx.Pojo.ArticelLike;
import com.zx.Pojo.Pageinfo;

public class ArticelDaoImp extends SqlSessionDaoSupport {
	
	public List<Articel> getAllarticel() {
		SqlSession session=this.getSqlSession();
		List<Articel> list=session.selectList("selectAllArticel");
		return list;
	}

	public List<Articel> getArticelbyModel(String model) {
		SqlSession session=this.getSqlSession();
		List<Articel> list=session.selectList("selectArticelBymodel",model);
		return list;
	}
	
	public int saveArticel(Articel articel)
	{
		SqlSession session=this.getSqlSession();
		int result=session.insert("saveArticel",articel);
		return result;
	}
	public int updateArticel(Articel articel)
	{
		SqlSession session=this.getSqlSession();
		int result=session.update("updateArticel",articel);
		return result;
	}

	public List<Articel> getArticelCeontent(String id) {
		SqlSession session=this.getSqlSession();
		List<Articel> list=session.selectList("selectArticelContent",id);
		return list;
	}
	
	public List<Articel> getArticelFilepath(String id) {
		SqlSession session=this.getSqlSession();
		List<Articel> list=session.selectList("selectArticelFilepath",id);
		return list;
	}
	
	/**
	 * 通过查询条件查询符合的文章数目
	 * @return
	 */
	public Integer getNumOfArticelWithcond(Articel articel)
	{
		SqlSession session=this.getSqlSession();
		List list=session.selectList("selectNumOfArticel",articel);
		return list!=null?(Integer)list.get(0):0;
	}
	/**
	 * 通过查询条件查询符合的文章
	 * @return
	 */
	public List getArticelWithcond(Pageinfo pageinfo)
	{
		SqlSession session=this.getSqlSession();
		List list=session.selectList("selectArticelByCond",pageinfo);
		return list;
	}
	public int deleteArtByid(String id)
	{
		SqlSession session=this.getSqlSession();
		//删除文章
		int result=session.delete("deleteartByid", id);
		//删除用户订单
		int userorder=session.delete("deleteUserorder", id);
		return result;
	}
	
	public void pointlike(Integer articelid)
	{
		SqlSession session=this.getSqlSession();
		int result=session.update("updatePointLike",articelid);
	}
	
	public void dispointlike(Integer articelid)
	{
		SqlSession session=this.getSqlSession();
		int result=session.update("updatedisPointLike",articelid);
	}
	
	public void savearticelLike(ArticelLike articellike)
	{
		SqlSession session=this.getSqlSession();
		int result=session.update("insertArticelLike",articellike); 
	}
	
	public void deletearticelLike(ArticelLike articellike)
	{
		SqlSession session=this.getSqlSession();
		int result=session.delete("deleteArticelLike",articellike); 
	}
	
	public List getArticellike(ArticelLike articellike)
	{
		SqlSession session=this.getSqlSession();
		List list=session.selectList("selectArticelLike",articellike);
		return list;
	}
	
	public List<ArticelLike> getArticellikeByuserid(String userid)
	{
		SqlSession session=this.getSqlSession();
		List<ArticelLike> list=session.selectList("selectArticelLikeByuserid",userid);
		return list;
	}
}
