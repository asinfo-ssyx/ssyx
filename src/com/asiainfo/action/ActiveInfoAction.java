package com.asiainfo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Decoder;

import com.asiainfo.Iservice.IActiveService;
import com.asiainfo.Iservice.ISendMessageService;
import com.asiainfo.Iservice.IUploadService;
import com.asiainfo.util.DataFinalUtil;
import com.asiainfo.util.DateUtil;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.Action;

@Controller
public class ActiveInfoAction implements Action{
	//ActiveInfoAction!getPieChart

	public Logger log = Logger.getLogger(ActiveInfoAction.class);
	private String adviceMessage;
	@Autowired
	public IActiveService service;
	@Autowired
	public IUploadService upservice;
	@Autowired
	public ISendMessageService smService;
	private String cityId;
	private String userId;
	
	public static String decode(String str) throws UnsupportedEncodingException{  
	 	if(str==null) {
	 		return null;
	 	}
	    byte[] bt = null;  
	    try {  
	        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();  
	        bt = decoder.decodeBuffer( str );  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	        return new String(bt,"UTF-8");  
	 } 
	
	public void createFromBass()  throws Exception{
		System.out.println(beginTime);
		System.out.println(endTime);
		System.out.println(activeCode);
		Map<String,Object> map=new HashMap<String,Object>();
		String scityId = "999";
		if(cityId!=null&&!"".equals(cityId)) {
			try {
				scityId = String.valueOf(Integer.parseInt(cityId));
			} catch(Exception e) {
				System.out.println("缺少地市参数");
			}
		}
		map.put("city_id", scityId);
		map.put("user_id", userId);
		map.put("active_name", decode(activeName));
		map.put("end_time", endTime);
		map.put("begin_time", beginTime);
		map.put("user_type", "微区域");
		map.put("active_type", "2");
		map.put("status", "5");
		map = service.createFromBass(map);
		System.out.println((String)map.get("active_code"));
		upservice.insertGroupBatchBass(scityId, (String)map.get("active_code"),activeCode);
	}
	
	/**
	 * 审核不通过方法，更新活动状态和添加未通过原因
	 * @throws Exception
	 */
	public void updateActiveStatus() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", activeCode);
		map.put("status", 6+"");
		map.put("adviceMessage",StringUtils.convertToUTF(URLDecoder.decode(adviceMessage, "iso-8859-1")));
		String rm=service.updateActiceStatus(map);
		ServletActionContext.getResponse().getWriter().print(rm);
	}

	/**
	 * 地市公司第一次审核方法
	 * @throws Exception
	 */
	public void cityExamineActive() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		System.out.println("active_type :"+activeType);
		map.put("activeCode", activeCode);
		String re="";
		if(activeType.equals("2")){//非实时
			String send_type=smService.getSubSendMessage(activeCode);
			if(send_type.equals("")||send_type==null)
				re="N";
			else{	
			  if(send_type.split(",")[0].equals("8")||send_type.split(",")[0].equals("10")){//如果为发送渠道类型为智库或营销顾问就直接通过
				map.put("status", 2+"");			
				re=service.updateActiceStatus(map);
			  }
			  else{
				  map.put("status", activeExStatus);
				  re=service.cityExamineActive(map);
			  }
			}
		}else if(activeType.equals("1")){//实时
		map.put("status", activeExStatus);
		re=service.cityExamineActive(map);
		}
		ServletActionContext.getResponse().getWriter().print(re);
	}


	/**
	 * 判断活动配置信息是否完全
	 * @throws Exception
	 */
	public void ActiveSetTest()throws Exception{
		ServletActionContext.getResponse().getWriter()
		.print(StringUtils.convertToISO(service.ActiveSetTest(activeCode,activeType)));
	}

	/**
	 * 获取3c Mq返回消息 判断状态
	 * 状态判断成功 最终修改活动表状态
	 * @throws Exception
	 */
	public void getAddMqStatus() throws Exception{
		//String status=DataFinalUtil.getCreateActiveMqStatus(activeCode);
		//log.info("statusstatusstatusstatusstatus="+status+"|"+activeCode);
		//if(status!=null){
			if(service.isMqInertDB(activeCode)){//查询c3数据库，是否有这条活动的记录
				Map<String,String> map=new HashMap<String,String>();
				map.put("activeCode", activeCode);
				map.put("status", 2+"");
				String rm=service.updateActiceStatus(map);
				//插入134表
				//插入活动统计表 活动id 和用户数
				ServletActionContext.getResponse().getWriter().print(rm);
			}
		//}
	}

	/**
	 * 网络端 需要的业务编码或是url链接
	 * @throws Exception
	 */
	public void sendEffectInfoToNet()throws Exception{
		Map<String,Object> activeInfo=service.getActiveInfo1(activeCode);

	}


	public void getDeleteMqStatus()throws Exception{
		//String status=DataFinalUtil.getDeleteActiveMqStatus(activeCode);
	//	log.info(status+"|getDeleteMqStatus>>>>>>>>>>>>>activeCode:"+activeCode);
		//if(status!=null){
	//		if(status.equals("2")){
				ServletActionContext.getResponse().getWriter().print("Y");
		//	}
	//	}
	}

	/**
	 * 删除活动
	 */
	private String deleteActiveType;
	public void deleteActive() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", activeCode);
		map.put("status", 9+"");//删除活动，状态跟新为9
		String rm=service.updateActiceStatus(map);
		if(rm.equals("Y")){
			PrintWriter pr=ServletActionContext.getResponse().getWriter();
			if(!"2".equals(deleteActiveType)){//实时活动，发送消息到c3删除活动信息
				service.sendMqDelete(map);
			}
			pr.print("Y");
		}
	}

	/**
	 * 活动审核通过
	 * @throws Exception
	 */
	public void sendMq() throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		if("2".equals(activeType)){//非实时活动流程
			map.put("activeCode", activeCode);
			map.put("status", 2+"");
			ServletActionContext.getResponse().getWriter().print(service.updateActiceStatus(map));
		}else{//实时活动流程
			map.put("activeCode", activeCode);
			map.put("beginTime", beginTime);
			map.put("endTime", endTime);
			service.sendMq(map);//发送消息到c3,添加活动规则
			ServletActionContext.getResponse().getWriter().print("Y");
		}
	}

	/**
	 * 实时活动，审核通过后，添加用户群到hbase
	 * @throws Exception
	 */
	public void loadUserGroup()throws Exception{
		service.getUserGroupHdfs(activeCode);
	}

	private String setStep;
	/**
	 * 保存设置步骤
	 */
	public void saveSetStep(){
		//
		String step="t1";
		if(!"".equals(setStep)&& null!=setStep){
			step=setStep;
		}
		ServletActionContext.getRequest().getSession().setAttribute("setStep", step);
	}

	/**
	 * 查询活动列表
	 */
	@Override
	public String execute() throws Exception {
        HttpServletRequest  request = ServletActionContext.getRequest();
		Map<String,Object> map=new HashMap<String,Object>();
		putData(map);
		log.debug("查询参数=="+map);
		List<Map<String,Object>> showList=service.getActiveList(map);
		Map<String,Object> page=service.getActiveCount(map);
		request.setAttribute("showList", showList);
		request.setAttribute("page", page);
		String exstatus=(String) getUserMap().get("userId");
		if(DataFinalUtil.getExamineUser(exstatus)!=null){//后面改成数据库 内存 判断
			request.setAttribute("exCityId", DataFinalUtil.getExamineUser(exstatus));
			request.setAttribute("exstatus", true);
			if("0".equals(DataFinalUtil.getExamineUser(exstatus))){
				request.setAttribute("adminStatus", true);
			}else{
				request.setAttribute("adminStatus", false);
			}
		}else{
			request.setAttribute("exstatus", false);
			request.setAttribute("adminStatus", false);
		}
		return "showList";
	}

	/**
	 * 创建新活动
	 * @return
	 */
	public void createNewActive() throws Exception{
		String code=createInitActive();
		ServletActionContext.getResponse().getWriter().print(code);
	}

	/**
	 * 创建一个初始化的活动
	 * @throws Exception
	 */
	public String createInitActive() throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		putData(map);
		map.put("user_id", getUserMap().get("userId"));
		map.put("cityId", getUserMap().get("cityId"));
		String activeCode="";
		try {
			map=service.insertActive(map);
			if(map!=null){
				activeCode=(String) map.get("active_code");
			}
		} catch (Exception e) {
			log.error("创建新活动出错="+e.getMessage());
		}
		return activeCode;
	}

	/**
	 * 获取登入用户信息
	 * @return
	 */
	private Map<String,Object> getUserMap(){
		@SuppressWarnings("unchecked")
		Map<String,Object> map=(Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("perUser");
		return map;
	}
	//复制获得的id
	private String copyActiceCode;

	public String copyCreateActive() throws Exception{
		String activeCode=createInitActive();
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", activeCode);
		map.put("copyActiceCode", copyActiceCode);
		map.put("userId", (String) ServletActionContext.getRequest().getSession().getAttribute("user_id"));
		try {
			service.copuCreateActive(map);
		} catch (Exception e) {

		}
		return "trigger";
	}

	/**
	 * 提交页面更新初始化的活动信息
	 * @throws Exception
	 */
	public void updateActive() throws Exception {
		log.info("进入 update 方法");
		Map<String,Object> map=new HashMap<String,Object>();
		putData(map);

		map.put("activeCode", activeCode);
		map.put("status", 7);//活动创建成功  分公司未审核 7  省公司审核 5     实时活动 获取mq消息之前状态定为5  通过是2  未通过是6
		Map<String,Object> user=(Map<String, Object>) ServletActionContext.getRequest().getSession().getAttribute("perUser");
		if(user!=null&&user.get("cityId").equals("999")){
			map.put("status", 5);//省公司跳过第一次分公司审核
		}
		map=service.updateActiveInfo(map);
		try {
			ServletActionContext.getResponse().getWriter().print(map.get("result"));
		} catch (Exception e) {
			log.info("Response输出流出错="+e.getMessage());
		}
	}



	/**
	 * 参数设置
	 * @param map
	 * @throws Exception
	 */
	public void putData(Map<String,Object> map) throws Exception{
		map.put("acitveScene", acitveScene);
		try{
			map.put("activeName",StringUtils.convertToUTF(URLDecoder.decode(activeName, "iso-8859-1")));
			map.put("active_name",StringUtils.convertToUTF(URLDecoder.decode(activeName, "iso-8859-1")));
		}catch(Exception e){}
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		try{
			map.put("pCode", StringUtils.convertToUTF(URLDecoder.decode(pCode, "iso-8859-1")));
		}catch(Exception e){}
		map.put("productId", productId);
		try{
			map.put("ourl", StringUtils.convertToUTF(URLDecoder.decode(ourl, "iso-8859-1")));
		}catch(Exception e){}

		map.put("showNum", showNum);
		map.put("pageNum", pageNum);
		map.put("activeType", activeType);
		map.put("activeMsType", activeMsType);

		try{
			map.put("userType", StringUtils.convertToUTF(URLDecoder.decode(userType, "iso-8859-1")));
		}catch(Exception e){}
		try{
			map.put("acitveContent", StringUtils.convertToUTF(URLDecoder.decode(acitveContent, "iso-8859-1")));
		}catch(Exception e){}
		if(null !=activeStatus && !"".equals(activeStatus)){

			if("1".equals(activeStatus)){//未开始
				map.put("nStart", DateUtil.getNowDateStr());
			}else if(activeStatus.equals("2")){//进行中
				map.put("runNow", DateUtil.getNowDateStr());
			}else if(activeStatus.equals("3")){//已结束
				map.put("isOver", DateUtil.getNowDateStr());
			}
		}
		map.put("scityId", scityId);
		map.put("activeExStatus", activeExStatus);

	}

	/**
	 * 创建活动页面，获取活动的触发条件等信息
	 * @return
	 * @throws Exception
	 */
	public String createActive() throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("activeName", StringUtils.convertToUTF(URLDecoder.decode(activeName, "iso-8859-1")));
		map.put("activeBody", StringUtils.convertToUTF(URLDecoder.decode(activeBody, "iso-8859-1")));
		map.put("endTime", endTime);
		map.put("beginTime", beginTime);
		map.put("activeCode", activeCode);
		map=service.getActiveChooseInfo(map);
		map.put("activeType", StringUtils.convertToUTF(URLDecoder.decode(activeType, "iso-8859-1")));
		ServletActionContext.getRequest().setAttribute("ChooseInfo", map);
		return "subActive";
	}


	/**
	 * 获取活动设置信息，用于导航列表展示
	 * @throws Exception
	 */
	public String getActiveInfo() throws Exception{
		Map<String,Object> map=service.getActiveInfo(activeCode);
		ServletActionContext.getRequest().setAttribute("activeAllInfo", map);
		return "activeInfo";
	}

	/**
	 * 停止活动短信发送
	 * @throws Exception
	 */
	public void shutActive()throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		map.put("activeCode", activeCode);
		map.put("status", "10");
		ServletActionContext.getResponse().getWriter().print(service.updateActiceStatus(map));
	}

	private String acitveScene;//活动场景
	private String activeCode;

	private String userType; //身份类型
	private String acitveContent;//活动内容介绍

	public String activeName;//活动名称
	public String beginTime;//开始时间
	public String endTime;	//结束时间
	public String pCode;	//产品名称
	private String productId; //产品编码id
	public String ourl;		//跟踪url
	public String activeBody;//活动介绍

	private int showNum;//展示条数

	private int pageNum;//页数

	private String activeType;//活动类型

	private String activeMsType;//跟着类型

	private String activeStatus;//活动状态

	private String activeExStatus;//活动审核状态

	private String scityId;//根据地市查询



	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAcitveContent() {
		return acitveContent;
	}

	public void setAcitveContent(String acitveContent) {
		this.acitveContent = acitveContent;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getBeginNum() {
		return showNum;
	}

	public void setBeginNum(int showNum) {
		this.showNum = showNum;
	}

	public String getActiveType() {
		return activeType;
	}

	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getActiveExStatus() {
		return activeExStatus;
	}


	public void setActiveExStatus(String activeExStatus) {
		this.activeExStatus = activeExStatus;
	}


	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getOurl() {
		return ourl;
	}

	public void setOurl(String ourl) {
		this.ourl = ourl;
	}

	public String getCopyActiceCode() {
		return copyActiceCode;
	}

	public void setCopyActiceCode(String copyActiceCode) {
		this.copyActiceCode = copyActiceCode;
	}

	public String getSetStep() {
		return setStep;
	}

	public void setSetStep(String setStep) {
		this.setStep = setStep;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getActiveBody() {
		return activeBody;
	}

	public void setActiveBody(String activeBody) {
		this.activeBody = activeBody;
	}

	public String getAcitveScene() {
		return acitveScene;
	}

	public void setAcitveScene(String acitveScene) {
		this.acitveScene = acitveScene;
	}

	public String getActiveMsType() {
		return activeMsType;
	}

	public void setActiveMsType(String activeMsType) {
		this.activeMsType = activeMsType;
	}

	public String getDeleteActiveType() {
		return deleteActiveType;
	}

	public void setDeleteActiveType(String deleteActiveType) {
		this.deleteActiveType = deleteActiveType;
	}

	public String getScityId() {
		return scityId;
	}

	public void setScityId(String scityId) {
		this.scityId = scityId;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getAdviceMessage() {
		return adviceMessage;
	}


	public void setAdviceMessage(String adviceMessage) {
		this.adviceMessage = adviceMessage;
	}

}
