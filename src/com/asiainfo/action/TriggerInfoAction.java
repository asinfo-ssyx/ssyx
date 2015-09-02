package com.asiainfo.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.Iservice.ITriggerInfoService;
import com.asiainfo.bean.KeyWord;
import com.asiainfo.bean.TriggerInfo;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class TriggerInfoAction extends ActionSupport {
	public Logger log = Logger.getLogger(TriggerInfoAction.class);
	private TriggerInfo triggerInfo;

	@Autowired
	private ITriggerInfoService triggerService;

	public TriggerInfo getTriggerInfo() {
		return triggerInfo;
	}

	public void setTriggerInfo(TriggerInfo triggerInfo) {
		this.triggerInfo = triggerInfo;
	}

	public ITriggerInfoService getTriggerService() {
		return triggerService;
	}

	public void setTriggerService(ITriggerInfoService triggerService) {
		this.triggerService = triggerService;
	}



	@Override
	public String execute() throws Exception {

		return null;
	}

	public void triggerInsert() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String triggerType = StringUtils.convertToUTF(request.getParameter("triggerType"));
		String triggerMs = StringUtils.convertToUTF(URLDecoder.decode(request.getParameter("triggerMs"),"iso-8859-1"));
		String searchType= request.getParameter("searchType");
		String code = getActiveCode();
		//System.out.println("............"+ triggerMs);
		String [] s = triggerMs.split(",");
		for(int i =0;i<s.length;i++){
			triggerInfo = new TriggerInfo();
			triggerInfo.setActiveCode(code);
			triggerInfo.setTriggerType(triggerType);
			triggerInfo.setTriggerMs(s[i]);
			triggerInfo.setSearchType(searchType);
			triggerService.triggerInsert(triggerInfo);
		}
	}

	public void searchKeyWordByWord() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String para=request.getParameter("keyName");
		para= StringUtils.convertToUTF(URLDecoder.decode(para, "iso-8859-1"));
		String result = "";
		List<KeyWord> list = triggerService.keyWordSelect(para);
		for(int i=0;i<list.size();i++){
			if (i!= list.size()-1){
				result += list.get(i).getKeyName() + "&";
			}else {
				result += list.get(i).getKeyName();
			}
		}
		System.out.println(result);
		try {
			response.getWriter().print(StringUtils.convertToISO(result));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void searchKeyWordByCode(){
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String activeCode = getActiveCode();
		String result = "";
		List<TriggerInfo> list = triggerService.triggerSelect(activeCode);
		for(int i=0;i<list.size();i++){
			if (i!= list.size()-1){
				result += list.get(i).getTriggerMs() + "&";
			}else {
				result += list.get(i).getTriggerMs();
			}
		}
		System.out.println(result);
		try {
			response.getWriter().print(StringUtils.convertToISO(result));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String getActiveCode(){
		return ServletActionContext.getRequest().getParameter("activeCode");
	}

}
