package com.zx.Service;

import java.util.Date;
import java.util.List;

import com.zx.Dao.UserDaoImp;
import com.zx.Pojo.Message;
import com.zx.Pojo.User;

public class UserService {
	private UserDaoImp userDaoimp;
	
	public UserDaoImp getUserDaoimp() {
		return userDaoimp;
	}

	public void setUserDaoimp(UserDaoImp userDaoimp) {
		this.userDaoimp = userDaoimp;
	}
	/**
	 * 登陆方法
	 * @param user
	 * @return
	 */
	public User getLogin(User user)
	{
		User u=userDaoimp.getLogin(user);
		return u;
	}
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public User regist(User user)
	{
		User u=userDaoimp.regist(user);
		return u;
	}
	/**
	 * 找回密码
	 * @param user
	 * @return
	 */
	public Message findpassword(User user)
	{
		Message msg=userDaoimp.findpassword(user);
		return msg;
	}
	/**
	 * 找回密码
	 * @param user
	 * @return
	 */
	public Message checkusername(String username)
	{
		Integer result=userDaoimp.checkusername(username);
		if(result>0)
		{
			return new Message("fail", "用户名已注册！", new Date());
		}else
		{
			return new Message("success", "可以使用", new Date());
		}
	}
	/**
	 * 登陆方法
	 * @param user
	 * @return
	 */
	public Integer getadminLogin(User user)
	{
		Integer u=userDaoimp.getadminLogin(user);
		return u;
	}
}
