<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.net.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
InetAddress addr = InetAddress.getLocalHost();
String ip=addr.getHostAddress().toString();//获得本机IP
//增加
String beginTime=(String)request.getSession().getAttribute("beginTime");
String endTime=(String)request.getSession().getAttribute("endTime");
%>


<style type="text/css">
body, div, table, tr, td, ul, li, dl, dt, dd, p, h1, h2, h3, h4, h5, h6, img, form, select, input {
    margin: 0;
    padding: 0;
}
</style>
<link href="shishiyingxiao-new1.css" rel="stylesheet" type="text/css">
<link href="js/skins/lhgcalendar.css" rel="stylesheet" type="text/css">
<script language="javascript"  src="js/lhgcalendar.min.js"></script>
<script type="text/javascript">
        //hacitveScene
        var crwshow="尊敬的用户您好,四川移动向你推荐:"
                +"10元流量包(指令KTLL10);"
                +"20元流量包（指令KTLL20);"
                +"10元闲时流量包(指令5G);"
                +"来电提醒,来电不漏沟通无忧(指令KTLDTX);"
                +"四川手机报,川内资讯随时知(指令SC);"
                +"咪咕特级会员,音乐就在您身边(指令TJ);"
                +"驾车助理,交通信息随时查(指令jczl);"
                +"千本图书尽在精品阅读包(指令KTJPYDB);"
                +"游戏玩家优惠包,做最IN游戏狂人(指令KTWJ);"
                +"爱视频,就订V+喜乐包(指令KTV3);"
                +"独家动漫作品就在漫赏包(指令KTDMMS);"
                +"乐途优惠包,让你出行更轻松(指令KTDHLT);"
                +"和娱乐优惠包：阅读游戏动漫音乐视频一网打尽(指令KTMM6).快编辑相应指令到10086订购吧,体验更多精彩!";
        var crwuse="尊敬的用户您好,四川移动向你推荐:CRM.快编辑相应指令到10086订购吧,体验更多精彩!";


        $(function(){
        		$("#sendTime").val(zbeginTime);
                if(activeType=='2'){
                $('#sendTime').calendar({ format:'yyyy-MM-dd HH:mm:ss'});
                        //$('#sendMassege_time').css("display","");
                        if(hfsscj=="Y"){
                        //$('#sendMassege_cycle').css("display","");
                        }
                }
                loadShrZscl();
                var ht=$("#down_left_id").outerHeight();
                $("#down_right_id").css('height',ht);

                if(acitveScene==5){
                        $("#sendMs").val(crwshow);
                        $("#sendMs").attr("disabled","disabled");
                }
                
                
                $("#sendMs").on("propertychange",sumConlen);
                $("#sendMs").on("input",sumConlen);
                
//                if(/msie/i.test(navigator.userAgent))    //ie浏览器 
//                 {    console.log("ie");
//             	   document.getElementById('sendMs').onpropertychange=sumConlen 
//                 } 
//                 else 
//                 {//非ie浏览器，比如Firefox 
//                 	console.log("firefox");
//                 document.getElementById('sendMs').addEventListener("textarea",sumConlen,false); 
//                 }  
                
        });

        var exCityId="";
        function loadShrZscl(){
                var p="";
                if(exCityId!=""){
                                p="?exCityId="+exCityId;
                }
                $.ajax({
                        type: "post",
                        url: "sendMessageAction!getActiveShr"+p,
                        dataType : "text",
                        success: function(v){
                                $('#shenheren').html(v);
                        },
                        error: function(){
                                alert("查询审核人信息出错！");
                        }
                });
        }
		
        var sendCode="";
        
        //提交内容按钮
        function yanzSm(){
        	
                var sendTime=$("#sendTime").val();
                var send_cycle=$('input[name="send_cycle"]:checked').val();
                if(activeType=='2'){
                        if(sendTime==""){
                                alert("请选择推送时间！");
                                return false;
                        }
                }


                var sendMs=$.trim($("#sendMs").val());
                
                //判断空值
                if($.trim(sendMs)==""){
                        alert("请填写推送内容！");
                        return false;
                }
               	
              
                //检查文本输入的字节长度
                if($("#conLen").html() < 0){
                    alert("友情提示：推送内容超过140个字符，短信会被拆分！");
           		}
                
                //检查字符的合法性
                if(!checkStr($.trim(sendMs))){
                	return false;
                }
                
                if(acitveScene==5){
                        sendMs=crwuse;
                }

                //var spr=$("#spr").val();
                var spr="";
				//if(spr==""){
                //        alert("请选择审批人！");
                //        return false;
                //}
				sendMs=strReplaceAll(sendMs,'#','RJH');
				sendMs=strReplaceAll(sendMs,'%','RBFH');
				var testPhone=$("#testPhone").val();

                var param="?send_ms="+encodeURI(sendMs)+"&spr="+spr+"&activeCode="+activeCode+"&send_type=2&send_cycle="+send_cycle+"&testPhone="+testPhone+"&";
                if(activeType=='2'){
                        param+="sendTime="+sendTime;
                }

                $.ajax({
                type: "post",
                url: "sendMessageAction!subSendMessage"+param,
                dataType : "text",
                beforeSend: function(XMLHttpRequest){
                        //ShowLoading();
                        $('#duanxinsf').unbind("click");
                },
                success: function(v){
						if("te"==v){
                                alert("测试号码保存失败，请重新提交！");
                                return false;
                        }
                        if("e"==v){
                                alert("推送信息提交失败，请重新提交！");
                                return false;
                        }
                       // console.log(v);
                        var s=v.split("|");
                      //  console.log(s);
                        if(v.indexOf("Y")>-1){
                               // alert("推送信息创建成功，请获取随机码验证！");
							    loadtsxxdata();
							    alert("提交成功");
                                //sendCode=s[1];
                        }else{
                                alert("提交失败");
                        }
                },
                complete: function(XMLHttpRequest, textStatus){
                        $('#duanxinsf').bind("click",yanzSm);
                },
                });
        }
        
        //判断字符串是否包含特殊字符串
        function checkStr(str){
/*         	str = '-'+str;  //---->这个是为了让输入的文本能更好的匹配 */
        	 //判断是否有非法字符
            if(str.toLowerCase().indexOf('!')> -1){
            	alert("推送内容不能包含 insert、!、having、;、script、alert、<、>");
            	 return false;
            }
            if(str.toLowerCase().indexOf('insert')> -1){
            	alert("推送内容不能包含 insert、!、having、;、script、alert、<、>");
            	 return false;
            }
            if(str.indexOf(";")> -1){
            	alert("推送内容不能包含 insert、!、having、;、script、alert、<、>");
            	 return false;
            }
            if(str.toLowerCase().indexOf('having')> -1){
            	alert("推送内容不能包含 insert、!、having、;、script、alert、<、>");
            	 return false;
            }
            if(str.toLowerCase().indexOf('script')> -1){
            	alert("推送内容不能包含 insert、!、having、;、script、alert、<、>");
            	 return false;
            }
            if(str.toLowerCase().indexOf('alert')> -1){
            	alert("推送内容不能包含 insert、!、having、;、script、alert、<、>");
            	 return false;
            }
            if(str.toLowerCase().indexOf('<')> -1){
            	alert("推送内容不能包含 insert、!、having、;、script、alert、<、>");
            	 return false;
            }
            if(str.toLowerCase().indexOf('>')> -1){
            	alert("推送内容不能包含 insert、!、having、;、script、alert、<、>");
            	 return false;
            }
            
            return true;
        }

        var exLev="1";
        function examine(){
                var randomNo=$("#yzm").val();
                var spr=$("#spr").val();
                if(sendCode==""){
                        return false;
                }
                if(randomNo==""){
                        alert("请填写随机码！");
                        return false;
                }

                var para="?send_code="+sendCode+"&send_type=2&random_no="+randomNo+"&activeCode="+activeCode+"&spr="+spr+"&exLev="+exLev;

                $.ajax({
                type: "post",
                url: "sendMessageAction!examineSendMessage"+para,
                dataType : "text",
                success: function(v){
                        if(v=="e"){
                                alert("活动异常，请重新创建");
                                window.location.href="createActive.jsp";
                        }
                        var s=v.split("|");
                        if(s[1]=="S"){
                                loadtsxxdata();
                                alert("审核通过！");
                                //$("#sendMs").attr("disabled","disabled");  //设置推送内容不可改变
                                //exCityId='0';
                                //loadShrZscl();  //加载二次审核人
                                //if(exLev==1){
                                //      alert(s[0]+"请继续选择领导审核。");
                                //      $('#yzm').val("");
                                //      $('#sfyzm').unbind("click");
                                //      $('#sfyzm').bind("click",sendSjRandom);
                                //}else{
                                //      alert(s[0]);
                                //      loadtsxxdata();
                                //}
                        }else{
                                alert(s[0]);
                        }

                }
                });
        }

        function sendSjRandom(){
                exLev="2";
                var sendMs=$("#sendMs").val();
                if($.trim(sendMs)==""){
                        alert("请填写推送内容！");
                        return false;
                }
                var spr=$("#spr").val();
                if(spr==""){
                        alert("请选择审批人！");
                        return false;
                }
                var param="?send_ms="+encodeURI(sendMs)+"&spr="+spr+"&activeCode="+activeCode+"&send_type=2&sendCode="+sendCode;

                $.ajax({
                type: "post",
                url: "sendMessageAction!randomNoSend"+param,
                dataType : "text",
                success: function(v){

                }

                });


        }

        var maxLen=140;
        
        //计算输入字节
        function sumConlen(){   	
        	var sendMs=$("#sendMs").val();
			var len=$.trim(sendMs).length;
			var byteLen=0;
			for(var i=0;i<len;i++){
				byteLen +=2;
// 				var ch =$.trim(sendMs).charCodeAt(i);
// 				if(ch>255){
// 					byteLen +=3;
					
// 				}
// 				else{
// 					byteLen +=1;
// 				}
				
			}		
			$("#conLen").html(maxLen-byteLen);
        }

</script>

         <div class="down_left" style="height:340px;" id="down_left_id" >
                        <div class="down_left_bar">
                                <span>推送渠道及内容设置</span>
						 </div>
           <div class="list_btn" >
           <ul style="list-style:none;margin-top:15px;margin-left:10px;">
            <li ><img src="images/zhangshangchongkan_btn.png" /></li>
			<li ><img src="images/wt.png" onclick="loadsmwt('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/zt.png" onclick="loadsmzt('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/wet.png" onclick="loadsmwxt('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/yingxiaoguwenqiantai_btn_wx.png" style="cursor:pointer;" onclick="loadsmyhgw('s');"/></li>
			<li ><img src="images/10086duanxin_wx.png" onclick="loadsm('s');" style="cursor:pointer;"/></li>
			<li ><img src="images/1111.png" /></li>
			<li ><img src="images/1111.png" onclick="loadsmskxt('s');" style="cursor:pointer;"/></li>

			             </ul>
        </div>


                <div class="nr_block" style="height:auto">
                        <table style="padding-bottom:10px;">
                        <tbody>
                        <tr>
                        <th style="text-align:right">活动编码：</th>
                        <td id="txActiveCode">001</td>
                        </tr>

                        <tr id="sendMassege_time" style="display:none;">
                        <th style="text-align:right">推送时间：</th>
                        <td><input id="sendTime" class="runcode inputText" style="width:140px;position:relative; "  type="text" value=""  name=
"task_name2" />
                        注：短信第一次发送时间
                        </td>
                        </tr>

                        <tr id="sendMassege_cycle" style="display:none;">
                        <th style="text-align:right">推送周期：</th>
                        <td><input type="radio" value="30000" name="send_cycle" >一次
                                <input type="radio" value="1" name="send_cycle" checked="checked">每天
                                <input type="radio" value="7" name="send_cycle">每周
                                <input type="radio" value="30" name="send_cycle">每月</td>
                        </tr>

                    <tr>
                        <th  style="text-align:right">推送内容：</th>
<!--                         onpropertychange="sumConlen();"    oninput="sumConlen();" onpropertychange="sumConlen();" -->
                        <td><textarea    maxchar="400" rows="3" cols="45" style="font-size:12px; width:450px;" id="sendMs" name="task_content" class="">
                        </textarea>
                        <div class="remain" style="width:450px;text-align:right;">
                          您可以输入
                        <span id="conLen">140</span>
                         个字符         点击<a href="http://<%=ip %>:18080/portal/aibi_portal/submenu.jsp?ID=90114770" target="_blank">内容检索库</a>查询最新
热门内容信息    </div>                  </td>
                        </tr>

						<tr>
                        <th style="text-align:right">测试号码：</th>
                        <td><input id="testPhone" class="runcode inputText" style="width:100px; position:relative; "  type="text" value=""  name="task_name" />
                        	&nbsp;选填                      
                        </td>
                        </tr>
                        <tr style="">
                        <th style="text-align:right;height:55px">&nbsp;友情提示 :&nbsp;</th>
                        <td><span style="color:#58a2db;">短信发送时段为上午09:00-11:00，下午13:00-20:00。其他时段为短信免打扰</span></td>             
                        </tr>
						<!--
                        <tr>
                        <th style="text-align:right">审核人：</th>
                        <td id="shenheren"> </td>
                        </tr>

                        <tr>
                        <th style="text-align:right">验证密码：</th>
                        <td><input id="yzm" class="runcode inputText" style="width:100px; position:relative; "  type="text" value=""  name="task_name" />
                          <img src="images/fsyzm_btn.png" id="sfyzm" onclick="yanzSm();" style="vertical-align:middle; cursor:pointer" />
                        </td>
                        </tr>
						-->
                        </tbody>
                        </table>


                </div>
                        <ul style="list-style:none;width:140px;margin:0 auto;">
                                <li style="float:left; margin-top:10px"><img src="images/tijiaoneirong_btn.png" onclick="yanzSm()" style="cursor:pointer;" /></li>
                                <li style="float:left;margin-left:5px;margin-top:10px"><img src="images/chongzhi_btn.png" /></li>
                        </ul>



         </div>


                <div class="down_right" id="down_right_id">
                        <div class="down_right_bar">
                                <ul>
                                <li >设置提示</li>
                                <li style="background:#FFFFFF;color:#58a2db;"></li>
                                </ul>

                        <div class="down_right_nr">
                        <span>申请短信内容编码：请走公司市场部服务组
的内容审批流程，有电渠支撑中心进行配置。

短信是试发：是将配置的营销短信内容发送到您本人的手机上且只有您本人能接收到此
短信，当短信发送成功，“提交审批”按钮才可用。</span>
           </div>

                </div>

                </div>