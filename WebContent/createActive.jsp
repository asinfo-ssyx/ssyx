<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
session.removeAttribute("activeCode");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>营销场景</title>
<link href="css/createindex.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body, div, table, tr, td, ul, li, dl, dt, dd, p, h1, h2, h3, h4, h5, h6, img, form, select, input
   {
    margin: 0;
    padding: 0;}
</style>
<script language="javascript"  src="js/jquery-1.7.1.min.js"></script>
<script language="javascript"  src="js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
        $(function(){
            $('#sscjtr').css("display","none");
            $('#fsscjtr').css("display","none");
            $("#acitveContent").on("input",sumConlen);
            $("#acitveContent").on("propertychange",sumConlen);
        });
        //$('#endTime').datetimepicker(); 
        //$('#endTime').timepicker();
//$('#endTime').calendar({ format:'yyyy-MM-dd HH:mm:ss'});

        var para="";
        var activeName="";
        var beginTime="";
        var endTime ="";
        var activeType="";
        var userType="";
        var acitveContent="";
        var maxLen=400;
        var len=0;
        //判断输入内容长度
        function sumConlen(){   
        	
        	var sendMs=$("#acitveContent").val();
			len=$.trim(sendMs).length;
			$("#contentLength").html(maxLen-len);
			
        }
        //当选择：0流量及低流量用户时触发时间
        function showCycleSelect(){
                var objS = document.getElementById("fsscj");
                var value = objS.options[objS.selectedIndex].value;
                //alert(value);
                if(value>7){
                        $('#cycle').css("display","");
                        $('#date').css("display","none");
                        $('#date1').css("display","none");
                        $('#beginTime').css("display","");
                        $('#beginTimeStamp').css("display","");
                        $('#to').css("display","none");
                        $('#to1').css("display","none");
                        $('#endTime').css("display","none");
                        $('#endTimeStamp').css("display","none");
                        $('#beginTime').attr("value","");
                        $('#beginTimeStamp').attr("value","");
                        $('#endTime').attr("value","");
                        $('#endTimeStamp').attr("value","");

                }else{
                        $('#cycle').css("display","none");
                        $('#date').css("display","");
                        $('#date2').css("display","");
                        $('#beginTime').css("display","");
                        $('#beginTimeStamp').css("display","");
                        $('#to').css("display","");
                        $('#to1').css("display","");
                        $('#endTime').css("display","");
                        $('#endTimeStamp').css("display","");
                        $('#beginTime').attr("value","");
                        $('#beginTimeStamp').attr("value","");
                        $('#endTime').attr("value","");
                        $('#endTimeStamp').attr("value","");
                }
        }
        //周期性radio切换触发事件
        function radio_click(obj){
            var value=obj.value;
            if(value=='1'){
                $('#date').css("display","");
                $('#date1').css("display","");
                $('#to').css("display","none");
                $('#to1').css("display","none");
                $('#beginTime').attr("value","");
                $('#beginTimeStamp').attr("value","");
                $('#endTime').css("display","none");
                $('#endTimeStamp').css("display","none");
            }else{
                $('#date').css("display","");
                $('#date1').css("display","");
                $('#to').css("display","");
                $('#to1').css("display","");
                $('#beginTime').attr("value","");
                $('#beginTimeStamp').attr("value","");
                $('#endTime').css("display","");
                $('#endTimeStamp').css("display","");
                $('#endTime').attr("value","");
                $('#endTimeStamp').attr("value","");
            }
        }

        function afterObserve(){
                if($("#ao").attr("checked")=="checked"){
                        $('#genz').css("display","block");
                }else{
                        $('#genz').css("display","none");
                }
        }

        var cjxz;
        function activeTypeSet(t){
                cjxz=t;
                $("#hactiveType").val(cjxz);
                if(t==1){
                        //alert(1);
                        $('#sscj').attr("value","");
                        $("#hsetStep").val("");
                        $('#sscjtr').css("display","");
                        $('#fsscjtr').css("display","none");
                        $('#cycle').css("display","none");
                        $('#endTime').css("display","");
                        $('#beginTime').attr("value","");
                        $('#endTime').attr("value","");


                }else{
                        //alert(2);
                        $('#fsscj').attr("value","");
                        $("#hsetStep").val("yhq");
                        $('#sscjtr').css("display","none");
                        $('#fsscjtr').css("display","");
                        $('#beginTime').attr("value","");
                        $('#beginTimeStamp').attr("value","");
                        $('#endTime').attr("value","");
                        $('#endTimeStamp').attr("value","");
                }
        }
        var yxcj="";
        function isCheck(){
                 //alert("获取页面信息!");
                 activeName=$("#activeName").val();
                 beginTime=$("#beginTime").val() + " " + $("#beginTimeStamp").val();
                 endTime =$("#endTime").val() + " " + $("#endTimeStamp").val();
                 activeType=$("input[name='activeT']:checked").val();
                 userType=$("input[name='userT']:checked").val();
                 acitveContent=$("#acitveContent").val();
                 if(cjxz==1){
                         yxcj=$("#sscj").val();
                 }else if(cjxz==2){
                         yxcj=$("#fsscj").val();
                 }
                $("#hacitveScene").val(yxcj);
                if(activeType=="undefined"||activeType==undefined){
                        alert("请选择活动类型！");
                        return false;
                }
                if(userType=="undefined"||userType==undefined){
                        alert("请选择部门类型！");
                        return false;
                }
                if(activeName==""){
                        alert("请填写活动名称！");
                        return false;
                }
                if(yxcj==""){
                        alert("请选择营销场景！");
                        return false;
                }

                if($.trim(beginTime)==""){
                        alert("请选择活动开始时间！");
                        return false;
                }
                if($.trim(endTime)==""){
                        if(yxcj<8){
                                alert("请选择活动结束时间！");
                                return false;
                        }
                        endTime=beginTime.substring(0, 10)+" 23:59:59";
                    alert("endTime:"+endTime);
                }
                if(acitveContent==""){
                        alert("请填写活动内容！");
                        return false;
                }
                else if(len>maxLen)
                	 {
                	  alert("输入内容字符超出范围");
                	  return false;
                	 }
                searchData();
        }


        function searchData(){
                para="?";
                para+="activeName="+encodeURI(activeName)+"&";
                para+="beginTime="+beginTime+"&";
                para+="endTime="+endTime+"&";
                para+="activeType="+activeType+"&";
                para+="userType="+encodeURI(userType)+"&";
                para+="acitveContent="+encodeURI(acitveContent)+"&";
                para+="acitveScene="+yxcj+"&";
                //alert(para);
                ceateActive();
        }

        function ceateActive(){
                $.ajax({

                type: "post",

                url: "activeAction!createNewActive"+para,
                dataType : "text",

                beforeSend: function(XMLHttpRequest){
                        //ShowLoading();
                        $('#sub').unbind("click");
                },
                success: function(v){
                        if(v==''){
                                alert("创建活动失败，请重新点击！");
                        }else{
                                $("#activeCode").val(v);
                                var acb=$("#acitveContent").val();
                                $("#activeBody").val(acb);
                                $("#hactiveName").val(activeName);
                                $("#hbeginTime").val(beginTime);
                                $("#hendTime").val(endTime);
                                if(yxcj>7){
                                        $("#hfsscj").val("Y");
                                        if(yxcj>10){
                                        //      $("#hsetStep").val("ActiveEventSelection");
                                        }
                                }
                                if(yxcj==5){//重入网场景特殊处理
                                        subUserGroup(v);
                                        $("#hsetStep").val("tsxx");
                                }
                                ///////////////////////////提交下一步，先插入数据至库中，然后提交表单
                                $("#suForm").submit();
                        }
                },
                complete: function(XMLHttpRequest, textStatus){
                        //HideLoading();
                        $("#sub").bind("click", isCheck);
                },
                error: function(e){
                        //请求出错处理
                        alert("创建活动失败，请重新点击！");
                }
                });

        }

        function suActiveType(t){
                activeType=t;
                alert(activeType);
        }

        function goto(i){
                if(i=='1'){
                        window.location.href="activeIndex.jsp";
                }else if(i=='2'){
                        window.location.reload();
                }else if(i=='3'){
                	window.location.href="effectIndex.jsp";
                }
        }


        function subUserGroup(activeCode){
                userGroup_code="userGroup_code";
                userGroup_name="<重入网用户群>";
                var para="?groupName="+userGroup_name+"&searchsql="+userGroup_code+"&activeCode="+activeCode;
                para=encodeURI(para);
                $.ajax({
                async: false,
                type: "post",
                url: "userGroup!selectedUserGroupInsert"+para,
                dataType : "text",
                success: function(v){
                }
                });
        }
        
        
        function checkTime(){
        	
        }

</script>
</head>


<body>
<div class="top">
    <div class="nav">
                <div style="padding-top:20px; float:left"><img src="images/top_logo.png" /></div>

                                <div class="top_xuanxiang">
                                <ul >
                    <li onclick="goto('1')" style="cursor:pointer;">营销任务管理</li>
                    <li onclick="goto('2')" style="cursor:pointer;"><img src="images/top_yxrwcj_1.png" /></li>
                    <li  onclick="goto('3')" style="cursor:pointer;">效果跟踪报表</li>
                </ul>
                                </div>

    </div>
</div>


<div class="main_xinxi">
        <div class="main_xinxi_bar" >
        <span>基本条件录入</span>       </div>

        <div class="main_xinxi_block">
           <div class="main_xinxi_block_step">
                <table cellpadding="4">
                <tbody >
                        <tr>
                        <th >活动类型：</th>
                        <td >
                        <form name="form1">
            <input type="radio" name="activeT" value="1" onclick="activeTypeSet(1);" />
             实时类
                        <input type="radio" name="activeT" value="2" onclick="activeTypeSet(2);" style="margin-left:15px;"/>
             非实时类</form>
                        </td>
                        </tr>


                        <tr>
                        <th>部门选择：</th>
                        <td>
                        <form name="form2">
            <input type="radio" name="userT" value="数据部" />
             数据部
                        <input type="radio" name="userT" value="市场部" style="margin-left:15px;" />
            市场部
                         </form>
                        </td>
                        </tr>

                <tr id="sscjtr">
                        <th>场景选择 :</th>
                        <td >
                        <select id="sscj">
                                                <option value="" selected="selected">请选择活动场景</option>
                                                <option value="1">基于上网行为的实时运营</option>
                                                <option value="2">基于用户位置的实时运营</option>
                        </select>
                        </td>
                  </tr>

                        <tr id="fsscjtr">
                        <th>场景选择 :</th>
                        <td >
                                <select id="fsscj" onChange="showCycleSelect();">
                                                <option value="" selected="selected">请选择活动场景</option>
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
												<option value="15">内容型业务活性提升</option>
												<option value="16">新入网用户关怀</option>
                                </select>
                        </td>
                        </tr>


                        <tr>
                        <th >名称设置：</th>
                        <td >
                        <input id="activeName" class="inputText" type="text" value="" maxlength="50" size="30" name="task_name" style="width:304px;" />
                        </td>
                        </tr>

                        <tr id="cycle" style="display:none;margin-top:-15px;margin-left:100px;">
                        <th >营销短信推送周期：</th>
                        <td>
                        <form name="form3">
            <input type="radio" name="cycle" onclick="radio_click(this)" value="1" />一次性推送
                        <input type="radio" name="cycle" onclick="radio_click(this)" value="2" style="margin-left:15px;" />周期性推送
                        </form>
                        </td>
                        </tr>

                        <tr id="date"  style="margin-top:-15px;margin-left:100px;">
                        <th>执行日期：</th>
                        <td>
                        <input  style="width:140px; height:25px;position:relative; " readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',minDate:'%y-%M-%d' ,maxDate:'#F{$dp.$D(\'endTime\')}'})" class="runcode inputText" id="beginTime"  />
                        <font style="position:relative;" id="to" >到</font>
                        <input style="width:140px; height:25px;position:relative; " readonly="readonly"  onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'beginTime\')}'})" class="runcode inputText" id="endTime" />
                        </td>
                        </tr>
                        
        
        				<tr id="date1"  style="margin-top:-15px;margin-left:100px;">
                        <th>执行时间：</th>
                        <td>
                        <input  style="width:140px; height:25px;position:relative; " readonly="readonly"  onfocus="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen',maxDate:'#F{$dp.$D(\'endTimeStamp\')}' })" class="runcode inputText" id="beginTimeStamp"  />
                        <font style="position:relative;" id="to1" >到</font>
                        <input style="width:140px; height:25px;position:relative; " readonly="readonly"  onfocus="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen',minDate:'#F{$dp.$D(\'beginTimeStamp\')}'})" class="runcode inputText" id="endTimeStamp" />
                        </td>
                        </tr>


                        <tr>
                        <th>活动内容：</th>
                        <td>
                        <textarea  id="acitveContent" class="inputText"  style="height:130px;font-size:12px; width:450px;" maxchar="400"  rows="5" cols="60"
 name="task_content" >
                        </textarea>
                        <div class="remain" style="width: 509px;text-align:right;">
                          您可以输入
                        <span id="contentLength">400</span>
                         个字符
                        </div>
                        </td>
                        </tr>
                  </tbody>
                 </table>
          </div>

                        <div align="center" style="height:60px;" >
                        <a class="botton" style="text-decoration:none;cursor:pointer;" onclick="isCheck(); "> 下一步 </a>
                        </div>
        </div>
</div>

 <div class="footer" >@2013中国移动通信集团四川有限公司  版权所有 </div>

<form action="trigger.jsp" id="suForm" method="post">
<input type="hidden" name="activeCode" id="activeCode" value="" />
<input type="hidden" name="activeBody" id="activeBody" value="" />
<input type="hidden" name="activeName" id="hactiveName" value="" />
<input type="hidden" name="beginTime" id="hbeginTime" value="" />
<input type="hidden" name="endTime" id="hendTime" value="" />
<input type="hidden" name="setStep" id="hsetStep" value="" />
<input type="hidden" name="activeType" id="hactiveType" value="" />
<input type="hidden" name="fsscj" id="hfsscj" value="" />
<input type="hidden" name="acitveScene" id="hacitveScene" value="" />

</form>
</body>
</html>