<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.basetable th{padding:0px}
table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;}
th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:10px 5px 10px 5px;}
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
<input type="hidden" id="adminExamine" value="${adminStatus}" />
<div id="div_table">
<table id="data_table" width="100%"  style="text-align:right;">
	<thead>
	<tr>
		<th class="sort_head" rowspan="2" style="width:30px;">地市</th>
		<th class="sort_head" rowspan="2" >场景类型</th>
		<th class="sort_head" rowspan="2" >活动名称</th>
		<th class="sort_head" rowspan="2" style="width:92px;">活动编码</th>
		<th class="sort_head" rowspan="2" style="width:7%;">创建时间 </th>
		<th class="sort_head" rowspan="2" style="width:7%;">开始时间</th>
		<th class="sort_head" rowspan="2" style="width:7%;">结束时间</th>
		<!--th class="sort_head" rowspan="2"> 审核状态</th-->
		<th class="sort_head" rowspan="2" style="width:60px;"> 效果追踪</th>
		<th class="sort_head" rowspan="2" style="width:92px;"> 操作</th>
		<th class="sort_head" rowspan="2" style="width:72px;">审核状态</th>
		<c:if test="${exstatus}">
		<th class="sort_head" rowspan="2"  style="width:70px;">审批</th>
		</c:if>
	</tr>
	</thead>
	<tbody>
	<c:choose>
	    <c:when test="${!empty showList}" >

          	<c:forEach items="${showList}" var="valueMap"  varStatus="idx" >
				<tr style="line-height:14px;">
					<td style="text-align:center;">${valueMap["city_name"]}</td>
					<td style="text-align:left;">
						${valueMap["scene_name"]}
					</td>
					<td style="text-align:left;">${valueMap["active_name"]}</td>
					<td style="text-align:left;">${valueMap["active_code"]}</td>
					<td style="text-align:left;">${fn:substring(valueMap.create_time,0,19)}</td>
					<td style="text-align:left;">${fn:substring(valueMap.begin_time,0,19)}</td>
					<td style="text-align:left;">${fn:substring(valueMap.end_time,0,19)}</td>
					<!--td>
					<c:if test="${valueMap.status == '2'}">
						已审核
					</c:if>
					<c:if test="${valueMap.status == '1'}">
						待审核
					</c:if>
					<c:if test="${valueMap.status == '0'}">
						未提交审核
					</c:if>
					</td-->
					<td style="text-align:center;"><c:if test="${!empty valueMap.url && valueMap.url != '' && valueMap.url != 'null'}">
							<a href="http://${valueMap.url}" target="_Blank" style="text-decoration:none;">效果跟踪</a>
						</c:if>
					</td>
					<td  style="text-align:center;padding-left:0px;"><a href="javascript:showActiveInfo('${valueMap.active_code}');" style="text-decoration:none;">查看</a><c:if test="${exstatus ||valueMap.city_id eq perUser.cityId }">|<a href="javascript:deleteNowActive('${valueMap.active_code}',${valueMap.active_type});" style="text-decoration:none;">删除</a>|<a href="javascript:shutNowActive('${valueMap.active_code}');" style="text-decoration:none;">终止</a>
						</c:if>
					</td>
					<td style="text-align:center;">
						<c:if test="${valueMap.status == '2'}">
							审核通过
						</c:if>
						<c:if test="${valueMap.status == '5'}">
							待省公司审核
						</c:if>
						<c:if test="${valueMap.status == '7'}">
							待分公司审核
						</c:if>
						<c:if test="${valueMap.status == '6'}">
							未通过
						</c:if>
						<c:if test="${valueMap.status == '10'}">
							手动终止
						</c:if>
					</td>

					<c:if test="${exstatus && !adminStatus}">
					   <td style="text-align:center;">
					   	   <c:if test="${valueMap.status == '7' && valueMap.city_id eq exCityId }">
								<a href="javascript:agree('${valueMap.active_code}',${valueMap.active_type},'${fn:substring(valueMap.begin_time,0,19)}','${fn:substring(valueMap.end_time,0,19)}');" style="text-decoration:none;">通过</a>|<a href="javascript:noAgree('${valueMap.active_code}');" style="text-decoration:none;">不通过</a>
					   	   </c:if>
					   </td>
					</c:if>
					<!-- 省公司审核人 -->
					<c:if test="${adminStatus}">
					   <td style="text-align:center;">
					   	   <c:if test="${valueMap.status == '5'}"> <!-- 分公司审核完成状态5 -->
								<a href="javascript:agree('${valueMap.active_code}',${valueMap.active_type},'${fn:substring(valueMap.begin_time,0,19)}','${fn:substring(valueMap.end_time,0,19)}');" style="text-decoration:none;">通过</a>|<a href="javascript:noAgree('${valueMap.active_code}');" style="text-decoration:none;">不通过</a>
					   	   </c:if>
					   </td>
					</c:if>

				</tr>

			</c:forEach>

		</c:when>
		<c:otherwise>
			<tr><td colspan="999" style="text-align:center;">暂无数据</td></tr>
		</c:otherwise>
	</c:choose>
	</tbody>

</table>
</div>
<form action="activeAction!getActiveInfo" id="activeInfoForm" method="post" id="hideform" target="_blank" >
        <input type="hidden" id="hidevalue" name="activeCode" value=""/>
</form>