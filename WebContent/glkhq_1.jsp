<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">

</style>
<script type="text/javascript">
   $(document).ready(function(){
	   $("#activeCode1").val(activeCode);
	   console.log("jjj"+$("#activeCode1").val());
   });
   function goBack(){
	   $("#loaddiv").load("UploadAction!glUserGroup",function(response,status){});
   }
   function chooseOperate(){
	   if($("#gl_cz").val()==3 || $("#gl_cz").val()=="" ){
		   document.getElementById("gl_upload").style.visibility="hidden";
	   }
	   else{
		   document.getElementById("gl_upload").style.visibility="visible";
	   }
   }
   
   function updateGlUserGroup(){
	   if($("#gl_user").val()=="")
		   alert("请选择用户名单！");
	   else if($("#gl_cz").val()=="")
		   alert("请选择操作！");
	   else if($("#gl_cz").val()!=3 && $("#glFile").val()=="")
		   alert("请选择上传文件");
	   else if($("#gl_cz").val()!=3 && $("#glFile").val()!=""){
		    var glUser =$("#gl_user").val();
		    var glCz = $("#gl_cz").val();
			$.ajaxFileUpload({
                url:"UploadAction!editGlUserGroup"+acparam+"&glUser="+encodeURI(glUser)+"&glCz="+encodeURI(glCz),
                secureuri:true,
				dataType : 'text',
                fileElementId:'glFile',//文件选择框的id属性
				beforeSend: function(XMLHttpRequest){
					$('#gl_update').unbind("click");
				},
                success: function(data, status){
                	if(data=="Y"){
               			alert("更新成功！");
                	}else{
						alert("更新失败！");
					}
                },
				complete: function(XMLHttpRequest, textStatus){
					$('#gl_update').bind("click","updateGlUserGroup");
				},
				error: function (data, status, e){
					$('#gl_update').bind("click","updateGlUserGroup");
                   alert("上传失败，请重新上传！");
                }
          });
	   }
	   else if($("#gl_cz").val()==3){
		   var glUser =$("#gl_user").val();
		   var glCz = $("#gl_cz").val();
		   $.get("UploadAction!editGlUserGroup"+acparam+"&glUser="+encodeURI(glUser)+"&glCz="+encodeURI(glCz),function(data,status){
			   if(data=="Y"){
			    alert("更新成功");
		        $("#down_left").load("UploadAction!glUserGroup1",function(response,status){});			  
			   }
			   else{
				   alert("更新失败");
			   }
		   });
	   }
	  
   }
</script>
  <div class="down_left_bar">
				<span style="float:left">编辑客户接触群</span>  
 </div>
 <br>
<!--  action="UploadAction!editGlUserGroup"method="post" enctype="multipart/form-data" -->
 <div name="form1" id="form1" style="text-align:center;margin-top:40px;height:200px"  >
 <input type="hidden" name="activeCode" id="activeCode1"  />
 <div id="gl_bj" style="width:100%;float:center">
    <select id="gl_user" name="glUser" style="width:130px; float:center" >
       <option value="">请选择用户名单</option>   
       <c:if test="${!empty glkhq}">
            <c:forEach items="${glkhq}" var="va" varStatus="idx" >
                  <option value="${va['ucode']}">${va['uname']}</option>                 
            </c:forEach>
       </c:if> 
    </select>
<!--     &nbsp;&nbsp;&nbsp; -->
    <select id="gl_cz"  name="glCz" style="width:130px;margin-left:70px" onchange="chooseOperate();">
       <option value="">请选择操作</option>    
<!--        <option value="1">批量删除</option>    -->
<!--        <option value="2">批量增加</option>  -->
       <option value="3">删除文件</option> 
       <option value="4">替换文件</option> 
    </select>
 </div>
 <br>
  <div id="gl_upload" style="visibility: hidden; width: 100%;margin-top:15px">
    <input id="glFile" name="glFile" type="file" style="width:170px;float:center;margin-right:160px"/>
<!--     <input id="subFile" name="subFile" type="button" value="提交" style="margin-left:15px;width:50px">  -->
  </div>
  <div id="gl_tj" style="margin-top:20px;width:100%;margin-top:82px" >
  <input type="button" id="gl_update" name="gl_update" value="更新" style="float:center;width:67px;height:31px;background:#4c9ed9;color:white;border:none" onclick="updateGlUserGroup();"/>
  <input type="button" id="gl_return" name="gl_return" value="返回" onclick="goBack();" style="margin-left:20px;width:67px;height:31px;background:#4c9ed9;color:white;border:none"/>
  </div>
 </div>