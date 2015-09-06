<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/queren.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function gotoIndex(){
                window.location.href="activeIndex.jsp";
}
function goto(){
                window.location.href="createActive.jsp";
}

function downloadFile(activeCode){
	window.open('download!downloadFile?activeCode='+activeCode+"&filename="+activeCode+".txt");
}
</script>
</head>


<body>
    <div id="all">
                <div id="top">
            <div id="nav">
                <div style="padding-top:20px; padding-left:0px; float:left"><img src="images/top_logo.png" /></div>
                <ul>
                    <li><img src="images/top_yxrwgl_3.png" style="cursor:pointer;" onclick="gotoIndex();" /></li>
                    <li><img src="images/top_yxrwcj_1.png"  onclick="goto();" style="cursor:pointer;"/></li>
                    <li><img src="images/top_xggzbb_3.png" /></li>
                </ul>
            </div>
                </div>
        <div id="crumbs">活动信息:
        </div>

                <div id="comtent2">

            	<img src="images/qr_icon_hdjcsz.png"/> &nbsp;&nbsp;&nbsp;&nbsp; 
				<input type="button" onclick="downloadFile('${activeAllInfo.activeInfo.active_code}')" value="下载活动信息" />

           	 <div id="box">
                <h1>基础信息
                </h1>
				<c:if test="${!empty activeAllInfo.activeInfo.advice_message}">
					 <p><span style="color:#FF0000;">审核不通过原因:${activeAllInfo.activeInfo.advice_message}</span></p>
			 	</c:if>
                <p>

                    <span>活动名称:${activeAllInfo.activeInfo.active_name}</span>
                    <span style="float:right">
                    </span>
                </p>
                                <p>
                    <span>活动内容:${activeAllInfo.activeInfo.active_title}</span>
                    <span style="float:right">
                    </span>
                </p>
                <p>
                    <span>活动周期:
                                        ${fn:substring(activeAllInfo.activeInfo.begin_time,0,19)}
                                        <font style="position:relative;left:0px;" >到</font>
                                        ${fn:substring(activeAllInfo.activeInfo.end_time,0,19)}
                                        </span>
                    <span style="float:right">
                    </span>
                </p>
                                <c:if test="${!empty activeAllInfo.activeInfo.active_ms_type}">
                <p>效果跟踪：</p>
                <div id="genz" style="display:block;">

                        <span></span>
                                           <c:if test="${activeAllInfo.activeInfo.active_ms_type == 1}">
                                                 <p><span>类&nbsp;&nbsp;&nbsp;&nbsp; 型：产品推荐</span></p>
                                                 <p><span>产品代码：${activeAllInfo.activeInfo.b_active_code}</span></p>
                                           </c:if>




                                        <c:if test="${activeAllInfo.activeInfo.active_ms_type == 2}">
                                                <p><span>类&nbsp;&nbsp;&nbsp;&nbsp; 型：内容推送</span>
                                                <span>URL地址：${activeAllInfo.activeInfo.url}</span></p>
                                        </c:if>
                </div>
                           </c:if>
            </div>
        </div>

        <div id="comtent1" >
            <img src="images/qr_icon_ysznr.png" />
            <div id="box">
                <h1>触发条件
                </h1>
                                <c:if test="${!empty activeAllInfo.trigger }">

                                        <c:forEach items="${activeAllInfo.trigger}" var="valueMap" varStatus="idx" >
                                                <p>类型:
                                                <c:if test="${valueMap.trigger_type == '1'}">
                                                        <span>关键词</span>
                                                </c:if>
                                                <c:if test="${valueMap.trigger_type == '2'}">
                                                        <span>APP</span>
                                                </c:if>
                                                <c:if test="${valueMap.trigger_type == '0'}">
                                                        <span>位置</span>
                                                </c:if>
                                                <c:if test="${valueMap.trigger_type == '4'}">
                                                        <span>终端换机</span>
                                                </c:if>
												<c:if test="${valueMap.trigger_type == '5'}">
                                                        <span>网站</span>
                                                </c:if>
                                                </p>


                                                <p>内容:<span>${valueMap.trigger_ms}</span> </p>
                                        </c:forEach>
                                </c:if>

                <div id="line"></div>

                            <h1>用户群
                </h1>
                                <c:if test="${!empty activeAllInfo.userGroup }">
                                        <p>类型:
                                        <c:forEach items="${activeAllInfo.userGroup}" var="valueMap" varStatus="idx" >
                                                 <span>${valueMap.table_col} </span>
                                        </c:forEach>
                                        </p>
                                </c:if>

                                <div id="line"></div>

                            <h1>客户接触管理
                </h1>
                                <c:if test="${!empty activeAllInfo.gluserGroup }">
                                <p>类型:
                                        <c:forEach items="${activeAllInfo.gluserGroup}" var="valueMap" varStatus="idx" >
                                                 <span>${valueMap.table_col} </span>
                                        </c:forEach>
                                </p>
                                </c:if>

                <div id="line"></div>

                <h1>推送渠道及内容</h1>
                                <c:if test="${!empty activeAllInfo.sendMessage }">
                                        <c:forEach items="${activeAllInfo.sendMessage}" var="valueMap" varStatus="idx" >
                                                <p>类型:
                                                <c:if test="${valueMap.send_type == '1'}">
                                                        <span>10086短信</span>
                                                </c:if>
                                                <c:if test="${valueMap.send_type == '2'}">
                                                        <span>掌上冲浪</span>
                                                </c:if>
                                                <c:if test="${valueMap.send_type == '3'}">
                                                        <span>营销顾问前台</span>
                                                </c:if>
												<c:if test="${valueMap.send_type == '5'}">
                                                        <span>网厅</span>
                                                </c:if>
												<c:if test="${valueMap.send_type == '6'}">
                                                        <span>掌厅</span>
                                                </c:if>
												<c:if test="${valueMap.send_type == '7'}">
                                                        <span>微厅</span>
                                                </c:if>
												<c:if test="${valueMap.send_type == '8'}">
                                                        <span>营销顾问前台</span>
                                                </c:if>
                                                <c:if test="${valueMap.send_type == '10'}">
                                                        <span>智库</span>
                                                </c:if>
                                                </p>
                                                <c:if test="${valueMap.send_type == '10'}">
                                                <p>区县：                               
                                                <span>${valueMap.active_rule}</span>
                                                </p>
                                                </c:if>
                                              
                                                <c:if test="${valueMap.send_type != '10'}">
                                                <p>内容：
                                                <span>${valueMap.send_ms}</span>
                                                </p>
                                                </c:if>
                                        </c:forEach>
                                </c:if>
            </div>
        </div>

        <div id="footer">© 2013中国移动通信集团四川有限公司  版权所有
    </div>
</body>
</html>