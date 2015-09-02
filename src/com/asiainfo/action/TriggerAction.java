package com.asiainfo.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.asiainfo.Iservice.ITriggerService;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.Action;

@Controller
public class TriggerAction implements Action{

	public Logger log = Logger.getLogger(TriggerAction.class);

	private String checked;
	private String cityName;
	private String pType;

	@Autowired
	public ITriggerService service;

	@Override
	public String execute() throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		Map<String,List<Map<String,String>>> list=service.getAppMessageList(map);
		HttpServletRequest  request = ServletActionContext.getRequest();
		request.setAttribute("showList", list);
		return "showList";
	}

	/**
	 * 查询地市下的基站类型列表
	 * @throws Exception
	 */
	public void searchBsType() throws Exception{
		Map<String,String> param=new HashMap<String,String>();
		String p=StringUtils.convertToUTF(URLDecoder.decode(cityName, "iso-8859-1"));
		if("1".equals(p))p="";
		param.put("cityName", p);
		String result=service.searchBsType(param);
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(result));
	}


	private String triggerName;
	private String triggerType;//1关键字2app3位置4事件类型
	private String titleType;//1触发条件2 客户群3.....
	public void deleteSetProperty() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("triggerName", StringUtils.convertToUTF(URLDecoder.decode(triggerName, "iso-8859-1")));
		map.put("triggerType", triggerType);
		map.put("titleType", titleType);
		map.put("activeCode", getActiveCode());
		boolean a=service.deleteSetProperty(map);
		if(a){
			ServletActionContext.getResponse().getWriter().print("Y");
		}else{
			ServletActionContext.getResponse().getWriter().print("F");
		}
	}


	public String getAppList(){
		HttpServletRequest  request = ServletActionContext.getRequest();
		request.setAttribute("appTitleList",service.getAppList() );
		return "showList";
	}

	public void getTriggerInfoMs() throws IOException{
		String activeCode=getActiveCode();
		String tms=service.getTriggerInfoMs(activeCode);
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(tms));
	}

	private String appType;
	public void getTypeAppList() throws Exception{

		Map<String,String> map=service.getTypeAppList(appType);
		if(map!=null){
			ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(map.get("0")+"_"+map.get("1")));
		}else{
			ServletActionContext.getResponse().getWriter().print("F");
		}

	}

	/**
	 * 提交app信息
	 * @throws IOException
	 */
	public void subCheckedApp() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", getActiveCode());
		//String dstr=URLDecoder.decode(checked, "iso-8859-1");
		//dstr=StringUtils.convertToUTF(dstr);
		//log.info("checkedchecked:"+dstr);
		map.put("checked", StringUtils.convertToUTF(checked));
		//map.put("checked", dstr);
		map.put("userId", getUserNo());
		
		if(!("").equals(checked.trim())){
			int subNum=map.get("checked").split(",").length;
			map.put("triggerType", "2");	//type=2:app
			int count=service.getCheckedAppCount(map);
			if(count+subNum>10){
				ServletActionContext.getResponse().getWriter().print("L");
				return;
			}
		}
		
		if(("").equals(checked.trim())||checked==null||service.insertCheckedApp(map)){
			ServletActionContext.getResponse().getWriter().print("Y");
		}else{
			ServletActionContext.getResponse().getWriter().print("N");
		}
	}

	/**
	 * 获取活动app列表
	 * @throws IOException
	 */
	public void getCheckedAppList() throws IOException{
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", getActiveCode());
		map=service.getCheckedApp(map);
		if(map==null){
			ServletActionContext.getResponse().getWriter().print("");
		}else{
			ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(map.get("appms")));
		}
	}

	/**
	 * 获取三级地市
	 * @return
	 * @throws Exception
	 */
	public String getCityList() throws Exception{
		HttpServletRequest  request = ServletActionContext.getRequest();
		String html=(String) request.getSession().getAttribute("cityList");
		String typehtml=(String) request.getSession().getAttribute("typeList");
		if(null==html ||"F".equals(html)||"null".equals(html)){
			html=service.getCityList();
			typehtml=service.getCityPositionList(new HashMap<String, String>());
			request.getSession().setAttribute("cityList", html);
			request.getSession().setAttribute("typeList", typehtml);
		}
		return "position";
	}

	/**
	 * 获取地区下基站列表
	 * @throws Exception
	 */
	public void getPositionList() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		String html=service.getCityPositionList(map);
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(html));
	}

	/**
	 * 提交基站信息
	 * @throws IOException
	 */
	public void subCheckedBs() throws IOException{
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", getActiveCode());
		map.put("checked", "'"+StringUtils.convertToUTF(URLDecoder.decode(checked, "iso-8859-1")).replaceAll(",", "','")+"'");
		map.put("bs_type", StringUtils.convertToUTF(URLDecoder.decode(checked, "iso-8859-1")));
		map.put("userId", getUserNo());
		map.put("cityName", StringUtils.convertToUTF(URLDecoder.decode(cityName, "iso-8859-1")));
		if(("").equals(checked.trim())||checked==null||service.subCheckedPosition(map)){
			ServletActionContext.getResponse().getWriter().print("Y");
		}else{
			ServletActionContext.getResponse().getWriter().print("N");
		}

	}

	/**
	 * 获取前台选取的基站
	 * @throws Exception
	 */
	public void getCheckedBsList() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", getActiveCode());
		map=service.getCheckedPosition(map);
		if(map==null){
			ServletActionContext.getResponse().getWriter().print("");
		}else{
			ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(map.get("bsms")));
		}
	}

	public void deleteTriggers() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("triggerMs", "");
		map.put("triggerType", "");
	}

	
	
	
	/**
	 * 获取以选择提交的app个数，用于判断，最多选择10个
	 * @throws Exception
	 */
	public void getCheckedAppCount()throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", getActiveCode());
		map.put("triggerType", triggerType);
		int count=service.getCheckedAppCount(map);
		ServletActionContext.getResponse().getWriter().print(count);
	}

	private String getUserNo(){
		return (String) ServletActionContext.getRequest().getSession().getAttribute("user_id");
	}
	private String getActiveCode(){
		return ServletActionContext.getRequest().getParameter("activeCode");
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getpType() {
		return pType;
	}

	public void setpType(String pType) {
		this.pType = pType;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}


	public String getTriggerName() {
		return triggerName;
	}


	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}


	public String getTriggerType() {
		return triggerType;
	}


	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}


	public String getTitleType() {
		return titleType;
	}


	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}

}
