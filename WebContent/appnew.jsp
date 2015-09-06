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
		titleApp();
	});

function subApp(){
	 var checked = [];
	 $('input:checkbox:checked').each(function() {
            checked.push($(this).val());
      });
	 checked=encodeURI(checked);
	 $.ajax({
		type: "post",
		url: "triggerAction!subCheckedApp?checked="+checked+"&activeCode="+activeCode,
		dataType : "text",
		beforeSend: function(XMLHttpRequest){
			$('#btn_tj').unbind("click");
		},
		success: function(v){
			//alert(v);
			if("Y"==v){
				titleApp();
			}else if("L"==v){
				alert("提交超过最大限制数");
			}
		},
		complete: function(XMLHttpRequest, textStatus){
			$('#btn_tj').bind("click","subApp");
		},
		error: function(){
			alert("App信息提交失败，请重新提交！");
		}
		});

}

function titleApp(){
	getCheckedAppCount();
	$.ajax({
		type: "post",
		url: "triggerAction!getCheckedAppList"+acparam,
		dataType : "text",
		success: function(v){
			if(v==""||v=="N"){
				return ;
			}
			var tableboby="";
			var apps=v.split("&");
			var uuid="";
			for (var i=0;i<apps.length-1;i++)
			{
				var app=apps[i].split(",");
				uuid = new UUID().createUUID();
				if(i==7 && apps.length-1>8){
					tableboby+="<div  class=\"gjc\" style=\"cursor:pointer;\" onclick=\"getAllApp('"+v+"');\">查看更多 ></div>";
					break;
				}
				tableboby+="<div id=\""+uuid+"\" style=\"cursor:pointer;\"  ondblclick=\"deleteApp('"+app[0]+"','"+uuid+"');\" class=\"gjc\">"+app[0]+"</div>";
			}
			$('#cftjt').html(tableboby);
		},
		error: function(){
			alert("App信息查询失败！");
		}
		});

}
function deleteApp(a,d){
	 if(setStep!="cftj") return;
	 if(confirm("确定要删除出发条件App'"+a+"'吗？")){
	 	var s=deleteSetProperty(a,2,1);
    	if(s=="Y"){
    		$("#"+d).remove();
		}
	 }else{

	 }
}



function getAllApp(v){
	var tableboby="";
	var apps=v.split("&");
	var uuid;
	for (var i=0;i<apps.length-1;i++)
	{
		var app=apps[i].split(",");
		uuid = new UUID().createUUID();
		tableboby+="<div id=\""+uuid+"\" style=\"cursor:pointer;\"  ondblclick=\"deleteApp('"+app[0]+"','"+uuid+"');\" class=\"gjc\">"+app[0]+"</div>";
	}
	$('#yingyong').html(tableboby);
	$('#allTriggerMs').css("display","block");
}

function czApp(){
	$("[name='checkbox']").removeAttr("checked");//取消全选
}

function allCheckApp(){
	$("[name='checkbox']").attr("checked","checked");//取消全选
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

function loadappinfo(tid,lid){
	$.ajax({
		type: "post",
		url: "triggerAction!getTypeAppList?appType="+tid,
		dataType : "text",
		success: function(v){
			if(v=="F"){
				alert("App信息查询失败！");
				return ;
			}
			var appi=v.split('_');
			for(var i=0;i<appi.length;i++){
				var apptype=appi[i];
				if(apptype=="N"){
					if(i==0){
						$('#zyapp').html("");
					}else{
						$('#fzyapp').html("");
					}
				}else{
				var apps=apptype.split("&");
				var htmlStr="<table  style=\"width:320px;border-spacing:0px;cellspacing=\"0\" cellpadding=\"0\"\">";
				for(var j=0;j<apps.length-1;j++){
					var appinfo=apps[j];
					if(j%4==0){
						htmlStr+="<tr>";
					}
					htmlStr+="<td style=\"line-height:1em;vertical-align:top;\" width=\"49px\" height=\"85px\" ><img width=\"49\" height=\"49\"  src=\""+appinfo.split(",")[2]+"\" /></br>";
					htmlStr+="<input type=\"checkbox\" name=\"checkbox\" value=\""+appinfo.split(",")[1]+"_"+appinfo.split(",")[0]+"\" />"+appinfo.split(",")[1]+"</td>";
					if((j+1)%4==0||j==apps.length-2){
						htmlStr+="</tr>";
					}
				}
				htmlStr+="</table>";
				if(i==0){
						$('#zyapp').html(htmlStr);
					}else{
						$('#fzyapp').html(htmlStr);
				}
			}
			}

			qiehuanqq('');
		},
		error: function(){
			alert("App信息查询失败！");
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

function getCheckedAppCount(){
	$.ajax({
			type: "post",
			url: "triggerAction!getCheckedAppCount"+acparam+"&triggerType=2",
			dataType : "text",
			success: function(v){
				$('#appcount').html(v);
			},
			});

	}

</script>

	<div class="down">
		<div class="down_left2">
			<div class="down_left_bar2">
				<div class="down_left_top" style="background:url(images/down_left_top_bg.png); width:619px;">
			   <span style="color:#FFFFFF">触发事件设置</span>
			   <select class="text1" id="spr"  onChange="loaddiv(this.value)" style="width:100px">
				  <option value ="gjz"  >关键字</option>
				  <option value ="app" selected="selected">APP</option>
				  <option value="wz">位置</option>
				  <option value="web">网站</option>

				</select>
		 </div>
	        </div>

            <div class="yingyong_nr">
			<p>应用类别(可多选）:<input type="button" value="全选" onclick="allCheckApp()" /> &nbsp;&nbsp;已选择： <font id="appcount" >0</font>&nbsp;个<font color="#FF0000">&nbsp;&nbsp;注：最多可选10个</font></p>

			<div class="yingyong_list">
			<%
				List<Map<String,String>> list=(ArrayList<Map<String,String>>) request.getAttribute("appTitleList");
				Map<String,String> amap=null;
				if(list!=null){
				for(int i=0 ;i<list.size();i++){
					amap=list.get(i);
			%>
			<% if(i%7==0){ %>
				<ul>
			<%}%>
				<li id="title<%=i %>" class="app_xz" style="cursor:pointer;" onclick="loadappinfo('<%=amap.get("apptypeid") %>','<%=i %>')"><%=amap.get("apptitle") %></li>
			<%if((i+1)%7==0||i==list.size()-1){ %>
				</ul>
			<%
				}
					}}
					%>
			</div>


			<div class="tj_btn">
			<ul style="list-style:none;">
			<li style="float:left;"><img src="images/tijiaoneirong_btn.png" style="cursor:pointer;" onclick="subApp();"></li>
			<li style="float:left;margin-left:5px;"><img onclick="czApp();" src="images/chongzhi_btn.png" style="cursor:pointer;"/></li>

			</ul>
			</div>






			</div>
	  </div>


		<div class="down_right2">
			<div class="down_right_bar2">
					<ul>
					<li onclick="qiehuanqq('fzy');" style="color: #000000;cursor:pointer;" id="fzyappt">非自有</li>
					<li onclick="qiehuanqq('zy');" style="background:#FFFFFF;color: #000000;cursor:pointer;" id="zyappt">自有</li>
					</ul>

				<div id="fzyapp" class="down_right_nr" style=" display:block;overflow-y:scroll;OVERFLOW-x: none; width:330px; height:256px;">

			   </div>

				<div id="zyapp" class="down_right_nr" style="display:none;overflow-y:scroll;OVERFLOW-x: none;width:330px; height:256px;">

			   </div>
			</div>

		</div>
    </div>