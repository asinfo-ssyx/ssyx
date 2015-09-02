<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/popup.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

	var smywname="";
	var sywcode="";
	function selectYwcCode(v){
		if(v==""){
			return ;
		}
		smywname=v.split("_")[1];
		sywcode=v.split("_")[0];
		$("#ywcode").html(smywname+".业务代码["+sywcode+"]");
	}

	function suywcode(){
		$("#busCode").val(sywcode);
		$("#busName").val(smywname);
		$("#selectBusCode").css("display","none");
		$("#selectBusCoded").css("display","none");
	}
</script>
</head>
<body></br>

    	    <p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="thtitle2">业务大类：</label>
				<select name="busi_big_type" size="1"  onchange="selectYwcCode(this.value);" id="busi_big_type" style="width:250px" >
					 <option value="">--请选择--</option>
					 <c:forEach items="${busicode}" var="valueMap" varStatus="idx" >
		                 <option value="${valueMap.busi_code}_${valueMap.busi_name}" >${valueMap["busi_name"]}</option>

		             </c:forEach>
              </select>

</br></br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<label class="thtitle2">业务代码：</label><label class="thtitle2" id="ywcode"></label>

</br></br></br></br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="提交"  onClick="suywcode();">
            </p>
</body>

</html>
