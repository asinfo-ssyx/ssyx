package com.asiainfo.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.asiainfo.Iservice.IBusiCodeService;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.Action;

public class BusiCodeAction  implements Action{
	private Logger log = Logger.getLogger(BusiCodeAction.class);
	@Autowired
	private IBusiCodeService service;

	private String codeType;
	/**
	 * ��������ҳ�棬��ѯҵ����뷽��
	 */
	@Override
	public String execute() throws Exception {
		if(codeType==null||"".equals(codeType)){
			codeType="1";
		}
		List<Map<String,Object>> list= service.getDataBusiBigType(codeType);
		System.out.println(list);
		ServletActionContext.getRequest().setAttribute("busicode", list);
		return "dataCodeSelect";
	}

	/**
	 * ����ҵ�����ͣ���ѯ��ҵ������
	 */
	private String typeName;
	public void getDataBusiSmallType() throws Exception {
		String s= service.getDataBusiSmallType(StringUtils.convertToUTF(URLDecoder.decode(typeName, "iso-8859-1")));
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(s));
	}

	/**
	 * ȷ��ҳ�棬��ѯЧ������ҵ�����
	 * @return
	 * @throws Exception
	 */
	public String queryProduct()throws Exception{
		List<String> list= service.getEffectProduct();
		System.out.println(list);
		ServletActionContext.getRequest().setAttribute("busicode", list);
		return "effectProduct";
	}


	public String queryUserCount() throws Exception{
		List<Map<String,String>> list=service.searchUserCount();
		ServletActionContext.getRequest().setAttribute("busicode", list);
		return "userCount";
	}


	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

}
