package cn.buy.dao;

import java.sql.SQLException;

import cn.buy.entity.User;

//用户
public interface UserDao {
	//查询用户  登录
	public User getUserByLoginName(String loginName)throws SQLException;
	//注册
	public int save(User user)throws SQLException;
	
}
