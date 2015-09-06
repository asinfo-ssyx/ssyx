<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>营销场景</title>

<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
.demo{margin:100px auto 0 auto;width:400px;text-align:center;font-size:18px;}
.demo .action{color:#3366cc;text-decoration:none;font-family:"微软雅黑","宋体";}

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
#bar td{border:0px;padding:5px 5px 5px 5px;}
</style>
<link href="js/skins/lhgcalendar.css" rel="stylesheet" type="text/css">
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script language="javascript"  src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(document).ready(function(){

		seachData();
	});

	var searchPara="";
	var activeType="";
	var activeStatus="2";
	var showNum=10;
	var pageNum=1;
	var scityId="";
	var startTime="";
	var endTime="";
	var activeScene="";
	var activeSendType="";
	var orderSta="";
	var sendStatus="";
	var activeName="";
	function setActiveSendType(v){
		activeSendType=v;
	}

	function search_active(){
		      beginLoading();
		$("#tab").load("effectAction!getMyActiveData"+searchPara,function(response,status){
				var n=$("#pageNum1").val();
				var c=$("#pageCount1").val();
				$("#apageNum").html(n);
				$("#apageCount").html(c);
				endLoading();
		});
	}

	function seachData(){
		startTime=$("#beginTime").val();
		endTime=$("#endTime").val();
		activeName=$("#activeName").val();
		searchPara="?";
		searchPara+="activeType="+activeType+"&";
		searchPara+="activeStatus="+activeStatus+"&";
		searchPara+="showNum="+showNum+"&";
		searchPara+="pageNum="+pageNum+"&";
		searchPara+="cityId="+scityId+"&";
		searchPara+="startTime="+encodeURI(startTime)+"&";
		searchPara+="endTime="+encodeURI(endTime)+"&";
		searchPara+="activeScene="+activeScene+"&";
		searchPara+="activeSendType="+activeSendType+"&";
		searchPara+="orderSta="+orderSta+"&";
		searchPara+="sendStatus="+sendStatus+"&";
		searchPara+="activeName="+activeName+"&";
		search_active();
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
	
	//页面跳转
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
		beginLoading();
		seachData();//默认刷新
	}

	function setScityId(v){
		//alert(v);
		scityId=v;
		pageNum=1;
		//seachData();
	}

	function setActiveScene(v){
		activeScene=v;
		pageNum=1;
	}
    function setSendStatus(v){
    	sendStatus=v;
    	pageNum=1;
    }
	function goto(a){
		if(a==1){
			window.location.href="createActive.jsp";
		}else if(a==2){
			window.location.href="activeIndex.jsp";
		}else if(a==3){
			window.location.href="effectAction!searchActiveCount";
		}else if(a==4){
			window.location.href="myActive.jsp";
		}else if(a==5){
			window.location.href="effectIndex.jsp";
		}
	}

	function queryData(){
		pageNum=1;
		beginLoading();
		seachData();
	}

	function showActiveSendInfo(code,btime){
		console.log("show");
		$.ajax({
			type: "post",
			url: "effectAction!searchActiveSendInfo?activeCode="+code+"&startTime="+btime,
			dataType : "text",
			success: function(v){
				 	alert(v);
			}
		});

	}

	function downLoadPhone(code){
		window.open('download!downLoadPhone?activeCode='+code+"&filename="+code+"_phone.txt");
	}

</script>
</head>


<body>
    <div id="all">
		<div id="top">
			<div style="padding-top:20px; padding-left:28px; float:left"><img src="images/top_logo.png" /></div>
			<ul id="nav">
				<li><img src="images/top_yxrwgl_3.png" onclick="goto(2);" style="cursor:pointer;"/></li>
				<li><img src="images/top_yxrwcj_3.png" onclick="goto(1);"  style="cursor:pointer;" /></li>
				<li><img src="images/top_xggzbb_1.png" onclick="goto(5);" style="cursor:pointer;"/></li>
			</ul>
		</div>
		<div id="content">
			<div id="left">
				<ul>
					<li><img src="images/left_myactive_1.png" onclick="goto(4);"  style="cursor:pointer;"/></li>
					<li><img src="images/xiaoguogenzong_2.png" onclick="goto(5);" style="cursor:pointer;"/></li>
<!-- 					<li><img src="images/left_shengactive_1.png" /></li> -->
					<li><img src="images/left_tiaozheng_1.png" /></li>
					<li><img src="images/active_count_n.png" onclick="goto(3);"  style="cursor:pointer;" /></li>
				</ul>
			</div>
			<div id="right">
				<div id="bar" style="height:80px;padding-top:10px;">
				<table style="border:0px;background:white;width:100%">
				<tr style="line-height:25px;border:0px;"><td style="width:30%;">
				活动地市：
				<select id="scity_id" onchange="setScityId(this.value);" style="width:150px;height:23px;"  >
						<option value="" selected="selected" >全部</option>
						<option value="999" title="999" >全省</option>
						<option value="20" >阿坝</option>
						<option value="10" >巴中</option>
						<option value="2" >成都</option>
						<option value="7" >达州</option>
						<option value="17" >德阳</option>
						<option value="21" >甘孜</option>
						<option value="9" >广安</option>
						<option value="6" >广元</option>
						<option value="15" >乐山</option>
						<option value="22" >凉山</option>
						<option value="8" >泸州</option>
						<option value="19" >眉山</option>
						<option value="3" >绵阳</option>
						<option value="13" >内江</option>
						<option value="18" >南充</option>
						<option value="5" >攀枝</option>
						<option value="11" >遂宁</option>
						<option value="16" >雅安</option>
						<option value="12" >宜宾</option>
						<option value="14" >资阳</option>
						<option value="4" >自贡</option>
					 </select>
				</td>
				<td style="width:30%;">
			  活动场景：
				<select id="scity_scene" style="width: 150px;height:23px;" onchange="setActiveScene(this.value);" >
						<option value="" selected="selected" >全部</option>
						<option value="1">基于上网行为的实时运营</option>
                        <option value="2">基于用户位置的实时运营</option>
                        <option value="4">终端换机7天运营</option>
                        <option value="5">重入网客户数据业务推荐</option>
                        <option value="6">终端内置应用激活</option>
                        <option value="7">自有业务交叉推荐</option>
                        <option value="8">0流量及低流量用户激活运营</option>
                        <option value="9">套餐归位运营</option>
                        <option value="10">数据业务高价值预警保有</option>
                        <option value="11">节假日及热点事件整合运营</option>
                        <option value="12">包月客户转全年包运营</option>
                        <option value="13">掌上冲浪客户端规模及活性提升</option>
						<option value="14">4G终端（换卡用户）流量及活性提升</option>
					 </select>
				</td>
				<td style="width:40%;">
				活动时间：
				<input type="text" class="Wdate" id="beginTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="height:21px;" value=""/>
						<font style="position:relative;" >到</font>
					  <input type="text" class="Wdate" id="endTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="height:21px;"  value=""/>
				</td>
				
				
				</tr>
				<tr style="line-height:25px;border:0px;">
<!-- 				<td style="width:30%;"> -->
<!-- 				发送状态： -->
<!-- 			     <select id="active_send_status"  onchange="setSendStatus(this.value);" style="width:150px;height:23px;"> -->
<!-- 						<option value="" selected="selected" >全部</option> -->
<!-- 						<option value="1">已审批</option> -->
<!--                         <option value="2">发送中</option> -->
<!--                         <option value="3">已发送</option> -->
<!--                         <option value="4">未成功</option> -->
<!-- 					 </select> -->
<!-- 				</td> -->
			   <td style="width:30%; ">
			         活动名称：
			 <input type="text" class="activeName" id="activeName" name="activeName" style="height:19px;" size="18"  value=""/> 
			   </td>
			   <td style="width:30%;">
				<input type="button" value="查询" style="width:70px;height:28px;" onclick="queryData();"  />
               </td>
               <td style="width:40%;"></td>
                 </tr>
                 </table>
				</div>

                <div id="tab" style="margin-top:20px;">

				<!-- 类表代码  -->



				<!-- 类表代码 end -->
 				</div>
 				
 				<div class="overlay"></div>
	      <div id="AjaxLoading" class="showbox">
	       <div class="loadingWord"><img src="images/waiting.gif">加载中，请稍候...</div>
        </div>
       
				<div style="text-align:left; line-height:40px; padding-left:70px; width:100%" >
                      <img style="cursor:pointer;"  onclick="goPage('begin');" src="images/mid_Last_1.png" />&nbsp;&nbsp;
					<img style="cursor:pointer;" onclick="goPage('b');" src="images/mid_before_1.png" />&nbsp;&nbsp;
					第 <font id="apageNum" ></font> 页/共 <font  id="apageCount"></font> 页
					<img style="cursor:pointer;" onclick="goPage('g');" src="images/mid_before_3.png" />&nbsp;&nbsp;
					<img style="cursor:pointer;" onclick="goPage('end');" src="images/mid_Last_3.png" /> &nbsp;&nbsp;
					<img style="cursor:pointer;" onclick="goPage('r');" src="images/mid_fresh.png" />刷新
              </div>
                <div id="footer">© 2013中国移动通信集团四川有限公司  版权所有
                </div>
			</div>
		</div>
    </div>
<div style="height:1200px;">

</div>

</body>
</html>
