<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

function fncJoin() {
	 if('<%=session.getAttribute("LOGIN_ID")%>' == "null" ){
			alert("로그인이 필요합니다.");
			location.href='/';
			return false;
			
		} else if('<%=session.getAttribute("LOGIN_BELONG")%>' != "관리자") {
			alert("권한이 없습니다.");
			location.href='/board/error';
			return false;
						
		}else {
			gfnOpenPop('/joinAction', '등록', '800', '500');
			return false; 
		 } 
	
	return false;
}

function fncManager() {
	 if('<%=session.getAttribute("LOGIN_ID")%>' == "null") {
			alert("로그인이 필요합니다.");
			location.href='/';
			return false;
			
		} else if('<%=session.getAttribute("LOGIN_BELONG")%>' != "관리자") {
			alert("권한이 없습니다.");
			location.href='/board/error';
			return false;
						
		}else {
			gfnOpenPop('/managerAction', '등록', '800', '550');
			return false;
		}
	
	return false;
}

function fncLogin() {
		gfnOpenPop('/loginHome', '로그인', '550', '450');
		return false;
}

function fncPrint() {
	document.form.action='/board/print';
	document.form.submit();
}
</script>



		
<!--Start Header-->
<div id="screensaver">
	<canvas id="canvas"></canvas>
	<i class="fa fa-lock" id="screen_unlock"></i>
</div>
<header class="navbar">
	<div class="container-fluid expanded-panel">
		<div class="row">
			<div id="logo" class="col-xs-12	 col-sm-2">
				<a href="/">C A P S</a>
			</div>
			<div id="top-panel" class="col-xs-12 col-sm-10">
				<div class="row">
					<div class="col-xs-8 col-sm-4">
					<form action="/board/print" method="post" name="form">
						<a href="/chart"  class="dropdown-toggle" > <i class="fa fa-bar-chart-o" ></i> <span class="hidden-xs">Chart</span></a>
						
						 
						<c:if test="${sessionScope.LOGIN_BELONG eq '관리자'}">
							
					<i>&nbsp; | &nbsp;</i>
						<a href="#" onclick="fncManager();" class="dropdown-toggle" > <i class="fa fa-desktop" ></i> <span class="hidden-xs"> Manager</span></a>
					<i>&nbsp; | &nbsp;</i>
						<a href="#" type="submit" onclick="fncPrint();" class="dropdown-toggle" > <i class="fa fa-book" ></i><span class="hidden-xs">Print</span></a>
					<i>&nbsp; | &nbsp;</i>
						<a href="#" onclick="fncJoin();" class="dropdown-toggle" > <i  class="fa fa-table" ></i> <span class="hidden-xs"> Join</span></a>
						
						</c:if>
						
						
						</form>
					</div>
				
				
					<div class="col-xs-4 col-sm-8 top-panel-right">
						<ul class="nav navbar-nav pull-right panel-menu">
							<c:set var="NAME" value="${sessionScope.LOGIN_NAME}" />
							<c:set var="ID" value="${sessionScope.LOGIN_ID}" />
									<c:if test="${NAME ne null}">
										<span class="welcome">Welcome,</span>
										<span>${sessionScope.LOGIN_NAME}</span>
									</c:if>
										
									<c:if test="${ID ne null}">
										<li><a href="/logout"> 
										<i class="fa fa-power-off"></i> <span class="hidden-sm text">Logout | </span>
										
										</a></li>
									</c:if>
									
									<c:if test="${ID eq null}">
										<li><a href="#" onclick="fncLogin();">
										<i class="fa fa-power-off"></i> <span class="hidden-sm text">Login |</span>
										
										</a></li>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>
		<!--End Header-->








