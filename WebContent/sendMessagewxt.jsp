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
<link href="shishiyingxiao-new1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

	$(document).ready(function(){

	});

	function subSm(){
		var busCode=$("#busCode").val();
		if($.trim(busCode)==""){
			alert("请填写业务代码！");
			return false;
		}
		var sendMs=$("#sendMs").val();
		sendMs=strReplaceAll(sendMs,'#','RJH');
		sendMs=strReplaceAll(sendMs,'%','RBFH');
		if($.trim(sendMs)==""){
			alert("请填推荐语！");
			return false;
		}
		var param="?busCode="+busCode+"&active_code="+activeCode+"&send_type=7&send_ms="+encodeURI(sendMs);
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
			<li ><img src="images/wet_b.png" onclick="loadsmwxt('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/yingxiaoguwenqiantai_btn_wx.png" style="cursor:pointer;"  onclick="loadsmyhgw('s');" /></li>
			<li ><img src="images/10086duanxin_wx.png" onclick="loadsm('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/1111.png" /></li>
			<li ><img src="images/1111.png" onclick="loadsmskxt('s');" style="cursor:pointer;"/></li>

			</ul>
        </div>


		<div class="nr_block" style="height:auto">
			<table style="padding-bottom:10px; height:140px;">
			<tbody>
			<tr>
			<th  style="text-align:right">活动编码：</th>
			<td id="txActiveCode">001</td>
			</tr>

		    <tr>
			<th   style="text-align:right">业务代码：</th>
			<td><input type="type" value="" disabled="disabled" id="busName" />
				<input type="hidden" value="" disabled="disabled" id="busCode" /> &nbsp;<input type="button" value="设置业务代码" onclick="setbusicode();"/>
			  </td>
			</tr>
			<tr height="20px;">
			<th   style="text-align:right">推荐语：</th>
					<td><textarea oninput="sumConlen();" onpropertychange="sumConlen();" rows="3" cols="45" style="font-size:12px; width:450px;" id="sendMs" name="task_content" class="">
                        </textarea>
                        <div class="remain" style="width:450px;text-align:right;">
                          您可以输入
                        <span id="conLen">140</span>
                         个字符
						 </td>
			</tr>
			</tbody>
			</table>


		</div>
			<ul style="list-style:none;width:140px;margin:0 auto;">
				<li style="float:left; margin-top:10px"><img src="images/tijiaoneirong_btn.png" onclick="subSm()" style="cursor:pointer;" /></li>
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
	<div style="float:left;padding-left:20px;padding-top:2px;color:#ffffff;" >选择数据业务</div>
	<div style="float:right;padding-right:20px; padding-top:10px;">
	<img src="images/close2_btn.png"  onclick="closebusicode();"  style="cursor: pointer;" />
	</div>
	</div>
	<div style=" height:460px; width:615px; display:none;position:absolute;top:233px;left:340px; background-color:#FFFFFF;" id="selectBusCoded">		</div>
</body>
</html>