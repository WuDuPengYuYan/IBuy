package cn.buy.service;

import cn.buy.entity.User;

public interface UserService {
	//查询用户  登录
	public User getUserByLoginName(String loginName);
	//注册
	public boolean save(User user);
		
}
