<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.net.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
InetAddress addr = InetAddress.getLocalHost();
String ip=addr.getHostAddress().toString();//获得本机IP

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<style type="text/css">
body, div, table, tr, td, ul, li, dl, dt, dd, p, h1, h2, h3, h4, h5, h6, img, form, select, input {
    margin: 0;
    padding: 0;
}
</style>
<link href="shishiyingxiao-new1.css" rel="stylesheet" type="text/css">
<link href="js/skins/lhgcalendar.css" rel="stylesheet" type="text/css">
<script language="javascript"  src="js/lhgcalendar.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){

	});


		function subSm(){
		//var busCode=$("#busCode").val();
// 		if($.trim(busCode)==""){
// 			alert("请填写业务代码！");
// 			return false;
// 		}
// 		var sendMs=$("#sendMs").val();
// 		if($.trim(sendMs)==""){
// 			alert("请填推荐语！");
// 			return false;
// 		}
		var disName=$("#districtName").val();
		if(disName==""){
			alert("请选择区县！");
			return false;
		}

		var param="?&active_code="+activeCode+"&send_type=10"+"&disName="+encodeURI(disName);
		$.ajax({
		type: "post",
		url: "sendMessageAction!setWebServiceSendMessage"+param,
		dataType : "text",
		success: function(v){
			if(v=="Y"){
				loadtsxxdata();
				alert("提交成功！");
			}
		}
		});

	}

	function setbusicode(){
			$("#selectBusCode").css("display","block");
			$("#selectBusCoded").css("display","block");
			$("#selectBusCoded").load("codeAction",function(response,status){});
	}
	function closebusicode(){
			$("#selectBusCode").css("display","none");
			$("#selectBusCoded").css("display","none");
	}

	var maxLen=140;
    function sumConlen(){
    	var sendMs=$("#sendMs").val();
		var len=$.trim(sendMs).length;
		//if(len>maxLen){
		//	alert("超过最大短信长度！");
		//	return;
		//}
		$("#conLen").html(maxLen-len);
    }
</script>
</head>
<body>
	 <div class="down_left" style="height:340px;" id="down_left_id">
			<div class="down_left_bar">
				<span>推送渠道及内容设置</span>


			</div>


		<div class="list_btn" >
			<ul style="list-style:none;margin-top:15px;margin-left:10px;">
			<li ><img src="images/zhangshangchonglang_wx.png"  onclick="loadsmzscl('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/wt.png" onclick="loadsmwt('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/zt.png" onclick="loadsmzt('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/wet.png" onclick="loadsmwxt('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/yingxiaoguwenqiantai_btn_wx.png" style="cursor:pointer;"  onclick="loadsmyhgw('s');"/></li>
			<li ><img src="images/10086duanxin_wx.png" onclick="loadsm('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/1111.png" /></li>
			<li ><img src="images/1111.png" onclick="loadsmskxt('s');" style="cursor:pointer;"/></li>

			</ul>
        </div>


		<div class="nr_block" style="height:auto">
			<table style="padding-bottom:10px; height:140px;">
			<tbody>
			<tr height="20px;">
			<th  style="text-align:right">活动编码：</th>
			<td id="txActiveCode">001</td>
			</tr>

		    <tr height="20px;">
			<th   style="text-align:right">区县：</th>
			<td>
					
			 		<select id="districtName">
			 			<option value="">-选择区县-</option>
			 			<option value="成都直属部门">成都直属部门</option>
			 			<option value="成都">成都</option>
						<option value="武侯">武侯</option>
						<option value="高薪">高薪</option>
						<option value="青羊">青羊</option>
						<option value="锦江">锦江</option>
						<option value="金牛">金牛</option>
						<option value="郫县">郫县</option>
						<option value="成华">成华</option>
						<option value="大邑">大邑</option>
						<option value="都江堰">都江堰</option>
						<option value="青白江">青白江</option>
						<option value="彭州">彭州</option>
						<option value="金堂">金堂</option>
						<option value="浦江">浦江</option>
						<option value="双流">双流</option>
						<option value="新都">新都</option>
						<option value="成都政企客户部">成都政企客户部</option>
			 		    <option value="龙泉驿">龙泉驿</option>
			 		    <option value="新津">新津</option>
			 		    <option value="崇州">崇州</option>
			 		    <option value="天府新区">天府新区</option>
			 		    <option value="温江">温江</option>
			 		    <option value="邛崃">邛崃</option>
			 		</select>
			  </td>
			</tr>
		

			</tbody>
			</table>


		</div>
			<ul style="list-style:none;width:140px;margin:0 auto;">
				<li style="float:left; margin-top:10px"><img src="images/tijiaoneirong_btn.png" onclick="subSm();" style="cursor:pointer;" /></li>
			</ul>



	 </div>


		<div class="down_right" id="down_right_id" style="height:360px;">
			<div class="down_right_bar">
				<ul>
				<li >设置提示</li>
				<li style="background:#FFFFFF;color:#58a2db;"></li>
				</ul>

			<div class="down_right_nr">
			<span>申请短信内容编码：请走公司市场部服务组
的内容审批流程，有电渠支撑中心进行配置。

短信是试发：是将配置的营销短信内容发送到您本人的手机上且只有您本人能接收到此
短信，当短信发送成功，“提交审批”按钮才可用。</span>
           </div>

		</div>

		</div>
<div id="selectBusCode" style="background:url(images/bar_bg.png) repeat-x;width:615px;height:33px;z-index:9999;position:absolute;top:200px;left:340px; display:none" >
	<div style="float:left;padding-left:20px;padding-top:2px;color:#ffffff;" >选择数据业务</div><div style="float:right;padding-right:20px; padding-top:10px;"><img src="images/close2_btn.png"  onclick="closebusicode();"  style="cursor: pointer;" /></div></div>
<div style=" height:460px; width:615px; display:none;position:absolute;top:233px;left:340px; background-color:#FFFFFF;" id="selectBusCoded">		</div>

</body>
</html>