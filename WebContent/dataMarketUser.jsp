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
		var db1=$("#dateBase").val();
		var dbname=$("#dateBase").find("option:selected").text();
		var tn=$("#tableName").val();
		var un=$("#groupName").val();
		if(tn==''){
			alert("请填写数据表名称");
			return;
		}
		if(un==''){
			alert("请填写用户群名称");
			return;
		}
		parent.userGroup_code=tn;
		parent.userGroup_name=un;
		parent.dataBaseS=db1;
		parent.$("#userGroupTd").html(dbname+":"+un);
		parent.$("#userGroupIframe").css("display","none");
		parent.$("#userGroupIframeTitle").css("display","none");
		parent.$('#userGroupIframe').attr("src","about:blank");
	}
</script>
</head>
<body style="background-color:#FFFFFF"></br>

    	    <p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="thtitle2">数据集市库：</label>
				<select  id="dateBase" style="width:150px" >
					 <option value="68">68数据库</option>
					 <option value="72">72数据库</option>
					 <option value="73">73数据库</option>
              	</select>&nbsp;&nbsp;
			  </br></br>
			  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  <label class="thtitle2">表&nbsp;&nbsp;名&nbsp;&nbsp;称：</label>
			  <input type="text" value=""  style="width:400px;" id="tableName"/>

</br></br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<label class="thtitle2">用户群名称：</label>&nbsp;<input type="text" value="" style="width:400px;" id="groupName"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div style="margin-left: 100px;height: 100px;"><label class="thtitle2" id="ywcode"></label></div>

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
