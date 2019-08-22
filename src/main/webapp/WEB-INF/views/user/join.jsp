<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.io.PrintWriter"%> 

<link rel="stylesheet" href="/css/board/common.css">
<link rel="stylesheet" href="/css/board/default.css">
<link rel="stylesheet" href="/css/board/content.css">

<script src="http://code.jquery.com/jquery.js">
</script>

<%-- <script>
function ok(index){
	if(index == 1){
		 	if ( (<%=session.getAttribute("LOGIN_BELONG")%>) != ("관리자") ) {
				alert("권한이 없습니다.");
		 	} else {
		 		document.form.action='/userInsert';
		 		document.form.submit();
		 	}
		 	
	}else if(index == 2){
	 	if ( (<%=session.getAttribute("LOGIN_BELONG")%>) != ("관리자") ) {
			alert("권한이 없습니다.");
	 	} else {
	 		document.form.action='/userModify';
	 		document.form.submit();
	 	}
	 	
	}else if(index == 2){
	 	if ( (<%=session.getAttribute("LOGIN_BELONG")%>) != ("관리자") ) {
			alert("권한이 없습니다.");
	 	} else {
	 		document.form.action='/userDelete';
	 		document.form.submit();
	 	}	

}
	 

}
</script> --%>
 


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
		<span>회원 관리하기</span>
	</div>
	<div class="pop_content">
		<form method="post" name="form"><input type="hidden" name="Id" value="${UserVO.id}">
			<table class="pop_table">
				<tbody>
				<colgroup>
					<col width="120px">
					<col width="*">
				</colgroup>
				
				<tr>
					<th>아이디</th>
					<td  colspan="3"><input type="text" placeholder="아이디" name="id" id="id" class="form-control" /><button type="button" id="idBtn" class="button">조회</button></td>
				<tr>
					<th>비밀번호</th>
					<td  colspan="3"><input type="password" id="pw" name="pw" placeholder="비밀번호" class="form-control" ></td>
				</tr>
				
				
				<tr>
					<th>이름</th>
					<td  colspan="3"><input type="text" placeholder="이름" name="manager_name" id="manager_name" class="form-control" ></td>
				</tr>
				<tr>
					<th>담당소속</th>										
					<td colspan="3">
					<select name="manager_belong" id="manager_belong">
						<c:set var="data" value="${UserVO.manager_belong}" />
						<option value="" selected>선택해주세요.</option>
					    <option value="IT기자재지원실" <c:if test="${data eq 'IT기자재지원실'}">selected</c:if>>IT기자재지원실</option>
					    <option value="PC지원실1" <c:if test="${data eq 'PC지원실1'}">selected</c:if>>PC지원실1</option>
					    <option value="PC지원실2" <c:if test="${data eq 'PC지원실2'}">selected</c:if>>PC지원실2</option>
					    <option value="관리자" <c:if test="${data eq '관리자'}">selected</c:if>>관리자</option>
						<option value="내비게이션" <c:if test="${data eq '내비게이션'}">selected</c:if>>내비게이션</option>
					</select></td>

					</tr>
				</tbody>
			</table>
				<span class="button bt01"><button type="button" id="submitBtn1" class="button" onClick="javascript: form.action='/userInsert';" >등록하기</button></span>
				
				<span class="button bt01"><button type="submit" id="submitBtn2" class="button" onClick="javascript: form.action='/userModify';">수정하기</button></span>
					
				<span class="button bt01"><button type="submit" class="button" id="submitBtn3" onclick="javascript: form.action='/userDelete';">삭제하기</button></span>
			
			</form>
		
		<div class="button_rtbox">
		<form action="/showUser" method="post">
			<span class="button bt02"><button type="submit" class="bt01">회원 목록보기</button></span>
		</form>		
		</div>
			
		
	</div>
</div>
 <script>

$(document).ready(function(){

	$("input:radio[name='manager_belong']:radio[value=${userVO.manager_belong}]").prop('checked', true);
});
						$("#idBtn").click(function() {
							if ($("#id").val() == "") {
												alert("아이디를 입력해주세요.");
												$("#id").focus();
												return false;
											}
											var pData = {};
											/* jQuery("form#saveForm :input[type!='button']").each(function(e) {
											pData[$(this).prop('name')] = $(this).val().toString();
											}); */
											//alert($("#number").val().toString());
											pData['id'] = $("#id").val().toString();

											$.ajax({
														type : "post",
														url : '/user/list',
														data : pData,
														type : 'post',

														success : function(response,txtStatus, xhr) {
																$('#pw').val(response.pw);
																$('#manager_name').val(response.manager_name);
																$('#manager_belong').val(response.manager_belong);
														},

														error : function(request,status, error) {
															alert("code = "+ request.status + " message = " + request.responseText 
																	+ " error = "
																	+ error);
														}
													})

										});
						

					    $("#submitBtn1").click(function() {
					    	if ($("#id").val() == "") {
								alert("아이디를 입력해주세요.");
								$("#id").focus();
								return false;
							}
							if ($("#pw").val() == "") {
									alert("비밀번호를 입력해주세요.");
									$("#pw").focus();
									return false;
							}
							if ($("#manager_name").val() == "") {
								alert("이름을 입력해주세요.");
								$("#manager_name").focus();
								return false;
							}

							if ($("#manager_belong").val() == "") {
								alert("소속를 입력해주세요.");
								$("#manager_belong").focus();
								return false;
							}

							

						});
					    
					    
					    $("#submitBtn2").click(function() {
					    	if ($("#id").val() == "") {
								alert("아이디를 입력해주세요.");
								$("#id").focus();
								return false;
							}
							if ($("#pw").val() == "") {
									alert("비밀번호를 입력해주세요.");
									$("#pw").focus();
									return false;
							}
							if ($("#manager_name").val() == "") {
								alert("이름을 입력해주세요.");
								$("#manager_name").focus();
								return false;
							}

							if ($("#manager_belong").val() == "") {
								alert("소속를 입력해주세요.");
								$("#manager_belong").focus();
								return false;
							}

							

						});
					    
					    
					    $("#submitBtn3").click(function() {
					    	if ($("#id").val() == "") {
								alert("아이디를 입력해주세요.");
								$("#id").focus();
								return false;
							}
							if ($("#pw").val() == "") {
									alert("비밀번호를 입력해주세요.");
									$("#pw").focus();
									return false;
							}
							if ($("#manager_name").val() == "") {
								alert("이름을 입력해주세요.");
								$("#manager_name").focus();
								return false;
							}

							if ($("#manager_belong").val() == "") {
								alert("소속를 입력해주세요.");
								$("#manager_belong").focus();
								return false;
							}

							

						});
						


</script> 








