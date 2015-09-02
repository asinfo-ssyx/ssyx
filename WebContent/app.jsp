<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<style>

</style>  
<script type="text/javascript">
$(document).ready(function(){	
		titleApp();
	});

function qhdiv(id){

$('#xw').css("display","none");
$('#ms').css("display","none");
$('#bk').css("display","none");
$('#yx').css("display","none");
$('#ss').css("display","none");

$('#'+id).css("display","block");


$('#sst').css("background","url(images/chufatiaojian_yingyong_tab_weixuanzhong_bg.png)");
$('#sst').css("color","#6f6f6f"); 
$('#xwt').css("background","url(images/chufatiaojian_yingyong_tab_weixuanzhong_bg.png)");
$('#xwt').css("color","#6f6f6f");
$('#mst').css("background","url(images/chufatiaojian_yingyong_tab_weixuanzhong_bg.png)");
$('#mst').css("color","#6f6f6f");
$('#bkt').css("background","url(images/chufatiaojian_yingyong_tab_weixuanzhong_bg.png)");
$('#bkt').css("color","#6f6f6f");
$('#yxt').css("background","url(images/chufatiaojian_yingyong_tab_weixuanzhong_bg.png)");
$('#yxt').css("color","#6f6f6f");

$('#'+id+'t').css("background","url(images/chufatiaojian_yingyong_tab_xuanzhong_bg.png)"); 
$('#'+id+'t').css("color","#FFFFFF"); 

}

function subApp(){
	 var checked = [];
	 $('input:checkbox:checked').each(function() {
            checked.push($(this).val());
      });	 
	 $.ajax({
		type: "post",
		url: "triggerAction!subCheckedApp?checked="+checked+"&activeCode="+activeCode,
		dataType : "text",
		beforeSend: function(XMLHttpRequest){
			$('#btn_tj').unbind("click");
		},
		success: function(v){
			//alert(v);
			if("Y"==v){
				titleApp();
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

function titleApp(){
	$.ajax({
		type: "post",
		url: "triggerAction!getCheckedAppList"+acparam,
		dataType : "text",
		beforeSend: function(XMLHttpRequest){
			
		},
		success: function(v){
			if(v==""||v=="N"){
				return ;
			}
			var tableboby="";
			var apps=v.split("&");
			for (var i=0;i<apps.length-1;i++)
			{	
				var app=apps[i].split(",");
				if(i==7 && apps.length-1>8){
					tableboby+="<div class=\"gjc\" onclick=\"getAllApp("+v+");\">查看更多 ></div>";
					break;
				}
				tableboby+="<div class=\"gjc\">"+app[0]+"</div>";
			}
			$('#cftjt').html(tableboby);	
		},
		complete: function(XMLHttpRequest, textStatus){
		},
		error: function(){
			alert("App信息查询失败！");
		}
		});
	
}
function getAllApp(){
	alert("全部app！");
}

function czApp(){
	$("[name='checkbox']").removeAttr("checked");//取消全选     
}
</script>

	 <div class="down_left" >
		 <div class="down_left_top" style="background:url(images/down_left_top_bg.png);">
			   <span style="color:#FFFFFF">触发条件设置</span>
			   <select class="text1" id="spr"  onChange="loaddiv(this.value)" style="width:100px">  
				  <option value ="gjz"  >关键字</option>  
				  <option value ="app" selected="selected">APP</option>  
				  <option value="wz">位置</option>  
				</select>
		 </div>
	
                <div style="width:707px;height:30px;margin-top:10px;margin-left:10px;">
				    <ul class="tab_xuanze" >
					<li onClick="qhdiv('ss');" style="cursor:pointer;background:url(images/chufatiaojian_yingyong_tab_xuanzhong_bg.png);color:#FFFFFF;" id="sst">搜索类</li>
					<li onClick="qhdiv('xw');" style="cursor:pointer;" id="xwt">新闻娱乐类</li>
					<li onClick="qhdiv('ms');" style="cursor:pointer;" id="mst">美食购物类</li>
					<li onClick="qhdiv('bk');" style="cursor:pointer;" id="bkt">报刊杂志书籍</li>
					<li onClick="qhdiv('yx');" style="cursor:pointer;" id="yxt">视频类</li>
					</ul>

                   <ul class="searchkuang">
				   <li><input class="text1" value="" onmouseover="this.style.borderColor='#e2e2e2'" onmouseout="this.style.borderColor='#e2e2e2'" onFocus="if (value ==''){value =''}" onBlur="if (value ==''){value=''}" style="width:140px;height:24px;border:#e2e2e2 1px solid;margin-left:15px;"/></li>
				   <li ><input id="btn_qd"  type="button"  class="btn_qd"  value=" " style="float:left;background:url(images/chufatiaojian_yingyong_sousuo_btn.png) no-repeat scroll 0 0;width:48px;height:29px;margin-left:5px;" ></li>
				   </ul> 
				</div>
				
				
				<div id="ss" style="display: block;margin-left:10px;overflow:scroll;" class="yingyong_xuanze">
				
				 
			       <c:forEach items="${showList.a}" var="valueMap" varStatus="idx" >
						<div style="margin-left:10px; margin-top:5px; width:90px;float:left"> 
							<p><img src="${valueMap['imgurl']}" /></p>
							<p> <input type="checkbox" name="checkbox" value="${valueMap['name']}_${valueMap['appid']}" />${valueMap["name"]}</p>	
						</div>
				   </c:forEach>
			
				 
                </div>
				
				<div id="xw" style="display:none;margin-left:10px;overflow:scroll;" class="yingyong_xuanze">
				
			       <c:forEach items="${showList.b}" var="valueMap" varStatus="idx" >
						<div style="margin-left:10px; margin-top:5px; width:90px;float:left"> 
							<p><img src="${valueMap['imgurl']}" /></p>
							<p> <input type="checkbox" name="checkbox" value="${valueMap['name']}_${valueMap['appid']}" />${valueMap["name"]}</p>	
						</div>
					</c:forEach>
				 
                </div> 
				
				<div id="ms" style="display:none;margin-left:10px;overflow:scroll;" class="yingyong_xuanze">
				
			       <c:forEach items="${showList.c}" var="valueMap" varStatus="idx" >
						<div style="margin-left:10px; margin-top:5px; width:90px;float:left"> 
							<p><img src="${valueMap['imgurl']}" /></p>
							<p> <input type="checkbox" name="checkbox" value="${valueMap['name']}_${valueMap['appid']}" />${valueMap["name"]}</p>	
						</div>
					</c:forEach>
				 
                </div> 
				
				<div id="bk" style="display:none;margin-left:10px;overflow:scroll;" class="yingyong_xuanze">
				
			       <c:forEach items="${showList.d}" var="valueMap" varStatus="idx" >
						<div style="margin-left:10px; margin-top:5px; width:90px;float:left"> 
							<p><img src="${valueMap['imgurl']}" /></p>
							<p> <input type="checkbox" name="checkbox" value="${valueMap['name']}_${valueMap['appid']}" />${valueMap["name"]}</p>	
						</div>
					</c:forEach>
				 
                </div> 
				
				<div id="yx" style="display:none;margin-left:10px;overflow:scroll;" class="yingyong_xuanze">
				
			       <c:forEach items="${showList.e}" var="valueMap" varStatus="idx" >
						<div style="margin-left:10px; margin-top:5px; width:90px;float:left"> 
							<p><img src="${valueMap['imgurl']}" /></p>
							<p> <input type="checkbox" name="checkbox" value="${valueMap['name']}_${valueMap['appid']}" />${valueMap["name"]}</p>	
						</div>
					</c:forEach>
				 
                </div>  
				
		    
			    <div  style="clear:both;width:150px;margin:0 auto;padding-top:10px;">
				
				   <input id="btn_tj"  type="button"  class="btn_tj"  onclick="subApp();" value=" " style="border:none;float:left;background:url(images/tijiaoneirong_btn.png);width:67px;height:31px;cursor:pointer;" >
				   <input id="btn_cz"  type="button"  class="btn_cz"  value="" onclick="czApp();" style="border:none;float:left;background:url(images/chongzhi_btn.png);width:67px;height:31px;margin-left:5px;cursor:pointer;" >
				 </div>	
  
     </div>
 
   
   
   <div class="down_right" >
      <div class="down_right_top">
	  <ul style="list-style:none;">
	  <li style="background:#58a2db;display:block;color:#FFFFFF;">热门关键词</li>
	  <li style="background:#FFFFFF;display:block;color:#6f6f6f;">相关活动</li>
	  </ul>
	  </div>
	  
	  
	  <div class="down_right_content_yingyong">
          <table >
			      <tr >
				  <td style="margin-left:10px;">
				    <p>
			        <input type="checkbox" name="checkbox" value="checkbox" style="position:absolute;"/>
			        <img src="images/chufatiaojian_yingyong_icon_1.png" /></p>
				    <p>智能360</p>
					</td>
					
				  <td>
				  <p>
			        <input type="checkbox" name="checkbox" value="checkbox" style="position:absolute;"/>
			        <img src="images/chufatiaojian_yingyong_icon_2.png" /></p>
				    <p>智能360</p>
					</td>
					
				  <td>
				  <p>
			        <input type="checkbox" name="checkbox" value="checkbox" style="position:absolute;"/>
			        <img src="images/chufatiaojian_yingyong_icon_1.png" /></p>
				    <p>UC浏览器</p>
					</td>
					
				  <td>
				  <p>
			        <input type="checkbox" name="checkbox" value="checkbox" style="position:absolute;"/>
			        <img src="images/chufatiaojian_yingyong_icon_2.png" /></p>
				    <p>智能360</p>
					</td>
			        </tr>
					
				</table>	  
	  </div>
	  </div>