<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	var productName="";
	var typeName="";
	var productCode="";
	function selectYwcCode(v){
		if(v==""){
			return ;
		}
		typeName=v;
		$.ajax({
                type: "post",
                url: "codeAction!getDataBusiSmallType?typeName="+encodeURI(typeName),
                dataType : "text",
                success: function(v){
					$("#product_name").html(v);
                }

        });
	}
	var showSel="";
	function selectSmallCode(v){
		if(v==""){
			return ;
		}
		productCode=v.split("_")[1];
		productName=v.split("_")[0];
		showSel+="<input checked name=\"productCode\"  proname=\""+productName+"\" type=\"checkbox\" value=\""+productCode+"\" />"+typeName+">"+productName+"</br>";
		$("#ywcode").html(showSel);
	}

	function suywcode(){
		var checked = [];
		var checkName=[];
		$('input[name="productCode"]:checked').each(function() {
	            checked.push($(this).val());
				checkName.push($(this).attr("proname"));
	    });
		//alert(checkName);
		if(checked==''){
			alert("未选择业务！");
			return;
		}
		$("#productId").val(checked);
		$("#proCode").val(checkName);
		$("#proCode").attr("title",checkName);
		$("#selectBusCode").css("display","none");
		$("#selectBusCoded").css("display","none");
	}
</script>
</head>
<body></br>

    	    <p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="thtitle2">业务大类：</label>
				<select name="busi_big_type" size="1"  onchange="selectYwcCode(this.value);" id="busi_big_type" style="width:150px" >
					 <option value="">--请选择--</option>
					 <c:forEach items="${busicode}" var="typeName" varStatus="idx" >
		                 <option value="${typeName}" >${typeName}</option>

		             </c:forEach>
              </select>&nbsp;&nbsp;
			  <label class="thtitle2">业务小类：</label>
			  <select name="busi_big_type" size="1"  onchange="selectSmallCode(this.value);" id="product_name" style="width:250px" >
					 <option value="">--请选择--</option>
              </select>

</br></br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<label class="thtitle2">业务代码：</label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div style="margin-left: 100px;height: 200px;"><label class="thtitle2" id="ywcode"></label></div>

</br></br></br></br></br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="提交"  onClick="suywcode();">
            </p>
</body>

</html>
