<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.asiainfo.util.DataBaseJdbc"%>
<style type="text/css">
body, div, table, tr, td, ul, li, dl, dt, dd, p, h1, h2, h3, h4, h5, h6, img, form, select, input
   {
    margin: 0;
    padding: 0;}

/* 过滤规则设置 */
.guize_nr{width:98%;margin:0 auto;margin-top:10px;}
.guize_nr ul{list-style:none;}
.guize_nr li{margin-left:20px;}
</style>
<script type="text/javascript">
//
//    $(document).ready(function(){
// 	   $("#guize_nr").load("effectAction"+searchPara,function(response,status){
		   
// 	   });
//      });
	function subUploadFile(){
		var userGroupName=$("#userGroupName").val();
		if(userGroupName==''){
			alert("用户群名称不能为空");

			return;
		}
		if(isRuserGroupName(userGroupName)=="R"){
			alert("用户群名称重复请重填");
			return;
		}


		$.ajaxFileUpload({
                url:"UploadAction!upLoadFile"+acparam+"&userGroupName="+encodeURI(userGroupName)+"&groupType=0",
                secureuri:true,
				dataType : 'text',
                fileElementId:'fileupload',//文件选择框的id属性
				beforeSend: function(XMLHttpRequest){
					$('#subupload').unbind("click");
				},
                success: function(data, status){
                	if(data.indexOf("Y")>0){
               			alert("上传成功！");
						loaddiv("glyhq");
                	}else{
						alert("上传失败！");
					}
                },
				complete: function(XMLHttpRequest, textStatus){
					$('#subupload').bind("click","subUploadFile");
				},
				error: function (data, status, e){
					$('#subupload').bind("click","subUploadFile");
                   alert("上传失败，请重新上传！");
                }
      });
	}
    function loadEdit(){
    	$("#down_left").load("UploadAction!glUserGroup1",function(response,status){});
    }
	function isRuserGroupName(v){
	    var re="";
		$.ajax({
		type: "post",
		async : false,
		url: "UploadAction!isRuserGroupName?userGroupName="+encodeURI(v),
		dataType : "text",
		success: function(s){
			re= s;
		}
		});

		return re;
	}

function subglkhq(){
	 var checked = [];
	 $('input:checkbox:checked').each(function() {
            checked.push($(this).val());
      });
     if(checked==""){
	 	return false;
	 }
	 checked=encodeURI(checked);
	 $.ajax({
		type: "post",
		url: "UploadAction!subGlUserGroup?userGroup="+checked+"&activeCode="+activeCode,
		dataType : "text",
		beforeSend: function(XMLHttpRequest){
			$('#btn_tj').unbind("click");
		},
		success: function(v){
			if(v=="Y"){
				loadglkhqdata();
			}else{
				alert("提交失败");
			}
		},
		complete: function(XMLHttpRequest, textStatus){
			$('#btn_tj').bind("click","subApp");
		},
		error: function(){
			alert("App信息提交失败，请重新提交！");
		}
		});

}
var ds;
var nw;
function czApp(){
	$("[name='checkbox1']").removeAttr("checked");//取消全选
	//nw=window.open("jichuxinxi.html","_blank");
	//ds= setInterval("closewin()",2000);
}

function closewin(){
	//nw.close();
	//clearInterval(ds);
}
</script>
		<div class="down_left" id="down_left">
			<div class="down_left_bar">
				<span style="float:left">客户接触管理</span>  <input type="button" id="edit" name="edit" style="margin-left:20px;margin-top:5px;width:60px;background:#58a2db;color:white;border:none" value="编辑" onclick="loadEdit();"/>
			</div>

            <div class="guize_nr" id="guize_nr" >
				<ul>
				<li>请选择需要排除的名单：</li>
					<c:if test="${!empty glkhq}">
						 <c:forEach items="${glkhq}" var="va" varStatus="idx" >
								<li style="float:left;padding-left:10px;">
								<input type="checkbox" name="checkbox1" value="${va['uname']}" /> ${va['uname']}</li>
						 </c:forEach>
					</c:if>
				</ul>


				<ul style="margin-top:50px;">
				<li style="float:left;">
				<input id="fileupload"  type="file" class="btn_sz" value=" " name="userFile"
						style="border: none; float: left; width: 150px; height: 29px; margin-left: 10px; margin-top: 10px;" />
						</br><font style="margin-left:10px;" >用户群名称:</font>
					<input id="subupload" type="button" value="提  交" onclick="subUploadFile()" >
					</br><input style=" margin-left:10px;" id="userGroupName" type="text" value="" />
						</li>

				<li style="clear:both;float:left;margin-left:10px;color:#c6c6c6;">文件：XXXXXXXXXXXXXX.txt</li>
				</ul>


			</div>
			<div
			style="clear: both; width: 250px; margin: 0 auto; padding-top: 10px; float: right; margin-right: 140px;">
			<!-- input id="subupload" type="button" class="btn_tj"
				onclick="subUploadFile()"
				style="border: none; float: left; background: url(images/tijiaoneirong_btn.png); width: 67px; height: 31px; margin-left: 5px;">
			 -->
			<input id="btn_tj" type="button" class="btn_tj"
				onclick="subglkhq()"
				style="border: none; float: left; background: url(images/tijiaoneirong_btn.png); width: 67px; height: 31px; margin-left: 5px;">
			<input id="btn_cz" type="button" class="btn_cz" value=" " onclick="czApp();"
				style="border: none; float: left; background: url(images/chongzhi_btn.png); width: 67px; height: 31px; margin-left: 5px;">
		</div>
			</div>


		<div class="down_right" style="height:308px">
			<div class="down_right_bar">
				<ul>
				<li style="background: #58a2db; display: block; color: #ffffff;">口径解释</li>
				<li style="background: #FFFFFF; display: block; color: #6f6f6f;"></li>
				</ul>

			<div class="down_right_nr">
			<br>
			<span>黑名单 ：恶意欠费、乱投诉等用户</span><br>
			<span>白名单 ：内部员工用户</span><br>
			<span>红名单 ：特殊号码用户</span><br>
           </div>

		</div>

		</div>

