<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.basetable th{padding:0px}
table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;}
th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;}
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
		<th class="sort_head" rowspan="2">name</th>
		<th class="sort_head" rowspan="2">code</th>
		<th class="sort_head" rowspan="2">countNum</th>
	</tr>
	</thead>
	<tbody>
	<c:choose>
	    <c:when test="${!empty busicode}" >

          	<c:forEach items="${busicode}" var="valueMap" varStatus="idx" >
				<tr>
					<td>${valueMap["active_name"]}</td>
					<td>${valueMap["active_code"]}</td>
					<td>${valueMap["userCount"]}</td>
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