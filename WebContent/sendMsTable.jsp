<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.basetable th{padding:0px}
table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;}
th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;}
th{font-weight:bold;background:#ccc;}
#sort_head{ font-size:10px}

.overlay{position:fixed;top:0;right:0;bottom:0;left:0;z-index:998;width:100%;height:100%;_padding:0 20px 0 0;background:#f6f4f5;display:none;}
.showbox{position:fixed;top:0;left:50%;z-index:9999;opacity:0;filter:alpha(opacity=0);margin-left:-80px;}
*html,*html body{background-image:url(about:blank);background-attachment:fixed;}
*html .showbox,*html .overlay{position:absolute;top:expression(eval(document.documentElement.scrollTop));}
#AjaxLoading{border:1px solid #8CBEDA;color:#37a;font-size:12px;font-weight:bold;}
#AjaxLoading div.loadingWord{width:180px;height:50px;line-height:50px;border:2px solid #D6E7F2;background:#fff;}
#AjaxLoading img{margin:10px 15px;float:left;display:inline;}
</style>
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<link href="css/ui-dialog.css" rel="stylesheet" type="text/css" />
<script language="javascript"  src="js/dialog.js"></script>
<script type="text/javascript" >
alert(1);
function showActiveInfo(i){
$("#hidevalue").val(i);
$("#activeInfoForm").submit();
}

function shutSendMs(code){
	 beginLoading();
	 $.ajax({
             type: "post",
             url: "effectAction!shutSendMs?activeCode="+code,
             dataType : "text",
             success: function(v){
                setInterval( endLoading,3000);  
             },
             error: function(){
                   alert("停止发送信息出错，请稍后重试！");
             }
     });
}
function beginLoading(){
		var h = $(document).height();
		$(".overlay").css({"height": h });
		$(".overlay").css({'display':'block','opacity':'0.8'});
		$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},200);
	}

	function endLoading(){
		$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);
		$(".overlay").css({'display':'none','opacity':'0'});
		location.reload();
	}
</script>
<table id="data_table" width="100%"  style="text-align:right;">
	<thead>
	<tr>
		<th class="sort_head" rowspan="2">活动名称</th>
		<th class="sort_head" rowspan="2">地市</th>
		<th class="sort_head" rowspan="2">活动开始时间</th>
		<th class="sort_head" rowspan="2">推送渠道</th>
		<th class="sort_head" rowspan="2">场景分类</th>
		<th class="sort_head" rowspan="2">目标客户量 </th>
		<th class="sort_head" rowspan="2">活动内容</th>
		<th class="sort_head" rowspan="2">操作</th>
	</tr>
	</thead>
	<tbody>
	<c:choose>
	    <c:when test="${!empty sendMsActiveList}" >

          	<c:forEach items="${sendMsActiveList}" var="valueMap" varStatus="idx" >
				<tr>
					<td>${valueMap["active_name"]}</td>
					<td>${valueMap["city_name"]}</td>
					<td>${valueMap["begin_time"]}</td>
					<c:if test="${valueMap.send_type == 2}">
						<td>掌上冲浪</td>
					</c:if>
					<c:if test="${valueMap.send_type == 5}">
						<td>网厅</td>
					</c:if>
					<c:if test="${valueMap.send_type == 6}">
						<td>掌厅</td>
					</c:if>
					<c:if test="${valueMap.send_type == 7}">
						<td>微厅</td>
					</c:if>
					<c:if test="${valueMap.send_type == 8}">
						<td>营销顾问前台</td>
					</c:if>
					<c:if test="${valueMap.send_type == ''||empty valueMap.send_type}">
						<td>掌上冲浪_</td>
					</c:if>

					<td>${valueMap["scene_name"]}</td>
					<td>${valueMap["use_val"]}</td>
					<td>
						<c:if test="${valueMap.sendStatus == 'n'}">正在发送</c:if>
						
						<c:if test="${valueMap.sendStatus == 'a'}">待发送</c:if>
						|
						<a href="javascript:showActiveInfo('${valueMap.active_code}');">查看</a>
					</td>
					<td>
						<a href="javascript:shutSendMs('${valueMap.active_code}');">停发</a>
					</td>
				</tr>

			</c:forEach>

		</c:when>
		<c:otherwise>
			<tr><td colspan="999">暂无数据</td></tr>
		</c:otherwise>
	</c:choose>
	</tbody>

</table>
<form action="activeAction!getActiveInfo" id="activeInfoForm" method="post" id="hideform" target="_blank" >
        <input type="hidden" id="hidevalue" name="activeCode" value=""/>
</form>
<div class="overlay"></div>

<div id="AjaxLoading" class="showbox">
	<div class="loadingWord"><img src="images/waiting.gif">加载中，请稍候...</div>
</div>

<div style="height:1200px;">

</div>