<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>​
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
	/* alert("ㅎㅎㅎ");
    var sw=screen.width;
    var sh=screen.height;
    var w=800;//팝업창 가로길이
    var h=600;//세로길이
    var xpos=(sw-w)/2; 
    var ypos=(sh-h)/2; 

    var pHeader="<html><head><link rel='stylesheet' type='text/css' href='/Exp_admin/css/print.css'><title>" + title + "</title></head><body>";
    var pgetContent=document.getElementById("printarea").innerHTML + "<br>";
    //innerHTML을 이용하여 Div로 묶어준 부분을 가져옵니다.
    var pFooter="</body></html>";
    pContent=pHeader + pgetContent + pFooter; 
     
    pWin=window.open("","print","width=" + w +",height="+ h +",top=" + ypos + ",left="+ xpos +",status=yes,scrollbars=yes"); //동적인 새창을 띄웁니다.
    pWin.document.open(); //팝업창 오픈
    pWin.document.write(pContent); //새롭게 만든 html소스를 씁니다.
    pWin.document.close(); //클로즈
    pWin.print(); //윈도우 인쇄 창 띄우고
    pWin.close(); //인쇄가 되던가 취소가 되면 팝업창을 닫습니다. */
   };
   </script>
<!-- 프린트를 위해 따로 나눠준 자바스크립트 파일 -->
</head>
<body>

	<form>
		<span class="button bt02"><input type="button" value="출력하기" onclick="goPrints(title);" /></span>
	</form>

<div id="printarea">
<!-- 프린트 하기위한 영역 -->
<table width="*" cellspacing="1" border="0" class="type04">
							<colgroup>
									<col width="10%"/>
									<col width="10%"/>
									<col width="15%"/>
									<col width="15%"/>
									<col width="15%"/>
									<col width="*%"/>
							</colgroup>
							<thead>
								
								<tr>
										<th>접수번호</th>
										<th>작업분류</th>
										<th>기계명</th>
										<th>의뢰인</th>
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
									<td>${boardList[rowIndex.index].client_name}</td>	
									
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





