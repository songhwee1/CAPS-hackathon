<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/includes/home.jsp" flush="true" />
<jsp:include page="/WEB-INF/views/includes/header.jsp" flush="true" />

<link rel="stylesheet" href="/css/board/content.css">
<script src="/js/main/util.js" type="text/javascript"></script>

<script src="http://code.jquery.com/jquery.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>

<!--  <table>
 	<tr>
 		<div id="logo" class="col-xs-12	col-sm-10">
				<a href="/">INHATCSYSTEM</a>
		</div>
 	</tr>
	<tr>
		<td> <select name="YEAR" id="YEAR" title="년도" class="select w80" onchange="javascript:viewChart(this)"></select> 
		</td>
	</tr>
</table> -->
	<div style="margin-top:70px;">
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	 <div id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	 </div>
<script>
    $(document).ready(function(){ //홈페이지 로딩 시 호출
        var dt = new Date();	// date 객체생성?
        var pre_year = dt.getFullYear(); // 현재 년도 -> 변수에 저장
        setDateBox(pre_year); // selectbox에 동적으로 년도 생성   현재년도 변수를 매개변수로 보내서    ? <현재년도< ? 로 생성 
        chartData(pre_year); // 위 차트 생성 매개변수 현재년도 
      	chartData2(pre_year);
    });    
 
    // select box 연도 , 월 표시
    function setDateBox(com_year){ // 매개변수 현재년도
        var year = "";
        // 발행 뿌려주기
        $("#YEAR").append("<option value=''>년도</option>");
        // 올해 기준으로 -1년부터 +5년을 보여준다.
        for(var y = (com_year-3); y <= (com_year); y++){
            $("#YEAR").append("<option value='"+ y +"'>"+ y + " 년" +"</option>");
        }
        $("#YEAR").val(com_year).prop("selected", true); //셀렉트박스에서 벨류 값이 현재년도 인것 셀렉트 */
	
    }
    
    function viewChart(year){
		chartData(year.value);
		chartData2(year.value);
    }
    
    function chartData2(com_year){ //기자재별 차트 생성 함수
    	 var pData = {};
    	 pData['year'] = com_year;
    	 getChartData2 = new Array();
    	 $.ajax({
    	      type : "post",
    	      url : "/chart2",
    	      data: pData,
    	      dataType: "json",
    	      success : function(jsonData){
    	    	  console.log(jsonData);
    	    	  $.each(jsonData, function(key,value){
    	    		  getChartData2.push(value);
    	    		  
    	    	  })
    
     	         var chartOption = {
     	         	    chart: {
     	         	        type: 'column'
     	         	    },
     	         	    title: {
     	         	        text: '기자재별 수리통계'
     	         	    },
     	         	    subtitle: {
     	         	        text: '(년)'
     	         	    },
     	         	    xAxis: {
     	         	        categories: [
     	         	            'Jan',
     	         	            'Feb',
     	         	            'Mar',
     	         	            'Apr',
     	         	            'May',
     	         	            'Jun',
     	         	            'Jul',
     	         	            'Aug',
     	         	            'Sep',
     	         	            'Oct',
     	         	            'Nov',
     	         	            'Dec'
     	         	        ],
     	         	        crosshair: true
     	         	    },
     	         	    yAxis: {
     	         	        min: 0,
     	         	        title: {
     	         	            text: '건수 (개)'
     	         	        }
     	         	    },
     	         	    tooltip: {
     	         	        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
     	         	        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
     	         	            '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
     	         	        footerFormat: '</table>',
     	         	        shared: true,
     	         	        useHTML: true
     	         	    },
     	         	    plotOptions: {
     	         	        column: {
     	         	            pointPadding: 0.2,
     	         	            borderWidth: 0
     	         	        }
     	         	    },
     	         	    series: getChartData2
     	         	}
     	   	
     	         Highcharts.chart('container2', chartOption);
     	      }
    	      
    	       
    	   
    	   }); 
    	 }
    	 
    	  function chartData(com_year){
    	    	/* alert(com_year+"ajax"); */
    	    	 var pData = {};
    	    	 pData['year'] = com_year; // 매개변수 담아서 전송
    	    	   
    	    	 $.ajax({
    	    	      type : "post",
    	    	      url : "/chart",
    	    	      data: pData,
    	    	      dataType: "json",
    	    	      success : function(response){
    	    	         /* alert(response) */
    	    	         var chartOption = {
    	    	         	    chart: {
    	    	         	        type: 'column'
    	    	         	    },
    	    	         	    title: {
    	    	         	        text: '부서별 수리 통계'
    	    	         	    },
    	    	         	    subtitle: {
    	    	         	        text: '(년)'
    	    	         	    },
    	    	         	    xAxis: {
    	    	         	        categories: [
    	    	         	            'Jan',
    	    	         	            'Feb',
    	    	         	            'Mar',
    	    	         	            'Apr',
    	    	         	            'May',
    	    	         	            'Jun',
    	    	         	            'Jul',
    	    	         	            'Aug',
    	    	         	            'Sep',
    	    	         	            'Oct',
    	    	         	            'Nov',
    	    	         	            'Dec'
    	    	         	        ],
    	    	         	        crosshair: true
    	    	         	    },
    	    	         	    yAxis: {
    	    	         	        min: 0,
    	    	         	        title: {
    	    	         	            text: '건수 (개)'
    	    	         	        }
    	    	         	    },
    	    	         	    tooltip: {
    	    	         	        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
    	    	         	        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
    	    	         	            '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
    	    	         	        footerFormat: '</table>',
    	    	         	        shared: true,
    	    	         	        useHTML: true
    	    	         	    },
    	    	         	    plotOptions: {
    	    	         	        column: {
    	    	         	            pointPadding: 0.2,
    	    	         	            borderWidth: 0
    	    	         	        }
    	    	         	    },
    	    	         	    series: response
    	    	         	}
    	    	   	
    	    	         Highcharts.chart('container', chartOption);
    	    	      }
    	    	   
    	    	   }); 
    }
    
 
</script>
    