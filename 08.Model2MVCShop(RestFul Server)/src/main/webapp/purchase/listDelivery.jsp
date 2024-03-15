<%@ page contentType="text/html; charset=euc-kr" pageEncoding="euc-kr"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta charset="euc-kr" />
<title>배송 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
		document.detailForm.submit();
	}
</script>

</head>

<body bgcolor="#ffffff" text="#000000">


	<div style="width: 98%; margin-left: 10px;">

		<form name="detailForm" action="/purchase/listDelivery"
			method="post">

			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37"></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">배송 관리</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37"></td>
				</tr>
			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>

					<td align="right">
						<select
							name="searchOrderBy" class="ct_input_g" style="width: 100px">
								<option value="0" ${search.searchOrderBy=='0' ? "selected" : "" }>도착 날짜 순</option>
								<option value="1-1" ${search.searchOrderBy=='1-1' ? "selected" : "" }>사용자ID 순</option>
								<option value="1-2" ${search.searchOrderBy=='1-2'? "selected" : "" }>배송상태 순</option>
						</select>
						<select name="searchCondition" class="ct_input_g"
						style="width: 80px">
							<option value="0"
								${search.searchCondition=='0' ? "selected" : "" }>사용자ID </option>
							<option value="1"
								${search.searchCondition=='1' ? "selected" : "" }>수령인</option>
							<option value="2"
								${search.searchCondition=='2' ? "selected" : "" }>상품번호</option>
						</select> <input type="text" name="searchKeyword"
						value="${search.searchKeyword}" class="ct_input_g"
						style="width: 120px; height: 19px">
					</td>

					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetList('1');">검색</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>

			<table>
				<tr>
					<td>
							판매 중<input type="checkbox" name="searchTranCodeOn0" value="0"
							${! empty search.searchTranCodeOn[0]&&search.searchTranCodeOn[0]==0 ? "checked" : "" } /> 
							구매 완료<input type="checkbox" name="searchTranCodeOn1" value="1"
							${! empty search.searchTranCodeOn[1]&&search.searchTranCodeOn[1]==1 ? "checked" : "" } />
							배송 중<input type="checkbox" name="searchTranCodeOn2" value="2"
							${! empty search.searchTranCodeOn[2]&&search.searchTranCodeOn[2]==2 ? "checked" : "" } />
							배송 완료<input type="checkbox" name="searchTranCodeOn3" value="3"
							${! empty search.searchTranCodeOn[3]&&search.searchTranCodeOn[3]==3 ? "checked" : "" } />
					</td>
				</tr>
			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="17">전체 ${resultPage.totalCount} 건수, 현재
						${resultPage.currentPage } 페이지</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="30">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="80">배송번호</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" >사용자ID</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" >수령인</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" >요청일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">배송 도착 일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">현재상태</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">배송 수량</td>
					<td class="ct_line02"></td>
				</tr>
				<tr>
					<td colspan="17" bgcolor="808285" height="1"></td>
				</tr>
				<c:set var="i" value="0" />
				<c:forEach var="purchase" items="${list}">
					<c:set var="i" value="${i+1 }" />
					<tr class="ct_list_pop">
						<td align="center">${i }</td>
						<td></td>
						<td align="left"><a
							href="/purchase/getPurchase/${purchase.tranNo }/manage">
							${purchase.tranNo }</a></td>
						<td></td>
						<td align="left">${purchase.buyer.userId }</td>
						<td></td>
						<td align="left">${purchase.receiverName }</td>
						<td></td>
						<td align="left"><a
							href="/product/getProduct/${purchase.purchaseProd.prodNo}/search">${purchase.purchaseProd.prodName }</a>
						</td>
						<td></td>
						<td align="left">${purchase.orderDate }</td>
						<td></td>
						<td align="left">${purchase.divyDate }</td>
						<td></td>
						<td align="left">
								<c:if test="${purchase.tranCode!=null}">
									<c:if test="${purchase.tranCode=='1' }">구매완료
										<a
											href="/purchase/updateTranCode/${purchase.tranNo }/${purchase.tranCode }">배송하기</a>
									</c:if>
									<c:if test="${purchase.tranCode=='2' }">배송 중</c:if>
									<c:if test="${purchase.tranCode=='3' }">배송 완료</c:if>
								</c:if>
							</td>
						<td></td>
							<td align="left">${purchase.tranQuantity} 개</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="17" bgcolor="D6D7D6" height="1"></td>
					</tr>
				</c:forEach>
			</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center"><input type="hidden" id="currentPage"
						name="currentPage" value="" />
						
						<jsp:include page="../common/pageNavigatorDefault.jsp" />

					</td>
				</tr>
			</table>
			<!--  페이지 Navigator 끝 -->
		</form>
	</div>

</body>
</html>



