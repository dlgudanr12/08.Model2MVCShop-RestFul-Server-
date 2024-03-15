package com.model2.mvc.service.purchase.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	public PurchaseDaoImpl() {
		System.out.println(this.getClass());
	}

	public void insertPurchase(Purchase purchase) throws Exception {
		if (purchase.getDivyDate() == null || purchase.getDivyDate().equals("")) {
			LocalDate localDate = LocalDate.now();
			purchase.setDivyDate(localDate.plusDays(3).toString());
		}

		sqlSession.insert("PurchaseMapper.addPurchase", purchase);
	}

	public Purchase findPurchase(int tranNo) throws Exception {
		Purchase purchase = sqlSession.selectOne("PurchaseMapper.getPurchase", tranNo);
		purchase.setBuyer(sqlSession.selectOne("UserMapper.getUser", purchase.getBuyer().getUserId()));
		purchase.setPurchaseProd(
				sqlSession.selectOne("ProductMapper.getProduct", purchase.getPurchaseProd().getProdNo()));

		purchase.setPaymentOption(purchase.getPaymentOption().trim());
		purchase.setTranCode(purchase.getTranCode().trim());

		return purchase;
	}

	public void updatePurchase(Purchase purchase) throws Exception {
		if (purchase.getDivyDate() == null || purchase.getDivyDate().equals("")) {
			LocalDate localDate = LocalDate.now();
			purchase.setDivyDate(localDate.plusDays(3).toString());
		}
		
		sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	public void updateTranCode(Purchase purchase) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer tranCode = Integer.parseInt(purchase.getTranCode()) + 1;
		map.put("tranCode", tranCode);
		map.put("purchase", purchase);
		sqlSession.update("PurchaseMapper.updateTranCode", map);
	}

	public List<Purchase> getPurchaseList(Search search, String buyer_Id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("buyer_Id", buyer_Id);

		List<Purchase> list = sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
		List<Purchase> listPurchase = new ArrayList<Purchase>();
		for (Purchase purchase : list) {
			purchase.setPurchaseProd(sqlSession.selectOne("ProductMapper.getProduct",purchase.getPurchaseProd().getProdNo()));
			purchase.setPaymentOption(purchase.getPaymentOption().trim());
			purchase.setTranCode(purchase.getTranCode().trim());
			listPurchase.add(purchase);
		}

		return listPurchase;
	}

	public int getTotalCount(String buyer_Id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer_Id", buyer_Id);
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", map);
	}

	public Map<String, Object> getSaleList(Search search) {
		return null;
	}

	public List<Purchase> getDeliveryList(Search search) throws Exception {
		System.out.println("PurchaseDaoImpl.getDeliveryList.searchTranCodeOn : " + search.getSearchTranCodeOn());
		System.out.println("PurchaseDaoImpl.getDeliveryList.listTranCode : " + search.getListTranCode());

		List<Purchase> list = sqlSession.selectList("PurchaseMapper.getDeliveryList", search);
		List<Purchase> listPurchase = new ArrayList<Purchase>();
		for (Purchase purchase : list) {
			purchase.setPurchaseProd(sqlSession.selectOne("ProductMapper.getProduct",purchase.getPurchaseProd().getProdNo()));
			purchase.setPaymentOption(purchase.getPaymentOption().trim());
			purchase.setTranCode(purchase.getTranCode().trim());
			listPurchase.add(purchase);
		}

		return listPurchase;
	}

	public int getTotalCountOfDeliveryList(Search search) throws Exception {
		return sqlSession.selectOne("PurchaseMapper.getTotalCountOfDeliveryList", search);
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
