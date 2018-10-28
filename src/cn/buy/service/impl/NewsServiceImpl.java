package cn.buy.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.buy.dao.NewsDao;
import cn.buy.dao.impl.NewsDaoImpl;
import cn.buy.entity.News;
import cn.buy.service.NewsService;
import cn.buy.utils.DataSourceUtils;

public class NewsServiceImpl implements NewsService{

	private Connection conn;
	private NewsDao newsDao;
	
	public List<News> queryAllNews() {
		List<News> newsList = new ArrayList<News>();
		conn = DataSourceUtils.getConn();
		newsDao = new NewsDaoImpl(conn);
		
		try {
			newsList = newsDao.queryAllNews();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DataSourceUtils.closeResource(conn);
		}
		return newsList;
	}

}
