<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String setStep=request.getParameter("setStep");
if(null==setStep||"".equals(setStep)){
	setStep="cftj";
}
String activecode=request.getParameter("activeCode");
String activeName=request.getParameter("activeName");
String activebody=request.getParameter("activeBody");
String beginTime=request.getParameter("beginTime");
String endTime =request.getParameter("endTime");
String activeType =request.getParameter("activeType");
String hfsscj =request.getParameter("fsscj");
String acitveScene=request.getParameter("acitveScene");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>营销场景</title>
<style type="text/css">
body, div, table, tr, td, ul, li, dl, dt, dd, p, h1, h2, h3, h4, h5, h6, img, form, select, input {
    margin: 0;
    padding: 0;
}
</style>
<link href="shishiyingxiao.css" rel="stylesheet" type="text/css" />
<link href="shishiyingxiao2.css" rel="stylesheet" type="text/css" />
<link href="shishiyingxiao-new.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="css/docs.css" rel="stylesheet" media="all" />
<link href="css/tck.css" rel="stylesheet" type="text/css" />
<link type="text/css" href="css/jquery.mcdropdown.min.css" rel="stylesheet" media="all" />
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script language="javascript"  src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src="js/jquery.mcdropdown.min.js"></script>
<script type="text/javascript" src="js/jquery.bgiframe.js"></script>
<script type="text/javascript" src="js/uuid.js"></script>
<script language="javascript" src="js/main.js"></script>
<script language="javascript" src="js/titleCss.js"></script>
<script language="javascript" src="js/trigger.js" charset="utf-8"></script>
<link href="css/shishiyingxiao-new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
<%
if(activecode==null){
%>
alert("请先创建活！");
window.location.href="createActive.jsp";
<%
}
%>

var activeCode="<%=activecode %>";
var acparam="?activeCode=<%=activecode %>";
var activebody="<%=activebody %>";
var activeName="<%=activeName %>";
var activeType="<%=activeType %>";
var setStep="<%=setStep %>";//cftj  yhq glyhq tsxx
var loadStop="";
var projectPath="<%=request.getContextPath() %>";
var userGroup_code="";
var userGroup_name="";
var customNum=0;
var hfsscj="<%=hfsscj %>";
var acitveScene="<%=acitveScene %>";
var zbeginTime="<%=beginTime %>";



	$(document).ready(function(){
		if(setStep=="cftj"){
			 loadStop="app";
		}else{
			 loadStop=setStep;
		}
		loaddiv(loadStop);
	});


	function wancheng(){
	$.ajax({
		type: "post",
		url: "activeAction!ActiveSetTest"+acparam+"&activeType="+activeType,
		dataType : "text",
		success: function(v){
			if(v=="Y"){
				$("#suForm").submit();
			}else{
				alert(v);
			}
		}
		});

	}

	function setUserGroup(code,name,num){
		if(code==""||name==""){
			alert("请重新选择客户群");
			return;
		}
		userGroup_code=code;
		userGroup_name=name;
		customNum=num;
		$("#userGroupIframe").css("display","none");
		$("#userGroupIframeTitle").css("display","none");
		$("#userGroupTd").html(userGroup_name+",用户数："+num);

	}

	function gotopage(i){
		if(i=='1'){
			window.location.href="activeIndex.jsp";
		}else if(i=='2'){
			window.location.href="createActive.jsp";
		}else if(i=='3'){
			window.location.href="effectIndex.jsp";
		}
	}


    //主要使用以免陷入死循环
    function strReplaceAll(str, oldStr, newStr){
        while (str.indexOf(oldStr) >= 0){
           str = str.replace(oldStr, newStr);
        }
        return str;
	}
</script>

</head>

<body style="width:100%;height:700px;margin:0 auto;">


<div id="top">
  <div id="nav">
                <div style="padding-top:20px; padding-left:0px; float:left"><img src="images/top_logo.png" /></div>
                <ul>
                    <li onclick="gotopage('1')" style="cursor:pointer;"><img src="images/top_yxrwgl_3.png" /></li>
                    <li onclick="gotopage('2')" style="cursor:pointer;"><img src="images/top_yxrwcj_1.png" /></li>
                    <li   onclick="gotopage('3')" style="cursor:pointer;"><img src="images/top_xggzbb_3.png" /></li>
                </ul>
            </div>
		</div>

<div class="tab_A clearfix">
  <ul>
    <li id="cftj" class="tab01_selected">
      <p>触发事件设置
	  	<span class="btn" id="cftju"></span>
	  </p>
		<div class="cftj_block_biaoqian" id="cftjt">

		</div>
	</li>
    <li id="yhq" class="tab02_noselected">
      <p>用户群设置
	  <span class="btn" id="yhqu"></span>
	  </p>
	  <div  class="cftj_block_biaoqian bg_sz" id="yhqt">
			<img src="images/shezhi_btn.png" onclick="loaddiv('yhq');" style="cursor:pointer;" />
	  </div>

    </li>
    <li id="glyhq" class="tab_noselected">
      <p>客户接触管理
	  <span class="btn" id="glyhqu"></span>
	  </p>
	  <div  class="cftj_block_biaoqian bg_sz" id="glyhqt">
			<img src="images/shezhi_btn.png" onclick="loaddiv('glyhq');" style="cursor:pointer;" />
	  </div>
    </li>
    <li id="tsxx" class="tab_noselected">
      <p>推送渠道及内容
	   <span class="btn" id="tsxxu"></span>
	  </p>
	  <div  class="cftj_block_biaoqian bg_sz" id="tsxxt">
			<img src="images/shezhi_btn.png" onclick="loaddiv('tsxx');" style="cursor:pointer;" />
	  </div>
    </li>
    <li id="queding" class="tab_last">
      <p>确定</p>
      <div class="bg_qd"> <img onclick="wancheng();" style="cursor:pointer;" src="images/wancheng_btn.png" /></div>
    </li>
  </ul>
</div>


 <div class="shezhi_main_down" id="loaddiv" >



 </div>
      <div id="footer">@2013中国移动通信集团四川有限公司  版权所有 </div>

<form action="activeAction!createActive" id="suForm" method="post">
<input type="hidden" name="activeCode" id="activeCode" value="<%=activecode %>" />
<input type="hidden" name="activeBody" id="activeBody" value="<%=activebody %>" />
<input type="hidden" name="activeName" id="activeName" value="<%=activeName %>" />
<input type="hidden" name="beginTime" id="hbeginTime" value="<%=beginTime %>" />
<input type="hidden" name="endTime" id="hendTime" value="<%=endTime %>" />
<input type="hidden" name="setStep" id="hsetStep" value="<%=setStep %>" />
<input type="hidden" name="activeType" id="hactiveType" value="<%=activeType %>" />
</form>

<div id="allTriggerMs" class="cfsz_tck" style="position:absolute;background-color: white; display:none;">
  <div class="tck_row" id="guanjianzi">

  </div>
  <div class="tck_row" id="yingyong">

  </div>
  <div class="tck_row" id="weizhi">

  </div>

  <div class="queding">
  <img src="images/yonghuqun_xinjian_queding.png" style="cursor:pointer;" onclick="closeAllTriggerMsDiv();" />
  </div>
</div>
<div id="userGroupIframeTitle" style="background:url(images/bar_bg.png) repeat-x;width:78%;height:33px;z-index:9999;position:absolute;top:85px;left:140px; display:none" >
	<div style="float:left;padding-left:20px;padding-top:2px;color:#ffffff;" >客户群选择</div><div style="float:right;padding-right:20px; padding-top:10px;"><img src="images/close2_btn.png"  onclick="getUserGroupPage()"  style="cursor: pointer;" /></div></div>
<iframe src="#" height="75%" width="78%" style="display:none;position:absolute;top:118px;left:140px;" scrolling="auto"  id="userGroupIframe" frameborder="no" border="0"  >

</iframe>

</body>
</html>