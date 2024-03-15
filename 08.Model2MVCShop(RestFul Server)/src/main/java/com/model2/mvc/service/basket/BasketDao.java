package com.model2.mvc.service.basket;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Basket;

public interface BasketDao {

	public void insertBasket(Basket basket) throws Exception;
	
	public List<Basket> getBasketList(Search search, String userId) throws Exception;
	
	public int getTotalCount(String userId) throws Exception;
	
	public void removeBasket(Basket basket) throws Exception;

}
