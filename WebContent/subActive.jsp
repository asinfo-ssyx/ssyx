<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>营销场景</title>
<link href="css/queren.css" rel="stylesheet" type="text/css">
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
</style>
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script language="javascript"  src="js/lhgcalendar.min.js"></script>
<script type="text/javascript">
	$(function(){
    	//$('#beginTime').calendar({ format:'yyyy-MM-dd HH:mm:ss'});
		//$('#endTime').calendar({ format:'yyyy-MM-dd HH:mm:ss'});
	});


	var para="";
	var activeName="";
	var beginTime="";
	var endTime ="";
	var activeType="1";
	var pCode="";
	var ourl="";
	var productId="";

	function afterObserve(){
		if($("#ao").attr("checked")=="checked"){
			$('#genz').css("display","block");
		}else{
			$('#genz').css("display","none");
		}
	}

	function isCheck(){
	    pCode=$("#proCode").val();
		productId=$("#productId").val();
		ourl=$("#ourl").val();
		if($("#ao").attr("checked")=="checked"){
		 	if(activeType==""){
			alert("请选择活动类型！");
			return false;
			}
			if(activeType=="1"){
				if(pCode==""){
				alert("请填写产品编码！");
				return false;
				}
			}
			if(activeType=="2"){
				if(ourl==""){
				alert("请填写跟踪url！");
				return false;
				}
			}
		}else{
			activeType="";
	 		pCode="";
			ourl="";
		}

		searchData();
	}


	function searchData(){
		para="?";
		para+="activeCode=${activeCode}&";
		para+="activeMsType="+activeType+"&";
		para+="pCode="+encodeURI(pCode)+"&";
		para+="ourl="+ourl+"&";
		para+="activeType=${ChooseInfo.activeType}&";
		para+="productId="+productId+"&";
		updateActive();
	}

	function updateActive(){
		$.ajax({
		type: "post",
		url: "activeAction!updateActive"+para,
		dataType : "text",

		beforeSend: function(XMLHttpRequest){
			//ShowLoading();
			$('#sub').unbind("click");
		},
		success: function(v){

			if(v=='Y'){
				alert("创建活动成功");
				//if('${ChooseInfo.activeType}'=='2'){//非实时
				//alert("创建活动成功");
				window.location.href="activeIndex.jsp";
				//}else{
				//	beginLoading();
				//	sendMq();
				//}
			}else{
				alert("创建活动失败，请重新点击！");
			}
		},
		complete: function(XMLHttpRequest, textStatus){
			//HideLoading();
			$("#sub").bind("click", isCheck);
		},
		error: function(){
			//请求出错处理
			alert("创建活动失败，请重新点击！");
		}
		});

	}

	function suActiveType(t){
		activeType=t;
		if(t==1){
			$('#prCode').css("display","block");
			$('#urldz').css("display","none");
		}else if(t==2){
			$('#urldz').css("display","block");
			$('#prCode').css("display","none");
		}
	}

	function gotoIndex(){
		window.location.href="activeIndex.jsp";
	}

	function goBack(){
		$("#suForm").submit();
	}

	function sendMq(){
		var para="?";
		para+="activeCode=${activeCode}&";
		para+="beginTime=${beginTime}&";
		para+="endTime=${endTime}&";
		$.ajax({

		type: "post",

		url: "activeAction!sendMq"+para,
		dataType : "text",
		success: function(v){
			openGetMqStatus();
		}
		});
	}
	var ds;
	function openGetMqStatus(){
		ds= setInterval(getMqStatus,2000);

	}

	function getMqStatus(){
		var para="?";
		para+="activeCode=${activeCode}&";
		$.ajax({
		type: "post",
		url: "activeAction!getAddMqStatus"+para,
		dataType : "text",
		success: function(v){
			if(v=="Y"){
				clearInterval(ds);
				endLoading();
				loadUserGroup();
				alert("创建活动成功");
				window.location.href="activeIndex.jsp";
			}
		}
		});

	}

	function loadUserGroup(){
		var para="?";
		para+="activeCode=${activeCode}&";
		$.ajax({
		type: "post",
		url: "activeAction!loadUserGroup"+para,
		dataType : "text",
		success: function(v){

		}
		});

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

	function setbusicode(){
			$("#selectBusCode").css("display","block");
			$("#selectBusCoded").css("display","block");
			$("#selectBusCoded").load("codeAction!queryProduct",function(response,status){});
	}
	function closebusicode(){
			$("#selectBusCode").css("display","none");
			$("#selectBusCoded").css("display","none");
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
                    <li><img src="images/top_yxrwcj_1.png" /></li>
                    <li><img src="images/top_xggzbb_3.png" /></li>
                </ul>
            </div>
		</div>
        <div id="crumbs">您的位置:<span style="color:#317cb5">设置向导 > 条件设置 ></span>
        </div>
        <div id="comtent1" >
            <img src="images/qr_icon_ysznr.png" />
            <div id="box">
                <h1>触发条件
                </h1>
				<c:if test="${!empty ChooseInfo.trigger }">

					<c:forEach items="${ChooseInfo.trigger}" var="valueMap" varStatus="idx" >
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
						</p>


						<p>内容:<span>${valueMap.trigger_ms}</span> </p>
					</c:forEach>
				</c:if>

                <div id="line"></div>

			    <h1>用户群
                </h1>
				<c:if test="${!empty ChooseInfo.userGroup }">
					<p>类型:
					<c:forEach items="${ChooseInfo.userGroup}" var="valueMap" varStatus="idx" >
						 <span>${valueMap.table_col} </span>
					</c:forEach>
					</p>
				</c:if>

				<div id="line"></div>

			    <h1>客户接触管理
                </h1>
				<c:if test="${!empty ChooseInfo.gluserGroup }">
				<p>类型:
					<c:forEach items="${ChooseInfo.gluserGroup}" var="valueMap" varStatus="idx" >
						 <span>${valueMap.table_col} </span>
					</c:forEach>
				</p>
				</c:if>

                <div id="line"></div>

                <h1>推送渠道及内容</h1>
				<c:if test="${!empty ChooseInfo.sendMessage }">
					<c:forEach items="${ChooseInfo.sendMessage}" var="valueMap" varStatus="idx" >
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
        <div id="comtent2">
            <img src="images/qr_icon_hdjcsz.png"/>
            <div id="box">
                <h1>基础信息
                </h1>
                <p>
                    <span>活动名称:${activeName}</span>
                    <span style="float:right">
                    </span>
                </p>
                <p>
                    <span>活动周期:
					${beginTime}
					<font style="position:relative;left:0px;" >到</font>
					${endTime}
					</span>
                    <span style="float:right">
                    </span>
                </p>
                <p>效果跟踪：<span><input type="checkbox" name="checkbox1" value="checkbox" onclick="afterObserve();" id="ao" checked="checked">需要</span></p>
                <div id="genz" style="display:block;">
                    <p>
                        <span>活动类型：</span>
                        <span><input type="radio" name="radiobutton" value="radiobutton" onclick="suActiveType(1);" checked="checked">产品推荐</span>
                        <span><input type="radio" name="radiobutton" value="radiobutton" onclick="suActiveType(2);">内容推送</span>
                    </p>
                    <p style="display:block;" id="prCode">
                        <span>
						产品代码： <input id="proCode" disabled="disabled" style="width:220px; height:25px;position:relative;left:10px; "/>
								 <input type="hidden" value="" disabled="disabled" id="productId" />
 						   &nbsp;<input type="button" value="设置业务代码" onclick="setbusicode();"/>
                        </span>
					</p>

					<p style="display:none;" id="urldz">
                        <span style="margin:0 15px 0 0px;">
						URL地址：<input id="ourl" style="width:120px; height:25px;position:relative;left:10px; "/>
                        </span>
                        <span>应用推荐，请输入应用下载网址</span>
                    </p>
                </div>
                <p style="text-align:center; line-height:80px;">
                    <img src="images/qr_btn_fh.png" onclick="goBack();"  />&nbsp;&nbsp;
                    <img id="sub"  src="images/qr_btn_wccj.png"  onclick="isCheck();"/>
                </p>
            </div>
        </div>
        <div id="footer">© 2013中国移动通信集团四川有限公司  版权所有
    </div>
<form action="trigger.jsp" id="suForm" method="post">
<input type="hidden" name="activeCode" id="activeCode" value="${activeCode}" />
<input type="hidden" name="activeBody" id="activeBody" value="${activeBody}" />
<input type="hidden" name="activeName" id="activeName" value="${activeName}" />
<input type="hidden" name="beginTime" id="hbeginTime" value="${beginTime}" />
<input type="hidden" name="endTime" id="hendTime" value="${endTime}" />
</form>

<div class="overlay"></div>

<div id="AjaxLoading" class="showbox">
	<div class="loadingWord"><img src="images/waiting.gif">加载中，请稍候...</div>
</div>

<div style="height:1200px;">

</div>
<div id="selectBusCode" style="background:url(images/bar_bg.png) repeat-x;width:615px;height:33px;z-index:9999;position:absolute;top:500px;left:340px; display:none" >
	<div style="float:left;padding-left:20px;padding-top:2px;color:#ffffff;" >选择数据业务</div><div style="float:right;padding-right:20px; padding-top:10px;"><img src="images/close2_btn.png"  onclick="closebusicode();"  style="cursor: pointer;" /></div></div>
<div style=" height:460px; width:615px; display:none;position:absolute;top:533px;left:340px; background-color:#FFFFFF;" id="selectBusCoded">		</div>
</body>
</html>

