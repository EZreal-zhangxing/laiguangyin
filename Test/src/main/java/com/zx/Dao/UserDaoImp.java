package com.zx.Dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.zx.Pojo.Message;
import com.zx.Pojo.Model;
import com.zx.Pojo.User;

public class UserDaoImp extends SqlSessionDaoSupport{
	public User getLogin(User user)
	{
		SqlSession session=this.getSqlSession();
		List<User> list=session.selectList("selectUser",user);
		return list.size()>0?list.get(0):null;
	}
	public User regist(User user)
	{
		SqlSession session=this.getSqlSession();
		int result=session.insert("insertUser",user);
		if(result>0)
		{
			List<User> list=session.selectList("selectUser",user);
			return list.size()>0?list.get(0):null;
		}else
		{
			return null;
		}
	}
	
	public Message findpassword(User user)
	{
		SqlSession session=this.getSqlSession();
		List checkuser=session.selectList("checkUser",user);
		Integer result=(checkuser.size())>0?(Integer)checkuser.get(0):0;
		if(result>0)
		{
			int upin=session.update("updateUser",user);
			if(upin>0)
			{
				return new Message("success", "更新密码成功", new Date());
			}else
			{
				return new Message("fail", "更新密码失败", new Date());
			}
		}else
		{
			return new Message("fail", "更新密码失败", new Date());
		}
	}
	
	public Integer checkusername(String username)
	{
		SqlSession session=this.getSqlSession();
		List list=session.selectList("checkusername",username);
		return list.size()>0?(Integer)list.get(0):0;
	}
	
	public Integer getadminLogin(User user)
	{
		SqlSession session=this.getSqlSession();
		List list=session.selectList("selectadminUser",user);
		return list.size()>0?(Integer)list.get(0):0;
	}
}
