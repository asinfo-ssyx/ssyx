<%@page import="org.apache.taglibs.standard.tei.ForEachTEI"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>营销场景</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}

.overlay{position:fixed;top:0;right:0;bottom:0;left:0;z-index:998;width:100%;height:100%;_padding:0 20px 0 0;background:#f6f4f5;display:none;}
.showbox{position:fixed;top:0;left:50%;z-index:9999;opacity:0;filter:alpha(opacity=0);margin-left:-80px;}
*html,*html body{background-image:url(about:blank);background-attachment:fixed;}
*html .showbox,*html .overlay{position:absolute;top:expression(eval(document.documentElement.scrollTop));}
#AjaxLoading{border:1px solid #8CBEDA;color:#37a;font-size:12px;font-weight:bold;}
#AjaxLoading div.loadingWord{width:180px;height:50px;line-height:50px;border:2px solid #D6E7F2;background:#fff;}
#AjaxLoading img{margin:10px 15px;float:left;display:inline;}

.basetable th{padding:0px}
table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;}
th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;}
th{font-weight:bold;background:#ccc;}
#sort_head{ font-size:10px}
</style>
<!-- <script type="text/javascript" src="js/jquery.js"></script> -->
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>

<script type="text/javascript">
 $(document).ready(function(){
	 searchBlackList();
});
 var searchPara="?";
 var insertPara="";
 var showNum=15;
 var pageNum=1;
function searchBlackList(){
	console.log("search");
	searchPara+="showNum="+showNum+"&";
	searchPara+="pageNum="+pageNum+"&";
	$("#tab").load("blackListAction"+searchPara,function(response,status){
		var n=$("#pageNum1").val();
		var c=$("#pageCount1").val();
		console.log("pageNum "+n);
		console.log("pageCount "+c);
		$("#apageNum").html(n);
		$("#apageCount").html(c);
		endLoading();
		});
	searchPara="?";
	}
function queryData(){
	searchPara+="phoneNumber="+$("#phoneNumber").val()+"&";
	searchBlackList();
}
function insertData(){
	var phoneNumber=$("#phoneNumber").val();
	var type=$("#typeList").val();
	if(phoneNumber==""){
		alert("请输入要插入的电话号码！");
	}else if(type==""){
		alert("请选择要插入的名单类别！");
	}
	else{
	insertPara="?phoneNumber="+phoneNumber+"&"+"type="+type+"&";
	$.get("blackListAction!insert"+insertPara,function(data,status){
// 		var mess=${message};
        console.log(data);
 		alert(data);
		searchBlackList();	
	   },"text");
	
	}
}

function beginLoading(){
	var h = $(document).height();
	$(".overlay").css({"height": h });
	$(".overlay").css({'display':'block','opacity':'0.8'});
	$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1'},200);
}

function endLoading(){
	$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0'},400);
	$(".overlay").css({'display':'none','opacity':'0'});
}
//换页
function goPage(t){
	var c=$("#pageCount1").val();
	if(t=="g"){
		if(c==pageNum){
			return false;
		}
		pageNum+=1;
	}

	if(t=="b"){
		if(pageNum==1){
			return false;
		}
		pageNum-=1;
	}

	if(t=="begin"){
		if(pageNum==1){
			return false;
		}
		pageNum=1;
	}

	if(t=="end"){
		if(c==pageNum){
			return false;
		}
		pageNum=c;
	}
	searchBlackList();//默认刷新
}

function go(a){
	if(a==1){
		window.location.href="createActive.jsp";
	}else if(a==2){
		window.location.href="effectIndex.jsp";
	}
	else if(a==3){
		window.location.href="activeIndex.jsp";
	}
}
</script>
</head>


<body>
    <div id="all">
		<div id="top">
		      <input type="hidden" id="message" value="${message}" />
			<div style="padding-top:20px; padding-left:28px; float:left"><img src="images/top_logo.png" /></div>
			<ul id="nav">
				<li><img src="images/top_yxrwgl_1.png" onclick="go(3);" style="cursor:pointer;" /></li>
				<li><img src="images/top_yxrwcj_3.png" onclick="go(1);" style="cursor:pointer;" /></li>
				<li><img src="images/top_xggzbb_3.png" onclick="go(2);" style="cursor:pointer;"/></li>
			</ul>
		</div>
		<div id="content">
			<div id="left">
				<ul>
					<li><a href="manegerAction!queryProduct" target="_blank" > <img src="images/left_myactive_1.png" style="cursor:pointer;" /></a></li>
					<li><img src="images/left_fenactive_2.png" /></li>
					<li><img src="images/left_shengactive_1.png" /></li>
					<li><a href="manegerAction" target="_blank" > <img src="images/left_tiaozheng_1.png" style="cursor:pointer;" /></a></li>
					<li ><a href="/DemoProject/blackList.jsp" > <img src="images/left_myactive_1.png" style="cursor:pointer;" /></a></li>
				</ul>
			</div>
			<div id="right">
				<div id="bar"  style="padding-top:20px;height:45px;text-align:center; ">
				&nbsp;&nbsp;
				电话号码：
						<input type="text" name="phoneNumber" id="phoneNumber"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="查询" style="width:70px;height:28px;" onclick="queryData();"  />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select id="typeList">  
			                                          <option value ="">请选择名单类别</option>  
                                                      <option value ="1">黑名单</option>  
                                                      <option value ="2">白名单</option>  
                                                      <option value="3">红名单</option>  
                                                      <option value="4">测试号码</option>  
                                                     </select>  
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="添加" style="width:70px;height:28px;" onclick="insertData();"  />
				</div>
				<img src="images/loading.gif" id="loading" style="display: none;"/>
                <div id="tab" style="margin-top:20px;">

 				</div>

				<div style="text-align:center; line-height:40px;" >
                    <img style="cursor:pointer;"  onclick="goPage('begin');" src="images/mid_Last_1.png" />&nbsp;&nbsp;
					<img style="cursor:pointer;" onclick="goPage('b');" src="images/mid_before_1.png" />&nbsp;&nbsp;
					第 <font id="apageNum" ></font> 页/共 <font  id="apageCount"></font> 页
					<img style="cursor:pointer;" onclick="goPage('g');" src="images/mid_before_3.png" />&nbsp;&nbsp;
					<img style="cursor:pointer;" onclick="goPage('end');" src="images/mid_Last_3.png" /> &nbsp;&nbsp;
					<img style="cursor:pointer;" onclick="goPage('r');" src="images/mid_fresh.png" />刷新
                    </div>
              </div>
              
                <div id="footer">© 2013中国移动通信集团四川有限公司  版权所有
                </div>
			</div>
		</div>
    <div class="overlay"></div>

<div id="AjaxLoading" class="showbox">
	<div class="loadingWord"><img src="images/waiting.gif" />加载中，请稍候...</div>
</div>
</body>
</html>
