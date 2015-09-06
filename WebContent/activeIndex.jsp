<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>营销场景</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
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
#bar td{border:0px;padding:5px 5px 5px 5px;}
</style>
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script language="javascript"  src="js/My97DatePicker/WdatePicker.js"></script>
<link href="css/ui-dialog.css" rel="stylesheet" type="text/css" />
<script language="javascript"  src="js/dialog.js"></script>


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
	var exStatus="";
	var cjType="";
	var beginTime="";
	var endTime="";

	function search_active(){
		$.ajaxSetup ({
			cache: false //close AJAX cache
			});
		$("#tab").load("activeAction"+searchPara,function(response,status){
				var n=$("#pageNum1").val();
				var c=$("#pageCount1").val();

				$("#apageNum").html(n);
				$("#apageCount").html(c);

				endLoading();
		});
	}


	function seachSubmit(){
		pageNum=1;
		seachData();
		beginLoading();
	}
	
	function seachData(){
		beginTime=$("#beginTime").val();
		endTime =$("#endTime").val();
		
		searchPara="?";
		searchPara+="activeType="+$("#activeType").val()+"&";
		searchPara+="activeStatus="+$("#activeStatus").val()+"&";
		searchPara+="showNum="+showNum+"&";
		searchPara+="pageNum="+pageNum+"&";
		searchPara+="scityId="+$("#scity_id").val()+"&";
		searchPara+="activeExStatus="+$("#exStatus").val()+"&";
		searchPara+="beginTime="+encodeURI(beginTime)+"&";
		searchPara+="endTime="+encodeURI(endTime)+"&";
		searchPara+="acitveScene="+$("#activeScene").val()+"&";
		searchPara+="activeName="+encodeURI($("#activeName").val())+"&";
		searchPara+="acitveContent="+encodeURI($("#activeContent").val())+"&";

		search_active();
	}

	function subType(t,imid){
		if(t==activeType){
			return false;
		}

		activeType=t;
		$("#img1").attr("src","images/mid_point_2.png");
		$("#img2").attr("src","images/mid_point_2.png");
		$("#img3").attr("src","images/mid_point_2.png");

		$("#"+imid).attr("src","images/mid_point_1.png");
		pageNum=1;
		seachData();
	}

	function suStatus(s,imid){
		if(s==activeStatus){
			return false;
		}
		if(s==0){
			activeStatus="";
		}else{
			activeStatus=s;
		}
		$("#img10").attr("src","images/mid_point_2.png");
		$("#img11").attr("src","images/mid_point_2.png");
		$("#img12").attr("src","images/mid_point_2.png");
		$("#img13").attr("src","images/mid_point_2.png");

		$("#"+imid).attr("src","images/mid_point_1.png");
		pageNum=1;
		seachData();
	}

	function suExStatus(s,imid){
		if(s==0){
			s="";
		}
		if(s==exStatus){
			return false;
		}

		exStatus=s;
		if(exStatus==5){
			var userType=$("#adminExamine").val();
			if(userType=='true'){
				exStatus=5;
			}else{
				exStatus=7;
			}
		}
		$("#img14").attr("src","images/mid_point_2.png");
		$("#img15").attr("src","images/mid_point_2.png");
		$("#img16").attr("src","images/mid_point_2.png");
		$("#img17").attr("src","images/mid_point_2.png");
		$("#img18").attr("src","images/mid_point_2.png");

		$("#"+imid).attr("src","images/mid_point_1.png");
		pageNum=1;
		seachData();
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

		seachData();//默认刷新
	}

	function goto(a){
		if(a==1){
			window.location.href="createActive.jsp";
		}else{
			window.location.href="effectIndex.jsp";
		}
	}

	var deleteActiveCode="";
	function deleteNowActive(aid,t){

	if(confirm("确定要删除活动吗？")){

	}else{
		return;
	}

	  $.ajax({
		type: "post",
		url: "activeAction!deleteActive?activeCode="+aid+"&deleteActiveType="+t,
		dataType : "text",
		success: function(v){
			if(v=="Y"){
				if(t==2){
					alert("删除成功！");
					goPage('r');
				}else{
					deleteActiveCode=aid;
					beginLoading();
					openGetMqStatus();
				}
			}
		}
	  });
	}

	function shutNowActive(aid){
		if(confirm("确定要终止该活动吗？")){

		}else{
			return;
		}

		  $.ajax({
			type: "post",
			url: "activeAction!shutActive?activeCode="+aid,
			dataType : "text",
			success: function(v){
				if(v=='Y'){
					alert("操作成功！");
					goPage('r');
				}else{
					alert("操作失败，稍后再试！");
				}
			}
		  });
		}


	function updateActiveState(activeCode,statue,activeType){
		var para="?";
		para+="activeCode="+activeCode+"&";
		para+="activeExStatus="+statue+"&";
        para+="activeType="+activeType;
		$.ajax({
			type: "post",
			url: "activeAction!cityExamineActive"+para,
			dataType : "text",
			success: function(v){
				 	if(v=='Y'){
				 		alert("操作成功");
						goPage('r');
					}
			}
		});
	}

	function agree(activeCode,activeType,beginTime,endTime){
		var userType=$("#adminExamine").val();
		if(userType=='false'){
			updateActiveState(activeCode,5,activeType);
			return;
		}


		var para="?";
		para+="activeCode="+activeCode+"&";
		para+="beginTime="+beginTime+"&";
		para+="endTime="+endTime+"&";
		para+="activeType="+activeType;
		$.ajax({
			type: "post",
			url: "activeAction!sendMq"+para,
			dataType : "text",
			success: function(v){
				 if(activeType==2){
				 	if(v=='Y'){
				 		alert("操作成功");
						goPage('r');
					}
				 }else{
				 	if(v=='Y'){
				 		beginLoading();
						openGetAddMqStatus(activeCode);
					}
				 }
			}
		});

	}

	var ds2;
	var exActiveCodeo;
	function openGetAddMqStatus(ac){
		exActiveCodeo=ac;
		ds2= setInterval(getAddMqStatus,2000);

	}

	function getAddMqStatus(){
		var para="?";
		para+="activeCode="+exActiveCodeo;
		$.ajax({
		type: "post",
		url: "activeAction!getAddMqStatus"+para,
		dataType : "text",
		success: function(v){
			if(v=="Y"){
				clearInterval(ds2);
				endLoading();
				loadUserGroup();
				alert("操作成功");
				goPage('r');
			}
		}
		});

	}

	function loadUserGroup(){
		var para="?";
		para+="activeCode="+exActiveCodeo;
		$.ajax({
		type: "post",
		url: "activeAction!loadUserGroup"+para,
		dataType : "text",
		success: function(v){
		}
		});
	}


	function noAgree(aCode){
		var status="y";
		var d = dialog({
		    title: '填写原因',
		    content: '<input id="nams"  maxlength="50" autofocus />',
		    ok: function () {

		        var that = this;
				var vsm=$("#nams").val();
				if(vsm==''){
					alert('必须填写不通过原因');
					return false;
				}
				this.title('正在提交..');
				var para="?";
				para+="activeCode="+aCode+"&adviceMessage="+encodeURI(vsm);
				$.ajax({
					type: "post",
					url: "activeAction!updateActiveStatus"+para,
					dataType : "text",
					success: function(v){
						if(v=='Y'){
							//alert("操作成功！");
							that.close().remove();
							goPage('r');
						}else{
							alert('系统繁忙请重新提交');
						}
					}
				});
		        //setTimeout(function () {
		        //   that.close().remove();
		        //}, 2000);
				status='Y';
		        return false;
		    },
		    cancel: function () {
				if(status=='n'){
		        	alert('正在提交,不许关闭');
		        	return false;
				}else{
					this.close().remove();
				}
		    }
		});
		d.show();
		return;
		var para="?";
		para+="activeCode="+aCode;
		$.ajax({
			type: "post",
			url: "activeAction!updateActiveStatus"+para,
			dataType : "text",
			success: function(v){
				if(v=='Y'){
					alert("操作成功！");
					goPage('r');
				}
			}
		});
	}


	var ds;
	function openGetMqStatus(){
		ds= setInterval( getMqStatus,2000);

	}

	function getMqStatus(){
		var para="?activeCode="+deleteActiveCode;
		$.ajax({
		type: "post",
		url: "activeAction!getDeleteMqStatus"+para,
		dataType : "text",
		success: function(v){
			if(v=="Y"){
				clearInterval(ds);
				endLoading();
				//alert("删除成功！");
				goPage('r');
			}
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

	function setScityId(v){
		scityId=v;
		pageNum=1;
		seachData();
	}

	function setCjType(v){
		cjType=v;
		seachData();
	}



</script>
</head>


<body>
    <div id="all">
		<div id="top">
			<div style="padding-top:20px; padding-left:28px; float:left"><img src="images/top_logo.png" /></div>
			<ul id="nav">
				<li><img src="images/top_yxrwgl_1.png" /></li>
				<li><img src="images/top_yxrwcj_3.png" onclick="goto(1);" style="cursor:pointer;" /></li>
				<li><img src="images/top_xggzbb_3.png" onclick="goto(2);" style="cursor:pointer;"/></li>
			</ul>
		</div>
		<div id="content">
			<div id="left">
				<ul>
					<li><a href="manegerAction!queryProduct" target="_blank" > <img src="images/left_myactive_1.png" style="cursor:pointer;" /></a></li>
					<li><img src="images/left_fenactive_2.png" /></li>
<!-- 					<li><img src="images/left_shengactive_1.png" /></li> -->
					<li><a href="manegerAction" target="_blank" > <img src="images/left_tiaozheng_1.png" style="cursor:pointer;" /></a></li>
<!-- 					<li><a href="/DemoProject/blackList.jsp" > <img src="images/left_myactive_1.png" style="cursor:pointer;" /></a></li> -->
				</ul>
			</div>
			<div id="right">
				<div id="bar" style="height:80px;padding-top:10px;">
				<table style="border:0px;background:white;width:100%">
				<tr  style="line-height:25px;border:0px;">
				 <td style="width:20%;">
				 	活动地市：
				 	<select id="scity_id" style="width:100px;height:23px;" ><!-- onchange="setScityId(this.value);"   -->
						<option value="" selected="selected" >请选择</option>
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
				 <td  style="width:20%;">
				 活动类别：
				<select id="activeType" style="width:100px;height:23px;" >
						<option value="">全部</option>
						<option value="1" >实时营销</option>
						<option value="2" >非实时营销</option>
			   </select>
			   </td>
			   <td style="width:22%;">
					场景类型：
						<select id="activeScene" style="width:130px;height:23px;" >
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
				  <td style="width:38%;">
					 	活动时间：
						<input type="text" class="Wdate" id="beginTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:140px;height:21px;" value=""/>
						<font style="position:relative;" >到</font>
					  <input type="text" class="Wdate" id="endTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width:140px;height:21px;"  value=""/>
				 </td>
				</tr>				
				<tr  style="line-height:25px;border:0px;">	 
					 <td style="width:20%;"> 
					 	活动状态：
						<select id="activeStatus" style="width:100px;height:23px;" >
						<option value="0">全部</option>
						<option value="1" >未开始</option>
						<option value="2" >进行中</option>
						<option value="3" >已结束</option>
					 </select>

				 </td>
				 
				 <td style="width:20%;"> 
					   审核状态：
						<select id="exStatus" style="width:100px;height:23px;" >
						<option value="">全部</option>
						<option value="5" >待审核</option>
						<option value="2" >审核通过</option>
						<option value="6" >审核未通过</option>
						<option value="10" >手动终止</option>
					 </select>
				  </td>
				 <td style="width:22%;"> 
				活动名称：
				<input type="text" class="inputText" id="activeName" name="activeName" style="height:19px;" size="16" value=""/>
			 </td>
			 <td style="width:20%;"> 
				活动内容：
				 <input type="text" class="inputText" id="activeContent" name="activeContent" style="height:19px;" size="18"  value=""/> 
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="width:70px;height:28px;" value="查询" onclick="seachSubmit();"  />
				  </td>
				</tr>
				</table>

				</div>

                <div id="tab" style="margin-top:20px;">

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

<div class="overlay"></div>

<div id="AjaxLoading" class="showbox">
	<div class="loadingWord"><img src="images/waiting.gif">加载中，请稍候...</div>
</div>

<div style="height:1200px;">

</div>
</body>
</html>
