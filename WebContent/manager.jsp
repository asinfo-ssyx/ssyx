<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
session.removeAttribute("activeCode");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>营销场景</title>
<link href="css/createindex.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body, div, table, tr, td, ul, li, dl, dt, dd, p, h1, h2, h3, h4, h5, h6, img, form, select, input
   {
    margin: 0;
    padding: 0;}
</style>
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script language="javascript"  src="js/lhgcalendar.min.js"></script>
<script type="text/javascript">



</script>
</head>


<body>
<div class="top">
    <div class="nav">
                <div style="padding-top:20px; float:left"><img src="images/top_logo.png" /></div>

				<div class="top_xuanxiang">
				<ul >
                    <li onclick="goto('1')" style="cursor:pointer;">营销任务管理</li>
                    <li onclick="goto('2')" style="cursor:pointer;"><img src="images/top_yxrwcj_1.png" /></li>
                    <li  style="cursor:pointer;">效果跟踪报表</li>
                </ul>
				</div>

    </div>
</div>


<div class="main_xinxi" style="width:95%">
	<div class="main_xinxi_bar" style="width:100%">
	<span>地市发送量配置</span>	</div>

	<div class="main_xinxi_block" style="width:100%">
	   <div class="main_xinxi_block_step" style="width:100%">
		<table cellpadding="4" style="width:100%">
		<tbody >
			<tr>
			<td align="center" style="font-size:15px">地市</td>
			<td align="center" style="font-size:15px">周一</td>
			<td align="center" style="font-size:15px">周二</td>
			<td align="center" style="font-size:15px">周三</td>
			<td align="center" style="font-size:15px">周四</td>
			<td align="center" style="font-size:15px">周五</td>
			<td align="center" style="font-size:15px">周六</td>
			<td align="center" style="font-size:15px">周日</td>
			<td align="center" style="font-size:15px">操作</td>
			</tr>


			<tr>
			<td>地市</td>
			<td >周一</td>
			<td >周二</td>
			<td >周三</td>
			<td >周四</td>
			<td >周五</td>
			<td >周六</td>
			<td >周日</td>
			<td >操作</td>
			</tr>

		
		  </tbody>
		 </table>
	  </div>

			<div align="center" style="height:60px;" >
		
			</div>
	</div>
</div>

 <div class="footer" >@2013中国移动通信集团四川有限公司  版权所有 </div>
</body>
</html>

