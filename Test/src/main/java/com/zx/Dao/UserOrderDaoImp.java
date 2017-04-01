package com.zx.Dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.zx.Pojo.Articel;
import com.zx.Pojo.Pageinfo;
import com.zx.Pojo.userOrder;

public class UserOrderDaoImp extends SqlSessionDaoSupport {
	public int saveUserOrder(userOrder uo)
	{
		SqlSession session=this.getSqlSession();
		int result=session.insert("saveUserOrder",uo);
		return result;
	}
	/**
	 * 判断用户在时间段内是否预定
	 * @param uo
	 * @return
	 */
	public Integer hasorder(userOrder uo)
	{
		SqlSession session=this.getSqlSession();
		List result=session.selectList("hasorder",uo);
		return result.size()>0?(Integer)result.get(0):null;
	}
	/**
	 * 判断是否有人再这个时间段内预定
	 * @param uo
	 * @return
	 */
	public Integer ishasorder(userOrder uo)
	{
		SqlSession session=this.getSqlSession();
		List result=session.selectList("isuserorder",uo);
		return result.size()>0?(Integer)result.get(0):null;
	}
	
	/**
	 * 根据用户ID查询对应的预约信息
	 * @param userid
	 * @return
	 */
	public List getuserOrderByid(String userid)
	{
		SqlSession session=this.getSqlSession();
		List map=session.selectList("selectUserorderByid", userid);
		return map;
	}
	public boolean cancleOrder(String orderid)
	{
		SqlSession session=this.getSqlSession();
		int result=session.update("cancleOrder", orderid);
		return result>0?true:false;
	}
	
	public List getuserOrderbyArtid(Pageinfo pi)
	{
		SqlSession session=this.getSqlSession();
		List result=session.selectList("selectUserorderByartid", pi);
		return result;
	}
	
	public Integer getNumuserOrderbyArtid(Integer articelId)
	{
		SqlSession session=this.getSqlSession();
		List result=session.selectList("selectNumUserorderByartid", articelId);
		return result.size()>0?(Integer)result.get(0):0;
	}
	
	public void passoutUserorder(String timeArea)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String todaystr=sdf.format(new Date());
		Map<String, String> map=new HashMap<String, String>();
		map.put("orderDate", todaystr);
		map.put("timeArea", timeArea);
		SqlSession session=this.getSqlSession();
		int result=session.update("updateUserorder", map);
	}
	
	public void passoutUserordereveryday(String date)
	{
		SqlSession session=this.getSqlSession();
		int result=session.update("updateUserordereveryday", date);
	}
	public Integer getNumofOrder(String articelId)
	{
		SqlSession session=this.getSqlSession();
		List list=session.selectList("getNumofOrder", articelId);
		return list.size()>0?(Integer)list.get(0):0;
	}
	
	public Articel getArticelbyid(String articelId)
	{
		SqlSession session=this.getSqlSession();
		List<Articel> list=session.selectList("selectArticelbyid", articelId);
		return list.size()>0?list.get(0):null;
	}
}
