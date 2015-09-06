<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="css/appcssnew.css" rel="stylesheet" type="text/css">
<style type="text/css">
body, div, table, tr, td, ul, li, dl, dt, dd, p, h1, h2, h3, h4, h5, h6, img, form, select, input
   {
    margin: 0;
    padding: 0;}
</style>
<script type="text/javascript">
$(document).ready(function(){
		titleWeb();
	});

function subWeb(){
	 var checked = [];
	 $('input:checkbox:checked').each(function() {
            checked.push($(this).val());
      });
	 checked=encodeURI(checked);
	 $.ajax({
		type: "post",
		url: "activeEvent!subCheckedWeb?checked="+checked+"&activeCode="+activeCode,
		dataType : "text",
		beforeSend: function(XMLHttpRequest){
			$('#btn_tj').unbind("click");
		},
		success: function(v){
			//alert(v);
			if("Y"==v){
				titleWeb();
			}else if("L"==v){
				alert("提交超过最大限制数");
			}
		},
		error: function(){
			alert("web信息提交失败，请重新提交！");
		}
		});

}

function getCheckedWebCount(){
$.ajax({
		type: "post",
		url: "activeEvent!getCheckedWebCount"+acparam+"&triggerType=5",
		dataType : "text",
		success: function(v){
			$('#webcount').html(v);
		},
		});

}

function titleWeb(){
	getCheckedWebCount();
	$.ajax({
		type: "post",
		url: "activeEvent!getCheckedWebList"+acparam+"&triggerType=5",
		dataType : "text",
		success: function(v){
			if(v==""||v=="N"){
				return ;
			}
			var tableboby="";
			var app=v.split(",");
			var uuid="";
			var ss="";
			for (var i=0;i<app.length;i++)
			{
				uuid = new UUID().createUUID();
				if(i==7 && app.length>8){
					tableboby+="<div  class=\"gjc\" style=\"cursor:pointer;\" onclick=\"getAllApp('"+v+"');\">查看更多 ></div>";
					break;
				}
				if(app[i].length>6) ss=app[i].substr(0,6)+'…';
				else ss=app[i];
				tableboby+="<div id=\""+uuid+"\" style=\"cursor:pointer;\"  title=\""+app[i]+"\" ondblclick=\"deleteWeb('"+app[i]+"','"+uuid+"',1);\" class=\"gjc\">"+ss+"</div>";
			}
			$('#cftjt').html(tableboby);
		},
		error: function(){
			alert("App信息查询失败！");
		}
		});

}
function deleteWeb(a,d,t){
	 if(setStep!="cftj") return;
	 if(confirm("确定要删除出发条件'"+a+"'网站吗？")){
	 	var s=deleteSetProperty(a,5,1);
    	if(s=="Y"){
    		$("#"+d).remove();
		}
	 }
	 if(t==2){
		titleWeb();
	 }
}



function getAllApp(v){
	var tableboby="";
	var app=v.split(",");
	var uuid;
	for (var i=0;i<app.length;i++)
	{
		uuid = new UUID().createUUID();
		if(app[i].length>6) ss=app[i].substr(0,6)+'…';
		else ss=app[i];
		tableboby+="<div id=\""+uuid+"\" style=\"cursor:pointer;\"  title=\""+app[i]+"\" ondblclick=\"deleteWeb('"+app[i]+"','"+uuid+"',2);\" class=\"gjc\">"+ss+"</div>";
	}
	$('#yingyong').html(tableboby);
	$('#allTriggerMs').css("display","block");
}

function czApp(){
	$("[name='eventType']").removeAttr("checked");//取消全选
}

function allCheckApp(){
	$("[name='eventType']").attr("checked","checked");//取消全选
}

function qiehuanqq(ty){
	if(ty=='zy'){
		$('#zyapp').css("display","block");
		$('#fzyapp').css("display","none");
		$('#zyappt').css("background","#58a2db");
		$('#fzyappt').css("background","#FFFFFF");
	}else{
		$('#zyapp').css("display","none");
		$('#fzyapp').css("display","block");
		$('#fzyappt').css("background","#58a2db");
		$('#zyappt').css("background","#FFFFFF");
	}
}

function loadweblist(tid,lid){
	$.ajax({
		type: "post",
		url: "activeEvent!queryWebListByCid?webClassId="+tid,
		dataType : "text",
		success: function(v){
		//alert(v);
			if(v==""){
				$('#weblist').html("");
			}else{
				var webs=v.split('&');
				var tableboby="<table width=\"100%\">";
				var info;
				for(var i=0;i<webs.length-1;i++){
					info=webs[i].split(",");
					tableboby+="<tr>";
					tableboby+="<td width=\"15%\"><input type=\"checkbox\" name=\"eventType\" value=\""+info[1]+"_"+info[0]+"\" /></td>";
					tableboby+="<td width=\"85%\" >"+info[1]+"</td>";
					tableboby+="</tr>";
				}
				tableboby+="</table>";
				$('#weblist').html(tableboby);
			}
		}
		});
		qiqhuanliCss(lid);
}

function qiqhuanliCss(lid){
	for(var i=0;i<100;i++){

		var lii=$('#title'+lid);
		if($('#title'+i).length > 0)
		{
			$('#title'+i).css("background","#FFFFFF");
		}else{
			break ;
		}
	}
	$('#title'+lid).css("background","#58a2db");
}

</script>

	<div class="down">
		<div class="down_left2">
			<div class="down_left_bar2">
				<div class="down_left_top" style="background:url(images/down_left_top_bg.png); width:619px;">
			   <span style="color:#FFFFFF">触发事件设置</span>
			   <select class="text1" id="spr"  onChange="loaddiv(this.value)" style="width:100px">
				  <option value ="gjz"  >关键字</option>
				  <option value ="app" >APP</option>
				  <option value="wz">位置</option>
				  <option value="web" selected="selected">网站</option>
				</select>
		 </div>
	        </div>

            <div class="yingyong_nr">
			<p>应用类别(可多选）:<input type="button" value="全选" onclick="allCheckApp()" /> &nbsp;&nbsp;已选择： <font id="webcount" >0</font>&nbsp;个<font color="#FF0000">&nbsp;&nbsp;注：最多可选100个</font></p>

			<div class="yingyong_list">
			<%
				List<Map<String,String>> list=(ArrayList<Map<String,String>>) request.getAttribute("webTitleList");
				Map<String,String> amap=null;
				if(list!=null){
				for(int i=0 ;i<list.size();i++){
					amap=list.get(i);
			%>
			<% if(i%7==0){ %>
				<ul>
			<%}%>
				<li id="title<%=i %>" class="app_xz" style="cursor:pointer;" onclick="loadweblist('<%=amap.get("site_class_id") %>','<%=i %>')"><%=amap.get("site_class_name") %></li>
			<%if((i+1)%7==0||i==list.size()-1){ %>
				</ul>
			<%
				}
					}}
					%>
			</div>


			<div class="tj_btn">
			<ul style="list-style:none;">
			<li style="float:left;"><img src="images/tijiaoneirong_btn.png"/ style="cursor:pointer;" onclick="subWeb();"></li>
			<li style="float:left;margin-left:5px;"><img onclick="czApp();" src="images/chongzhi_btn.png" style="cursor:pointer;"/></li>
			</ul>
			</div>






			</div>
	  </div>


		<div class="down_right2">
			<div class="down_right_bar2">
					<ul>
					<li onclick="qiehuanqq('fzy');" style="color: #000000;cursor:pointer; width:340px;" id="fzyappt">网站信息</li>
					</ul>

				<div id="weblist" class="down_right_nr" style=" display:block;overflow-y:scroll;OVERFLOW-x: none; width:330px; height:256px;">

			   </div>
			</div>

		</div>
    </div>