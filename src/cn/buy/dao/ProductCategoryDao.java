package cn.buy.dao;

import java.util.List;

import cn.buy.entity.ProductCategory;

//商品类别数据访问层接口
public interface ProductCategoryDao {
	//查询所有的商品分类
	public List<ProductCategory> queryAllProductCategory(String parentId);

}
