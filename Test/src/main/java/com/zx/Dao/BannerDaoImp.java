package com.zx.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.zx.Pojo.Banner;

public class BannerDaoImp extends SqlSessionDaoSupport {
	public List<Banner> getallbanner()
	{
		SqlSession session=getSqlSession();
		List<Banner> list=session.selectList("selectAllbanner");
		return list;
	}
	
	public int deleteBanner(String id)
	{
		SqlSession session=getSqlSession();
		return session.delete("deleteBanner", id);
	}
	
	public int saveBanner(Banner banner)
	{
		SqlSession session=getSqlSession();
		return session.insert("saveBanner", banner);
	}
	public Banner getbannerByid(String id)
	{
		SqlSession session=getSqlSession();
		List<Banner> list=session.selectList("selectBannerByid",id);
		return list.size()>0?list.get(0):null;
	}
}
