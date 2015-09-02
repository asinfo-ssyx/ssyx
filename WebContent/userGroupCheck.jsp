<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%
String groupName=request.getParameter("groupName");
String groupCode=request.getParameter("groupCode");
String customNum=request.getParameter("customNum");

%>
<script type="text/javascript">
	window.onload = function(){
        var name=decodeURIComponent("<%=groupName %>");
		var code=decodeURIComponent("<%=groupCode %>");
		var num =<%=customNum %>;
        parent.parent.setUserGroup(code,name,num);
    }
</script>
<html>
<body>

</body>
</html>