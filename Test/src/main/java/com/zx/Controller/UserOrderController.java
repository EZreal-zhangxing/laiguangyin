package com.zx.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.Pojo.Articel;
import com.zx.Pojo.Message;
import com.zx.Pojo.Pageinfo;
import com.zx.Pojo.userOrder;
import com.zx.Service.UserOrderService;
import com.zx.Service.UserService;

@RestController
public class UserOrderController {
	private UserOrderService userOrderService;
	@RequestMapping("/order")
	public Message order(@RequestParam(value="name",required=true) String name
			,@RequestParam(value="userTel",required=true) String userTel
			,@RequestParam(value="userAddress",required=true) String userAddress
			,@RequestParam(value="comAddress",required=true) String comAddress
			,@RequestParam(value="timeArea",required=false) Integer timeArea
			,@RequestParam(value="articelId",required=true) Integer articelId
			,@RequestParam(value="modelId",required=true) Integer modelId
			,@RequestParam(value="userId",required=true) Integer userId)
	{
		userOrder uo=new userOrder();
		uo.setName(name);
		uo.setTimeArea(timeArea);
		uo.setArticelId(articelId);
		uo.setUserTel(userTel);
		uo.setUserAddress(userAddress);
		uo.setComAddress(comAddress);
		uo.setUserId(userId);
		String todayStr=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		uo.setOrderDate(todayStr);
		uo.setStatue(1);//设置为已预约
		boolean iscan=userOrderService.iscanOrder(uo);
		if(iscan)
		{
			boolean userresult=userOrderService.isuserOrder(uo);
			if(!userresult)
			{
				//是否由用户预定同一个时段( 约活动，约场地)
				if(modelId == 27 ||modelId == 29)
				{
					boolean orderresult=userOrderService.ishasorder(uo);
					if(orderresult)
					{
						return new Message("exist", "已被预定！", new Date());
					}else{
						//未预定
						userOrderService.saveUserorder(uo);
						//此处应该添加推送
						return new Message("success", "预定成功！", new Date());
					}
				}else
				{
					userOrderService.saveUserorder(uo);
					return new Message("success", "预定成功！", new Date());
				}
				
			}else
			{
				return new Message("false", "您已经预定！", new Date());
			}
		}else
		{
			return new Message("fail", "人数已达到上限，无法预订！", new Date());
		}
	}
	/**
	 * 根据用户ID获取订单
	 * @param userid
	 * @return
	 */
	@RequestMapping("/getUserOrder/{userid}")
	public List getUserOrder(@PathVariable(value="userid") String userid)
	{
		List map=userOrderService.getuserOrderByid(userid);
		return map;
	}
	/**
	 * 根据订单 用户ID 取消订单
	 * @param orderid
	 * @return
	 */
	@RequestMapping("/cancleOrder/{orderid}/{userid}")
	public Message cancleOrder(@PathVariable(value="orderid") String orderid,@PathVariable(value="userid",required=true) String userid)
	{
		boolean result=userOrderService.cancleOrder(orderid);
		if(result)
		{
			return new Message("success", "取消成功！", new Date());
		}else
		{
			return new Message("fail", "取消失败！", new Date());
		}
	}
	
	@RequestMapping("/getUserorder")
	public Pageinfo getuserOrder(@RequestParam(value="articelId") Integer articelId,@RequestParam(value="page",defaultValue="1") String page)
	{
		Integer num=userOrderService.getNumuserOrderbyArtid(articelId);
		Pageinfo pi=initpage(num, page);
		Articel ac=new Articel();
		ac.setId(articelId);
		pi.setCondarticel(ac);
		pi.setBegin((pi.getPagenum()-1)*pi.getShownum());
		pi.setEnd(pi.getBegin()+pi.getShownum());
		List list=userOrderService.getuserOrderbyArtid(pi);
		pi.setResult(list);
		return pi;
	}
	
	//页面初始化
	public Pageinfo initpage(Integer datanum,String page)
	{
		Pageinfo pageif=new Pageinfo();
		int pageshownum=10;//每页展示数
		pageif.setShownum(pageshownum);
		pageif.setFirstpage(1);
		Integer allpage=(datanum>0)?(((datanum)%pageshownum==0)?(datanum/pageshownum):(datanum/pageshownum)+1):1;
		pageif.setAllpage(allpage);
		pageif.setLastpage(allpage);
		Integer pagenum=0;
		
		if(page==null)
		{
			pageif.setPagenum(1);
		}else
		{
			pagenum=Integer.parseInt(page);
			pageif.setPagenum(pagenum);
		}
		pageif.setPrev((pageif.getPagenum()-1)>0?pageif.getPagenum()-1:1);
		pageif.setNext((pageif.getPagenum()+1)<allpage?pageif.getPagenum()+1:allpage);
		String pageStr="";
		if(pagenum-3<=1||allpage<=7)
		{
			for (int i = 1; i <= ((allpage>7)?7:allpage); i++) {
				if(i==((allpage>7)?7:allpage))
				{
					pageStr+=i;
				}else
				{
					pageStr+=i+",";
				}
			}
			if(allpage>7)
			{
				pageStr+=",...";
			}
		}else
		{
			pageStr+="...,";
			int num=((((pagenum+3>allpage)?allpage:pagenum)>3)?3:((pagenum+3>allpage)?allpage:pagenum));
			int cnum=(num+pagenum)>allpage?(allpage-pagenum):num;
			for (int i = cnum-7; i <=cnum; i++) {
				if(i==cnum)
				{
					if(cnum+pagenum==allpage)
					{
						pageStr+=(pagenum+i);
					}else
					{
						pageStr+=(pagenum+i)+",...";
					}
				}else
				{
					pageStr+=(pagenum+i)+",";
				}
			}
		}
		pageif.setPageStr(pageStr.split(","));
		return pageif;
	}
	public UserOrderService getUserOrderService() {
		return userOrderService;
	}
	public void setUserOrderService(UserOrderService userOrderService) {
		this.userOrderService = userOrderService;
	}
	
}
