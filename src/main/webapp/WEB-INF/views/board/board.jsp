<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/includes/home.jsp" flush="true" />
<jsp:include page="/WEB-INF/views/includes/header.jsp" flush="true" />

<link rel="stylesheet" href="/css/board/content.css">

<script src="/js/main/util.js" type="text/javascript"></script>
<script type="text/javascript" src="/Exp_admin/js/print.js"></script>

<script>
function fncWrite() {
	 if('<%=session.getAttribute("LOGIN_ID")%>' == "null") {
			alert("로그인이 필요합니다.");
			location.href='/';
			return false;
		} else {
			gfnOpenPop('/board/write', '글 등록', '736', '500');
			return false;
		}
	
	return false;
}
function fncView(idx) {
	 if('<%=session.getAttribute("LOGIN_ID")%>' == "null") {
			alert("로그인이 필요합니다.");
			location.href = '/';
			return false;
		
	 }else {
			gfnOpenPop('/board/read?bno=' + idx, '글 상세보기', '736', '500');
			return false;
		}

		return false;
	}
	
$(function(){
	//perPageNum select 박스 설정
	setPerPageNumSelect();
	//searchType select 박스 설정
	setSearchTypeSelect();
	
	/* //등록, 삭제 후 문구 처리
	var result = '${result}';
	$(function(){
		if(result === 'registerOK'){
			$('#registerOK').removeClass('hidden');
			$('#registerOK').fadeOut(2000);
		}
		if(result === 'removeOK'){
			$('#removeOK').removeClass('hidden');
			$('#removeOK').fadeOut(2000);
		}
	}) */
	
	//prev 버튼 활성화, 비활성화 처리
	var canPrev = '${pageMaker.prev}';
	if(canPrev !== 'true'){
		$('#page-prev').addClass('disabled');
	}
	
	//next 버튼 활성화, 비활성화 처리
	var canNext = '${pageMaker.next}';
	if(canNext !== 'true'){
		$('#page-next').addClass('disabled');
	}
	
	//현재 페이지 파란색으로 활성화
	var thisPage = '${pageMaker.cri.page}';
	//매번 refresh 되므로 다른 페이지 removeClass 할 필요는 없음->Ajax 이용시엔 해야함
	$('#page'+thisPage).addClass('active');
})

function setPerPageNumSelect(){
	var perPageNum = '${pageMaker.cri.perPageNum}';
	var $perPageSel = $('#perPageSel');
	var thisPage = '${pageMaker.cri.page}';
	
	$perPageSel.val(perPageNum).prop("selected",true);
	$perPageSel.on('change',function(){
		window.location.href = "="+thisPage+"&perPageNum="+$perPageSel.val();
	})
}

function setSearchTypeSelect(){
	var $searchTypeSel = $('#searchTypeSel');
	var $keyword = $('#keyword');
	
	$searchTypeSel.val('${pageMaker.cri.searchType}').prop("selected",true);
	//검색 버튼이 눌리면
	$('#searchBtn').on('click',function(){
		var searchTypeVal = $searchTypeSel.val();
		var keywordVal = $keyword.val();
		//검색 조건 입력 안했으면 경고창 
		if(!searchTypeVal){
			alert("검색 조건을 선택하세요!");
			$searchTypeSel.focus();
			return;
		//검색어 입력 안했으면 검색창
		}else if(!keywordVal){
			alert("검색어를 입력하세요!");
			$('#keyword').focus();
			return;
		}
		var url = "?page=1"
			+ "&perPageNum=" + "${pageMaker.cri.perPageNum}"
			+ "&searchType=" + searchTypeVal
			+ "&keyword=" + encodeURIComponent(keywordVal);
		window.location.href = url;
	})
}

</script>

<style>
table{
margin: auto;
text-align: center;
}
</style>
						
		<!-- <div class="row" style="margin-top:70px;">
			<div class="col-xs-12">
				<div class="box">
					 -->
					<div class="row" style="margin-top:30px;">
						<div class="col-lg-12">
						<div class="panel panel-default">
						

						<div class="panel-body">
						<table class="table table-striped table-bordered table-hover">
							<colgroup>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
									<col width="10%"/>
							</colgroup>
							<thead>
								
								<tr>
										<th>접수번호</th>
										<th>작업분류</th>
										<th>담당소속</th>
										<th>기계명</th>
										<th>의뢰인</th>
										<th>위치</th>
										<th>의뢰받은시간</th>
										<th>작성자</th>
										<th>현황</th>
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
													<td>${boardList[rowIndex.index].manager_classification}</td>
													<td>${boardList[rowIndex.index].manager_belong}</td>
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
													<td>${boardList[rowIndex.index].client_local}</td>
													<td>${boardList[rowIndex.index].regdate}</td>
													<td>${boardList[rowIndex.index].writer}</td>
													<td><a href="#" onclick="fncView('${boardList[rowIndex.index].bno}');" ><c:if test="${boardList[rowIndex.index].progress == '처리완료'}"><font color="blue" >
처리완료</font></c:if><c:if test="${boardList[rowIndex.index].progress == '처리중'}"><font color="green">처리중</font></c:if><c:if test="${boardList[rowIndex.index].progress == '처리대기'}"><font color="red">처리대기</font></c:if></a></td>
								
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
			</div>
		</div>
	<!--End Content-->
		
	
				<c:if test="${sessionScope.LOGIN_BELONG eq '내비게이션' || sessionScope.LOGIN_BELONG eq '관리자'}">
				<button type="button" class="btn btn-primary" onclick="fncWrite()" style="float: right;">
					<strong>등록</strong>
				</button>
				</c:if> 
				
				<div class="form-inline">
						<select id="searchTypeSel" name="searchType">
							<option value="">검색조건</option>
							<option value="t">작업분류</option> 
							<option value="c">내용</option>
							<option value="w">기계명</option>
							<option value="tc">의뢰인</option>
							<option value="all">현황</option>
						</select>
						<input class="form-control" type="text" id="keyword" name="keyword" 
							value="${pageMaker.cri.keyword}" placeholder="검색어를 입력하세요"/>
						<button id="searchBtn" class="btn btn-primary">검색</button>
				
				</div>
					
					
				
						
						
<div class="page-content">
						<div class="text-center">
							<nav aria-label="pagination">
							<%-- <ul class="pagination">

							<c:if test="${pageMaker.prev}">
								<li><a href="?page=${pageMaker.startPage -1}">&laquo;</a></li>
							</c:if>

							<c:forEach begin="${pageMaker.startPage }"
								end="${pageMaker.endPage}" var="idx">

								<li
									<c:out value="${pageMaker.cri.page == idx?'class =active':'' }"/>>
									<a href="?page=${idx}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next && pageMaker.endPage >0 }">
								<li><a href="?page=${pageMaker.endPage +1}">&raquo;</a></li>
							</c:if>

						</ul> --%>
								<ul class="pagination">
									<li id="page-prev">
										<a href="${pageMaker.makeQuery(pageMaker.startPage-1)}" aria-label="Prev">
											<span aria-hidden="true">«</span>
										</a>
									</li>
									<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
									    <li id="page${idx}">
										    <a href="${pageMaker.makeQuery(idx)}">
										      	<span>${idx}<span class="sr-only">(current)</span></span>
										    </a>
									    </li>
									</c:forEach>
									<li id="page-next">
									    <a href="${pageMaker.makeQuery(pageMaker.endPage + 1)}" aria-label="Next">
									    	<span aria-hidden="true">»</span>
									    </a>
									</li>
									
								</ul>
							</nav>
						</div>
						
						
			
</div>


