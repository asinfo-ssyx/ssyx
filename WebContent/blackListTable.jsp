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
<script>
		$(function(){
			console.log("blackListTable");
			var file = document.getElementById('myFile');
			var leadBu=document.getElementById('lead');
			if(file.value=="")
				leadBu.disabled=true;
			$(":checkbox[name='all']").click(function(){
				console.log("all");
				if(this.checked){
					console.log("all checked");
					$(":checkbox[name='child']").attr('checked',this.checked);
				}else{
					$(":checkbox[name='child']").removeAttr('checked');
				}
			})
		});
	function deleteData(){
		var value =new Array() ;
		var count=0;
	     $(":checkbox[name='child']").each(function(){
	    	     
					if($(this).attr('checked')){
						value[count]=$(this).closest('tr').find('.blackTd').text();
						count ++;
					}
				})
				console.log("count  :"+count);
		if(count==0) alert("请选择要删除的对象");
		else{
			if(confirm("确定删除吗？")){
				 $(":checkbox[name='child']").each(function(){
		    	     
						if($(this).attr('checked')){
							$(this).closest('tr').remove();
						}
					})
				console.log('个数'+count+'值:'+value);
			    var phones=JSON.stringify(value);
			    console.log("phones :"+phones);
				$.post("blackListAction!delete.action",{phones:phones},function(data,status){
					console.log(data);
					alert(data);
					searchBlackList();
				 },"text");	
			}
		  else{
			return ;
		  }
		}
			
	}
	function chooseFile(){
		console.log("choose");
		document.getElementById('lead').disabled=false;
	}
	function leadInData(){
		  var file = document.getElementById('myFile');
		  var type1=$("#typeList1").val();
          if (file.value =="") {
          alert("请选择您需要上传的文件！");
          return;
          }else if(type1==""){
        	  alert("请选择需要导入的类别！");
          }
          else{
           document.form1.submit();
           } 
		
	}
</script>
<input type="hidden" id="pageNum1" value="${page.pageNum}" />
<input type="hidden" id="pageCount1" value="${page.pageCount}" />
<div id="div_table" style="text-align:center;">
  <div style="width:42%;margin:auto;height:30px">
  <input type="button" class="delete" style="width:65px;height:28px;float:left;" value="删除" onclick="deleteData();"/>
  <form action="blackListAction!leadInData.action" method="post" enctype="multipart/form-data" style="float: left;" id="form1" name="form1">
  <input type="file" id="myFile" name="myFile" onchange="chooseFile();" style="width:140px;height:28px; float:left;margin-left:20px"/>
  <select id="typeList1"  name="typeList1" style=" float:left;margin-left:14px;margin-top:5px;">  
	<option value ="">请选择名单类别</option>  
    <option value ="1">黑名单</option>  
    <option value ="2">白名单</option>  
    <option value="3">红名单</option>  
    <option value="4">测试号码</option>  
 </select>
  <input type="button" value="导入" id="lead" style="width:70px;height:28px; float:left;margin-left:14px" onclick="leadInData();"/>
  </form>
  </div>
<table id="data_table" width="42%"  style="text-align:right; margin:auto ;margin-top:10px; ">
 <thead>
  <tr>
    <th class="sort_head" rowspan="2" style="text-align:left;"><input type="checkbox" name="all" /></th>
    <th class="sort_head" rowspan="2" width="80%" style="text-align:left;">电话号码</th>
  </tr>
<!--    <tr> -->
<!--     <th class="sort_head" rowspan="2" style="text-align:left;"><input type="button" class="delete" style="width:70px;height:28px;" value="删除" onclick="deleteData();"/></th> -->
<!--     <th class="sort_head" rowspan="2" width="80%" style="text-align:left;"> -->
<!--     <input type="file" id="myFile" name="myFile" style="width:160px;height:28px;"/> -->
<!--     <input type="button" value="导入" style="width:70px;height:28px;margin-left:10px;" onclick="leadInData();"/>  -->
<!--     </th> -->
<!--    </tr> -->
 </thead>
 <tbody>
   <c:choose>
     <c:when test="${!empty blackList}" >
       	<c:forEach items="${blackList}" var="valueMap"  varStatus="idx" >
          <tr style="line-height:14px;">
            <td style="text-align:left;"><input type="checkbox" name="child" /></td>
            <td style="text-align:left;" class="blackTd" name="blackTd" >${valueMap["phone_no"]}</td>
<%--             <td style="text-align:left;">${valueMap["descinfo"]}</td> --%>
          </tr>
       </c:forEach>
     </c:when>
   </c:choose>
 </tbody>
</table>
<!-- <div name="caozuo" style="width:150px;height:180px;float:left;" > -->
<!-- <input type="button" class="delete" style="width:70px;height:28px; margin-top: 50px;margin-left: 10px;float: left;" value="删除" onclick="deleteData();"/> -->
<!-- <form action="blackListAction!leadInData.action" method="post" enctype="multipart/form-data" style="width:150px;height:90px;float: left;" id="form1" name="form1"> -->
<!-- 				<input type="file" id="myFile" name="myFile" style="width:160px;height:28px;float: left;margin-left:10px;margin-top:10px"/> -->
<!-- 				<input type="button" value="导入" style="width:70px;height:28px;margin-left:10px;margin-top:10px;float:left;" onclick="leadInData();" />   -->
<!-- </form>	 -->
<!-- </div>	 -->
</div>