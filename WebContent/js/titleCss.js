// JavaScript Document
function changeCss(tid){
	  //删除css  remove
	  $("#cftj").removeClass();
	  $("#yhq").removeClass();
	  $("#glyhq").removeClass();
	  $("#tsxx").removeClass();
	  $("#queding").removeClass();
	  
	  if(tid=="cftj"){//触发条件
		  $("#cftj").addClass("tab01_selected");//触发条件单独设置
		  $("#cftjt").removeClass();
	 	  $("#cftjt").addClass("cftj_block_biaoqian");
		  
		  $("#yhq").addClass("tab02_noselected");
		  $("#glyhq").addClass("tab_noselected");
		  $("#tsxx").addClass("tab_noselected");
		  $("#queding").addClass("tab_last");
	  }
	  if(tid=="yhq"){//用户群
		  $("#yhq").addClass("tab02_selected");//触发条件单独设置
		  $("#yhqt").removeClass();
	 	  $("#yhqt").addClass("cftj_block_biaoqian");
		  
		  $("#glyhq").addClass("tab02_noselected");
		  $("#cftj").addClass("tab01_noselected");
		  $("#tsxx").addClass("tab_noselected");
		  $("#queding").addClass("tab_last");
	  }
	  if(tid=="glyhq"){//过滤条件
		  $("#glyhq").addClass("tab02_selected");//触发条件单独设置
		  $("#glyhqt").removeClass();
	 	  $("#glyhqt").addClass("cftj_block_biaoqian");
		  
		  $("#tsxx").addClass("tab02_noselected");
		  $("#yhq").addClass("tab_noselected");
		  $("#cftj").addClass("tab01_noselected");
		  $("#queding").addClass("tab_last");
	  }
	  if(tid=="tsxx"){//推送信息
		  $("#tsxx").addClass("tab02_selected");//触发条件单独设置
		  $("#tsxxt").removeClass();
	 	  $("#tsxxt").addClass("cftj_block_biaoqian");
		  
		  $("#queding").addClass("tab_last01");
		  $("#glyhq").addClass("tab_noselected");
		  $("#yhq").addClass("tab_noselected");
		  $("#cftj").addClass("tab01_noselected");
	  }
}

function loadTitle(){//查询出标题设置的数据
		$.ajax({
		type: "post",
		url: "filterUserAction!getSubFilterUserGroup",
		dataType : "text",
		success: function(v){
			
			
		}
		});
}





