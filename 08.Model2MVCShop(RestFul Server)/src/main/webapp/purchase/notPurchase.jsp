<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<p align="center">로그인을 해주셔야 구매를 하실 수 있습니다.</p>
<br />
<c:import var="loginViewOfNotPurchase" url="../user/loginView.jsp"
	scope="request" />${loginViewOfNotPurchase}
