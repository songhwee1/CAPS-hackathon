<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/hdr.jsp"%><%-- 헤더 관련 --%>
<script type="text/javascript">
	function fncModify() {
		location.href = "/board/modify.do?bdIdx=${boardInfo.bdIdx}";	
	}
	
	function fncDelete() {
		if(!confirm("정말 삭제하시겠습니까?")) return false;
		
		var pData = {};
		pData['bdIdx'] = "${boardInfo.bdIdx}";
		
		jQuery.ajax({
			url: "/board/deleteBoard.inhatc",
			type : "post",
			dataType : "json",
			data : pData,
			success : function(response,txtStatus, xhr) {
				if(parseInt(response) > 0 ){
					alert("삭제가 완료되었습니다.");
					window.close();
				} else {
					alert("삭제 중 오류가 발생하였습니다.");
					return false;
				}
			}, error: function(request, status, error) {
				
			}
		});
	}
</script>

<div class="pop_wrap" id="popUpLayerId">
	<div class="pop_tit">
		<span>게시판 상세보기</span>
	</div>

	<div class="pop_content">
		<form name="saveForm" id="saveForm" method="post">
			<table class="pop_table">
				<tbody>
				<colgroup>
					<col width="150px">
					<col width="*">
				</colgroup>
				<tr>
					<th>문의유형</th>
					<td>
						<c:if test="${boardInfo.bdType == 'D'}">배송관련</c:if>
						<c:if test="${boardInfo.bdType == 'P'}">상품관련</c:if>
						<c:if test="${boardInfo.bdType == 'A'}">결재관련</c:if>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${boardInfo.bdTitle}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${boardInfo.bdContent}</td>
				</tr>
				</tbody>
			</table>
		</form>
		<div class="button_rtbox">
			<span class="button bt01"><button class="bt01" onclick="fncModify()">수정</button></span> 
			<span class="button bt01"><button class="bt01" onclick="fncDelete()">삭제</button></span>
			<span class="button bt02"><button class="bt01" onclick="window.close()">닫기</button></span>
		</div>
	</div>
</div>