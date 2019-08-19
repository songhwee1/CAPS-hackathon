<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../include/hdr.jsp"%><%-- 헤더 관련 --%>
<%@ include file="../include/menu.jsp"%><%-- 헤더 관련 --%>
<script type="text/javascript">
function fncWrite(){
  if('<%=session.getAttribute("LOGIN_ID")%>' == "null") {
			alert("로그인후 이용해주세요.");
			gfnOpenPopPost('/member/login.do', '로그인', 'login', '600', '300');
			return false;
		} else {
			gfnOpenPop('/board/write.do', '게시판등록', '600', '500');
			return false;
		}
	}

	function fncView(idx) {
		gfnOpenPop('/board/view.do?bdIdx=' + idx, '상세보기', '600', '500');
		return false;
	}
</script>
<div class="wrap30 row">
	<div class="headInquiry">
		<form id="cndForm" name="cndForm" method="post"
			onsubmit="return false">
			<table summary="게시판 테이블">
				<colgroup>
					<col width="150px">
					<col width="*">
					<col width="150px">
					<col width="*">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">제목</th>
						<td class="sel3"><input type="text" name="bdTitle"
							id="bdTitle" style="width: 95%"></td>
						<th scope="row">작성자</th>
						<td class="sel3"><input type="text" name="bdRegiId"
							id="bdRegiId" style="width: 95%"></td>
					</tr>
				</tbody>
			</table>
		</form>
		<button type="button" onclick="fncSearch();">조회</button>
	</div>

	<div class="row">
		<p class="sub_contit">게시판 목록</p>
		<table class="data00">
			<tbody>
			<colgroup>
				<col width="15%">
				<col width="*">
				<col width="10%">
				<col width="10%">
				<col width="10%">
			</colgroup>
			</tbody>
			<tr>
				<th>문의유형</th>
				<th>제목</th>
				<th>조회수</th>
				<th>등록일</th>
				<th>등록자</th>
			</tr>
			<c:forEach end="${fn:length(boardList)-1}" begin="0"
				varStatus="rowIndex">
				<tr>
					<td><c:if test="${boardList[rowIndex.index].bdType == 'D'}">배송관련</c:if>
						<c:if test="${boardList[rowIndex.index].bdType == 'P'}">상품관련</c:if>
						<c:if test="${boardList[rowIndex.index].bdType == 'A'}">결재관련</c:if>
					</td>
					<td style="text-align: left; padding-left: 5px"><a href="#" onclick="fncView('${boardList[rowIndex.index].bdIdx}');">${boardList[rowIndex.index].bdTitle}</a></td>
					<td>${boardList[rowIndex.index].bdReadCnt}</td>
					<td>${boardList[rowIndex.index].bdRegiDttm}</td>
					<td>${boardList[rowIndex.index].bdRegiId}</td>

				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="button_rtbox">
		<span class="button bt01"><button class="bt01" type="button" onclick="fncWrite()">등록</button></span>
	</div>
</div>
<%@ include file="../include/ftr.jsp"%><%-- 푸터 관련 --%>
