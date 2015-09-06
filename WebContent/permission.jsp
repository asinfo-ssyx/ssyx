<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="com.asiainfo.util.DataBaseJdbc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
session.removeAttribute("perUser");
String userId=request.getParameter("userId");
System.out.println("userIduserIduserId:"+userId);
Map<String,Object> user=DataBaseJdbc.getPermissionUserData(userId);
String microFlag="";//微区域营销跳转用

 if (request.getParameter("microFlag")!=null){
    microFlag = request.getParameter("microFlag");
 }
if(user==null){
	session.setAttribute("errorMs", "请使用正确账号登入");
	response.sendRedirect("errorms.jsp");
}else{ 
	session.setAttribute("perUser",user);
			String groupid="2";
		if (request.getParameter("groupid")!=null){
		  groupid= request.getParameter("groupid");
		}
		if (microFlag!=null && microFlag.equals("1")){

	   response.sendRedirect("effectIndexMicro.jsp?groupid="+groupid);
  }else if (microFlag!=null && microFlag.equals("2")){
	   response.sendRedirect("http://bass.scmcc.com.cn:9000/scbass/portal/microMarketing/marketingEffect.jsp?ssyxre=1&groupid="+groupid);
  }else{
	   response.sendRedirect("activeIndex.jsp");
	}
}
///permission.jsp?userId=sjb_yinshunmao 
%>
