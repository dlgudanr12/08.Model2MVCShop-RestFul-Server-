package com.model2.mvc.common;

import java.util.List;

//==>리스트화면을 모델링(추상화/캡슐화)한 Bean 
public class Search {
	
	///Field
	private int currentPage;
	private String searchCondition;
	private String searchKeyword;
	private int pageSize;
	//==> 리스트화면 currentPage에 해당하는 회원정보를 ROWNUM 사용 SELECT 위해 추가된 Field 
	//==> UserMapper.xml 의 
	//==> <select  id="getUserList"  parameterType="search"	resultMap="userSelectMap">
	//==> 참조
	private int endRowNum;
	private int startRowNum;
	
	private String searchOrderBy;
	private int searchPriceLowerLimit;
	private int searchPriceUpperLimit;
	private List<String> searchTranCodeOn;
	private List<Integer> listTranCode;
	
	///Constructor
	public Search() {
	}
	
	///Method
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int paseSize) {
		this.pageSize = paseSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	//==> Select Query 시 ROWNUM 마지막 값 
	public int getEndRowNum() {
		return getCurrentPage()*getPageSize();
	}
	//==> Select Query 시 ROWNUM 시작 값
	public int getStartRowNum() {
		return (getCurrentPage()-1)*getPageSize()+1;
	}

	public String getSearchOrderBy() {
		return searchOrderBy;
	}

	public void setSearchOrderBy(String searchOrderBy) {
		this.searchOrderBy = searchOrderBy;
	}

	public int getSearchPriceLowerLimit() {
		return searchPriceLowerLimit;
	}

	public void setSearchPriceLowerLimit(int searchPriceLowerLimit) {
		this.searchPriceLowerLimit = searchPriceLowerLimit;
	}

	public int getSearchPriceUpperLimit() {
		return searchPriceUpperLimit;
	}

	public void setSearchPriceUpperLimit(int searchPriceUpperLimit) {
		this.searchPriceUpperLimit = searchPriceUpperLimit;
	}

	public List<String> getSearchTranCodeOn() {
		return searchTranCodeOn;
	}

	public void setSearchTranCodeOn(List<String> searchTranCodeOn) {
		this.searchTranCodeOn = searchTranCodeOn;
	}

	public List<Integer> getListTranCode() {
		return listTranCode;
	}

	public void setListTranCode(List<Integer> listTranCode) {
		this.listTranCode = listTranCode;
	}

	@Override
	public String toString() {
		return "Search [currentPage=" + currentPage + ", searchCondition="
				+ searchCondition + ", searchKeyword=" + searchKeyword
				+ ", pageSize=" + pageSize + ", endRowNum=" + endRowNum
				+ ", startRowNum=" + startRowNum + "]";
	}
}