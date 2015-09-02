<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="shishiyingxiao-new.css" rel="stylesheet" type="text/css">
<style type="text/css">
body, div, table, tr, td, ul, li, dl, dt, dd, p, h1, h2, h3, h4, h5, h6, img, form, select, input
   {
    margin: 0;
    padding: 0;}
</style>
<link href="js/skins/lhgcalendar.css" rel="stylesheet" type="text/css">
<script language="javascript"  src="js/lhgcalendar.min.js"></script>

<script type="text/javascript">
	$(function(){
    	$('#sendTime').calendar({ format:'yyyy-MM-dd HH:mm:ss'});
		loadShr();
	});

	function loadShr(){
		$.ajax({
			type: "post",
			url: "sendMessageAction!getActiveShr",
			dataType : "text",
			success: function(v){
				$('#shenheren').html(v);
			},
			error: function(){
				alert("查询审核人信息出错！");
			}
		});
	}


	var sendType="1";//1短信2掌上冲浪3营销顾问前台
	var sendCode="";
	var goStatus="N";
	var num=0;

	function dxfs(){
		var smbCode=$("#smbCode").val();
		var sendTime=$("#sendTime").val();

		if(sendTime==""){
			alert("请选择发送时间！");
			return false;
		}
		if(smbCode==""){
			alert("请填写短信编码！");
			return false;
		}
		var para="?smbCode="+smbCode+"&send_type="+sendType+"&sendTime="+sendTime+"&activeCode="+activeCode;
		$.ajax({
			type: "post",
			url: "sendMessageAction!shortMessageCodeSend"+para,
			dataType : "text",
			success: function(v){
				if("F"==v||"E"==v){
					alert("推送信息短信发送失败，请重新提交！");
					return false;
				}
				alert("短信发送成功！");
				sendCode=v;//防止刷新丢失，发送验证码时需要验证是否为""
			},
			error: function(){
				alert("推送信息短信发送失败，请重新提交！");
			}
		});
	}

	function yanzSm(){
		var smbCode=$("#smbCode").val();
		if(smbCode==""){
			alert("请填写短信编码！");
			return false;
		}
		var spr=$("#spr").val();
		if(spr==""){
			alert("请选择审批人！");
			return false;
		}
		if(sendCode==""){
			alert("请先验证短信编码内容！");
			return false;
		}
		var para="?smbCode="+smbCode+"&send_code="+sendCode+"&activeCode="+activeCode+"&spr="+spr;
		$.ajax({
			type: "post",
			url: "sendMessageAction!randomNoSend"+para,
			dataType : "text",
			success: function(v){
				if("F"==v||"E"==v){
					alert("验证码下发失败，请重新发送！");
					return false;
				}
				alert("验证码发送成功！");
			},
			error: function(){
				alert("验证码下发失败，请重新发送！");
			}
		});
	}



	function subSm(){
		var sendMessage=$("#sendMessage").val();

		if(sendMessage==""){
			alert("请填写推送信息内容！");
			return false;
		}

		var para="?send_ms="+sendMessage+"&send_type="+sendType+"&activeCode="+activeCode;

		$.ajax({
		type: "post",
		url: "sendMessageAction!subSendMessage"+para,
		dataType : "text",
		beforeSend: function(XMLHttpRequest){
			//ShowLoading();
			$('#xgsh').unbind("click");
			$('#tjsh').unbind("click");
		},
		success: function(v){
			if("e"==v){
				alert("推送信息提交失败，请重新提交！");
				return false;
			}
			var s=v.split("|");
			if(s[0]=="Y"){
				num=60;
				alert("推送信息创建成功，请获取随机码验证！");
				$('#xgsh').css("display","none");
				$('#tjsh').css("display","none");
				sendCode=s[1];
				$('#exStatus').text("待审核");
				$('#trRandomNo').css("display","table-row");
				$('#examine').css("display","block");
				exdjs();
			}else{
				alert(s[1]);
			}

		},
		complete: function(XMLHttpRequest, textStatus){
			$('#xgsh').bind("click",subSm);
			$('#tjsh').bind("click",subSm);
		},
		error: function(){
			//请求出错处理
			$('#xgsh').bind("click",subSm);
			$('#tjsh').bind("click",subSm);
			alert("推送信息提交失败，请重新提交！");
		}
		});
	}

	function examine(){
		var randomNo=$("#yzm").val();
		var spr=$("#spr").val();
		if(randomNo==""){
			alert("请填写随机码！");
			return false;
		}

		var para="?send_code="+sendCode+"&send_type="+sendType+"&random_no="+randomNo+"&activeCode="+activeCode+"&spr="+spr;

		$.ajax({
		type: "post",
		url: "sendMessageAction!examineSendMessage"+para,
		dataType : "text",
		beforeSend: function(XMLHttpRequest){
		},
		success: function(v){
			if(v=="e"){
				alert("活动异常，请重新创建");
				window.location.href="createActive.jsp";
			}

			var s=v.split("|");
			if(s[1]=="S"){
				alert(s[0]);
				loadtsxxdata();
			}else{
				alert(s[0]);
			}

		},
		complete: function(XMLHttpRequest, textStatus){
		},
		error: function(){
			alert("服务器繁忙，请重新提交验证！");
		}
		});
	}

	function goto(){
		window.location.href="activeAction!createActive";
	}

	function exdjs(){
		if(num==0){
			return false;
		}
		setTimeout("exdjs()",1000);
		--num;
		$('#djs').html(num);
	}


</script>

		<div class="down_left" style="height:340px;">
			<div class="down_left_bar">
				<span>推送渠道及内容设置</span>


			</div>


		<div class="list_btn" >
			<ul style="list-style:none;margin-top:15px;margin-left:10px;">
			<li ><img src="images/zhangshangchonglang_wx.png"  onclick="loadsmzscl('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/wt.png" onclick="loadsmwt('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/zt.png" onclick="loadsmzt('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/wet.png" onclick="loadsmwxt('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/yingxiaoguwenqiantai_btn_wx.png" onclick="loadsmyhgw('s');" style="cursor:pointer;"/></li>
			<li><img src="images/10086duanxin.png" /></li>
			<li ><img src="images/1111.png" /></li>
			<li ><img src="images/1111.png" onclick="loadsmskxt('s');" style="cursor:pointer;"/></li>

			</ul>
        </div>


		<div class="nr_block">

			<table style="padding-bottom:20px;">
			<tbody>
			<tr>
			<th width="104" style="text-align:right">活动名称：</th>
			<td id="smActiveName" colspan="2">超级星期天推荐</td>
			</tr>
			<tr>
			<th style="text-align:right">活动内容：</th>
			<td id="smActiveBody" colspan="2">XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX</td>
			</tr>


			<tr>
			<th style="text-align:right">发送时间：</th>
			<td colspan="2" >
			<input id="sendTime" class="runcode inputText" style="width:140px; height:25px;position:relative; "  type="text" value="" >
			</td>
			</tr>


			<tr>
			<th style="text-align:right">短信编码：</th>
			<td colspan="2"><input id="smbCode" class="inputText" type="text" style=" width:100px" >&nbsp;
			<img src="images/fsyzm_btn.png" onclick="dxfs();" style="vertical-align:middle; cursor:pointer" /></td>

			</tr>

			<tr>
			<th style="text-align:right">审批人：</th>
			<td width="106" id="shenheren"></td>
			<td width="347"><img src="images/fsyzm_btn.png" onclick="dxfs();" style="cursor:pointer"></td>
			</tr>


			<tr>
			<th style="text-align:right">验证密码：</th>
			<td colspan="2"><input id="yzm" class="inputText" type="text" value="" style=" width:100px"></td>
			</tr>
			</tbody>
			</table>

			<ul style="list-style:none;width:140px;margin:0 auto;margin-top:10px;margin-bottom:20px;">
			<li style="float:left;"><img src="images/tijiaoneirong_btn.png" onclick="examine()" style="cursor:pointer;" /></li>
			<li style="float:left;margin-left:5px;"></li>
			</ul>
		</div>

		</div>


		<div class="down_right" style="height:360px">
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

