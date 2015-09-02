<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="shishiyingxiao2.css" rel="stylesheet" type="text/css">
<link type="text/css" href="css/docs.css" rel="stylesheet" media="all" />
<link type="text/css" href="css/jquery.mcdropdown.min.css" rel="stylesheet" media="all" />
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery.mcdropdown.js"></script>
<script type="text/javascript" src="js/jquery.bgiframe.js"></script>
<script type="text/javascript">
	$(document).ready(function (){
		$("#cityListText").mcDropdown("#categorymenu");
		titleBs();
	});

	function weizhi_xuanze(){
		var cityName=$("#cityListText").val();
		if(""==cityName){
			alert("请选择地区！");
			return false;
		}
		var pType =$("#posiType").val();
		var param="?cityName="+cityName+"&pType="+pType;
		$.ajax({
			type: "post",
			url: "triggerAction!getPositionList"+param,
			dataType : "text",
			beforeSend: function(XMLHttpRequest){
				$('#search').unbind("click");
			},
			success: function(v){
				$('#bs_table').html(v);
			},
			complete: function(XMLHttpRequest, textStatus){
				$('#search').bind("click",weizhi_xuanze);
			},
			error: function(){
				$('#search').bind("click",weizhi_xuanze);
				alert("位置信息查询失败！");
			}
			});
	}

function subBs(){
	 var checked = [];
	 $('input:checkbox:checked').each(function() {
            checked.push($(this).val());
      });
     var cityName=$("#cityListText").val();

	 if(cityName==""){
	 	alert("请选择地区");
		return false;
	 }
	 if(checked==""){
	 	alert("请选择基站！");
		return false;
	 }

	 $.ajax({
		type: "post",
		url: "triggerAction!subCheckedBs?checked="+encodeURI(checked)+"&cityName="+encodeURI(cityName)+"&activeCode="+activeCode,
		dataType : "text",
		beforeSend: function(XMLHttpRequest){
			$('#btn_tj').unbind("click");
		},
		success: function(v){
			//alert(v);
			//if("Y"==v){
				titleBs();
			//}
		},
		complete: function(XMLHttpRequest, textStatus){
			$('#btn_tj').bind("click","subApp");
		},
		error: function(){
			alert("基站信息提交失败，请重新提交！");
		}
		});

}

function titleBs(){
	$.ajax({
		type: "post",
		url: "triggerAction!getCheckedBsList"+acparam,
		dataType : "text",
		success: function(v){
			if(""==v){
				return;
			}
			var tableboby="";
			var bss=v.split("&");//.replace
			var uuid="";
			var ss="";
			for (var i=1;i<=bss.length;i++)
			{
				uuid = new UUID().createUUID();
				if(bss[i-1].length>6){
					ss=bss[i-1].substr(0,6)+'…';
				}else {
					ss=bss[i-1];
				}
				tableboby+="<div id=\""+uuid+"\" style=\"cursor:pointer;\" title=\""+bss[i-1]+"\"  ondblclick=\"deleteBs('"+bss[i-1]+"','"+uuid+"');\" class=\"gjc\">"
																														+ss.replace(",",">")+"</div>";
				if(i==8 && apps.length>8){
					tableboby+="<div class=\"gjc\" onclick=\"getAllPosttion("+v+");\">查看更多 ></div>";
					break;
				}
			}
			$('#cftjt').html(tableboby);
		}
		});

}

function deleteBs(a,d){
	if(confirm("确定要删除触发条件基站位置'"+a+"'吗？")){
	 	var s=deleteSetProperty(a,0,1);
    	if(s=="Y"){
    		$("#"+d).remove();
		}
	 }else{

	 }
}
function getAllPosttion(al){
	alert("所有位置！");
}

function czBs(){
	$("[name='posiCheckbox']").removeAttr("checked");//取消全选
}

function checkAll(){
	$("[name='posiCheckbox']").attr("checked","checked");//取消全选
}

function searchBsType(){
	var cityName=$("#cityListText").val();
	 if(cityName==""){
	 	alert("请选择地区");
		return false;
	 }
	 //alert(cityName);
	 $.ajax({
		type: "post",
		url: "triggerAction!searchBsType?cityName="+encodeURI(cityName),
		dataType : "text",
		success: function(v){
			if(v==''){
				alert('没有查到基站类型');
			}
			$('#bsTypeTable').html(v);
		}
		});
}

</script>

<div class="down_left">
		 <div class="down_left_top" style="background:url(images/down_left_top_bg.png);">
			   <span style="color:#FFFFFF">触发事件设置</span>
			   <select class="text1" id="spr"  onChange="loaddiv(this.value)" style="width:100px">
				  <option value ="gjz">关键字</option>
				  <option value ="app">APP</option>
				  <option value="wz"  selected="selected">位置</option>
				   <option value="web">网站</option>
				</select>
		 </div>

                <div>
				    <ul >
					<li style="color:#6f6f6f;float:left;margin-left:15px;margin-top:10px;list-style:none;">地区</li>
					<li style="float:left;margin-left:30px;list-style:none;">
					<label for="textfield"></label>
					<input type="text" name="cityListText" id="cityListText" style="margin-left:5px;width:170px;height:25px;margin-top:0px;border-left:0px;border-top:0px;border-right:0px;border-bottom:1px;" value="" />
						<ul id="categorymenu" class="mcdropdown_menu">
							<li rel="1">
								四川省
								<ul>
				 			 	<%= session.getAttribute("cityList")%>
								</ul>
							</li>
						</ul>
					</li>

					<li style="color:#6f6f6f;float:left;margin-left:350px;margin-top:10px;list-style:none;"><input type="button" value="查询基站类型" onclick="searchBsType();" /> &nbsp; &nbsp; &nbsp;
					<input type="button" value="全选" onclick="checkAll();" />&nbsp; &nbsp; &nbsp;<input type="button" value="重置" onclick="czBs();" /></li>
                   </ul>
				</div>


				<div class="weizhi_xuanze">
					<span >类型</span>
					<div  style=" display:block;overflow-y:scroll;OVERFLOW-x: none;  height:130px;">
					<table id="bsTypeTable" >


					</table>
					</div>
				</div>


			 <div  style="clear:both;width:150px;margin:0 auto;padding-top:50px;">

				   <input id="btn_tj"  type="button" onclick="subBs();"  class="btn_tj"  value=" " style="border:none;float:left;background:url(images/tijiaoneirong_btn.png);width:67px;height:31px;" >
				 </div>

     </div>


   <div class="down_right" style="height:310px;">
      <div class="down_right_top">
	  <ul style="list-style:none;">
	  <li style="background:#58a2db;display:block;color:#FFFFFF;">热门关键词</li>
	  <li style="background:#FFFFFF;display:block;color:#6f6f6f;">相关活动</li>
	  </ul>
	  </div>


	  <div class="down_right_content_weizhi">
      <ul >
	  <li style="margin-left:10px;"></li>
	  <li></li>
	  <li></li>
	  </ul>

	  <ul >
	  <li style="margin-left:10px;"></li>
	  <li></li>
	  <li></li>
	  </ul>
	  </div>
	  </div>
