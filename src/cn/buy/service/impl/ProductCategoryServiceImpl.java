package cn.buy.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.buy.dao.ProductCategoryDao;
import cn.buy.dao.impl.ProductCategoryDaoImpl;
import cn.buy.entity.ProductCategory;
import cn.buy.service.ProductCategoryService;
import cn.buy.utils.DataSourceUtils;

//商品类型的业务实现类
public class ProductCategoryServiceImpl implements ProductCategoryService{
	
	private Connection conn;
	private ProductCategoryDao dao;
	
	//查询所有的商品分类	
	public List<ProductCategory> queryAllProductCategory(String parentId) {
		
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		conn = DataSourceUtils.getConn();//connection
		dao = new ProductCategoryDaoImpl(conn);
		
		if(null == parentId && "".equals(parentId)){
			parentId = "0";
		}
		
		list = dao.queryAllProductCategory(parentId);
		
		DataSourceUtils.closeResource(conn);//close
		return list;
	}

}
