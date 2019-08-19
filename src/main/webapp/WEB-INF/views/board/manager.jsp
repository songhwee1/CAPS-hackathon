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
		script.println("alert('로그인을 해주세요.')");
		script.println("location.href = '/'");
		script.println("</script>");

	}

	if (!(session.getAttribute("LOGIN_BELONG").equals("관리자")) ) {

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
		<form method="post"><input type="hidden" name="Client_number" value="${boardVO.client_number}">
			<table class="pop_table">
				<tbody>
				<colgroup>
					<col width="120px">
					<col width="*">
				</colgroup>

				<tr>
					<th>내선번호</th>
					<td><input type="text" placeholder="전화번호" name="client_number" id="client_number" class="form-control" onkeypress="if(event.keyCode == 13){ fncLogin(); return;}">
						<button type="button" id="numberBtn" class="button">조회</button></td>
					<th>이름</th>
					<td><input type="text" placeholder="이름" name="client_name"
						id="client_name" class="form-control"></td>
					
				</tr>
				
				<tr>
					<th>소속</th>
					<td><input type="text" placeholder="소속" name="client_belong"
						id="client_belong" class="form-control" /></td>
					<th>위치</th>
					<td><input type="text" placeholder="위치" name="client_local"
						id="client_local" class="form-control" ></td>
				</tr>
				</tbody>
			</table>
				
				<span class="button bt01"><button type="submit" id="submitBtn2" class="button" onclick="javascript: form.action='/managerInsert';" >추가하기</button></span>
				
				<span class="button bt01"><button type="submit" id="submitBtn1" class="button" onclick="javascript: form.action='/managerModify';">수정하기</button></span>
					
				<span class="button bt01"><button type="submit" class="button" id="submitBtn3" onclick="javascript: form.action='/managerDelete';">삭제하기</button></span>
			</form>
			
				
			<div class="button_rtbox">
			<form action="/showInformation" method="post">
				<span class="button bt02"><button type="submit" class="button">교내  내선번호 목록보기</button></span>
			</form>
			</div>	
		
		
		
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
											/* jQuery("form#saveForm :input[type!='button']").each(function(e) {
											pData[$(this).prop('name')] = $(this).val().toString();
											}); */
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
						

					    $("#submitBtn1").click(function() {
					    	if ($("#client_number").val() == "") {
								alert("전화번호를 입력해주세요.");
								$("#client_number").focus();
								return false;
							}
							if ($("#client_name").val() == "") {
									alert("이름을 입력해주세요.");
									$("#client_name").focus();
									return false;
							}
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

							

						});
					    
					    
					    $("#submitBtn2").click(function() {
					    	if ($("#client_number").val() == "") {
								alert("전화번호를 입력해주세요.");
								$("#client_number").focus();
								return false;
							}
							if ($("#client_name").val() == "") {
									alert("이름을 입력해주세요.");
									$("#client_name").focus();
									return false;
							}
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

							

						});
					    
					    
					    $("#submitBtn3").click(function() {
					    	if ($("#client_number").val() == "") {
								alert("전화번호를 입력해주세요.");
								$("#client_number").focus();
								return false;
							}
							if ($("#client_name").val() == "") {
									alert("이름을 입력해주세요.");
									$("#client_name").focus();
									return false;
							}
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

							

						});

					});
	
</script>

