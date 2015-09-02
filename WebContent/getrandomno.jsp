<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>营销场景</title>
</style>
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">

	function getRondomNo(){
		var code=$("#activeCode").val();
		if(code==''){
			alert("输入code");
		}
		var para="?";
		para+="activeCode="+code;
		$.ajax({
		type: "post",
		url: "sendMessageAction!getSendMsRandomNo"+para,
		dataType : "text",
		success: function(v){
			alert(v);
		}
		});

	}

</script>
</head>


<body>
<input type="text" style="width:200px;" id="activeCode" value="" />
<input type="button" onclick="getRondomNo();" value="search" />
</body>
</html>