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
</style>
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<link href="css/ui-dialog.css" rel="stylesheet" type="text/css" />
<script language="javascript"  src="js/dialog.js"></script>

<script type="text/javascript">
	var searchPara="";
	function search_active(){
		$("#tab").load("searchPageAction"+searchPara,function(response,status){
				var n=$("#pageNum1").val();
				var c=$("#pageCount1").val();

				$("#apageNum").html(n);
				$("#apageCount").html(c);
		});
	}


	function executeSql(){
		searchPara="?";
		var sqlStr=$.trim($("#sqlStr").val());
		var dbs=$.trim($("#dbs").val());
		var sqlType=$.trim($("#sqlType").val());
		if(sqlStr==""||dbs==""||sqlType==""){
			alert("有未选项！");
			return ;
		}
		searchPara+="sqlStr="+encodeURI(sqlStr)+"&";
		searchPara+="dbSou="+dbs+"&";
		searchPara+="sqlType="+sqlType+"&";
		search_active();
	}

	function closeDBS(aid,t){
	  $.ajax({
		type: "post",
		url: "searchPage!closeDbs",
		dataType : "text",
		success: function(v){

		}
	  });
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
					<li><img src="images/left_shengactive_1.png" /></li>
					<li><img src="images/left_tiaozheng_1.png" /></li>
				</ul>
			</div>
			<div id="right">
				<div id="bar">
				数据源：<select id="dbs" >
						<option value="" selected="selected" >请选择</option>
						<option value="1">134DB2</option>
						<option value="2" >228DB2</option>
						<option value="3" >85MySQL</option>
					 </select>
					  &nbsp;&nbsp;类型：<select id="sqlType" >
						<option value="1"  selected="selected">CRUD</option>
						<option value="2" >DDR</option>
					 </select>
					 &nbsp;&nbsp;<input type="button" value="执行" onclick="executeSql();" />
					  &nbsp;&nbsp; &nbsp;&nbsp;fetch first 1 rows only
					 <br/>
					 <textarea style="width:100%; height:50px;" id="sqlStr">

					 </textarea>
				</div>

                <div id="tab">

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
