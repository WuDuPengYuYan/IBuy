package cn.buy.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.buy.dao.NewsDao;
import cn.buy.dao.comm.BaseDao;
import cn.buy.entity.News;

public class NewsDaoImpl extends BaseDao implements NewsDao{

	public NewsDaoImpl(Connection conn) {
		super(conn);
	}
	
	//字段到对象过程 
	public News tableToClass(ResultSet rs) throws Exception {
		News news = new News();
		news.setId(rs.getInt("id"));
		news.setTitle(rs.getString("title"));
		news.setContent(rs.getString("content"));
		news.setCreateTime(rs.getDate("createTime"));
		return news;
	}
	//到得所的新闻
	public List<News> queryAllNews() throws Exception {
		List<News> newsList = new ArrayList<News>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,title,content,createTime from easybuy_news ");
		sql.append(" limit 0,5");//暂时
		ResultSet rs = super.executeQuery(sql.toString());
		while(rs.next()){
			newsList.add(tableToClass(rs));
		}
		super.closeResource(rs);
		super.closeResource();
		return newsList;
	}

}
