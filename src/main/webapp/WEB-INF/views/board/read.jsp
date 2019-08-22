<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.PrintWriter"%>
<script src="/js/main/util.js" type="text/javascript"></script>

<link rel="stylesheet" href="/css/board/common.css">
<link rel="stylesheet" href="/css/board/default.css">
<link rel="stylesheet" href="/css/board/content.css">
<script src="https://code.jquery.com/jquery.js"></script>

<%
String userID = null;
	System.out.println(session.getAttribute("LOGIN_BELONG"));
	if (session.getAttribute("LOGIN_ID") != null) {
		userID = (String) session.getAttribute("LOGIN_ID");
	}
	if (userID == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인이 필요합니다.')");
		script.println("location.href = '/'");
		script.println("</script>");
	}
%>

<script>
	$(document)
			.ready(
					function() {
						$("input:radio[name='instrument']:radio[value=${boardVO.instrument}]").prop('checked', true);
					});
	
	
	
	
	
</script>

<!--Start Content-->
<div class="pop_wrap" id="popUpLayerId">
	<div class="pop_tit">
		<span>게시판 상세보기</span>
	</div>
	<div class="pop_content">
	<form method="post"><input type="hidden" name="bno" value="${boardVO.bno}">
		<table class="pop_table">
			<tbody>
			<colgroup>
				<col width="150px">
				<col width="*">
			</colgroup>
			<tbody>
				<tr><th>접수 번호</th>
					<td><input type="text" name="bno" class="form-control" value="${boardVO.bno}" readonly></td>
				
					<th>작성자</th>
					<td><input type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly></td>
				</tr>
				<tr>
					<th>담당소속</th>
					<td><input type="text" name="manager_belong" class="form-control"
						value="${boardVO.manager_belong}" style="width:117px;"  /></td>
				
					<th>작업분류</th>
					<td><input type="text" name="manager_classification" class="form-control"
						value="${boardVO.manager_classification}" style="width:117px;"  /></td>
				</tr>


				<tr>
					<th>소속</th>
					<td><input type="text" name="client_belong" class="form-control"
						value="${boardVO.client_belong}" style="width:117px;" /></td>
				
					<th>위치</th>
					<td><input type="text" name="client_local" class="form-control"
						value="${boardVO.client_local}" style="width:117px;" /></td>
				</tr>

				<tr>
					<th>이름</th>
					<td><input type="text" name="client_name" class="form-control"
						value="${boardVO.client_name}" style="width:117px;"  /></td>		
			
					<th >내선번호</th>
					<td><input type="text" name="client_number" class="form-control"
						value="${boardVO.client_number}" style="width:117px;"  /></td>
				</tr>

				<tr>
					<th>기기종류</th>
					<td colspan="3"><input type="radio" name="instrument" value="1">모니터
						<input type="radio" name="instrument" value="2">프린터 <input
						type="radio" name="instrument" value="3">PC <input
						type="radio" name="instrument" value="4">인터넷 <input
						type="radio" name="instrument" value="5">전화기 <input
						type="radio" name="instrument" value="6">프로젝터 빔 <input
						type="radio" name="instrument" value="7">앰프 
						<br>
						<input
						type="radio" name="instrument" value="8">소프트웨어 설치 <input
						type="radio" name="instrument" value="9">기타</td>

				</tr>
							
				<tr>
					<th>내용</th>
					<td colspan="3"><textarea name="content" class="form-control">${boardVO.content}</textarea></td>
				</tr>
				
				
				<c:if test="${boardVO.progress ne '처리대기'}">
					<tr>
						<th>처리자</th>
						<c:if test="${boardVO.progress eq '처리중'}">
							<td colspan="3"><input type="text" name="processId" class="form-control"
								value="${boardVO.completeId}" readonly /></td>
						</c:if>
						<c:if test="${boardVO.progress eq '처리완료'}">
							<td colspan="3"><input type="text" name="processId" class="form-control"
								value="${boardVO.completeId}" readonly /></td>
						</c:if>
					</tr>
				</c:if>
				
			</tbody>
		</table>
		
		<div class="button_rtbox">
			<c:if test="${boardVO.progress eq '처리대기' }">
				<c:if test="${sessionScope.LOGIN_NAME eq boardVO.writer || sessionScope.LOGIN_BELONG eq '관리자' }">
						<span class="button bt01"><button type="submit" class="button" onclick="javascript: form.action='/board/modify';">수정하기</button></span>
						<span class="button bt01"><button type="submit" class="button" onclick="javascript: form.action='/board/delete';">삭제하기</button></span>
				</c:if>
				<span class="button bt01"><button type="submit" class="bt01" onclick="javascript: form.action='/board/updateIng';">처리하기</button></span>
				<span class="button bt02"><button class="bt01"	onclick="window.close()">닫기</button></span>
		
			</c:if>
		</div>
		
		<div class="button_rtbox">
		
			<c:set var="data" value="boardVO.progress" />
				<c:choose>
					<c:when test="${boardVO.progress eq '처리중' && (sessionScope.LOGIN_NAME eq boardVO.completeId|| sessionScope.LOGIN_NAME eq boardVO.writer || sessionScope.LOGIN_BELONG eq '관리자')}">
							<span class="button bt01"><button type="submit" class="button" onclick="javascript: form.action='/board/modify';">수정하기</button></span>
							<span class="button bt01"><button type="submit" class="button" onclick="javascript: form.action='/board/delete';">삭제하기</button></span> 
							<span class="button bt01"><button type="submit" class="bt01" onclick="javascript: form.action='/board/updateEnd';">처리완료</button></span>
							<span class="button bt02"><button class="bt01"	onclick="window.close()">닫기</button></span>
					</c:when>
				
					<c:when test="${boardVO.progress eq '처리중' && (sessionScope.LOGIN_NAME ne boardVO.completeId|| sessionScope.LOGIN_NAME ne boardVO.writer || sessionScope.LOGIN_BELONG ne '관리자')}">
							<span class="button bt01"><button type="button" class="bt01" onclick="javascript: form.action='/board/updateIng';">처리중</button></span>
							<span class="button bt02"><button class="bt01"	onclick="window.close()">닫기</button></span>
					</c:when>
				
					<c:when test="${boardVO.progress eq '처리완료' && (sessionScope.LOGIN_NAME eq boardVO.completeId|| sessionScope.LOGIN_NAME eq boardVO.writer || sessionScope.LOGIN_BELONG eq '관리자')}">
						<span class="button bt01"><button type="submit" class="button" onclick="javascript: form.action='/board/modify';">수정하기</button></span>
						<span class="button bt01"><button type="submit" class="button" onclick="javascript: form.action='/board/delete';">삭제하기</button></span> 
						<span class="button bt02"><button class="bt01"	onclick="window.close()">닫기</button></span>
					</c:when>
				
					<c:when test="${boardVO.progress eq '처리완료' && (sessionScope.LOGIN_NAME ne boardVO.completeId|| sessionScope.LOGIN_NAME ne boardVO.writer || sessionScope.LOGIN_BELONG ne '관리자')}">
						<span class="button bt02"><button class="bt01"	onclick="window.close()">닫기</button></span>
					</c:when>
				
				</c:choose>
			
			</div>	
	</form>
</div>
	</div>






