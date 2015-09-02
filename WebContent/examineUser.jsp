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
	var czType=1;
	function updateExamineUser(id,name,phone,vgopid){
		czType=2;
		
	
	}
	
	function deleteExamineUser(id){
		$.ajax({
                type: "post",
                url: "manegerAction!deleteExamineUser?examineId="+id,
                dataType : "text",
                success: function(v){
					if(v=='Y'){
						alert('添加成功');
					}
                }
        });
	}
	
</script>

<table id="data_table" width="100%"  style="text-align:right;">
	<thead>
	<tr>
		<th class="sort_head" rowspan="2">地市</th>
		<th class="sort_head" rowspan="2">审核人姓名</th>
		<th class="sort_head" rowspan="2">审核人电话</th>
		<th class="sort_head" rowspan="2">审核人vgop_id</th>
		<th class="sort_head" rowspan="2">操作</th>
	</tr>
	</thead>
	<tbody>
	<c:choose>
	    <c:when test="${!empty showList}" >

          	<c:forEach items="${showList}" var="valueMap"  varStatus="idx" >
				<tr>
					<td>${valueMap["city_name"]}</td>
					<td style="text-align:left;">
						${valueMap["examine_name"]}
					</td>
					<td>${valueMap["examine_phone"]}</td>
					<td>${valueMap["examine_userid"]}</td>
					<td><a href="javascript:updateExamineUser('${valueMap.ex_no}','${valueMap.city_name}','${valueMap.examine_name}','${valueMap.examine_phone}','${valueMap.examine_userid}');">修改</a>|&nbsp&nbsp&nbsp&nbsp
					<a href="javascript:deleteExamineUser('${valueMap.ex_no}');">删除</a>
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
