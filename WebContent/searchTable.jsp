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
<input type="hidden" id="adminExamine" value="${adminStatus}" />
<table id="data_table" width="100%"  style="text-align:right;">
	<thead>
	<tr>
		<%

			Set<String> s=null;



			List<Map<String,Object>> list=(List<Map<String,Object>>)request.getAttribute("tableList");
			Map<String,Object> map=null;
		if(list!=null && list.size()>0){
				map=list.get(0);
				Map<String,Object> map1=new HashMap<String,Object>();
				Set<String> ks=map.keySet();
				for (String string : ks) {
					map1.put(string.toUpperCase(), "");
				}
				s=map1.keySet();
				for (String string : s) {
		%>
		<th class="sort_head" rowspan="2"><%=string %></th>

		<%
				}

		}
		%>
	</tr>
	</thead>
	<tbody>
		<%
		if(s!=null){
			for(int i=0;i<list.size();i++){
				Map<String,Object> mapr=list.get(i);
		%>
			<tr>
		<%
				for (String string : s) {
		%>

					<td><%=mapr.get(string) %></td>

		<%
				}
		%>
			</tr>
		<%
			}
		}else{
		%>
			<tr><td colspan="999">暂无数据</td></tr>
		<%
		}
		%>
	</tbody>

</table>
<form action="activeAction!getActiveInfo" id="activeInfoForm" method="post" id="hideform" target="_blank" >
        <input type="hidden" id="hidevalue" name="activeCode" value=""/>
</form>