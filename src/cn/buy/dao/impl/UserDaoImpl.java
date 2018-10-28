package cn.buy.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.buy.dao.UserDao;
import cn.buy.dao.comm.BaseDao;
import cn.buy.entity.User;
import cn.buy.utils.DataSourceUtils;
import cn.buy.utils.EmptyUtils;

public class UserDaoImpl extends BaseDao implements UserDao{

	public UserDaoImpl(Connection conn) {
		super(conn);
	}

	public User getUserByLoginName(String loginName) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from easybuy_user where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!EmptyUtils.isEmpty(loginName)){
			sql.append(" and loginName=?");
			params.add(loginName);
		}
		ResultSet rs = super.executeQuery(sql.toString(), params.toArray());
		User user = null;
		while(rs.next()){
			user = tableToClass(rs);
		}
		return user;
	}
	
	private User tableToClass(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setEmail(rs.getString("email"));
		user.setIdentityCode(rs.getString("identityCode"));
		user.setLoginName(rs.getString("loginName"));
		user.setMobile(rs.getString("mobile"));
		user.setPassword(rs.getString("password"));
		user.setSex(rs.getInt("sex"));
		user.setType(rs.getInt("type"));
		user.setUserName(rs.getString("userName"));
		return user;
	}
	
	public int save(User user) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into easybuy_user(loginName,userName,password,sex,email,identityCode,mobile,type) values(?,?,?,?,?,?,?,?)");
		Object[] params = {user.getLoginName(),user.getUserName(),user.getPassword(),user.getSex(),user.getEmail(),user.getIdentityCode(),user.getMobile(),user.getType()};
		return super.executeInsert(sql.toString(), params);
	}
	
	
	public static void main(String[] args) throws SQLException {
		UserDaoImpl dao = new UserDaoImpl(DataSourceUtils.getConn());
		User user = new User();
		user.setUserName("1111");
		user.setLoginName("1222");
		user.setPassword("333");
		user.setIdentityCode("3333");
		user.setEmail("sfdsf");
		user.setSex(1);
		user.setMobile("33333");
		int index = dao.save(user);
		System.out.println(index);
	}
}
