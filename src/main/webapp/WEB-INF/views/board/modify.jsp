<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.PrintWriter"%>

<link rel="stylesheet" href="/css/board/common.css">
<link rel="stylesheet" href="/css/board/default.css">
<link rel="stylesheet" href="/css/board/content.css">
<script src="http://code.jquery.com/jquery.js"></script>

<%
String userID = null;
	System.out.println(session.getAttribute("LOGIN_BELONG"));
	if (session.getAttribute("LOGIN_ID") != null) {
		userID = (String) session.getAttribute("LOGIN_ID");
	}
	if (userID == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요.')");
		script.println("location.href = '/loginHome'");
		script.println("</script>");
	}
%>

<script>
	$(document)
			.ready(
					function() {
						$("input:radio[name='instrument']:radio[value=${boardVO.instrument}]").prop('checked', true);
					});
	
	function fncModify() {
		 if('<%=session.getAttribute("LOGIN_ID")%>' == "null") {
				alert("로그인후 이용해주세요.");
				location.href='/loginHome';
				return false;
			} else {
				gfnOpenPop('/board/modify', '등록', '736', '500');
				return false;
			}
		
		return false;
	}
</script>

<!--Start Content-->
<div class="pop_wrap" id="popUpLayerId">
	<div class="pop_tit">
		<span>게시판 상세보기</span>
	</div>
	<div class="pop_content">
	<form action="/board/modifyAction" method="post">
		<table class="pop_table">
			<tbody>
			<colgroup>
				<col width="150px">
				<col width="*">
			</colgroup>
			<tbody>
				<tr>
					<th>담당소속</th>
					<td><input type="text" name="content" class="form-control"
						value="${boardVO.manager_belong}" style="width:117px;" /></td>
				
					<th>작업분류</th>
					<td><input type="text" name="content" class="form-control"
						value="${boardVO.manager_classification}" style="width:117px;" /></td>
				</tr>


				<tr>
					<th>소속</th>
					<td><input type="text" name="content" class="form-control"
						value="${boardVO.client_belong}" style="width:117px;"  /></td>
				
					<th>위치</th>
					<td><input type="text" name="content" class="form-control"
						value="${boardVO.client_local}" style="width:117px;"  /></td>
				</tr>

				<tr>
					<th>이름</th>
					<td><input type="text" name="content" class="form-control"
						value="${boardVO.client_name}" style="width:117px;"  /></td>		
			
					<th >내선번호</th>
					<td><input type="text" name="content" class="form-control"
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
						<input type="radio" name="instrument" value="8">소프트웨어 설치 <input
						type="radio" name="instrument" value="9">기타</td>

				</tr>
				
				<tr>
					<th>작성자</th>
					<td colspan="3"><input type="text" name="writer" class="form-control" value="${boardVO.writer}" style="width:117px;" readonly></td>
				</tr>
			
				<tr>
					<th>내용</th>
					<td colspan="3"><textarea name="content" class="form-control" >${boardVO.content}</textarea></td>
				</tr>
			</tbody>
		</table>
	</form>	
				
	<span class="button bt01"><button type="submit" class="bt01">확인</button></span>
	<span class="button bt02"><button class="bt01" onclick="window.close()">닫기</button></span>
	</div>
</div>
 --%>