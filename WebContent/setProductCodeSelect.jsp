<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/popup.css" rel="stylesheet" type="text/css" />
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	function suywcode(){
		var bigName=$.trim($("#setTypePname").val());
		var pNmae=$.trim($("#setPname").val());
		var priceCode=$.trim($("#setPrice").val());
		var busCode=$.trim($("#setBus").val());
		var proCode=$.trim($("#setPro").val());
		if(bigName==''){
			alert("选择业务类型");
			return;
		}
		if(pNmae==''){
			alert("填写业务名称");
			return;
		}
		if(priceCode==''&&busCode==''&&proCode==''){
			alert("编码不能全部为空");
			return;
		}
		var para="?";
		para+="bigName="+encodeURI(bigName)+"&";
		para+="proNmae="+encodeURI(pNmae)+"&";
		para+="priceCode="+priceCode+"&";
		para+="busCode="+busCode+"&";
		para+="proCode="+proCode+"&";
		$.ajax({
                type: "post",
                url: "manegerAction!setProductCode"+para,
                dataType : "text",
                success: function(v){
					if(v=='Y'){
						alert('添加成功');
					}
                }

        });
	}

	function setTypeName(v){
		$("#setTypePname").val(v);
	}


	function searchActive(){
		var sendMs=$.trim($("#sendMs").val());
		if(sendMs==''){
			alert("推送信息不能为空");
			return false;
		}
		var para="?";
		para+="sendMs="+encodeURI(sendMs)+"&";
		$.ajax({
            type: "post",
            url: "manegerAction!searchActive"+para,
            dataType : "text",
            success: function(v){
            	$("#activeInfo").html(v);
            }

   	    });
	}

	function showActiveInfo(i){
		$("#hidevalue").val(i);
		$("#activeInfoForm").submit();
	}
</script>
</head>
<body></br>

    	    <p>
				<label class="thtitle2">业务大类：</label>
				<select name="busi_big_type" size="1" id="busi_big_type" style="width:150px" onchange="setTypeName(this.value)" >
					 <option value="">--请选择--</option>
					 <c:forEach items="${busicode}" var="typeName" varStatus="idx" >
		                 <option value="${typeName}" >${typeName}</option>

		             </c:forEach>
              </select>&nbsp;&nbsp;<input type="text" id="setTypePname" value="" />
			  </br>
			  <label class="thtitle2">业务名称：</label>
			  <input type="text" id="setPname" value="" />
			  </br>
			  <label class="thtitle2">资费编码：</label>
			  <input type="text" id="setPrice" value="" />
			  </br>
			  <label class="thtitle2">企业编码：</label>
			  <input type="text" id="setBus" value="" />
			  &nbsp;&nbsp;
			  <label class="thtitle2">业务代码：</label>
			  <input type="text" id="setPro" value="" />

</br></br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="添加"  onClick="suywcode();">
            </p>
            </br></br>
<h2>查询活动</h2>
&nbsp;&nbsp;推送信息：<textarea maxchar="400" rows="3" cols="45" style="font-size:12px; width:450px;" id="sendMs" name="task_content" class=""> </textarea>
<input type="button" value="查询"  onClick="searchActive();">
</br>
&nbsp;&nbsp;查询结果:</br>
<font id="activeInfo"></font>
<form action="activeAction!getActiveInfo" id="activeInfoForm" method="post" id="hideform" target="_blank" >
        <input type="hidden" id="hidevalue" name="activeCode" value=""/>
</form>

</body>

</html>
