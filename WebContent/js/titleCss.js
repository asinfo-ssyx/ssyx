// JavaScript Document
function changeCss(tid){
	  //ɾ��css  remove
	  $("#cftj").removeClass();
	  $("#yhq").removeClass();
	  $("#glyhq").removeClass();
	  $("#tsxx").removeClass();
	  $("#queding").removeClass();
	  
	  if(tid=="cftj"){//��������
		  $("#cftj").addClass("tab01_selected");//����������������
		  $("#cftjt").removeClass();
	 	  $("#cftjt").addClass("cftj_block_biaoqian");
		  
		  $("#yhq").addClass("tab02_noselected");
		  $("#glyhq").addClass("tab_noselected");
		  $("#tsxx").addClass("tab_noselected");
		  $("#queding").addClass("tab_last");
	  }
	  if(tid=="yhq"){//�û�Ⱥ
		  $("#yhq").addClass("tab02_selected");//����������������
		  $("#yhqt").removeClass();
	 	  $("#yhqt").addClass("cftj_block_biaoqian");
		  
		  $("#glyhq").addClass("tab02_noselected");
		  $("#cftj").addClass("tab01_noselected");
		  $("#tsxx").addClass("tab_noselected");
		  $("#queding").addClass("tab_last");
	  }
	  if(tid=="glyhq"){//��������
		  $("#glyhq").addClass("tab02_selected");//����������������
		  $("#glyhqt").removeClass();
	 	  $("#glyhqt").addClass("cftj_block_biaoqian");
		  
		  $("#tsxx").addClass("tab02_noselected");
		  $("#yhq").addClass("tab_noselected");
		  $("#cftj").addClass("tab01_noselected");
		  $("#queding").addClass("tab_last");
	  }
	  if(tid=="tsxx"){//������Ϣ
		  $("#tsxx").addClass("tab02_selected");//����������������
		  $("#tsxxt").removeClass();
	 	  $("#tsxxt").addClass("cftj_block_biaoqian");
		  
		  $("#queding").addClass("tab_last01");
		  $("#glyhq").addClass("tab_noselected");
		  $("#yhq").addClass("tab_noselected");
		  $("#cftj").addClass("tab01_noselected");
	  }
}

function loadTitle(){//��ѯ���������õ�����
		$.ajax({
		type: "post",
		url: "filterUserAction!getSubFilterUserGroup",
		dataType : "text",
		success: function(v){
			
			
		}
		});
}





