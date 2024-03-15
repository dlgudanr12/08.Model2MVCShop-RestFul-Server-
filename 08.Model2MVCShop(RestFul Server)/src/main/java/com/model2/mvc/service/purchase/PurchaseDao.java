package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {

	public Purchase findPurchase(int tranNo) throws Exception;

	public void insertPurchase(Purchase purchase) throws Exception;

	public void updatePurchase(Purchase purchase) throws Exception;

	public void updateTranCode(Purchase purchase) throws Exception;

	public List<Purchase> getPurchaseList(Search search, String buyer_Id) throws Exception;

	public int getTotalCount(String buyer_Id) throws Exception;

	public Map<String, Object> getSaleList(Search search);

	public List<Purchase> getDeliveryList(Search search) throws Exception;

	public int getTotalCountOfDeliveryList(Search search) throws Exception;
}
