package cn.buy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.buy.entity.User;
import cn.buy.service.UserService;
import cn.buy.service.impl.UserServiceImpl;
import cn.buy.utils.Constants;
import cn.buy.utils.EmptyUtils;
import cn.buy.utils.RegUtils;
import cn.buy.utils.ReturnResult;
import cn.buy.utils.SecurityUtils;
import cn.buy.web.servlet.AbstractBaseServlet;

//
//@SuppressWarnings("serial")
//@WebServlet(urlPatterns={"/Register"},name="Register")
public class RegisterServlet extends AbstractBaseServlet{
	private UserService userService;
	@SuppressWarnings("rawtypes")
	public Class getServletClass() {
		return RegisterServlet.class;
	}
	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}
	//
	public String toRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/pre/regist";
	}
	public ReturnResult doRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ReturnResult result = new ReturnResult();
		
		User user = new User();
		
		String loginName = request.getParameter("loginName");
		User oldUser = userService.getUserByLoginName(loginName);
		//判断
		if(EmptyUtils.isNotEmpty(oldUser)){
			result.returnFail("用户已存在！！！");
			return result;
		}
		//获取前台数据
		
		user.setLoginName(loginName);
		user.setUserName(request.getParameter("userName"));
		user.setPassword(SecurityUtils.md5Hex(request.getParameter("password")));
		String sex = request.getParameter("sex");
		user.setSex(sex.equals("1") ? 1 : 0);
		
		user.setEmail(request.getParameter("email"));
		user.setIdentityCode(request.getParameter("identityCode"));
		user.setMobile(request.getParameter("mobile"));
		user.setType(0);
		
		
		//后台校验
		result = checkUser(user);
		//是否通过后台校验
		if(result.getStatus() == Constants.ReturnResult.SUCCESS){
			if(!userService.save(user)){
				return result.returnFail("注册失败！！！");
			}else{
				return result.returnSuccess("注册成功！！！");
			}
		}
		return result;
	}
	//对用户进行后台校验
	public ReturnResult checkUser(User user){
		ReturnResult result = new ReturnResult();
		
		if(EmptyUtils.isNotEmpty(user.getMobile())){
			if(!RegUtils.checkMobile(user.getMobile())){
				return result.returnFail("手机格式不正确-后台！！！");
			}
		}
		if(EmptyUtils.isNotEmpty(user.getEmail())){
			if(!RegUtils.checkEmail(user.getEmail())){
				return result.returnFail("邮箱格式不正确-后台！！！");
			}
		}
		if(EmptyUtils.isNotEmpty(user.getIdentityCode())){
			if(!RegUtils.checkIdentityCode(user.getIdentityCode())){
				return result.returnFail("身份证格式不正确-后台！！！");
			}
		}
		return result.returnSuccess();
	}
	
}
