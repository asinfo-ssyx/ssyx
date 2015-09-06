
function loaddiv(v){
	if(v=="web"){//网站
		loadWebClass();
		changeCss('cftj');
		setStep="cftj";
		deleteUpdateImage("cftj");
		$("#hsetStep").val(setStep);
	}else if(v=="tsxx"){//推送设置
		loadsmzscl(v);
		changeCss('tsxx');
		setStep="tsxx";
		deleteUpdateImage("tsxx");
		$("#hsetStep").val(setStep);
	}else if(v=="ActiveEventSelection"){//活动事件选择
		loadActiveEventSelection();
		changeCss('cftj');
		setStep="cftj";
		deleteUpdateImage("cftj");
		$("#hsetStep").val(setStep);
	}else if(v=="app"){//app
		loadapp(v);
		changeCss('cftj');
		setStep="cftj";
		deleteUpdateImage("cftj");
		$("#hsetStep").val(setStep);
	}else if(v=="wz"){//位置
		loadposition(v);
		changeCss('cftj');
		setStep="cftj";
		deleteUpdateImage("cftj");
		$("#hsetStep").val(setStep);
	}else if(v=="gjz"){//关键字
		loadgjz(v);
		changeCss('cftj');
		setStep="cftj";
		deleteUpdateImage("cftj");
		$("#hsetStep").val(setStep);
	}else if(v=="yhq"){//用户群
		loadyhq(v);
		changeCss('yhq');
		setStep="yhq";
		deleteUpdateImage("yhq");
		$("#hsetStep").val(setStep);
	}else if(v=="glyhq"){//过滤设置
		loadgltj(v);
		changeCss('glyhq');
		setStep="glyhq";
		deleteUpdateImage("glyhq");
		$("#hsetStep").val(setStep);
	}
	loadAllTitle();
	$("#userGroupIframe").css("display","none");
	$('#userGroupIframe').attr("src","about:blank");
}


//load 应用设置
function loadapp(v){
	$("#loaddiv").load("triggerAction!getAppList",function(response,status){});
}
//活动事件类型
function loadActiveEventSelection(){
	$("#loaddiv").load("activeEvent?eventType="+acitveScene,function(response,status){});
}
//网站
function loadWebClass(){
	$("#loaddiv").load("activeEvent!queryWebClassList",function(response,status){});
}

//load 位置设置
function loadposition(v){
	$("#loaddiv").load("triggerAction!getCityList",function(response,status){});
}

//load 关键字设置
function loadgjz(v){
	$("#loaddiv").load("keyWord.jsp",function(response,status){});
}

//load 用户群设置
function loadyhq(v){
	$("#loaddiv").load("userGroup.jsp",function(response,status){});
}

//load 过滤用户群设置
function loadgltj(v){
	$("#loaddiv").load("UploadAction!glUserGroup",function(response,status){});
}

//load 推送设置 10086
function loadsm(v){
	$("#loaddiv").load("sendMessage10086.jsp",function(response,status){
			$("#smActiveName").html(activeName);
			$("#smActiveBody").html(activebody);
	});
}

function loadsmzscl(v){
	$("#loaddiv").load("sendMessageZscl.jsp",function(response,status){
			$("#txActiveCode").html(activeCode);
	});
}
function loadsmwt(v){
	$("#loaddiv").load("sendMessagewt.jsp",function(response,status){
			$("#txActiveCode").html(activeCode);
	});
}
function loadsmzt(v){
	$("#loaddiv").load("sendMessagezt.jsp",function(response,status){
			$("#txActiveCode").html(activeCode);
	});
}
function loadsmwxt(v){
	$("#loaddiv").load("sendMessagewxt.jsp",function(response,status){
			$("#txActiveCode").html(activeCode);
	});
}
function loadsmyhgw(v){
	$("#loaddiv").load("sendMessageyhgw.jsp",function(response,status){
			$("#txActiveCode").html(activeCode);
	});
}
function loadsmskxt(v){
	$("#loaddiv").load("sendMessageskxt.jsp",function(response,status){
			$("#txActiveCode").html(activeCode);
	});
}

function loadAllTitle(){
	if(setStep!="cftj"){
		loadcftjdata();
	}
	loadyhqdata();
	loadglkhqdata();
	loadtsxxdata();
}

//查询结果没有数据调用这个方法 添加设置图片 id=v+t
function setTitleInitCss(v){
	 if(setStep==v){
			return;
	 }
	 $("#"+v+"t").removeClass();
	 $("#"+v+"t").addClass("bg_sz");
	 var loadParm="";
	 if("cftj"==v){
		if(acitveScene>10){
			loadParm="ActiveEventSelection";
		}else{
	 		loadParm="app";
		}
	 }else{
		loadParm=v;
	 }
	 $("#"+v+"t").html("<img src=\""+projectPath+"/images/shezhi_btn.png\" onclick=\"loaddiv('"+loadParm+"');\" style=\"cursor:pointer;\" />");
}

//查询到有数据调用这个方法  添加修改图片 id=v+"u"
function setTitleUpdateCss(v){
		if(setStep==v){
			return;
		}
		$("#"+v+"t").removeClass();
	 	$("#"+v+"t").addClass("cftj_block_biaoqian");
		var loadParm="";
		 if("cftj"==v){
			if(acitveScene>10){
				loadParm="ActiveEventSelection";
			}else{
				loadParm="app";
			}
		}else{
			loadParm=v;
		}
		$("#"+v+"u").html("<img src=\""+projectPath+"/images/xiugai-btn.png\" onclick=\"loaddiv('"+loadParm+"');\" style=\"cursor:pointer;\"/>");
}

//删除设置图片
function deleteUpdateImage(v){
	$("#"+v+"t").html("");
	$("#"+v+"u").html("");
}

//查询客户群数据--导航
function loadyhqdata(){
 	$.ajax({
		type: "post",
		url: "filterUserAction!getSubUserGroup"+acparam,
		dataType : "text",
		success: function(v){
			if(v==""){
				setTitleInitCss("yhq");//无数据调用添加设置图片
				$("#yhqu").html("");
				return;
			}
			var tableboby="";
			var apps=v.split(",");
			var uuid="";
			for (var i=0;i<apps.length-1;i++)
			{
				var app=apps[i];
				uuid = new UUID().createUUID();
				tableboby+="<div id=\""+uuid+"\"  style=\"cursor:pointer;width:170px;\"  ondblclick=\"deleteyhq('"+app+"','"+uuid+"');\" class=\"gjc\">"+app+"</div>";
			}
			setTitleUpdateCss("yhq");//有数据添加修改图片
			$('#yhqt').html(tableboby);
		}
		});
}

//查询活动事件类型--导航
function loadEventTypedata(){
 	$.ajax({
		type: "post",
		url: "activeEvent!queryCheckedEventType"+acparam,
		dataType : "text",
		success: function(v){
			if(v==""){
				setTitleInitCss("cftj");//无数据调用添加设置图片
				return;
			}
			var tableboby="";
			var apps=v.split("_");
			var uuid=new UUID().createUUID();
				tableboby+="<div id=\""+uuid+"\"  style=\"cursor:pointer;width:170px;\"  ondblclick=\"deleteaet('"+apps[1]+"','"+uuid+"');\" class=\"gjc\">"+apps[1]+"</div>";
			setTitleUpdateCss("cftj");//有数据添加修改图片
			$('#cftjt').html(tableboby);
		}
		});
}


//查询过滤条件数据--导航
function loadglkhqdata(){
 	$.ajax({
		type: "post",
		url: "filterUserAction!getSubFilterUserGroup"+acparam,
		dataType : "text",
		success: function(v){
			if(v==""){
				setTitleInitCss("glyhq");//无数据调用添加设置图片
				return;
			}
			var tableboby="";
			var apps=v.split(",");
			var uuid;
			for (var i=0;i<apps.length-1;i++)
			{
				var app=apps[i];
				uuid = new UUID().createUUID();
				tableboby+="<div id=\""+uuid+"\" style=\"cursor:pointer;\"  ondblclick=\"deleteglyhq('"+app+"','"+uuid+"');\" class=\"gjc\">"+app+"</div>";
				if(i==7 && apps.length-1>8){
					tableboby+="<div class=\"gjc\" onclick=\"getAllGlyhq("+v+");\">查看更多 ></div>";
					break;
				}
			}
			setTitleUpdateCss("glyhq");//有数据添加修改图片
			$('#glyhqt').html(tableboby);
		}
		});
}
//查询推送设置
function loadtsxxdata(){
	$.ajax({
		type: "post",
		url: "sendMessageAction!getSubSendMessage"+acparam,
		dataType : "text",
		success: function(v){
			if(v==""){
				setTitleInitCss("tsxx");//无数据调用添加设置图片
				return;
			}
			var tableboby="";
			var apps=v.split(",");
			var uuid;
			for (var i=0;i<apps.length-1;i++)
			{
				var app=apps[i];
				var sm="";
				if(app==1){
					sm="10086短信";
				}else if(app==2){
					sm="掌上冲浪";
				}else if(app==3){
					sm="营销顾问前台";
				}else if(app==5){
					sm="网厅";
				}else if(app==6){
					sm="掌厅";
				}else if(app==7){
					sm="微厅";
				}else if(app==8){
					sm="营销顾问前台";
				}else if(app==10){
					sm="熟客系统";
				}
				uuid = new UUID().createUUID();
				tableboby+="<div id=\""+uuid+"\" style=\"cursor:pointer;width:170px;\"  ondblclick=\"deletesendms('"+app+"','"+sm+"','"+uuid+"');\" class=\"gjc\">"+sm+"</div>";
			}
			setTitleUpdateCss("tsxx");//有数据添加修改图片
			$('#tsxxt').html(tableboby);
		}
		});
}
//触发条件查询--
function loadcftjdata(){
	$.ajax({
		type: "post",
		url: "triggerAction!getTriggerInfoMs"+acparam,
		dataType : "text",
		success: function(v){
			if(v=="&&"){
				setTitleInitCss("cftj");//无数据调用添加设置图片
				return;
			}
			var tableboby="";
			var keyWord=v.split("&")[0];
			var app =v.split("&")[1];
			var wz  =v.split("&")[2];

			var keyWords=keyWord.split(",");
			var apps=app.split(",");
			var wzs=wz.split(",");

			tableboby+="<div class=\"gjc\" style=\"color:#317cb5;font-weight:bold;background:#FFFFFF;\">关键字 ></div>";
			for (var i=0;i<keyWords.length+3;i++)
			{
				var app="";
				if(i<keyWords.length){
					app=keyWords[i];
				}
				if(i==3 )break;
				tableboby+="<div class=\"gjc\">"+app+"</div>";
			}
			tableboby+="<div class=\"gjc\" style=\"color:#317cb5;font-weight:bold;background:#FFFFFF;\">应用 ></div>";
			for (var i=0;i<apps.length+1;i++)
			{
				var app="";
				if(i<apps.length){
					app=apps[i];
				}
				if(i==1 )break;
				tableboby+="<div class=\"gjc\">"+app+"</div>";
			}
			tableboby+="<div class=\"gjc\" style=\"color:#317cb5;font-weight:bold;background:#FFFFFF;\">位置 ></div>";
			tableboby+="<div class=\"gjc\" onclick=\"getAllTriggerMs('"+v+"');\">查看更多 ></div>";

			setTitleUpdateCss("cftj");//有数据添加修改图片
			$('#cftjt').html(tableboby);
		}
		});
}


function getAllTriggerMs(v){
	var keyWord=v.split("&")[0];
	var app =v.split("&")[1];
	var wz  =v.split("&")[2];

	var keyWords=keyWord.split(",");
	var apps=app.split(",");
	var wzs=wz.split(",");

	var gjz="<div class=\"tm\">关键词></div>";
	for (var i=0;i<keyWords.length;i++){
		var app=keyWords[i];
		if(app!=null&& app!=""){
			gjz+="<div class=\"tab2\">"+app+"</div>";
		}
	}
	$('#guanjianzi').html(gjz);

	var yingy="<div class=\"tm\">应 用></div>";
	for (var i=0;i<apps.length;i++){
			var app=apps[i];
			if(app!=null&& app!=""){
				yingy+="<div class=\"gjc\">"+app+"</div>";
			}
	}
	$('#yingyong').html(yingy);

	var weizhi="<div class=\"tm\">位 置></div>";
	for (var i=0;i<wzs.length;i++){
			var app=apps[i];
			if(app!=null&& app!=""){
				yingy+="<div class=\"gjc\">"+app+"</div>";
			}
	}
	$('#weizhi').html(weizhi);
	$('#allTriggerMs').css("display","block");
}

function closeAllTriggerMsDiv(){
	$('#allTriggerMs').css("display","none");

	var d=$('#spr').val();
	if(d=="app"){
		titleApp();
	}else if(d=="gjz"){
		searchKeyWork();
	}else if(d=="wz"){
		titleBs();
	}
}

function deleteyhq(y,d){
	 if(setStep!="yhq") return;
	if(confirm("确定要删除用户群'"+y+"'吗？")){
	 	var s=deleteSetProperty(y,1,2);
    	if(s=="Y"){
			$("#"+d).remove();
		}
	 }else{

	 }
}

function deleteglyhq(g,d){
	if(setStep!="glyhq") return;
	if(confirm("确定要删除过滤规则用户群'"+g+"'吗？")){
	 	var s=deleteSetProperty(g,2,2);
    	if(s=="Y"){
    		$("#"+d).remove();
		}
	 }else{

	 }
}

function deletesendms(s,sm,d){
	if(setStep!="tsxx") return;
	if(confirm("确定要删除推送渠道内容'"+sm+"'吗？")){
	 	var s1=deleteSetProperty(s,1,3);
    	if(s1=="Y"){
    		$("#"+d).remove();
		}
	 }else{

	 }
}

function deleteaet(eid,did){
	if(confirm("确定要删除活动事件类型'"+eid+"'吗？")){
	 	var s=deleteSetProperty(eid,4,1);
    	if(s=="Y"){
    		//同时删除用户群
    		$.ajax({
    			type: "post",
    			async:false,
    			url: "activeEvent!deleteActiveEventTypeUserGroup"+acparam,
    			dataType : "text",
    			success: function(v){
    				s=v;
    			}
    			});
    		if(s=="Y"){
    			$("#"+did).remove();
    			loadyhqdata();
    		}
		}
	 }else{

	 }
}

function deleteSetProperty(cn,ty,tty){
	var p=acparam+"&triggerName="+encodeURI(cn)+"&triggerType="+ty+"&titleType="+tty;
	var re="F";
	$.ajax({
		type: "post",
		async:false,
		url: "triggerAction!deleteSetProperty"+p,
		dataType : "text",
		success: function(v){
			re=v;
		}
		});
	return re;
}








