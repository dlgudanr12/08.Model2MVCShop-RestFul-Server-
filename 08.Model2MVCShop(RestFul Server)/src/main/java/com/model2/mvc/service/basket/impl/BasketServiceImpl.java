package com.model2.mvc.service.basket.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketDao;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;

@Service("basketServiceImpl")
public class BasketServiceImpl implements BasketService {

	@Autowired
	@Qualifier("basketDaoImpl")
	BasketDao basketDao;

	public BasketServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addBasket(Basket basket) throws Exception {
		basketDao.insertBasket(basket);
	}

	@Override
	public Map<String, Object> getBasketList(Search search, String userId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", basketDao.getBasketList(search, userId));
		map.put("totalCount", basketDao.getTotalCount(userId));
		return map;
	}

	@Override
	public void removeBasket(Basket basket) throws Exception {
		basketDao.removeBasket(basket);
		
	}

//	public void setBasketDao(BasketDao basketDao) {
//		this.basketDao = basketDao;
//	}

}
