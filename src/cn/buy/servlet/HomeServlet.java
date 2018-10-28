package cn.buy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.buy.entity.News;
import cn.buy.entity.ProductCategory;
import cn.buy.service.NewsService;
import cn.buy.service.ProductCategoryService;
import cn.buy.service.impl.NewsServiceImpl;
import cn.buy.service.impl.ProductCategoryServiceImpl;
import cn.buy.web.servlet.AbstractBaseServlet;

/*
 * servlet3.0
 * 	IDE：MyEclipse 10+
 * 	JDK:JDK 1.6+
 * 	Tomcat:tomcat7+
 * 
 */
//控制层
//@SuppressWarnings("serial")
//@WebServlet(urlPatterns={"/Home"},name="Home")
public class HomeServlet extends AbstractBaseServlet{
	private ProductCategoryService productCategoryService;
	private NewsService newsService;
	@SuppressWarnings("rawtypes")
	@Override
	public Class getServletClass() {
		return HomeServlet.class;
	}
	public void init() throws ServletException {
		productCategoryService = new ProductCategoryServiceImpl();
		newsService = new NewsServiceImpl();
	}
	
	public  String index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ProductCategory> list = productCategoryService.queryAllProductCategory("0");
		List<News> newsList = newsService.queryAllNews();
		request.setAttribute("pcList", list);
		request.setAttribute("newsList", newsList);
		
		return "/pre/index";
	}


	
}
