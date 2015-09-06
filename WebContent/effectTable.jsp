 <%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.basetable th{padding:0px}
table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;}
th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:10px 5px 5px 10px;}
th{font-weight:bold;background:#ccc;}
#sort_head{ font-size:10px}
</style>
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>

<script type="text/javascript" >

function showActiveInfo(i){
$("#hidevalue").val(i);
$("#activeInfoForm").submit();
}

</script>
<input type="hidden" id="pageNum1" value="${page.pageNum}" />
<input type="hidden" id="pageCount1" value="${page.pageCount}" />
<table id="data_table" width="100%"  style="text-align:right;">
	<thead>
	<tr>
		<th class="sort_head" rowspan="2">活动名称</th>
		<th class="sort_head" rowspan="2" style="width:30px;">地市</th>
		<th class="sort_head" rowspan="2" style="width:80px;">活动开始时间</th>
		<th class="sort_head" rowspan="2" style="width:60px;">推送渠道</th>
		<th class="sort_head" rowspan="2">场景分类</th>
		<th class="sort_head" rowspan="2" style="width:70px;">目标客户量 </th>
		<th class="sort_head" rowspan="2" style="width:70px;">实际推送量</th>
<%-- 		<c:if test="${activeSendType =='' || empty activeSendType }"> --%>
<!-- 		<th class="sort_head" rowspan="2" style="width:70px;">成功办理量</th> -->
<%-- 		</c:if> --%>
		<c:if test="${activeSendType ==2 }">
		<th class="sort_head" rowspan="2" style="width:40px;">点击量</th>
		<th class="sort_head" rowspan="2" style="width:40px;">点击率</th>
		</c:if>
		<c:if test="${activeSendType !=2}">
		<th class="sort_head" rowspan="2" style="width:70px;">成功办理量</th>
		<th class="sort_head" rowspan="2" style="width:70px;">活动成功率</th>
		</c:if>
		<c:if test="${activeSendType ==2}">
		<th class="sort_head" rowspan="2" style="width:40px;">流量(kb)</th>
		</c:if>
		<th class="sort_head" rowspan="2" style="width:140px;">活动内容</th>
	</tr>
	</thead>
	<tbody>
	<c:choose>
	    <c:when test="${!empty effectList}" >

          	<c:forEach items="${effectList}" var="valueMap" varStatus="idx" >
				<tr style="line-height:14px;">
					<td style="text-align:left;">${valueMap["active_name"]}</td>
					<td style="text-align:center;">${valueMap["city_name"]}</td>
					<td style="text-align:left;">${fn:substring(valueMap["begin_time"],0,19)}</td>
					<c:if test="${valueMap.send_type == 2}">
						<td style="text-align:center;">掌上冲浪</td>
					</c:if>
					<c:if test="${valueMap.send_type == 5}">
						<td style="text-align:center;">网厅</td>
					</c:if>
					<c:if test="${valueMap.send_type == 6}">
						<td style="text-align:center;">掌厅</td>
					</c:if>
					<c:if test="${valueMap.send_type == 7}">
						<td style="text-align:center;">微厅</td>
					</c:if>
					<c:if test="${valueMap.send_type == 8}">
						<td style="text-align:center;">营销顾问前台</td>
					</c:if>
					<c:if test="${valueMap.send_type == ''||empty valueMap.send_type}">
						<td style="text-align:center;">掌上冲浪_</td>
					</c:if>
                    <c:if test="${valueMap.send_type == 10}">
						<td style="text-align:center;">智库</td>
					</c:if>
					<td style="text-align:left;">${valueMap["scene_name"]}</td>
					<td>${valueMap["use_val"]}</td>
					<td>${valueMap["send_count"]}</td>
					<td>${valueMap["tran_count"]}</td>
					<c:if test="${valueMap.send_count == 0}">
						<td>0 %</td>
					</c:if>
					<c:if test="${valueMap.send_count != 0}">
						<td>${fn:substring((valueMap.tran_count/valueMap.send_count)*100,0,4)} %</td>
					</c:if>
                    <c:if test="${activeSendType == 2 }">
					<td>${valueMap["flow_count"]/1000}</td>
					</c:if>
					<td  style="text-align:center;">
					<a href="javascript:showActiveSendInfo('${valueMap.active_code}','${valueMap.begin_time}');" style="text-decoration:none;">短信发送信息</a>|<a href="javascript:showActiveInfo('${valueMap.active_code}');" style="text-decoration:none;">查看</a><c:if test="${valueMap.tran_count > 0 && ! empty valueMap.product_id }">|<a href="javascript:downLoadPhone('${valueMap.active_code}');" style="text-decoration:none;">下载</a></c:if>

					</td>
				</tr>

			</c:forEach>

		</c:when>
		<c:otherwise>
			<tr><td colspan="999" style="text-align:center;">暂无数据</td></tr>
		</c:otherwise>
	</c:choose>

	</tbody>

</table>
<form action="activeAction!getActiveInfo" id="activeInfoForm" method="post" id="hideform" target="_blank" >
        <input type="hidden" id="hidevalue" name="activeCode" value=""/>
</form>