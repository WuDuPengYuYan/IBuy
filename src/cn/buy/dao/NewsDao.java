package cn.buy.dao;

import java.util.List;

import cn.buy.entity.News;

public interface NewsDao {
	//到得所的新闻
	public List<News> queryAllNews() throws Exception;

}
