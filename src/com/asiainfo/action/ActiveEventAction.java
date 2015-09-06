package com.asiainfo.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.Iservice.ActiveEventService;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.Action;

public class ActiveEventAction  implements Action{
	private Logger log = Logger.getLogger(ActiveEventAction.class);
	@Autowired
	private ActiveEventService service;
	private String eventType;

	/**
	 * 触发条件，事件列表查询
	 */
	@Override
	public String execute() throws Exception {
		ServletActionContext.getRequest().setAttribute("activeEventTypeList", service.getEventList(eventType));
		return "eventList";
	}

	/**
	 * 获取事件列表
	 */
	private String seventType;
	public void getEventNameList()throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("f_type", eventType);
		map.put("s_type", seventType);
		String r=service.getEventNameList(map);
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(r));
	}


	private String checked;
	private String activeCode;
	/**
	 * 提交选取事件类型
	 * @throws Exception
	 */
	public void subCheckedEvent()throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		String s=StringUtils.convertToUTF(URLDecoder.decode(checked, "iso-8859-1"));

		map.put("eventCode",s.split("_")[0]);
		map.put("eventName",s.split("_")[1]);
		map.put("activeCode", activeCode);
		String b=service.subCheckedEvent(map);
		if(b.equals("Y")){
			String tn=service.queryUserGroup(s.split("_")[0]);
			b+="#"+tn;
		}
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(b));
	}

	/**
	 * 查询提交的事件类型
	 * @throws Exception
	 */
	public void queryCheckedEventType()throws Exception{
		String r=service.queryCheckedEventType(activeCode);
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(r));
	}


	public void deleteActiveEventTypeUserGroup()throws Exception{
		ServletActionContext.getResponse().getWriter().print(service.deleteActiveEventTypeUserGroup(activeCode));
	}

	public String queryWebClassList()throws Exception{
		ServletActionContext.getRequest().setAttribute("webTitleList", service.queryWebClassList());
		return "webClassList";
	}

	private String webClassId;
	public void queryWebListByCid()throws Exception{
		ServletActionContext.getResponse().getWriter().
		print(StringUtils.convertToISO(service.queryWebListByCid(webClassId)));
	}

	/**
	 * 触发条件，提交选择的web网站
	 * @throws Exception
	 */
	public void subCheckedWeb()throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", getActiveCode());
		map.put("checked", StringUtils.convertToUTF(checked));
		map.put("userId", "");
		if(!("").equals(checked.trim())){
			int subNum=map.get("checked").split(",").length;
			map.put("triggerType", "5");
			int count=service.getCheckedWebCount(map);
			if(count+subNum>99){
				ServletActionContext.getResponse().getWriter().print("L");
				return;
			}
		}
		if(("").equals(checked.trim())||checked==null||service.insertCheckedWeb(map)){
			ServletActionContext.getResponse().getWriter().print("Y");
		}else{
			ServletActionContext.getResponse().getWriter().print("N");
		}
	}

	private String triggerType;

	/**
	 * 获取活动以选择的web网站列表
	 * @throws Exception
	 */
	public void getCheckedWebList()throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", activeCode);
		map.put("triggerType", triggerType);
		String r=service.queryCheckedWebList(map);
		if("".equals(r)){
			r="N";
		}
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(r));
	}

	/**
	 * 获取以选择提交的web网站个数，用于判断，最多选择100个
	 * @throws Exception
	 */
	public void getCheckedWebCount()throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", activeCode);
		map.put("triggerType", triggerType);
		int count=service.getCheckedWebCount(map);
		ServletActionContext.getResponse().getWriter().print(count);
	}


	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getSeventType() {
		return seventType;
	}
	public void setSeventType(String seventType) {
		this.seventType = seventType;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getWebClassId() {
		return webClassId;
	}

	public void setWebClassId(String webClassId) {
		this.webClassId = webClassId;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

}
