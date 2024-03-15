package com.model2.mvc.service.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;


@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	public ProductDaoImpl() {
		System.out.println(this.getClass());
	}

	public void insertProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProduct", product);
	}// end of insertProduct(ProductVO productVO) method

	public Product findProduct(int productNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct", productNo);
	}

	public List<Product> getProductList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}

	public void updateProduct(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProduct", product);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}

	public void updateQuantity(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateQuantity", product);
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}// end of class
