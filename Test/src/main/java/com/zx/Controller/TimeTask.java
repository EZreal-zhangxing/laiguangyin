package com.zx.Controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zx.Dao.UserOrderDaoImp;

@Component
public class TimeTask {
	/**
	 * 过点更新 用户订单信息
	 */
	@Scheduled(cron="1 0 10 * * ?")
	public void passoutUserorder()
	{
		userOrderDaoimp.passoutUserorder("1");
	}
	@Scheduled(cron="1 0 11 * * ?")
	public void passoutUserorder1()
	{
		userOrderDaoimp.passoutUserorder("2");
	}
	@Scheduled(cron="1 0 14 * * ?")
	public void passoutUserorder2()
	{
		userOrderDaoimp.passoutUserorder("3");
	}
	@Scheduled(cron="1 0 15 * * ?")
	public void passoutUserorder3()
	{
		userOrderDaoimp.passoutUserorder("4");
	}
	@Scheduled(cron="1 0 16 * * ?")
	public void passoutUserorder4()
	{
		userOrderDaoimp.passoutUserorder("5");
	}
	@Scheduled(cron="1 0 17 * * ?")
	public void passoutUserorder5()
	{
		userOrderDaoimp.passoutUserorder("6");
	}
	
	/**
	 * 每天更新用户订单过期信息
	 */
	@Scheduled(cron="1 0 0 * * ?")
	public void passoutUserorderEveryday()
	{
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);   
        int month=c.get(Calendar.MONTH)+1;   
        int date=c.get(Calendar.DATE);
        String yesterday=year+"-"+month+"-"+(date-1);
        userOrderDaoimp.passoutUserordereveryday(yesterday);
	}
	private UserOrderDaoImp userOrderDaoimp;
	public UserOrderDaoImp getUserOrderDaoimp() {
		return userOrderDaoimp;
	}

	public void setUserOrderDaoimp(UserOrderDaoImp userOrderDaoimp) {
		this.userOrderDaoimp = userOrderDaoimp;
	}
}
