package cn.buy.service;

import java.util.List;

import cn.buy.entity.ProductCategory;
//业务层
public interface ProductCategoryService {
	//查询所有的商品分类
	public List<ProductCategory> queryAllProductCategory(String parentId);

}
