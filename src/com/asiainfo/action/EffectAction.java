package com.asiainfo.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.asiainfo.Iservice.EffectService;
import com.asiainfo.util.SendMsClientService;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.Action;
@Controller
public class EffectAction implements Action{
	@Autowired
	private EffectService service;

	private String cityId;
	private String startTime;
	private String endTime;
	private String activeType;
	private String activeScene;

	private String activeSendType;
	private String orderSta;
	private int showNum;//展示条数
	private int pageNum;//页数
    private String activeName;//活动内容
	@Override
	public String execute() throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		putParaMap(map);
		System.out.println(map);
		List<Map<String,Object>> list=service.queryEffectList(map);
		Map<String,Object> page=service.getEffectCount(map);		
		HttpServletRequest  request = ServletActionContext.getRequest();
		request.setAttribute("effectList", list);
		request.setAttribute("page", page);
		request.setAttribute("activeSendType", activeSendType);
		return "effectList";
	}

	public String searchActiveCount()throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		if(!"".equals(startTime))map.put("startTime", startTime);
		if(!"".equals(endTime)) map.put("endTime", endTime);
		Map<String,List<Map<String,Object>>> smap=service.queryActiveCountList(map);

		HttpServletRequest  request = ServletActionContext.getRequest();
		request.setAttribute("beginTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("activeCount", smap);
		return "activeCountIndex";
	}


	private String activeCode;


	public void searchActiveSendInfo()throws Exception{
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=sf.parse(startTime);
		if(date.after(new Date())){
			ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO("活动未开始"));
			return;
		}

		Map<String,String> map=service.searchActiveSendInfo(activeCode);
		if(map!=null){
			ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(map.get("infoMs")));
		}else{
			ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO("暂无查到短信发送信息"));
		}

	}

    /**
     * 获取效果跟踪的我的活动板块的数据（必须是自己创建的活动而且是没有标记效果跟踪的活动）
     * @return
    */
      public String getMyActiveData(){
	
	   Map<String,Object> map=new HashMap<String,Object>();
		try {
			putParaMap(map);
			@SuppressWarnings("unchecked")
			Map<String,Object> map1=(Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("perUser");
		    map.put("userId", map1.get("userId"));
		    map.put("isEffect", 0);//非效果跟踪
			List<Map<String,Object>> list=service.queryEffectList(map);
			Map<String,Object> page=service.getEffectCount(map);
			HttpServletRequest  request = ServletActionContext.getRequest();	
			request.setAttribute("effectList", list);
			request.setAttribute("page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   return "myActiveTable";
   }
	private void putParaMap(Map<String,Object> map) throws Exception{
		System.out.println(startTime);
		map.put("cityId", cityId);
		map.put("startTime", URLDecoder.decode(startTime, "iso-8859-1"));
		map.put("endTime", URLDecoder.decode(endTime, "iso-8859-1"));
		map.put("activeType", activeType);
		map.put("showNum", showNum);
		map.put("pageNum", pageNum);
		map.put("activeScene", activeScene);
		map.put("activeSendType", activeSendType);
		map.put("activeName", activeName);
		map.put("orderSta", orderSta);

	}


	public String getSendMsActiveInfo()throws Exception{
		System.out.println("--------------------service------"+service);
		HttpServletRequest  request = ServletActionContext.getRequest();
		request.setAttribute("sendMsActiveList", SendMsClientService.sendMsActiveList);
		return "sendMsPage";
	}

	public void shutSendMs()throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("shutStatue", "Y");
		map.put("activeCode", activeCode);
		SendMsClientService.sendMsT0Server(map);
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getActiveType() {
		return activeType;
	}

	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getActiveScene() {
		return activeScene;
	}

	public void setActiveScene(String activeScene) {
		this.activeScene = activeScene;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getActiveSendType() {
		return activeSendType;
	}

	public void setActiveSendType(String activeSendType) {
		this.activeSendType = activeSendType;
	}

	public String getOrderSta() {
		return orderSta;
	}

	public void setOrderSta(String orderSta) {
		this.orderSta = orderSta;
	}

	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	

}
