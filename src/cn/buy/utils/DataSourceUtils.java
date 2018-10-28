package cn.buy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//数据操作基类
public class DataSourceUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	static{
		init();
	}
	//初始化参数
	private static void init(){
		Properties pro = new Properties();
		InputStream is = DataSourceUtils.class.getClassLoader().getResourceAsStream("database.properties");
		try {
			pro.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		driver = pro.getProperty("driver");
		url = pro.getProperty("url");
		username = pro.getProperty("username");
		password = pro.getProperty("password");
	}
	
	//获取数据库连接对象
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭连接
	public static void closeResource(Connection con){
		try {
			if(con != null){
				con.close();//关闭
				con = null;//GC回收
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		System.out.println(getConn());
	}

}
