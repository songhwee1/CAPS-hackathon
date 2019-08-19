<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%><%-- 헤더 관련 --%>
<script type="text/javascript">
	function fncSave() {
		if ($("#bdType").val() == "") {
			alert("문의유형을 선택해주세요.");
			$("#bdType").focus();
			return false;
		}
		if ($("#bdTitle").val() == "") {
			alert("제목을 입력해주세요.");
			$("#bdTitle").focus();
			return false;
		}
		if ($("#bdContent").val() == "") {
			alert("내용을 입력해주세요.");
			$("#bdContent").focus();
			return false;
		}

		var pData = {};
		jQuery("form#saveForm :input[type!='button']").each(function(e) {
			pData[$(this).prop('name')] = $(this).val().toString();
		});//pData[bdTitle] = "ssssss"

		$.ajax({
			dataType : "json",
			type : "post",
			url : "/board/save.inhatc",
			data : pData,
			success : function(response, txtStatus, xhr) {
				console.log(response);//F12를 이용해서 값추출(디버깅용도)

				if (parseInt(response) > 0) {
					alert("등록이 완료되었습니다.");
					window.close();
				} else {
					alert("저장중 오류가 발생하였습니다.");
					return false;
				}
			},
			error : function(request, status, error) {
				alert("code = " + request.status + " message = "
						+ request.responseText + " error = " + error);
			}
		});

	}
</script>
<div class="pop_wrap" id="popUpLayerId">
	<div class="pop_tit">
		<span>게시판 등록</span>
	</div>

	<div class="pop_content">
		<form name="saveForm" id="saveForm" method="post">
			<table class="pop_table">
				<tbody>
				<colgroup>
					<col width="150px">
					<col width="*">
				</colgroup>
				<tr>
					<th>문의유형</th>
					<td><select id="bdType" name="bdType">
							<option value="">선택</option>
							<option value="D">배송관련</option>
							<option value="P">상품관련</option>
							<option value="A">결재관련</option>
					</select></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" id="bdTitle" name="bdTitle"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea id="bdContent" name="bdContent" rows="3" cols="7"></textarea></td>
				</tr>
				</tbody>
			</table>
		</form>
		<div class="button_rtbox">
			<span class="button bt01"><button class="bt01"onclick="fncSave()">저장</button></span> 
			<span class="button bt02"><button class="bt01" onclick="window.close()">닫기</button></span>
		</div>
	</div>
</div>