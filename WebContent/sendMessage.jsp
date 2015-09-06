<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style type="text/css">
body, div, table, tr, td, ul, li, dl, dt, dd, p, h1, h2, h3, h4, h5, h6, img, form, select, input {
    margin: 0;
    padding: 0;
}
</style>
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script language="javascript"  src="layer/layer.min.js"></script>

<script type="text/javascript">
	var sendType="1";//短信
	var sendCode="";
	var goStatus="N";
	var num=0;
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
		var randomNo=$("#randomNo").val();

		if(randomNo==""){
			alert("请填写随机码！");
			return false;
		}

		var para="?send_code="+sendCode+"&send_type="+sendType+"&random_no="+randomNo+"&activeCode="+activeCode;

		$.ajax({
		type: "post",
		url: "sendMessageAction!examineSendMessage"+para,
		dataType : "text",
		beforeSend: function(XMLHttpRequest){
		},
		success: function(v){

			var s=v.split("|");
			if(s[1]=="S"){
				alert(s[0]);
				$('#exStatus').text("审核通过");
				$('#xgsh').css("display","block");
				$('#tjsh').css("display","block");
				$('#examine').css("display","none");
				var adsendtype="";
				if(sendType==1){
					adsendtype="<li>短信</li>";
				}
				$('#titleli').html(adsendtype);
				goStatus="Y";
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

	 <div class="down_left" >
		 <div class="down_left_top" style="background:url(images/down_left_top_bg.png);">
			   <span style="color:#FFFFFF">推送渠道及内容设置</span>
		 </div>



				<div style="width:680px; height:190px;margin:10px auto;">
                    <ul style="list-style:none; float:left;">
                        <li><input type="button" value="" style=" width:108px; height:29px; background:url(images/sz_btn_zt1.png) no-repeat; border:none; margin-bottom:15px;" />
                        </li>
                        <li><input type="button" value="" style=" width:108px; height:29px; background:url(images/sz_btn_wt2.png) no-repeat; border:none; margin-bottom:15px;" />
                        </li>
                        <li><input type="button" value="" style=" width:108px; height:29px; background:url(images/sz_btn_yxgw2.png) no-repeat; border:none; margin-bottom:15px;" />
                        </li>
                        <li><input type="button" value="" style=" width:108px; height:29px; background:url(images/sz_btn_zscl2.png) no-repeat; border:none; margin-bottom:15px;" />
                        </li>
                    </ul>
                    <div style="width:540px; height:190px; background:#eef8ff; float:right;">
                        <table style="margin-left:10px; width:100%">
                            <tr>
                                <td width="15%">活动名称：</td>
                                <td id="smActiveName" width="84%"></td>
                            </tr>
							<tr>
                                <td>活动内容：</td>
                              <td id="smActiveBody"></td>
                            </tr>
							<tr>
                                <td>短信编码：</td>
                             	<td><input type="text" value="" id="randomNo" style="width:80px; height:26px;" /> 
									<input type="button" value="短信试发" onclick="dxfs();"/></td>
                            </tr>
							<tr>
                                <td>审批人：</td>
                                <td><input type="text" value="" id="randomNo" style="width:80px; height:26px;" />
									<input type="button" value="提交审批" onclick="dxfs();"/></td>
                            </tr>
							<tr id="trRandomNo">
								<td>验证码：</td>
								<td><input type="text" value="" id="randomNo" style="width:80px; height:26px;" /><span id="djs"></span></td>
							</tr>
                        </table>

                    </div>
				</div>
                <ul style=" width:384px; height:30px; float:right; list-style:none;">

                    <li style="float:left; padding:0 10px;"><input type="button" id="tjsh" onclick="subSm();" value="" style="width:67px; height:31px; background:url(images/tijiao_btn.png) no-repeat; border:none; margin-right:15px;"></li>
					<li></li>
					<li style="float:left; padding:0 10px; display:none;" id="examine"><input type="button"  onclick="examine();" value="" style="width:67px; height:31px; background:url(images/yanzheng_btn.png) no-repeat; border:none; margin-right:15px;"></li>
                </ul>

			<div class="main_right" >

    </div>
  </div>


   <div class="down_right" >
      <div class="down_right_top">
		  <ul style="list-style:none;">
		  <li style="background:#58a2db;display:block;color:#ffffff;">热门关键词</li>
		  <li style="background:#FFFFFF;display:block;color:#6f6f6f;">相关活动</li>
		  </ul>
	  </div>


		  <div class="down_right_content">
			  <ul>
			  <li style="margin-left:8px;">富士山下</li>
			  <li style="margin-left:5px;">浮夸</li>
			  </ul>
		</div>
	</div>

