package cn.buy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.buy.entity.User;
import cn.buy.service.UserService;
import cn.buy.service.impl.UserServiceImpl;
import cn.buy.utils.EmptyUtils;
import cn.buy.utils.ReturnResult;
import cn.buy.utils.SecurityUtils;
import cn.buy.web.servlet.AbstractBaseServlet;

//@SuppressWarnings("serial")
//@WebServlet(urlPatterns={"/Login"},name="Login")
public class LoginServlet extends AbstractBaseServlet{
	private UserService userService;
	
	@SuppressWarnings("rawtypes")
	public Class getServletClass() {
		return LoginServlet.class;
	}
	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}

	//用户登录
	public ReturnResult login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		System.out.println("----------------------------------->"+"    username:"+loginName+"---pwd:"+password);
		ReturnResult result = new ReturnResult();
		
		User user = userService.getUserByLoginName(loginName);
		
		if(EmptyUtils.isEmpty(user)){//如果为空
			result.returnFail("用户名不存在！");
		}else{
			if(user.getPassword().equals(SecurityUtils.md5Hex(password))){
				request.getSession().setAttribute("loginUser", user);
				result.returnSuccess("登录成功！！");
			}else{
				result.returnFail("用户密码不正确！");
			}
		}
		System.out.println(user+"=====result===>"+result);
		return result;
	}
	
	//用户注销
	public String loginOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//删除Session
		request.getSession().removeAttribute("loginUser");
		
		return "/pre/login";
	}
	//登录页面
	public String toLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/pre/login";
	}
	
	
}
