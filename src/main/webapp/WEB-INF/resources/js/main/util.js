/*******************************************************************************
* DECLARE : 전역함수 지정
*******************************************************************************/
"https://code.jquery.com/jquery.js"

jQuery(document).on("keyup", "input:text[num=\"y\"]", function() {$(this).val( $(this).val().replace(/[^0-9.]/gi,"") );});

/*******************************************************************************
* FUNCTION 명 : gfnAjaxError
* FUNCTION 기능설명 : AJax 오류처리
*******************************************************************************/
function gfnAjaxError(response, msg) {
	var msg ="";
    if (typeof(response.AjaxtagErrors) != 'undefined') {
	   	msg = response.AjaxtagErrors;

		var form = document.createElement("form");
		form.setAttribute("charset", "UTF-8");
		form.setAttribute("method", "Post"); // Get 또는 Post 입력
		form.setAttribute("action", "/error.do");

		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "msg");
		hiddenField.setAttribute("value", msg);
		form.appendChild(hiddenField);

		var url ="/error.do"
		document.body.appendChild(form);
		form.submit();
    }else{
		return true;
    }
}

/*******************************************************************************
* FUNCTION 명 : gfnOpenPop
* FUNCTION 기능설명 : 팝업오픈
*******************************************************************************/
function gfnOpenPop(url, target, w, h, scroll, resizable)
{
    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

    var pW = parseInt(w, 10);
    var pH = parseInt(h, 10);
    var winWidth = window.screen.width;    //해상도가로
    var winHeight = window.screen.height;     //해상도세로

    var pL = ((winWidth - pW)/ 2) + dualScreenLeft - parseInt(pW / 2);
    var pT = ((winHeight - pH)/ 2) + dualScreenTop - parseInt(pH / 2);

    var sScroll = (typeof (scroll) == "undefined") ? "no" : ((scroll) ? "yes" : "no");
    var sResizable = (typeof (resizable) == "undefined") ? "no" : ((resizable) ? "yes" : "no");

    var nWin2 = window.open(url, target, " location=0, toolbar=0, menubar=no, scrollbars=" + sScroll + ", resizable=" + sResizable + ", status=yes, left=" + pL + ", top=" + pT + ", width=" + w + " , height=" + h + " ");

    try
    {
        nWin2.focus();
    }
    catch (oEx) { 
    	System.out.println(e.getMessage());
    }
    return nWin2;
}

/*******************************************************************************
* FUNCTION 명 : gfnOpenPopPost
* FUNCTION 기능설명 : 팝업오픈 POST전송
*******************************************************************************/
function gfnOpenPopPost(url, postData, target, w, h, scroll, resizable)
{
    gfnOpenPop("about:blank", target, w, h, scroll, resizable);

    var forms1 = document.forms["__popForm"];
    if (typeof(forms1) != "undefined") {
        var formNode = document.getElementById("__popForm");
        document.documentElement.removeChild(formNode);
    }
    var frm = document.createElement("form");
    frm.name = "__popForm";
    frm.id = "__popForm";
    frm.method = "post";
    frm.action = url;
    frm.target = target;
    document.documentElement.appendChild(frm);

    for(var param in postData) {
        var ipt = document.createElement("input");
        ipt.type = "hidden";
        ipt.name = param;
        ipt.value = postData[param] ;
        frm.appendChild(ipt);
    }
    frm.submit();
}

function gfnIsAlphanumeric(sValue){
    var sExpPattern = /^[0-9a-zA-Z]+$/;
    return (sValue.match(sExpPattern)) ? true : false;
}

function gfnIsUserName(sValue){
    var sExpPattern = /^[a-zA-Z가-힝]+$/;
    return (sValue.match(sExpPattern)) ? true : false;
}

function gfnIsUserId(sValue){
    var sExpPattern = /^[a-zA-Z0-9_]{6,20}$/;
    return (sValue.match(sExpPattern)) ? true : false;
}

function gfnIsUserPass(sValue){
    var sExpPattern = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/;
    return (sValue.match(sExpPattern)) ? true : false;
}

function gfnIsPhoneNumber(sValue){
    var sExpPattern = /^(01[016789]{1}|070|02|0[3-9]{1}[0-9]{1})-[0-9]{3,4}-[0-9]{4}$/;
    return (sValue.match(sExpPattern)) ? true : false;
}

function gfnIsEmailAddr(sValue){
    var sExpPattern = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
    return (sValue.match(sExpPattern)) ? true : false;
}