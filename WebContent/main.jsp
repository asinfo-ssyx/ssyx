<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
request.getSession().setAttribute("activeCode", "12345");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>营销服务平台-触发条件设置-关键词</title>
<link href="css/trigger.css" rel="stylesheet" type="text/css">
<script language="javascript" src="js/jquery-1.7.2.min.js"></script>
<script language="javascript" src="js/main.js"></script>
<style type="text/css">
body,div,table,tr,td,ul,li,dl,dt,dd,p,h1,h2,h3,h4,h5,h6,img,form,select,input
	{
	margin: 0;
	padding: 0;
}
</style>

</head>

<body style="width: 100%; height: 700px; margin: 0 auto;">


	<div id="top">
		<div id="nav">
			<div style="padding-top: 20px; padding-left: 0px; float: left">
				<img src="images/top_logo.png" />
			</div>
			<ul>
				<li><img src="images/top_yxrwgl_3.png" /></li>
				<li><img src="images/top_yxrwcj_1.png" /></li>
				<li><img src="images/top_xggzbb_3.png" /></li>
			</ul>
		</div>
	</div>


	<div class="shezhi_main_up">
		<div class="cftj_gjc"
			style="background: url(images/xuanzhong_bg.png);">
			<div class="xuanzhong_bar"
				style="background: url(images/bar_xuanzhong.png) no-repeat scroll 0 0;">
				<span style="color: #FFFFFF">触发条件设置</span> <span><s:select
						id="top_select" class="text1" name="triggerType"
						onmouseover="this.style.borderColor='#e2e2e2'"
						list="{'关键字','应用','位置'}"
						onmouseout="this.style.borderColor='#e2e2e2'"
						onchange="changeType()">
					</s:select> </span>
			</div>

			<div class="xuanzhong_content">
				<ul id="keyWordList1" class="gjc_tag">
				</ul>


				<ul id="keyWordList2" class="gjc_tag2">
				</ul>
			</div>
		</div>

		<div class="yhq">
			<div class="weixuanzhong_bar"
				style="background: url(images/bar_weixuanzhong.png) no-repeat scroll 0 0;">
				<p style="color: #FFFFFF">用户群设置</p>
			</div>
			
			<div class="xuanzhong_content">
				<ul id="groupList1" class="gjc_tag">
				</ul>
				<ul id="groupList2" class="gjc_tag2">
				</ul>
			</div>

			<div class="yonghuqun_content">

				<input id="userGroupButton" type="button" class="btn_sz" onclick="loadUserGroup()"
					style="float: left; background: url(images/shezhi_btn.png) no-repeat scroll 0 0; width: 86px; height: 34px; margin-left: 80px; margin-top: 50px;">
			</div>
		</div>


		<div class="tsqd">
			<div class="weixuanzhong_bar2"
				style="background: url(images/bar_weixuanzhong.png) no-repeat scroll 0 0;">
				<p style="color: #FFFFFF">推送渠道及内容设置</p>
			</div>


			<div class="tuisongqudao_content">
				<input id="btn_sz" type="button" class="btn_sz" value=" "
					style="float: left; background: url(images/shezhi_btn.png) no-repeat scroll 0 0; width: 86px; height: 34px; margin-left: 80px; margin-top: 50px;">
			</div>
		</div>


		<div class="jsxj">
			<div class="jieshuxinjian_bar"
				style="background: url(images/bar_jieshu.png) no-repeat scroll 0 0;">
				<p style="color: #FFFFFF">提交</p>
			</div>


			<div class="jieshuxinjian_content">
				<input id="btn_qd" type="button" class="btn_qd" value=" "
					style="float: left; background: url(images/queding_btn.png) no-repeat scroll 0 0; width: 86px; height: 34px; margin-left: 80px; margin-top: 50px;">
			</div>
		</div>
	</div>

	<div id="optionDiv" class="shezhi_main_down">
		
	</div>
	
	
	<div id="footer">@2013中国移动通信集团四川有限公司 版权所有</div>


</body>
</html>
