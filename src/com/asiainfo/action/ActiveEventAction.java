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
	 * �����������¼��б��ѯ
	 */
	@Override
	public String execute() throws Exception {
		ServletActionContext.getRequest().setAttribute("activeEventTypeList", service.getEventList(eventType));
		return "eventList";
	}

	/**
	 * ��ȡ�¼��б�
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
	 * �ύѡȡ�¼�����
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
	 * ��ѯ�ύ���¼�����
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
	 * �����������ύѡ���web��վ
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
	 * ��ȡ���ѡ���web��վ�б�
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
	 * ��ȡ��ѡ���ύ��web��վ�����������жϣ����ѡ��100��
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
