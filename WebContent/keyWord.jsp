<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	$(document).ready(function(){	
		searchKeyWork();
	});
	
	function searchKeyWork(){
			$.ajax({
					type : "post",
					url : "triggerInfo!searchKeyWordByCode"+acparam,
					dataType : "text",
					success : function(v) {
						if(v==""){
							return false;
						}
						var arr =[];
						arr = v.split("&");
						var txt1="";
						for (var x = 0; x < arr.length; x++) {
							//顶部keyWord显示
							if(x<7){
								txt1 += "<div class=\"gjc\">" + arr[x] + "</div>";
								
							}
							if(x==7){
								txt1 += "<div class=\"gjc\">查看更多 ></div>";
								break;
							}
						}
						$("#cftjt").html(txt1)
					},
					error : function() {
						alert("获取关键字失败，请重新获取！");
					}
				});
	}
	var num =0;
	function addKeyWord(){
		var kw = $("#serachPara").val();
		var txt = "<label id='labac" + num+ "' name='trigger_ms' ondblclick='removeKeyWord(labac"+ num + ")'>" + kw + "</label> &nbsp";
		$("#select_key").append(txt);
	 	num++;
	}
	
</script>	

<html>
<body>
	<!-- 关键字div区 -->
		<div class="down_left">
			<div class="down_left_top"
				style="background: url(images/down_left_top_bg.png);">
				<span style="color: #FFFFFF">触发条件设置</span>
				<select class="text1" id="spr"  onChange="loaddiv(this.value)" style="width:100px">  
				  <option value ="gjz"  selected="selected">关键字</option>  
				  <option value ="app">APP</option>  
				  <option value="wz">位置</option> 
				  <option value="web">网站</option> 
				</select>			
			</div>
			<div class="main_left" style="float: left;">
				<ul style="list-style: none;">
					<li style="float: left; list-style: none; margin-left: 25px;">
						<s:textfield id="serachPara"
							style="margin-left: -15px; width: 170px; height: 25px; margin-top: 10px;"
							value="" onmouseover="this.style.borderColor='#e2e2e2'"
							onmouseout="this.style.borderColor='#e2e2e2'" />
					</li>
					<li style="float: left; margin-left: 5px; margin-top: 10px;">
						<input id="btn_tianjia" type="button" class="btn_tianjia"
						onclick="addKeyWord()"
						style="float: left; background: url(images/chufatiaojian-gjc_tianjia_btn.png); width: 48px; height: 29px;">
					</li>
					
			
						
					
					
				</ul>
				<ul style="margin-top: 50px; margin-left: 120px; list-style: none;">
					<li style="float: left; color: #b7b7b7; list-style: none;">
					<select id="searchType" >
							<option value="1" >google</option>
							<option value="2" >百度</option>
							<option value="3" >京东</option>
							<option value="4" >淘宝</option>
						</select>
					</li>
					<li style="float: left; margin-left: 12px;"><input id="btn_sc"
						type="button" class="btn_sc" value=" "
						style="float: left; background: url(images/chufatiaojian-gjc_shangchuan_btn.png); width: 48px; height: 29px;">
					</li>
				</ul>
			</div>
			<div id="select_key" class="biaoqian_xuanze">
				<!-- 	<ul class="gjc_tag">
						<li>陈奕迅</li>
						<li>十年</li>
						<li>陈奕迅</li>
						<li>十年</li>
					</ul>
					<ul class="gjc_tag2">
						<li>陈奕迅</li>
						<li>十年</li>
						<li>陈奕迅</li>
					</ul>
					<ul class="gjc_tag2">
						<li>陈奕迅</li>
						<li>十年</li>
						<li>陈奕迅</li>
					</ul>
					 -->
			</div>
			<div
				style="clear: both; width: 150px; margin: 0 auto; padding-top: 10px; float: right; margin-right: 140px;">
				<input type="button" id="btn_tj" class="btn_tj" onClick="submitKeyWord()"
					style="float: left; background: url(images/tijiaoneirong_btn.png); width: 67px; height: 31px;" />
				<s:reset id="btn_cz" class="btn_cz" value=" "
					style="float: left; background: url(images/chongzhi_btn.png); width: 67px; height: 31px; margin-left: 5px;" />
			</div>
		</div>



		<div class="down_right">
			<div class="down_right_top">
				<ul style="list-style: none;">
					<li style="background: #58a2db; display: block; color: #ffffff;">热门关键词</li>
					<li style="background: #FFFFFF; display: block; color: #6f6f6f;">相关活动</li>
				</ul>
			</div>


			<div class="down_right_content">
				<ul>
					<li style="margin-left: 8px;">富士山下</li>
					<li style="margin-left: 5px;">浮夸</li>
				</ul>
			</div>
		</div>
</body>
</html>