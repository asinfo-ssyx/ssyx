<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
$(document).ready(function(){
	//$("#uploadCount").text("今日上传上限量为："+"1000");
	$.ajax({
		type: "get",
		async : true,
		url: "UploadAction!getUploadCount"+acparam,
		dataType : "text",
		success: function(s){
			$("#uploadCount").text("今日上传上限量为："+s);
		}
		});
	
});
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
            url:"UploadAction!upLoadFile"+acparam+"&userGroupName="+encodeURI(userGroupName)+"&groupType=1",
            secureuri: false,
			dataType : 'text',
            fileElementId:'fileupload',//文件选择框的id属性
			beforeSend: function(XMLHttpRequest){
				$('#subupload').unbind("click");
			},
            success: function(data, status){
				if(data.indexOf("Y")!=-1){
					var useCon=data.split(",")[1];
               		alert("上传成功！");
					userGroup_code=activeCode;
					userGroup_name=userGroupName;
					customNum=useCon;
					$("#userGroupTd").html(userGroup_name+",用户数："+useCon);
                }else if(data.indexOf("N")!=-1){
					alert("上传客户数超出上限量！上传失败！");
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

	var dataBaseS="";
	function subUserGroup(){
		//userGroup_code="userGroup_code";
		//userGroup_name="测试用户群";
		if(userGroup_code==""||userGroup_name==""){
			alert("请选择用户群！");
			return false;
		}
		var para="?customNum="+customNum+"&groupName="+userGroup_name+"&searchsql="+userGroup_code+"&activeCode="+activeCode+"&dataBaseS="+dataBaseS;
		para=encodeURI(para);
		$.ajax({
		type: "post",
		url: "userGroup!selectedUserGroupInsert"+para,
		dataType : "text",
		success: function(v){
			if(v=="Y"){
				loadyhqdata();
			}else if(v=="R"){
				alert("已经选择了用户群! 如果想要修改请先删除已选用户群(双击删除)。");
			}else{
				alert("提交失败！");
			}
		},
		error: function(){
			alert("推送信息提交失败，请重新提交！");
		}
		});
	}

	function getUserGroupPage(t){
		//if(acitveScene>10){
		//	alert("当前事件类型，无法单独选择客户群！");
		//	return;
		//}

		if($("#userGroupIframe").css("display")=="block"){
			$("#userGroupIframe").css("display","none");
			$("#userGroupIframeTitle").css("display","none");
			$('#userGroupIframe').attr("src","about:blank");
			return;
		}
		if(t=='bq'){
			$.ajax({
				type: "post",
				url: "userGroup!getUserGroupParamUrl",
				dataType : "text",
				success: function(v){
						var ugurl="http://bass.scmcc.com.cn:9000/coc/ci/customersManagerAction!queryGroup.ai2do?param="+v;
						$('#userGroupIframe').attr("src",ugurl);
						$("#userGroupIframeTitle").css("display","block");
						$("#userGroupIframe").css("display","block");
					}
			});
		}else{
			var ugurl="dataMarketUser.jsp";
			$('#userGroupIframe').attr("src",ugurl);
			$("#userGroupIframeTitle").css("display","block");
			$("#userGroupIframe").css("display","block");
		}
	}

</script>
<html>
<body>
	<div class="down_left">
		<div class="down_left_top"
			style="background: url(images/down_left_top_bg.png);">
			<span style="color: #FFFFFF">用户群设置</span>
		</div>

		<div class="main_left" style="float: left;">
			<table>
				<tr>
					<td>
					<p style=" margin-left:10px; margin-bottom:-5px;">1，选择已有客户群!</p>
					<input name="button" type="button" class="btn_sz"
						id="button"
						style="border: none; float: left; background: url(images/yonghuqun_xuanzeshangchuan_btn.png) no-repeat scroll 0 0; width: 150px; height: 29px; margin-left: 10px; margin-top: 3px;"
						onclick="getUserGroupPage('bq')" />
					</td>
				</tr>
				<tr>
					<td>
					<p style=" margin-left:10px; margin-bottom:-5px;">2，自定义上传客户群!</p>
					<font style="margin-left:15px;" color="#58a2db" id="uploadCount"></font>
					<br><input id="fileupload"  type="file" class="btn_sz" value="" name="userFile"
						style="border: none; float: left; width: 150px; height: 29px; margin-left: 10px; margin-top: 10px;" />
					<br><font style="margin-left:10px;" >用户群名称:</font>
					<input id="subupload" type="button" value="提  交" onClick="subUploadFile()" >
					<br><input style=" margin-left:10px;" id="userGroupName" type="text" value="" />
					</td>
				</tr>



				<tr>
					<td>
					<p style=" margin-left:10px; margin-bottom:-5px;">3，导入数据集市用户群!</p>
					<input id="btn_sz" type="button" class="btn_sz" value=" "
						style="border: none; float: left; background: url(images/yonghuqun_shujujishidaoru_btn.png) no-repeat scroll 0 0; width: 150px; height: 29px; margin-left: 10px; margin-top: 20px;" onClick="getUserGroupPage('sjjs')">
					</td>
				</tr>

			</table>
		</div>


		<div class="biaoqian_xuanze_2">
			<table id="groupTab" style="float: left;">
				<tr>
			  		<td style="width:400px;height:29px;" id="userGroupTd"></td>
			  	</tr>
			</table>

		</div>
		<div
			style="clear: both; width: 250px; margin: 0 auto; padding-top: 10px; float: right; margin-right: 140px;">
			<input id="btn_tj" type="button" class="btn_tj"
				onclick="subUserGroup()"
				style="border: none; float: left; background: url(images/tijiaoneirong_btn.png); width: 67px; height: 31px; margin-left: 5px;">
			<input id="btn_cz" type="button" class="btn_cz" value=" "
				style="border: none; float: left; background: url(images/chongzhi_btn.png); width: 67px; height: 31px; margin-left: 5px;">
		</div>
	</div>

	<div class="down_right" style="height:308px">
		<div class="down_right_top">
			<ul style="list-style: none;">
				<li style="background: #58a2db; display: block; color: #ffffff;">热门关键词</li>
				<li style="background: #FFFFFF; display: block; color: #6f6f6f;"></li>
			</ul>
		</div>


		<div class="down_right_content">
			<ul>
			</ul>
		</div>
	</div>
</body>
</html>