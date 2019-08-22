<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.PrintWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>​
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/includes/home.jsp" flush="true" />
<jsp:include page="/WEB-INF/views/includes/header.jsp" flush="true" />

<link rel="stylesheet" href="/css/board/content.css">
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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>업무 보고서 출력</title>
<style>
table.type04 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
  margin : 20px 10px;
}
table.type04 th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
table.type04 td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/board/common.css">
<link rel="stylesheet" href="/css/board/default.css">
<link rel="stylesheet" href="/css/board/content.css">
<script type="text/javascript">

var goPrints = function(title){
 window.print();

   };
   </script>
<!-- 프린트를 위해 따로 나눠준 자바스크립트 파일 -->
</head>
<body>

	<form>
		<span style="margin-top:50px;" class="button bt02"><input type="button" value="출력하기" onclick="goPrints(title);" /></span>
	</form>

<div id="printarea">
<!-- 프린트 하기위한 영역 -->
<table width="*" cellspacing="1" border="0" class="type04" >
							<colgroup>
									<col width="10%"/>
									<col width="10%"/>
									<col width="15%"/>
									<col width="15%"/>
									<col width="*%"/>
							</colgroup>
							<thead>
								
								<tr>
										<th>접수번호</th>
										<th>작업분류</th>
										<th>기계명</th>
										<th>의뢰받은시간</th>
										<th>내용</th>
								</tr>
							</thead>
							<c:choose>
									<c:when test="${fn:length(boardList)-1 >= 0}">
										<c:forEach end="${fn:length(boardList)-1}" begin="0"
											varStatus="rowIndex">
							<tbody>
								<!-- Start: list_row -->
								<tr>
									<td>${boardList[rowIndex.index].bno}</td>
									<td>${boardList[rowIndex.index].manager_classification}</a></td>
									<td ><c:if test="${boardList[rowIndex.index].instrument == '1'}">모니터</c:if>
										<c:if test="${boardList[rowIndex.index].instrument == '2'}">프린터</c:if>
										<c:if test="${boardList[rowIndex.index].instrument == '3'}">PC</c:if>
										<c:if test="${boardList[rowIndex.index].instrument == '4'}">인터넷</c:if>
										<c:if test="${boardList[rowIndex.index].instrument == '5'}">전화기</c:if>
										<c:if test="${boardList[rowIndex.index].instrument == '6'}">프로젝터 빔</c:if>
										<c:if test="${boardList[rowIndex.index].instrument == '7'}">앰프</c:if>
										<c:if test="${boardList[rowIndex.index].instrument == '8'}">소프트웨어 설치</c:if>						
										<c:if test="${boardList[rowIndex.index].instrument == '9'}">기타</c:if>
										</td>
									
									<td >${boardList[rowIndex.index].regdate} </td>
									<td>${boardList[rowIndex.index].content}</td>
								</tr>
								</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="9">조회된 결과가 없습니다.</td>
										</tr>
									</c:otherwise>
								</c:choose>
								<!-- End: list_row -->
							</tbody>
						</table>
</div>


</body>
</html>





