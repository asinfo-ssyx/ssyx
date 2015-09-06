package com.asiainfo.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.Iservice.IFilterUserService;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.Action;

public class FilterUserAction implements Action{

	public Logger log = Logger.getLogger(FilterUserAction.class);
	@Autowired
	public IFilterUserService service;

	@Override
	public String execute() throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		List<Map<String,String>> list=service.searchFilterUserGroup(map);
		ServletActionContext.getRequest().setAttribute("filterUser", list);
		return "filterUser";
	}

	private String fuserGroup;

	/**
	 * 提交选择过滤客户群
	 * @throws Exception
	 */
	public void subFilterUserGroup() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("fuserGroup", fuserGroup);
	}

	/**
	 * 查询选择用户群
	 * @throws Exception
	 */
	public void getSubUserGroup() throws Exception{
		String activeCode=(String) ServletActionContext.getRequest().getParameter("activeCode");
		String rms=service.getSubUserGroup(activeCode);
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(rms));
	}

	/**
	 * 查询选择的过滤用户群
	 * @throws Exception
	 */
	public void getSubFilterUserGroup() throws Exception{
		String activeCode=(String) ServletActionContext.getRequest().getParameter("activeCode");
		String rms=service.getSubFilterUserGroup(activeCode);
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(rms));
	}


	public String getFuserGroup() {
		return fuserGroup;
	}

	public void setFuserGroup(String fuserGroup) {
		this.fuserGroup = fuserGroup;
	}



}
