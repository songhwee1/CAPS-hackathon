<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900"
	rel="stylesheet">

<link rel="apple-touch-icon" href="apple-touch-icon.png">

<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../css/fontAwesome.css">
<link rel="stylesheet" href="../css/hero-slider.css">
<link rel="stylesheet" href="../css/owl-carousel.css">
<link rel="stylesheet" href="../css/templatemo-style.css">
<link rel="stylesheet" href="../css/lightbox.css">
<!-- main css, font, js -->


<jsp:include page="/WEB-INF/views/includes/home.jsp" flush="true" />


<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<div class="containers">
	<div class="row">
		<div class="text - center">
			<ul class="pagination">

				<c:if test="${pageMaker.prev}">
					<li><a href="?page = ${pageMaker.startPage -1}">&laquo;</a></li>
				</c:if>

				<c:forEach begin="${pageMaker.startPage }"
					end="${pageMaker.endPage }" var="idx">

					<li
						<c:out value="${pageMaker.cri.page == idx?'class =active':'' }"/>>
						<a href="?page = ${idx}">${idx}</a>
					</li>
				</c:forEach>

				<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
					<li><a href="?page = ${pageMaker.endPage+1 }">&requo</a></li>
				</c:if>

			</ul>
		</div>
	</div>
</div>
