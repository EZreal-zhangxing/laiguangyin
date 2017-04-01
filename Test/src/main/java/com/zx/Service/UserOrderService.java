package com.zx.Service;

import java.util.List;
import java.util.Map;

import com.zx.Dao.UserOrderDaoImp;
import com.zx.Pojo.Articel;
import com.zx.Pojo.Pageinfo;
import com.zx.Pojo.userOrder;

public class UserOrderService {
	private UserOrderDaoImp userOrderDaoimp;
	public int saveUserorder(userOrder userOrder)
	{
		return userOrderDaoimp.saveUserOrder(userOrder);
	}
	//判断用户是否满员
	public boolean iscanOrder(userOrder userOrder)
	{
		Integer orderNum=userOrderDaoimp.getNumofOrder(userOrder.getArticelId()+"");
		Articel al=userOrderDaoimp.getArticelbyid(userOrder.getArticelId()+"");
		if(al.getMaxMannum()>orderNum)
		{
			return true;
		}else
		{
			return false;
		}
	}
	/**
	 * 判断用户同一个时段是否预定
	 * @param userOrder
	 * @return true 预定
	 * false 未预定
	 */
	public boolean isuserOrder(userOrder userOrder)
	{
		Integer result=userOrderDaoimp.hasorder(userOrder);
		if(result != null)
		{
			if(result>0)
			{
				return true;
			}else
			{
				return false;
			}
		}else
		{
			//程序异常
			return true;
		}
	}
	/**
	 * 判断是否有用户预定
	 * @return
	 */
	public boolean ishasorder(userOrder userOrder)
	{
		Integer result=userOrderDaoimp.ishasorder(userOrder);
		if(result != null)
		{
			if(result>0)
			{
				return true;
			}else
			{
				return false;
			}
		}else
		{
			//程序异常
			return true;
		}
	}
	/**
	 * 根据用户ID查询对应的预约信息
	 * @param userid
	 * @return
	 */
	public List getuserOrderByid(String userid)
	{
		return userOrderDaoimp.getuserOrderByid(userid);
	}
	/**
	 * 取消预订
	 * @param orderid
	 * @return
	 */
	public boolean cancleOrder(String orderid)
	{
		return userOrderDaoimp.cancleOrder(orderid);
	}
	
	public List getuserOrderbyArtid(Pageinfo pi)
	{
		return userOrderDaoimp.getuserOrderbyArtid(pi);
	}
	
	public Integer getNumuserOrderbyArtid(Integer articelId)
	{
		return userOrderDaoimp.getNumuserOrderbyArtid(articelId);
	}
	public UserOrderDaoImp getUserOrderDaoimp() {
		return userOrderDaoimp;
	}

	public void setUserOrderDaoimp(UserOrderDaoImp userOrderDaoimp) {
		this.userOrderDaoimp = userOrderDaoimp;
	}
	
}
