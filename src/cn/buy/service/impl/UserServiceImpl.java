package cn.buy.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import cn.buy.dao.UserDao;
import cn.buy.dao.impl.UserDaoImpl;
import cn.buy.entity.User;
import cn.buy.service.UserService;
import cn.buy.utils.DataSourceUtils;
//用户业务
public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	private Connection conn;
	
	@Override
	public User getUserByLoginName(String loginName) {
		conn = DataSourceUtils.getConn(); 
		userDao = new UserDaoImpl(conn);
		
		User user = null;
		try {
			user = userDao.getUserByLoginName(loginName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DataSourceUtils.closeResource(conn);
		return user;
	}

	@Override
	public boolean save(User user) {
		boolean flag = true;
		conn = DataSourceUtils.getConn(); 
		userDao = new UserDaoImpl(conn);
		
		int id = 0;
		try {
			id = userDao.save(user);
			flag = id > 0;
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}finally{
			DataSourceUtils.closeResource(conn);
		}
		return flag;
	}

}
