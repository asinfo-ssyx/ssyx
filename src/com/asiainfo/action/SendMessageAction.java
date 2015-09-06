package com.asiainfo.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.asiainfo.Iservice.IActiveService;
import com.asiainfo.Iservice.ISendMessageService;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.Action;

@Controller
public class SendMessageAction implements Action{
	private String send_code;
	private String active_code	;
	private int send_time	;
	private String send_ms;
	private String cross_flag;
	private String statue;
	private String send_type;
	private String examine_id;
	private String user_id;
	private String random_no;
	private String busCode;
	private String disName;
	private String exLev;

	private String testPhone;

	public Logger log = Logger.getLogger(SendMessageAction.class);
	@Autowired
	public IActiveService aservice;
	@Autowired
	public ISendMessageService smService;

	@Override
	public String execute() throws Exception {

		return null;
	}

	public void setWebServiceSendMessage()throws Exception{
		Map<String,Object> param=new HashMap<String,Object>();
		if(send_ms!=null && !"".equals(send_ms)){
			param.put("sendMs", StringUtils.convertToUTF(URLDecoder.decode(send_ms, "iso-8859-1")).replaceAll("RJH","#").replaceAll("RBFH", "%"));
		}
		if(disName!=null && !"".equals(disName)){
			param.put("disName", StringUtils.convertToUTF(URLDecoder.decode(disName, "iso-8859-1")));
		}
		param.put("busCode", busCode);
		param.put("sendType", send_type);
		param.put("activeCode", active_code);
		if(smService.addWebServiceSendMessage(param)){
			ServletActionContext.getResponse().getWriter().print("Y");
		}
	}

	public void getSendMsRandomNo()throws Exception{
		String code=smService.getSendMsRondom(getActiveCode());
		ServletActionContext.getResponse().getWriter().print(code);
	}

	private String exCityId;
	/**
	 * 查询审核人
	 * @throws IOException
	 */
	public void getActiveShr() throws IOException{
		Map<String,String> map=new HashMap<String,String>();
		if(exCityId==null||"undefined".equals(exCityId)){
			map.put("cityId", (String) getUser().get("cityId"));
		}else{
			map.put("cityId", exCityId);
		}
		String sms=smService.getActiveShr(map);
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(sms));
	}

	@SuppressWarnings("unchecked")
	public Map<String,Object> getUser(){
		return (Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("perUser");
	}

	/**
	 * 获取提交的推送信息设置
	 * @throws Exception
	 */
	public void getSubSendMessage() throws Exception{
		String activeCode=(String) ServletActionContext.getRequest().getParameter("activeCode");
		String rms=smService.getSubSendMessage(activeCode);
		ServletActionContext.getResponse().getWriter().print(StringUtils.convertToISO(rms));
	}

	/**
	 * 提交推送信息
	 * @throws Exception
	 */
	public void subSendMessage() throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		user_id=getUserNo();
		active_code=getActiveCode();
		putMap(map);
		if(!"".equals(testPhone)){//插入测试短信
			if(!smService.addTestPhone(map)){
				ServletActionContext.getResponse().getWriter().print("te");
				return;
			}
		}
		map=smService.insertSendMessage(map);
		try {
				String result="";
				for(Map.Entry<String, Object> entry : map.entrySet()) {
					result+=entry.getValue()+"|";
				}
				result=StringUtils.convertToISO(result);
				ServletActionContext.getResponse().getWriter().print(result);
		} catch (Exception e) {
			log.info("Response输出流出错="+e.getMessage());
		}
	}
	/**
	 * 推送信息审核
	 * @return
	 * @throws Exception
	 */
	public void examineSendMessage() throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		user_id=getUserNo();
		active_code=getActiveCode();
		if(active_code.equals("")){
			ServletActionContext.getResponse().getWriter().print("e");
			return;
		}
		putMap(map);

		map=smService.examineSendMessage(map);

		try {
			String result="";
			for(Map.Entry<String, Object> entry : map.entrySet()) {
				result+=entry.getValue()+"|";
			}
			result=StringUtils.convertToISO(result);
			ServletActionContext.getResponse().getWriter().print(result);
		} catch (Exception e) {
			log.info("Response输出流出错="+e.getMessage());
		}
	}

	private String sendTime;
	private String smbCode;
	/**
	 * 短信试发
	 * @throws Exception
	 */
	public void shortMessageCodeSend() throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		putMap(map);
		String result=smService.shortMessageCodeSend(map);
		try {
			ServletActionContext.getResponse().getWriter().print(result);
		} catch (IOException e) {
			log.error("Response输出流出错="+e.getMessage());
		}
	}
	/**
	 * 发送验证码
	 */
	private String spr;
	public void randomNoSend() throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		putMap(map);
		String result= smService.randomNoSend(map);
		try {
			ServletActionContext.getResponse().getWriter().print(result);
		} catch (IOException e) {
			log.error("Response输出流出错="+e.getMessage());
		}
	}

	private String getUserNo(){
		return (String) ServletActionContext.getRequest().getAttribute("user_id");
	}

	private String getActiveCode(){
		return ServletActionContext.getRequest().getParameter("activeCode");
	}

	public void agentSendMessage() throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("activeCode", getActiveCode());
		map.put("sendCode", send_code);
		map.put("send_type", send_type);
		map.put("rsend", "Y");
		smService.sendExamineRandomNo(map);
	}

	public void examineSjSendMessage()throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		putMap(map);

	}



	private String send_cycle;

	private void putMap(Map<String,Object> map) throws Exception{
		map.put("sendCode",send_code );
		map.put("send_cycle",send_cycle);
		map.put("activeCode", getActiveCode());
		map.put("send_time",send_time );
		try{
		log.info("send_ms:"+send_ms);
		map.put("send_ms",StringUtils.convertToUTF(URLDecoder.decode(send_ms, "iso-8859-1")).trim().replaceAll("RJH","#").replaceAll("RBFH", "%"));
		}catch(Exception e){}
		map.put("cross_flag", cross_flag);
		map.put("statue",statue );
		map.put("send_type",send_type);
		map.put("examine_id", examine_id);
		map.put("user_id", getUserNo());
		map.put("random_no", random_no);
		map.put("sendTime", sendTime);
		map.put("smbCode", smbCode);
		map.put("spr", spr);
		map.put("exLev", exLev);
		map.put("testPhone", testPhone);
	}

	public String getSpr() {
		return spr;
	}

	public void setSpr(String spr) {
		this.spr = spr;
	}

	public String getSend_code() {
		return send_code;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSmbCode() {
		return smbCode;
	}

	public void setSmbCode(String smbCode) {
		this.smbCode = smbCode;
	}

	public void setSend_code(String send_code) {
		this.send_code = send_code;
	}
	public String getActive_code() {
		return active_code;
	}
	public void setActive_code(String active_code) {
		this.active_code = active_code;
	}
	public int getSend_time() {
		return send_time;
	}
	public void setSend_time(int send_time) {
		this.send_time = send_time;
	}
	public String getSend_ms() {
		return send_ms;
	}
	public void setSend_ms(String send_ms) {
		this.send_ms = send_ms;
	}
	public String getCross_flag() {
		return cross_flag;
	}
	public void setCross_flag(String cross_flag) {
		this.cross_flag = cross_flag;
	}
	public String getStatue() {
		return statue;
	}
	public void setStatue(String statue) {
		this.statue = statue;
	}
	public String getSend_type() {
		return send_type;
	}
	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}
	public String getExamine_id() {
		return examine_id;
	}
	public void setExamine_id(String examine_id) {
		this.examine_id = examine_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRandom_no() {
		return random_no;
	}

	public void setRandom_no(String random_no) {
		this.random_no = random_no;
	}

	public String getSend_cycle() {
		return send_cycle;
	}

	public void setSend_cycle(String send_cycle) {
		this.send_cycle = send_cycle;
	}

	public String getExCityId() {
		return exCityId;
	}

	public void setExCityId(String exCityId) {
		this.exCityId = exCityId;
	}

	public String getExLev() {
		return exLev;
	}

	public void setExLev(String exLev) {
		this.exLev = exLev;
	}

	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public String getTestPhone() {
		return testPhone;
	}

	public void setTestPhone(String testPhone) {
		this.testPhone = testPhone;
	}

	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

}
