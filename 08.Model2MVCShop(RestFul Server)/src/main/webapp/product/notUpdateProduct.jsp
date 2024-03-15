<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<p align="center">해당 상품은 수정 할 수 없습니다. 그래서 구매 상세 페이지로 넘어감</p>
<br />
<c:import var="getProductOfNotUpdateProduct" url="../product/getProduct.jsp"
	scope="request" />${getProductOfNotUpdateProduct}
