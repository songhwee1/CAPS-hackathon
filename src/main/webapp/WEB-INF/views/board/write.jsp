<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.PrintWriter"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
		script.println("alert('로그인이 필요합니다.')");
		script.println("location.href = '/'");
		script.println("</script>");

	}

	if (!(session.getAttribute("LOGIN_BELONG").equals("내비게이션") || session.getAttribute("LOGIN_BELONG").equals("관리자")) ) {

		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('권한이 없습니다.')");
		script.println("location.href = '/'");
		script.println("</script>");
	}
%>

<div class="pop_wrap" id="popUpLayerId">
	<div class="pop_tit">
		<span>등록하기</span>
	</div>
	<div class="pop_content">
		<form action="/board/register" method="post">
			<table class="pop_table">
				<tbody>
				<colgroup>
					<col width="120px">
					<col width="*">
				</colgroup>



				<tr>
					<th>담당소속</th>
					<td><select name="manager_belong">
							<option value="IT기자재지원실" selected="selected">IT기자재지원실</option>
							<option value="PC지원실">PC지원실</option>
					</select></td>
					<th>작업분류</th>
					<td><select name="manager_classification">
							<option value="실습실" selected="selected">실습실</option>
							<option value="행정연구실">행정연구실</option>
							<option value="일반강의실">일반강의실</option>
					</select></td>
				</tr>

				<tr>
					<th>내선번호</th>
					<td><input type="text" placeholder="내선번호를 입력하세요." name="client_number" id="client_number" class="form-control" >
						<button type="button" id="numberBtn" class="button">조회</button></td>
					<th>의뢰자</th>
					<td><input type="text" placeholder="의뢰인 이름을 입력하세요." name="client_name"	id="client_name" class="form-control"></td>
					
				</tr>
				
				<tr>
					<th>소속</th>
					<td><input type="text" placeholder="소속을 입력하세요." name="client_belong" id="client_belong" class="form-control"/></td>
					<th>위치</th>
					<td><input type="text" placeholder="위치를 입력하세요." name="client_local" id="client_local" class="form-control"></td>
				</tr>

				<tr>
					<th>기기종류</th>
					<td colspan="3"><input type="radio" name="instrument" value="1">모니터
						<input type="radio" name="instrument" value="2">프린터 <input
						type="radio" name="instrument" value="3">PC <input
						type="radio" name="instrument" value="4">인터넷 <input
						type="radio" name="instrument" value="5">전화기 <input
						type="radio" name="instrument" value="6">프로젝터 빔
						<input type="radio" name="instrument" value="7">앰프 
						<br> <input type="radio" name="instrument" value="8">소프트웨어 설치
						<input type="radio" name="instrument" value="9">기타</td>
				</tr>

				<tr>
					<th>의뢰내용</th>
					<td colspan="3"><textarea name="content" class="form-control" type="text">요구사항: <%="\n"%> <%="\n"%>처리사항: </textarea></td>
				</tr>
				</tbody>
			</table>
			<div class="button_rtbox">
				<span class="button bt01"><button type="submit" id="submitBtn" class="bt01">등록</button></span>
				<span class="button bt02"><button class="bt01"
						onclick="window.close()">닫기</button></span>
			</div>
		</form>
	</div>
</div>

<!-- In Content End -->







<script>
	$(document).ready(function() {
						$("#numberBtn").click(function() {
							 if ($("#client_number").val() == "") {
												alert("전화번호를 입력해주세요.");
												$("#client_number").focus();
												return false;
											} 

											var pData = {};
											 jQuery("form#saveForm :input[type!='button']").each(function(e) {
											pData[$(this).prop('name')] = $(this).val().toString();
											});
											//alert($("#number").val().toString());
											pData['client_number'] = $("#client_number").val().toString();

											$.ajax({
														type : "post",
														url : '/board/information',
														data : pData,
														type : 'post',

														success : function(response,txtStatus, xhr) {

															$('#client_belong').val(response.client_belong);
															$('#client_local').val(response.client_local);
															$('#client_name').val(response.client_name);
														},

														error : function(request,status, error) {
															alert("code = "+ request.status + " message = " + request.responseText 
																	+ " error = "
																	+ error);
														}
													})

										});

						$("#submitBtn").click(function() {

											/* if ($("#client_number").val() == "") {
												alert("전화번호를 입력해주세요.");
												$("#client_number").focus();
												return false;
											} */

											if ($("#client_belong").val() == "") {
												alert("소속을 입력해주세요.");
												$("#client_belong").focus();
												return false;
											}

											if ($("#client_local").val() == "") {
												alert("위치를 입력해주세요.");
												$("#client_local").focus();
												return false;
											}

											if ($("#client_name").val() == "") {
												alert("이름을 입력해주세요.");
												$("#client_name").focus();
												return false;
											}

											if ($(':radio[name="instrument"]:checked').length < 1) {
												alert("고장난 기자재를 체크해주세요.");
												$("#instrument").focus();
												return false;
											}
											
											if (confirm('등록 하시겠습니까?') == true){ 
												}
											else{ return false; } ;
											

										});
					});
</script>

