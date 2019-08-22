<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.io.PrintWriter"%>

<link rel="stylesheet" href="/css/board/common.css">
<link rel="stylesheet" href="/css/board/default.css">
<link rel="stylesheet" href="/css/board/content.css">
<script src="https://code.jquery.com/jquery.js"></script>
<jsp:include page="/WEB-INF/views/includes/home.jsp" flush="true" />

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

	if (!(session.getAttribute("LOGIN_BELONG").equals("관리자"))) {

		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('권한이 없습니다.')");
		script.println("location.href = '/'");
		script.println("</script>");
	}
%>

<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div style="overflow: scroll; width: 795px; height: 390px;">
				<div class="box-header">
					<div class="box-name"></div>
					<div class="box-icons"></div>
					<div class="no-move"></div>
				</div>
				<div class="box-content no-padding">

					<table
						class="table table-bordered table-striped table-hover table-heading table-datatable"
						id="datatable-1">
						<thead>
							<tr>
								<th>내선번호</th>
								<th>소속</th>
								<th>이름</th>
								<th>위치</th>
							</tr>
						</thead>
						<c:choose>
							<c:when test="${fn:length(informationList)-1 >= 0}">
								<c:forEach end="${fn:length(informationList)-1}" begin="0"
									varStatus="rowIndex">
									<tbody>
										<!-- Start: list_row -->
										<tr>
											<td>${informationList[rowIndex.index].client_number}</td>
											<td>${informationList[rowIndex.index].client_belong}</td>
											<td>${informationList[rowIndex.index].client_name}</td>
											<td>${informationList[rowIndex.index].client_local}</td>
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

			</div>

			<div class="button_rtbox">
				<span class="button bt01"><button class="bt01"
						onclick="window.history.back()">뒤로가기</button></span>
			</div>
		</div>
	</div>

</div>
