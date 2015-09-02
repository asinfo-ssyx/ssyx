	var num = 0;
	//查询关键字 
	function searchKeyWord() {
		para = $("#serachPara").val();
		para = "keyName=" + para;
		para=encodeURI(para);
		//alert(para);
		$.ajax({
			type : "post",
			url : "triggerInfo!searchKeyWordByWord?" + para,
			dataType : "text",
			success : function(v) {
				var arr = new Array();
				arr = v.split("&");
				for (var x = 0; x < arr.length; x++) {
					//底部keyWord显示;
					var txt = "<label id='labac" + num
							+ "' name='trigger_ms' ondblclick='removeKeyWord(labac"
							+ num + ")'>" + arr[x] + "</label> &nbsp";
					$("#select_key").append(txt);
					num++;
				}
			},
			error : function() {
				//获取出错提示
				alert("获取关键字失败，请重新获取！");
			}
		});
	}

	function removeKeyWord(i) {
		$(i).remove();
	}
	function submitKeyWord() {
		//var labelArray = new Array();
		var message="";
		//labelArray = $("label[name=trigger_ms]");
		var labelArray=$('label[id^=labac]');
		//拼接字符串
		for ( var i = 0; i < labelArray.length; i++) {
			message += labelArray[i].innerHTML + ",";
		}
		var searchType=$("#searchType").val();
		var paras = "";
		var ttype = $("#top_select").val();
		message=encodeURI(message);
		paras += "triggerType=1&";
		paras += "triggerMs=" + message;
		paras += "&activeCode="+activeCode;
		paras +="&searchType="+searchType;
		$.ajax({
			type : "post",
			url : "triggerInfo!triggerInsert?" + paras,
			//dataType : "text",
			success : function(v) {
				searchKeyWork();				
			},
			error : function() {
				//提交出错提示
				alert("提交失败，请重新提交！");
			}
		});
	}
	
	

	//用户群页面展示
	function loadUserGroup() {
		$("#loaddiv").load("userGroup.jsp",function() {
			$.ajax({
				type : "post",
				url : "userGroup!searchGroup?",
				dataType : "text",
				success : function(v) {
					var arrGroup = new Array();
					arrGroup = v.split("&");
					//alert(arrGroup);
					for (var i = 0; i < arrGroup.length; i++) {						
						//所有用户群显示;
						//alert(arr);
						if (i%2 == 0){
							var txt = "<tr><td><input type='radio' name='userGroup' value='" + arrGroup[i] +  "'>" + arrGroup[i] + "</input></td></tr>";
						}else {
							var txt = "<tr><td style='background:#FFFFFF;width:400px;height:29px;'>" +
										    "<input type='radio' name='userGroup' value='" + arrGroup[i] + "'>" + arrGroup[i] + "</input></td></tr>";
						}
						$("#groupTab").append(txt);
					}
				},
				error : function() {
					//获取用户群出错提示
					alert("获取用户群失败，请重新获取！");
				}
			});
		});
		$("#userGroupButton").remove();
	}
	//选中用户群提交
	function submitGroup(){
		var groupName = $("input:radio:checked").val();
		//var groupArr = message.split(",");
		var paras = "";
		paras += "groupName=" + groupName;
		$.ajax({
			type : "post",
			url : "userGroup!selectedUserGroupInsert?" + paras,
			//dataType : "text",
			success : function(v) {
				$.ajax({
					type : "post",
					url : "userGroup!searchGroupNameByCode?",
					dataType : "text",
					success : function(v) {
						$("#groupList1 li").remove();
						$("#groupList2 li").remove();
						var arr = new Array();
						arr = v.split("&");
						for (var x = 0; x < arr.length; x++) {
							//顶部group显示
							if(x<7){
								var txt1 = "<li>" + arr[x] + "</li>";
								if(x%2==0){
									$("#groupList1").append(txt1);
								}else{
									$("#groupList2").append(txt1);
								}
							}
							if(x==7){
								var txt1 = "<li style='color: #317cb5;'>more...</li>";
								$("#groupList2").append(txt1);
							}
						}
					},
					error : function() {
						//获取出错提示
						alert("获取用户群失败，请重新获取！");
					}
				});				
			},
			error : function() {
				//提交出错提示
				alert("提交失败，请重新提交！");
			}
		});
	}
	function chooseLabel(){
		window.open("http://www.baidu.com");
	}
	//新增客户群tab效果实现
	function liClick(id){
		$('#jc').css("display","none");
		$('#jz').css("display","none");

		$('#'+id).css("display","block");


		$('#jct').css("background","url(images/chufatiaojian_yingyong_tab_weixuanzhong_bg.png)");
		$('#jct').css("color","#6f6f6f"); 
		$('#jzt').css("background","url(images/chufatiaojian_yingyong_tab_weixuanzhong_bg.png)");
		$('#jzt').css("color","#6f6f6f");

		$('#'+id+'t').css("background","url(images/chufatiaojian_yingyong_tab_xuanzhong_bg.png)"); 
		$('#'+id+'t').css("color","#FFFFFF"); 

	}
	//新建用户群涉及标签提交
	function submitLabel() {
		var checked = new Array();
		var para1 = "";
		var para2 = "";
		var search_sql = "";
		var attr_value = "";
		$('input:checkbox:checked').each(function() {
            checked.push($(this).val());
        });
		var groupName = $("#groupName").val();
		if (groupName == ""){
			alert("请输入新用户群名称！");
			$("#groupName").focus();
			return false;
		}
		
		for(var i=0;i<checked.length;i++){
			var opr = "";
			var val = "";
			search_sql += checked[i];
			//attr_value += "column" + i + "=" + checked[i] + ",";
			attr_value += checked[i] + ",";
			
			val = $("#val_" + checked[i]).val();
			if (val == ""){
				alert("请输入value值！");
				return false;
			}
			if($("#opr_" + checked[i]).is('label')){
				opr = $("#opr_" + checked[i]).text();
			} else {
				opr = $("#opr_" + checked[i]).val();
			}
			
			//attr_value += "operator" + i + "=" + opr + ",";
			attr_value += opr + ",";
			if(i != checked.length-1){
				search_sql += opr + val + " and ";
				attr_value += val + ",,";
			}else {
				search_sql += opr + val;
				attr_value += val;
			}
		}
		para1 += "groupName=" + groupName + "&";
		para1 += "sql=" + search_sql;
		para2 += "groupName=" + groupName + "&";
		para2 += "attr_value=" + attr_value;
		alert(para2);
		$.ajax({
			type : "post",
			url : "userGroup!userGroupInsert?" + para1,
			dataType : "text",
			success : function(v) {
			},
			error : function() {
				//新增用户群失败提示
				alert("新增用户群失败，请重试！");
			}
		});
		$.ajax({
			type : "post",
			url : "userGroup!userGroupValueInsert?" + para2,
			dataType : "text",
			success : function(v) {
			},
			error : function() {
				//插入新增用户群属性失败提示
				alert("新增用户群属性插入失败，请重试！");
			}
		});
		loadUserGroup();
	}
	
	