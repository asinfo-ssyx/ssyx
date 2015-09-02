<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
		String groupid="";
		if (request.getParameter("groupid")!=null){
		  groupid= request.getParameter("groupid");
		}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>营销场景</title>
<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
.demo{margin:100px auto 0 auto;width:400px;text-align:center;font-size:18px;}
.demo .action{color:#3366cc;text-decoration:none;font-family:"微软雅黑","宋体";}

.overlay{position:fixed;top:0;right:0;bottom:0;left:0;z-index:998;width:100%;height:100%;_padding:0 20px 0 0;background:#f6f4f5;display:none;}
.showbox{position:fixed;top:0;z-index:9999;opacity:0;filter:alpha(opacity=0);margin-left:-80px;}
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
     queryData();
	
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
	function setActiveSendType(v){
		activeSendType=v;
	}

	function search_active(){
		$("#tab").load("effectAction"+searchPara,function(response,status){
				var n=$("#pageNum1").val();
				var c=$("#pageCount1").val();

				$("#apageNum").html(n);
				$("#apageCount").html(c);
				endLoading();
		});
	}

	function seachData(){
		startTime=$("#beginTime").val();
		endTime =$("#endTime").val();
		orderSta=$("#orderSta").val();

		searchPara="?";
		searchPara+="activeType="+activeType+"&";
		searchPara+="activeStatus="+activeStatus+"&";
		searchPara+="showNum="+showNum+"&";
		searchPara+="pageNum="+pageNum+"&";
		searchPara+="cityId="+$("#scity_id").val()+"&";
		searchPara+="startTime="+encodeURI(startTime)+"&";
		searchPara+="endTime="+encodeURI(endTime)+"&";
		searchPara+="activeScene="+$("#scity_scene").val()+"&";
		searchPara+="activeSendType="+$("#active_send_type").val()+"&";
		searchPara+="orderSta="+orderSta+"&";
     
		search_active();
	}

	function beginLoading(){
		var h = $(document).height();
		$(".overlay").css({"height": h });
		$(".overlay").css({'display':'block','opacity':'0.8'});
		$(".showbox").stop(true).animate({'margin-top':'300px','opacity':'1','left':'50%'},200);
	}

	function endLoading(){
		$(".showbox").stop(true).animate({'margin-top':'250px','opacity':'0','left':'0%'},400);
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

	function goto(a){
		if(a==1){
			window.location.href="createActive.jsp";
		}else if(a==2){
			window.location.href="activeIndex.jsp";
		}else if(a==3){
			window.location.href="effectAction!searchActiveCount";
		}
	}

	function queryData(){
		pageNum=1;
		beginLoading();
		seachData();
	}

	function showActiveSendInfo(code,btime){
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
    <div id="all" style="border:#c3c8bd 1px solid;width:100%;">

		<div id="content">
			<div id="right" style="width:100%;">
				<div id="bar" style="height:80px;padding-top:10px;width:96.5%;">
				<table style="border:0px;background:white;width:100%">
				<tr style="line-height:25px;border:0px;"><td style="width:30%;">
				活动地市：
				<select id="scity_id" onchange="setScityId(this.value);" style="width:150px;height:23px;"  >
						<option value="" selected="selected" >全部</option>
						<option value="999" title="999" >全省</option>
						<option value="20" <%if (groupid!=null && groupid.equals("20")){%> selected<%}%>>阿坝</option>
						<option value="10" <%if (groupid!=null && groupid.equals("10")){%> selected<%}%>>巴中</option>
						<option value="2" <%if (groupid!=null && groupid.equals("2")){%> selected<%}%>>成都</option>
						<option value="7" <%if (groupid!=null && groupid.equals("7")){%> selected<%}%>>达州</option>
						<option value="17" <%if (groupid!=null && groupid.equals("17")){%> selected<%}%>>德阳</option>
						<option value="21" <%if (groupid!=null && groupid.equals("21")){%> selected<%}%>>甘孜</option>
						<option value="9" <%if (groupid!=null && groupid.equals("9")){%> selected<%}%>>广安</option>
						<option value="6" <%if (groupid!=null && groupid.equals("6")){%> selected<%}%>>广元</option>
						<option value="15" <%if (groupid!=null && groupid.equals("15")){%> selected<%}%>>乐山</option>
						<option value="22" <%if (groupid!=null && groupid.equals("22")){%> selected<%}%>>凉山</option>
						<option value="8" <%if (groupid!=null && groupid.equals("8")){%> selected<%}%>>泸州</option>
						<option value="19" <%if (groupid!=null && groupid.equals("19")){%> selected<%}%>>眉山</option>
						<option value="3" <%if (groupid!=null && groupid.equals("3")){%> selected<%}%>>绵阳</option>
						<option value="13" <%if (groupid!=null && groupid.equals("13")){%> selected<%}%>>内江</option>
						<option value="18" <%if (groupid!=null && groupid.equals("18")){%> selected<%}%>>南充</option>
						<option value="5" <%if (groupid!=null && groupid.equals("5")){%> selected<%}%>>攀枝</option>
						<option value="11" <%if (groupid!=null && groupid.equals("11")){%> selected<%}%>>遂宁</option>
						<option value="16" <%if (groupid!=null && groupid.equals("16")){%> selected<%}%>>雅安</option>
						<option value="12" <%if (groupid!=null && groupid.equals("12")){%> selected<%}%>>宜宾</option>
						<option value="14" <%if (groupid!=null && groupid.equals("14")){%> selected<%}%>>资阳</option>
						<option value="4" <%if (groupid!=null && groupid.equals("4")){%> selected<%}%>>自贡</option>
					 </select>
				</td>
				<td style="width:30%;">
				&nbsp;&nbsp;&nbsp;&nbsp;活动场景：
				<select id="scity_scene" style="width: 150px;height:23px;" onchange="setActiveScene(this.value);" >
						<option value="" >全部</option>
						<option value="1"  selected="selected">基于上网行为的实时运营</option>
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
				<td>
				活动类型：
				<select id="active_send_type" onchange="setActiveSendType(this.value);" style="width:150px;height:23px;">
						<option value=""  >全部</option>
						<option value="1" selected="selected" >业务订购类</option>
                        <option value="2">活性提升类</option>
					 </select>
					 </td>
					 <td>
				成功率(排序)：
				<select id="orderSta" style="width: 150px;height:23px;" >
                        <option value="" selected="selected" >全部</option>
						<option value="asc">升</option>
                        <option value="desc">降</option>
					 </select>
					 </td>
					 <td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="查询" style="width:70px;height:28px;" onclick="queryData();"  />
                 </td>
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
