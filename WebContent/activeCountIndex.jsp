<%@page import="org.apache.taglibs.standard.tei.ForEachTEI"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>营销场景</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}

.overlay{position:fixed;top:0;right:0;bottom:0;left:0;z-index:998;width:100%;height:100%;_padding:0 20px 0 0;background:#f6f4f5;display:none;}
.showbox{position:fixed;top:0;left:50%;z-index:9999;opacity:0;filter:alpha(opacity=0);margin-left:-80px;}
*html,*html body{background-image:url(about:blank);background-attachment:fixed;}
*html .showbox,*html .overlay{position:absolute;top:expression(eval(document.documentElement.scrollTop));}
#AjaxLoading{border:1px solid #8CBEDA;color:#37a;font-size:12px;font-weight:bold;}
#AjaxLoading div.loadingWord{width:180px;height:50px;line-height:50px;border:2px solid #D6E7F2;background:#fff;}
#AjaxLoading img{margin:10px 15px;float:left;display:inline;}

.basetable th{padding:0px}
table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;}
th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;}
th{font-weight:bold;background:#ccc;}
#sort_head{ font-size:10px}
</style>

<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script language="javascript"  src="js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
	
	});

	function goto(a){
		if(a==1){
			window.location.href="createActive.jsp";
		}else if(a==2){
			window.location.href="activeIndex.jsp";
		}else if(a==4){
			window.location.href="effectIndex.jsp";
		}
	}

	function queryData(){
		var beginTime = $('#beginTime').val();
		var endTime =$('#endTime').val();
		$('#qbeginTime').val(beginTime);
		$('#qendTime').val(endTime);
		$('#queryForm').submit();
	}

	function download(){
		var beginTime = $('#beginTime').val();
		var endTime =$('#endTime').val();
		window.open('download!downloatScene?startTime='+ beginTime +'&endTime='+ endTime +'&filename=scene_report.xls');
		
	}
	
	
</script>
</head>


<body>
    <div id="all">
		<div id="top">
			<div style="padding-top:20px; padding-left:28px; float:left"><img src="images/top_logo.png" /></div>
			<ul id="nav">
				<li><img src="images/top_yxrwgl_3.png" onclick="goto(2);" style="cursor:pointer;"/></li>
				<li><img src="images/top_yxrwcj_3.png" onclick="goto(1);"  style="cursor:pointer;" /></li>
				<li><img src="images/top_xggzbb_1.png" /></li>
			</ul>
		</div>
		<div id="content">
			<div id="left">
				<ul>
					<li><img src="images/left_myactive_1.png" /></li>
					<li><img src="images/xiaoguogenzong_1.png"  onclick="goto(4);"  style="cursor:pointer;"/></li>
<!-- 					<li><img src="images/left_shengactive_1.png" /></li> -->
					<li><img src="images/left_tiaozheng_1.png" /></li>
					<li><img src="images/active_count_y.png" /></li>
				</ul>
			</div>
			<div id="right">
				<div id="bar"  style="padding-top:20px;height:45px;">
				&nbsp;&nbsp;
				活动时间：
						<input type="text" class="Wdate" id="beginTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${beginTime}"/>
						<font style="position:relative;" >到</font>
					  <input type="text" class="Wdate" id="endTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${endTime}"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="查询" style="width:70px;height:28px;" onclick="queryData();"  />
				
				<input type="button" value="导出Excel" style="width:80px;float:right;margin-right:60px;height:28px;" onclick="download();"  />
				</div>

                <div id="tab" style="margin-top:20px;">

				<!-- 类表代码  -->

				<table id="data_table" width="100%"  style="text-align:right;">

	<c:choose>
	    <c:when test="${!empty activeCount}" >
	<thead>

	<tr>
		<th class="sort_head" rowspan="2">地市</th>
	<c:forEach items="${activeCount.titleList}" var="valueMap" varStatus="idx" >
		<th class="sort_head" rowspan="2">${valueMap["scene_name"]}</th>
	</c:forEach>
		<th class="sort_head" rowspan="2">合计</th>
	</tr>
	</thead>
	<tbody>

			<%
				Map<String,List<Map<String,Object>>> countInfo=(Map<String,List<Map<String,Object>>>)request.getAttribute("activeCount");

				List<Map<String,Object>> countList=countInfo.get("countList");
				List<Map<String,Object>> titleList=countInfo.get("titleList");
			%>
			<%
				Map<String,Object> cmap=null;
				Map<String,Object> smap=null;
				String sceneKey="";
				for(int i=0;i<countList.size();i++){
					cmap=countList.get(i);
			%>
				<tr>
					<td><%=cmap.get("cityName") %></td>
					<%
						for(int j=0;j<titleList.size();j++){
							smap=titleList.get(j);
							sceneKey=smap.get("scene_id").toString();
					%>
						<td><%=cmap.get(sceneKey) %></td>
					<%
						}
					%>
					<td><%=cmap.get("cityCount") %></td>
				</tr>

			<%
				}
			%>
		</c:when>
		<c:otherwise>
			<tr><td colspan="999">暂无数据</td></tr>
		</c:otherwise>
	</c:choose>
	</tbody>

</table>


				<!-- 类表代码 end -->
 				</div>

				<div style="text-align:left; line-height:40px; padding-left:70px; width:100%" >

              </div>
                <div id="footer">© 2013中国移动通信集团四川有限公司  版权所有
                </div>
			</div>
		</div>
    </div>
<div style="height:1200px;">

</div>
<form action="effectAction!searchActiveCount" method="post" id="queryForm" >
	<input type="hidden" value="" id="qbeginTime"  name="startTime"  />
	<input type="hidden" value="" id="qendTime"    name="endTime"  />
</form>
</body>
</html>
