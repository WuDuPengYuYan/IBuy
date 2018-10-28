package cn.buy.dao.comm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BaseDao(Connection conn){
		this.conn = conn;
	}
	//公共的查询 方法
	public ResultSet executeQuery(String sql,Object...params){
		try {
			//得到预处理SQL 对象
			pstmt = conn.prepareStatement(sql);
			//SQL赋值
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject((i+1), params[i]);
			}
		   rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	//
	public int executeUpdate(String sql,Object...params){
		int row = 0;
		try {
			//得到预处理SQL 对象
			pstmt = conn.prepareStatement(sql);
			//SQL赋值
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject((i+1), params[i]);
			}
			row = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	public int executeInsert(String sql,Object...params){
		int id = 0;
		try {
			//得到预处理SQL 对象
			pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//SQL赋值
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject((i+1), params[i]);
			}
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	//关闭资源
	public boolean closeResource(ResultSet rs){
		try {
			if(null != rs){
				rs.close();//关闭
				rs = null;//GC回收
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean closeResource(){
		try {
			if(null != pstmt){
				pstmt.close();//关闭
				pstmt = null;//GC回收
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
}

