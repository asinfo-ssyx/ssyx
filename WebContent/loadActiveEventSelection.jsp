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
		loadEventTypedata();
	});
	
function subCheckedEvent(){

	var eventType=$('input[name="eventType"]:checked').val();
	if(eventType==""||eventType==undefined){
		alert("请先选择活动事件类型！");
		return ;
	}
	eventType=encodeURI(eventType);
	 $.ajax({
		type: "post",
		url: "activeEvent!subCheckedEvent?checked="+eventType+"&activeCode="+activeCode,
		dataType : "text",
		success: function(v){
			if("R"==v){
				alert("已经选择了事件类型，无法在提交！双击可以删除事件。");
			}else{
				var rs=v.split("#");
				if(rs[0]=="Y"){
					loadEventTypedata();
					subUserGroup(rs[2],rs[1]);
				}
			}
		}
		});

}

function subUserGroup(userGroup_code,userGroup_name){
		var para="?groupName="+userGroup_name+"&searchsql="+userGroup_code+"&activeCode="+activeCode;
		para=encodeURI(para);
		$.ajax({
		async: false,
		type: "post",
		url: "userGroup!selectedUserGroupInsert"+para,
		dataType : "text",
		success: function(v){
			loadyhqdata();
		}
		});
}


function loadtype(st,lid){
	$.ajax({
		type: "post",
		url: "activeEvent!getEventNameList?eventType="+acitveScene+"&seventType="+st,
		dataType : "text",
		success: function(v){
			var tableboby="";
			if(v!=""){
				tableboby+="<table width=\"100%\">";
				var ts=v.split('Y_Y');
				var info;
				for(var i=0;i<ts.length-1;i++){
					info=ts[i].split("#");
					tableboby+="<tr>";
					tableboby+="<td width=\"15%\"><input type=\"radio\" name=\"eventType\" value=\""+info[1]+"_"+info[0]+"\" /></td>";
					tableboby+="<td width=\"85%\" title=\""+info[2]+"\">"+info[0]+"</td>";
					tableboby+="</tr>";
				}
				tableboby+="</table>";
			}
			$('#eventTypeDiv').html(tableboby);
		}
		});
		qiqhuanliCss(lid);
}

function qiqhuanliCss(lid){
	for(var i=0;i<20;i++){
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
		 </div>
	        </div>

            <div class="yingyong_nr">
			<p>应用类别(可多选）:</p>

			<div class="yingyong_list">
			<%
				List<Map<String,String>> list=(ArrayList<Map<String,String>>) request.getAttribute("activeEventTypeList");
				Map<String,String> amap=null;
				if(list!=null){
				for(int i=0 ;i<list.size();i++){
					amap=list.get(i);
			%>
			<% if(i%7==0){ %>
				<ul>
			<%}%>
				<li id="title<%=i %>" class="app_xz" style="cursor:pointer;" onclick="loadtype('<%=amap.get("s_type_code") %>','<%=i %>')"><%=amap.get("s_event_type") %></li>
			<%if((i+1)%7==0||i==list.size()-1){ %>
				</ul>
			<%
				}
					}}
					%>
			</div>


			<div class="tj_btn">
			<ul style="list-style:none;">
			<li style="float:left;"><img src="images/tijiaoneirong_btn.png"/ style="cursor:pointer;" onclick="subCheckedEvent();"></li>
			<li style="float:left;margin-left:5px;"><img onclick="czApp();" src="images/chongzhi_btn.png" style="cursor:pointer;"/></li>
			</ul>
			</div>

			</div>
	  </div>


		<div class="down_right2">
			<div class="down_right_bar2">
					<ul>
					<li  style="color: #000000;cursor:pointer;" >类型名称</li>
					</ul>

				<div id="eventTypeDiv" class="down_right_nr" style=" display:block;overflow-y:scroll;OVERFLOW-x: none; width:330px; height:256px;">

			   </div>
			</div>

		</div>
    </div>