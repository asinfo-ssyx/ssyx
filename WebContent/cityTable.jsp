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
	function execute(id){
		var citycount=$("#"+id).val();
		if(citycount==''||citycount=='null'){
			alert("推送数量不能为空");
			return false;
		}
		var butv=$("#but"+id).val();
		if(butv=='修改'){
			$("#"+id).removeAttr("disabled");
			$("#but"+id).val("更新");
		}else{
			$.ajax({
			type: "post",
			url: "manegerAction!updateCityCount?scaleId="+id+"&cityCount="+citycount,
			dataType : "text",
			success: function(v){
				if("Y"==v){
					$("#"+id).attr("disabled","disabled");
					$("#but"+id).val("修改");	
				}
			}
			});
		
		}
		
		
		
	}
</script>
<table id="data_table" width="100%"  style="text-align:right;">
	<thead>
	<tr>
		<th class="sort_head" width="120px;" rowspan="2">地市</th>
		<th class="sort_head" width="120px;" rowspan="2">星期1</th>
		<th class="sort_head" width="120px;" rowspan="2">星期2</th>
		<th class="sort_head" width="120px;" rowspan="2">星期3</th>
		<th class="sort_head" width="120px;" rowspan="2">星期4</th>
		<th class="sort_head" width="120px;" rowspan="2">星期5</th>
		<th class="sort_head" width="120px;" rowspan="2">星期6</th>
		<th class="sort_head" width="120px;" rowspan="2">星期7</th>
	</tr>
	</thead>
	<tbody>
		<%
			
			Map<String,Map<String,Object>> cityMap=(Map<String,Map<String,Object>>)request.getAttribute("cityMap");
			
			
		if(cityMap!=null){
			Set<String> set=cityMap.keySet();
			for (String string : set) {
				Map<String,Object> m=cityMap.get(string);
		%>
			<tr>
					<td><%=string %></td>
					<td><input type="text" style="width:65px;" value='<%=m.get("1")%>' id='<%=m.get("1cid")%>' disabled="disabled" />
						<input type="button"  value="修改" id="but<%=m.get("1cid")%>" onclick="execute('<%=m.get("1cid")%>');" /></td>
					<td><input type="text" style="width:65px;" value='<%=m.get("2")%>' id='<%=m.get("2cid")%>' disabled="disabled" />
						<input type="button"  value="修改" id="but<%=m.get("2cid")%>"  onclick="execute('<%=m.get("2cid")%>');" /></td>
					<td><input type="text" style="width:65px;" value='<%=m.get("3")%>' id='<%=m.get("3cid")%>' disabled="disabled" />
						<input type="button"  value="修改" id="but<%=m.get("3cid")%>"  onclick="execute('<%=m.get("3cid")%>');" /></td>
					<td><input type="text" style="width:65px;" value='<%=m.get("4")%>' id='<%=m.get("4cid")%>' disabled="disabled" />
						<input type="button"  value="修改" id="but<%=m.get("4cid")%>"  onclick="execute('<%=m.get("4cid")%>');" /></td>
					<td><input type="text" style="width:65px;" value='<%=m.get("5")%>' id='<%=m.get("5cid")%>' disabled="disabled" />
						<input type="button"  value="修改" id="but<%=m.get("5cid")%>" onclick="execute('<%=m.get("5cid")%>');" /></td>
					<td><input type="text" style="width:65px;" value='<%=m.get("6")%>' id='<%=m.get("6cid")%>' disabled="disabled" />
						<input type="button"  value="修改" id="but<%=m.get("6cid")%>" onclick="execute('<%=m.get("6cid")%>');" /></td>
					<td><input type="text" style="width:65px;" value='<%=m.get("7")%>' id='<%=m.get("7cid")%>' disabled="disabled" />
						<input type="button"  value="修改" id="but<%=m.get("7cid")%>" onclick="execute('<%=m.get("7cid")%>');" /></td>
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