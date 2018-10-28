package cn.buy.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.buy.dao.ProductCategoryDao;
import cn.buy.dao.comm.BaseDao;
import cn.buy.entity.ProductCategory;

//商品类型
public class ProductCategoryDaoImpl extends BaseDao implements ProductCategoryDao{
	
	public ProductCategoryDaoImpl(Connection conn){
		super(conn);
	} 
	
	//查询所有的商品分类
	public List<ProductCategory> queryAllProductCategory(String parentId) {
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		StringBuilder sql = new StringBuilder();
		sql.append("select id,name,parentId,type,iconClass from easybuy_product_category where 1=1 ");
		
		List<Object> params = new ArrayList<Object>();//存放参数
		if(null != parentId && !"".equals(parentId)){
			sql.append(" and parentId =? ");
			params.add(parentId);
		}
		ResultSet rs = super.executeQuery(sql.toString(), params.toArray());
		try {
			while(rs.next()){
				ProductCategory pro = tableToClass(rs);
				list.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			super.closeResource(rs);
			super.closeResource();
		}
		return list;
	}
	//表到对象
	public ProductCategory tableToClass(ResultSet rs) throws SQLException{
		ProductCategory pro = new ProductCategory();
		pro.setId(rs.getInt("id"));
		pro.setName(rs.getString("name"));
		pro.setParentId(rs.getInt("parentid"));
		pro.setType(rs.getInt("type"));
		pro.setIconClass(rs.getString("iconClass"));
		return pro;
	}
	
	
}
