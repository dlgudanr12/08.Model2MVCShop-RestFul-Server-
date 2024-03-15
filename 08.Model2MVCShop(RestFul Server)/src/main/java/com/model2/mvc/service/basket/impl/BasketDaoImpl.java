package com.model2.mvc.service.basket.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketDao;
import com.model2.mvc.service.domain.Basket;

@Repository("basketDaoImpl")
public class BasketDaoImpl implements BasketDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	public BasketDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertBasket(Basket basket) throws Exception {
		sqlSession.insert("BasketMapper.addBasket", basket);
	}

	@Override
	public List<Basket> getBasketList(Search search, String userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("userId", userId);
		List<Basket> listBasket = new ArrayList<Basket>();
		List<Basket> baskets=sqlSession.selectList("BasketMapper.getBasketList", map);
		for (Basket basket : baskets) {
			basket.setBasketProd(sqlSession.selectOne("ProductMapper.getProduct", basket.getProdNo()));
			listBasket.add(basket);
		}
		return listBasket;
	}

	@Override
	public int getTotalCount(String userId) throws Exception {
		return sqlSession.selectOne("BasketMapper.getTotalCount", userId);
	}

	@Override
	public void removeBasket(Basket basket) throws Exception {
		sqlSession.delete("BasketMapper.deleteBasket",basket);
	}

//	public void setSqlSession(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}

}
