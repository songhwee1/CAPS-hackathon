<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--Start Container-->
<div id="main" class="container-fluid">
	<div class="row">
		<div id="sidebar-left" class="col-xs-2 col-sm-2">
			<ul class="nav main-menu">
			<c:set var="belong"
			value="${sessionScope.LOGIN_BELONG}" />
		<c:if test="${belong eq 'IT기자재지원실'}">
		<li>
					<a href="/board" class="active ajax-link">
						<i class="fa fa-dashboard"></i>
						<span class="hidden-xs">IT Equipment</span>
					</a>
				</li>
		</c:if>
		<c:if test="${belong eq 'PC지원실1'}">
			<li>
					<a href="/board" class="active ajax-link">
						<i class="fa fa-dashboard"></i>
						<span class="hidden-xs">PC Support1</span>
					</a>
				</li>
		</c:if>
		<c:if test="${belong eq 'PC지원실2'}">
			<li>
					<a href="/board" class="active ajax-link">
						<i class="fa fa-dashboard"></i>
						<span class="hidden-xs">PC Support2</span>
					</a>
				</li>
		</c:if>
		<c:if test="${belong eq '네비게이션'}">
			<li>
					<a href="/board" class="active ajax-link">
						<i class="fa fa-dashboard"></i>
						<span class="hidden-xs">Navigation</span>
					</a>
				</li>
		</c:if>
				
				<li class="dropdown">
					<a href="#" class="dropdown-toggle">
						<i class="fa fa-bar-chart-o"></i>
						<span class="hidden-xs">Charts</span>
					</a>
					<ul class="dropdown-menu">
						<li><a class="ajax-link" href="ajax/charts_xcharts.html">xCharts</a></li>
						<li><a class="ajax-link" href="ajax/charts_flot.html">Flot Charts</a></li>
						<li><a class="ajax-link" href="ajax/charts_google.html">Google Charts</a></li>
						<li><a class="ajax-link" href="ajax/charts_morris.html">Morris Charts</a></li>
						<li><a class="ajax-link" href="ajax/charts_coindesk.html">CoinDesk realtime</a></li>
					</ul>
				</li>
				
			</ul>
		</div>
		<!--Start Content-->
		<div id="content" class="col-xs-12 col-sm-10">
			
			<div id="ajax-content"></div>
		</div>
		<!--End Content-->
	</div>
</div>
<!--End Container-->