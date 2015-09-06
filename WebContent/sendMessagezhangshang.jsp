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
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script language="javascript"  src="js/lhgcalendar.min.js"></script>
<link href="js/skins/lhgcalendar.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
	$(function(){
    	$('#sendTime').calendar({ format:'yyyy-MM-dd HH:mm:ss'});
	});
	
	var sendType="掌上冲浪";//掌上冲浪
	var sendCode="";
	var goStatus="N";
	var num=0;
	function sendRandom(){
		var sendMessage=$("#sendMessage").val();
		var sendTime=$("#sendTime").val();
		var spr=$("#spr").val();
		if(sendTime==""){
			alert("请选择发送时间！");
			return false;
		}
		if(sendMessage==""){
			alert("请填写推送信息内容！");
			return false;
		}
		if(spr==""){
			alert("请选择审批人！");
			return false;
		}
		var para="?send_ms="+sendMessage+"&send_type="+sendType+"&activeCode="+activeCode+"&sendTime="+sendTime+"&spr="+spr;

		$.ajax({
		type: "post",
		url: "sendMessageAction!subSendMessage"+para,
		dataType : "text",
		beforeSend: function(XMLHttpRequest){
			//ShowLoading();
			$('#sendButton').unbind("click");
		},
		success: function(v){
			if("e"==v){
				alert("推送信息提交失败，请重新提交！");
				return false;
			}
			var s=v.split("|");
			if(s[0]=="Y"){
				//num=60;
				alert("推送信息创建成功，请获取随机码验证！");
				$('#xgsh').css("display","none");
				$('#tjsh').css("display","none");
				sendCode=s[1];
				$('#exStatus').text("待审核");
				$('#trRandomNo').css("display","table-row");
				$('#examine').css("display","block");
				//exdjs();
			}else{
				alert(s[1]);
			}

		},
		complete: function(XMLHttpRequest, textStatus){
			$('#sendButton').bind("click",sendRandom);
		},
		error: function(){
			//请求出错处理
			$('#sendButton').bind("click",sendRandom);
			alert("推送信息提交失败，请重新提交！");
		}
		});
	}
	
</script>
		<div class="down_left">
			<div class="down_left_bar">
				<span>推送渠道及内容设置</span>
				
		    
			</div>
		
		
		<div class="list_btn" >
			<ul style="list-style:none;margin-top:15px;margin-left:10px;">
			<li ><img src="images/10086duanxin_wx.png" /></li>
			<li ><img src="images/zhangshangchongkan_btn.png" /></li>
			<li ><img src="images/yingxiaoguwenqiantai_btn_wx.png" /></li>
			</ul>
        </div>
		
	
		<div class="nr_block">
			<table>
			<tbody>
			<tr>
			<th>活动编码：</th>
			<td id="txActiveCode"></td>
			</tr>
			
			
			
			<tr>
			<th>发送时间：</th>
			<td><input id="sendTime" class="runcode" style="width:140px; height:25px;position:relative; "  type="text" value="" maxlength="50" size="20" name="task_name"></td>
			</tr>
			
			
		    <tr>
			<th style="width: 120px;">活动内容：</th>
			<td><textarea maxchar="400" rows="4" cols="45" id="sendBody" name="task_content" class="">
			</textarea>
			<div class="remain" style="width: 400px;text-align:right;">
			  您可以输入 
			<span>400</span>
			 个字符			</div>			</td>
			</tr>
			<tr>
			<th>审批人：</th>
			<td><select class="inputText" id="spr"  style="width:140px">  
				  <option value ="1001">审批人1</option>  
				  <option value ="1002">审批人2</option>  
				</select>
			</td>
			</tr>
			
			<tr>
			<th>验证密码：</th>
			<td><input id="task_name" class="inputText" type="text" value="" maxlength="50" size="20" name="task_name" style="float:left;">
			<input id="sendButton"  type="button"  class="" onclick="sendRandom();"  value=" " style="border:none;float:left;background:url(images/duanxinshifa.png)no-repeat 0 0 scroll  ;width:67px;height:31px;float:left;margin-left:5px;"></td>
			</tr>
			</tbody>
			</table>
			
			<ul style="list-style:none;width:140px;margin:0 auto;margin-top:15px;">
			<li style="float:left;"><img src="images/tijiaoneirong_btn.png" /></li>
			<li style="float:left;margin-left:5px;"><img src="images/chongzhi_btn.png" /></li>
			</ul>
		</div>
	
		</div>
		
		
		<div class="down_right">
			<div class="down_right_bar">
				<ul>
				<li >设置提示</li>
				<li style="background:#FFFFFF;color:#58a2db;">相关活动</li>
				</ul>
			
			<div class="down_right_nr">
			<span>申请短信内容编码：请走公司市场部服务组
的内容审批流程，有电渠支撑中心进行配置。

短信是试发：是将配置的营销短信内容发送到您本人的手机上且只有您本人能接收到此
短信，当短信发送成功，“提交审批”按钮才可用。</span>
           </div>
		
		</div>
		
		</div>
