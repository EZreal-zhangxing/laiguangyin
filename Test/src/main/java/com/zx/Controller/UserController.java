package com.zx.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zx.Pojo.Articel;
import com.zx.Pojo.Message;
import com.zx.Pojo.User;
import com.zx.Service.EncoderHandler;
import com.zx.Service.UserService;

@RestController
public class UserController {
	private UserService userService;
	//登陆后 保存用户信息 至cookie 在预约的时候一并提交
	@RequestMapping("/login")
	public User login(@RequestParam(value="username") String username,@RequestParam(value="password") String password)
	{
		EncoderHandler eh=new EncoderHandler();
		String md5Password=eh.encodeByMD5(password);
		User user=new User();
		user.setUserName(username);
		user.setPassword(md5Password);
		User u=userService.getLogin(user);
		if(u!=null)
		{
			u.setCode("success");
		}
		return u;
	}
	
	@RequestMapping("/regist")
	public User regist(@RequestParam(value="username") String username,@RequestParam(value="password") String password)
	{
		EncoderHandler eh=new EncoderHandler();
		String md5Password=eh.encodeByMD5(password);
		User user=new User();
		user.setUserName(username);
		user.setPassword(md5Password);
		User u=userService.regist(user);
		if(u!=null)
		{
			u.setCode("success");
		}
		return u;
	}
	/**
	 * 找回密码
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/findpassword")
	public Message findpassword(@RequestParam(value="username") String username,@RequestParam(value="password") String password)
	{
		EncoderHandler eh=new EncoderHandler();
		String md5Password=eh.encodeByMD5(password);
		User user=new User();
		user.setUserName(username);
		user.setPassword(md5Password);
		Message msg=userService.findpassword(user);
		return msg;
	}
	
	@RequestMapping("/checkusername")
	public Message checkusername(@RequestParam(value="username") String username)
	{
		Message msg=userService.checkusername(username);
		return msg;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@RequestMapping("/adminlogin/{username}/{password}")
	public Message adminlogin(@PathVariable(value="username") String username,@PathVariable(value="password") String password,HttpServletRequest request)
	{
		EncoderHandler eh=new EncoderHandler();
		String md5Password=eh.encodeByMD5(password);
		User user=new User();
		user.setUserName(username);
		user.setPassword(md5Password);
		Integer u=userService.getadminLogin(user);
		if(u >0)
		{
			HttpSession session=request.getSession();
			session.setAttribute("login", "success");
			return new Message("success", "登陆成功!", new Date());
		}else
		{
			return new Message("fail", "登陆失败!", new Date());
		}
	}
	
}
