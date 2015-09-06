package com.asiainfo.Implservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.Iservice.ISendMessageService;
import com.asiainfo.dao.ActiveDAO;
import com.asiainfo.dao.RandomDAO;
import com.asiainfo.dao.SendMessageDAO;
import com.asiainfo.util.DataBaseJdbc;
import com.asiainfo.util.DateUtil;
import com.asiainfo.util.RandomUtil;

@Service
public class SendMessageServiceImpl implements ISendMessageService{
	public Logger log = Logger.getLogger(SendMessageServiceImpl.class);

	@Autowired
	public SendMessageDAO sendMessageDao;
	@Autowired
	public RandomDAO randomDao;
	@Autowired
	public ActiveDAO activeDao;

	@Override
	public Map<String, Object> insertSendMessage(Map<String,Object> map){
		Map<String,Object> rmap=new HashMap<String,Object>();

		map.put("createTime", DateUtil.getNowDateStr());
		map.put("sendCode",DateUtil.getNowDateStr2()+RandomUtil.getRandom());
//		Map<String,String> m=getExamineId((String)map.get("spr"));
//		if(m==null ||m.size()==0){
//			rmap.put("status", "N");
//			rmap.put("msg", "未查询到审核人信息！");
//			return rmap;
//		}
//		map.put("examine_id", m.get("examine_id"));
//		map.put("phone_no",m.get("examine_phone"));
		int i = 0;
		//判断推送信息是否重复
		String dxCode="";
		try {
			dxCode=sendMessageDao.queryMessageCode((String)map.get("send_ms"));
		} catch (Exception e1) {
			log.error("查询dxCode出错："+e1.getMessage());
			rmap.put("status", "N");
			rmap.put("msg", "保存推送信息出错！");
			return rmap;
		}
		if(null==dxCode||"".equals(dxCode)){
			dxCode=UUID.randomUUID().toString().replaceAll("-", "");
		}
		map.put("dxCode", dxCode);
		try {
			map.put("status", "2");
			sendMessageDao.updateAfterSendMessageStatus(map);
			i = sendMessageDao.insertSendMessage(map);
		} catch (Exception e) {
			log.info("插入推送信息出错！"+e.getMessage());
		}
		if(1!=i){
			rmap.put("status", "N");
			rmap.put("msg", "保存推送信息出错！");
			return rmap;
		}

//		if(sendExamineRandomNo(map)){
//			try {//创建成功  修改目前状态等于2的 保证 一个类型下 只有一个推送信息可用
//				map.put("status", "2");
//				sendMessageDao.updateAfterSendMessageStatus(map);
//			} catch (Exception e) {
//			}
//			rmap.put("status","Y");
//			rmap.put("sendCode",map.get("sendCode"));
//			rmap.put("exName", m.get("examine_name"));
//			rmap.put("exPhoneNo", m.get("examine_phone"));
//		}else{
//			rmap.put("status", "N");
//			rmap.put("msg", "生成随机码出错，请重新提交");
//		}
		rmap.put("status","Y");
		rmap.put("msg", "保存推送信息成功！");
		return rmap;
	}

	@Override
	public boolean sendExamineRandomNo(Map<String, Object> map){
		if("Y".equals(map.get("rsend"))){//从新发送短信随机码
			//修改现有的随机码状态
			map.put("randomStatus", "2");//超时状态
			try {
				randomDao.updateRandomNo(map);
			} catch (Exception e) {
				log.error("更新随机码状态（失效）出错="+e.getMessage());
			}
			Map<String,Object> smMap=sendMessageDao.getSendMessages(map);
			Map<String,String> m=getExamineId((String)map.get("user_id"));//获取审核人
			map.put("examine_id", m.get("examine_id"));
			map.put("send_ms", smMap.get("send_ms"));
		}
		String randomNo=RandomUtil.getRandom();
		map.put("random_type", "sh");
		map.put("create_time",DateUtil.getNowDateStr());
		map.put("random_no",randomNo);
		map.put("status","0");//0未使用

		int r=0;
		try {
			r = randomDao.insertRandomCode(map);
		} catch (Exception e) {
			log.info("随机码插入日志表出错="+e.getMessage());
		}//随机码入库
		if(1!=r){
			return false;
		}

		//生成短信信息
		map.put("send_time",DateUtil.getNowDateStr());
		map.put("send_ms",map.get("send_ms")+"|本次审核随机码："+randomNo);
		map.put("ms_type","9");//系统内部短信

		//调用发送信息方法
		//if短信是否发送成功
		log.info("下发短信信息="+map);

		//发送短信入库日志表  》》
		try {
			sendMessageDao.insertSendMessageLog(map);
		} catch (Exception e) {
			log.info("发送短信入库日志表出错="+e.getMessage());
		}

		return sendmsdb(map);
	}
	/**
	 * 获取审批人号码
	 * @param userId
	 * @return
	 */
	public Map<String,String> getExamineId(String userId){
		Map<String,String> map = new HashMap<String,String>();
		try {
			map =sendMessageDao.getExamineId(userId);
		} catch (Exception e) {
			log.error("获取审批人号码出错="+e.getMessage());
		}
		return map;
	}


	@Override
	public Map<String, Object> examineSendMessage(Map<String, Object> map)
			throws Exception {
		int r=randomDao.queryRandomNo(map);
		if(r<1){
			map.clear();
			map.put("result", "验证失败！");
			map.put("status", "N");
			return map;
		}
		//成功审核日志

		map.put("examine_id",map.get("spr"));
		map.put("examine_time", DateUtil.getNowDateStr());
		sendMessageDao.insertSendMessageLog(map);
		if("1".equals(map.get("exLev"))){
			map.put("status", "2");//可用 -- 地市审核状态修改成5  省级领导审核 状态为2
		}else if("2".equals(map.get("exLev"))){
			map.put("status", "2");//可用
		}
		int j=sendMessageDao.updateSendMessageStatus(map);//更新推送信息状态
		if(j==1){
			map.put("randomStatus", "1");//已经使用
			randomDao.updateRandomNo(map);
			map.clear();
			map.put("result", "审核通过！");
			map.put("status", "S");
		}else{
			map.clear();
			map.put("result", "审核通过，推送信息状态更新失败！");
			map.put("status", "Y");
		}
		return map;
	}



	@Override
	public Map<String, Object> updateSendMessage(Map<String, Object> map)
			throws Exception {

		return null;
	}

	@Override
	public String getSubSendMessage(String activeCode) {
		String rms="";
		try {
			List<String> list=sendMessageDao.querySubSendMessages(activeCode);
			for (String string : list) {
				rms+=string+",";
			}
		} catch (Exception e) {
			log.error("查询以提交的推送信息类型出错="+e.getMessage());
		}
		return rms;
	}

	@Override
	public String shortMessageCodeSend(Map<String, Object> map) {
		map.put("create_time", DateUtil.getNowDateStr());
		map.put("sendCode",DateUtil.getNowDateStr2()+RandomUtil.getRandom());
		String resultms=(String) map.get("sendCode");

		String isql="短信表";
		try {
			String r=insertShortMessageCode(map);
			if(r.equals("N")){
				resultms="F";
			}else{
				map.put("status", "2");//插入成功，修改目前状态已经为2的数据
				sendMessageDao.updateAfterSendMessageStatus(map);
			}
			Connection con=DataBaseJdbc.getDB2Connection();
			if(con!=null){
				Statement stmt = con.createStatement();
				int i=stmt.executeUpdate(isql);
				if(i<1){
					resultms="F";
				}
			}else{
				resultms="F";
			}
		} catch (Exception e) {
			log.error("cc="+e.getMessage());
			resultms="E";
		}
		return resultms;
	}
	/**
	 * 短信编码本地入库
	 * @param map
	 * @return
	 */
	public String insertShortMessageCode(Map<String,Object> map){
		int i=0;
		try {
			i = sendMessageDao.shortMessageCodeSend(map);
		} catch (Exception e) {
			log.warn("短信编码入库失败="+e.getMessage());
		}
		if(i>0){
			return "Y";
		}
		return "N";
	}

	@Override
	public String randomNoSend(Map<String, Object> map) {
		String randomNo=RandomUtil.getRandom();
		map.put("random_type", "sh");
		map.put("create_time",DateUtil.getNowDateStr());
		map.put("random_no",randomNo);
		map.put("status","0");//0未使用
		int r=0;
		try {
			r = randomDao.insertRandomCode(map);
		} catch (Exception e) {
			log.info("随机码插入日志表出错="+e.getMessage());
			return "E";
		}//随机码入库
		if(1!=r){
			return "N";
		}

		Map<String,String> pm=getExamineId((String)map.get("spr"));
		if(pm!=null){
			map.put("phone_no", pm.get("examine_phone"));
		}
		//生成短信信息
		map.put("send_time",DateUtil.getNowDateStr());
		map.put("send_ms",map.get("smbCode")+"|本次审核随机码："+randomNo);
		map.put("ms_type","9");//系统内部短信
		//调用发送信息方法
//select * from vgop.msm_upload_his where msm_phonenum='15828295348' order by msm_date desc fetch first 10 rows only


		//if短信是否发送成功
		log.info("下发短信信息="+map);
		//发送短信入库日志表  》》
		try {
			sendMessageDao.insertSendMessageLog(map);
		} catch (Exception e) {
			log.info("发送短信入库日志表出错="+e.getMessage());
			return "E";
		}

		if(!sendmsdb(map)){
			return "N";
		}
		return "Y";
	}

	@Override
	public String getActiveShr(Map<String, String> map) {
		List<Map<String,String>> list=null;
		try {
			list=sendMessageDao.queryActiveShr(map);
		} catch (Exception e) {
			log.error("查询审核人信息出错="+e.getMessage());
		}
		StringBuffer rms=new StringBuffer();
		rms.append("<select class=\"inputText\" id=\"spr\"  style=\"width:100px\">");
		for (Map<String, String> map2 : list) {
			rms.append("<option value =\""+map2.get("examine_id")+"\">"+map2.get("examine_name")+"</option>");
		}
		rms.append("</select>");
		return rms.toString();
	}


	public boolean sendmsdb(Map<String,Object> map){
		String insert="insert into vgop.msm_upload(msm_id,msm_content,msm_state,msm_date,msm_phonenum) " +
				"values('100860470009',?,0,?,?)";

		Connection conn=DataBaseJdbc.getDB2Connection();
		PreparedStatement  stmt=null;
		try {
			stmt=conn.prepareStatement(insert);
			stmt.setString(1, map.get("send_ms").toString());
			stmt.setString(2, map.get("create_time").toString());
			stmt.setString(3, map.get("phone_no").toString());
			int i = stmt.executeUpdate();
			log.info("插入vgop 短信表条数 ："+i);
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			log.error("创建数据库查询对象出错="+e.getMessage());
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public String getSendMsRondom(String activeCode) {
		try {
			return randomDao.querySendMsRandom(activeCode);
		} catch (Exception e) {
			log.error("手动查询验证码出错："+e.getMessage());
		}
		return null;
	}

	@Override
	public boolean addWebServiceSendMessage(Map<String, Object> map)
			throws Exception {
		sendMessageDao.updatAllSendMessageStatus(map.get("activeCode").toString());
		map.put("sendCode",DateUtil.getNowDateStr2()+RandomUtil.getRandom());
		map.put("createTime",DateUtil.getNowDateStr());
		int i=sendMessageDao.addWebServiceSendMessage(map);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean addTestPhone(Map<String, Object> map) {
		try {
			int i=sendMessageDao.addTestPhone(map);
			if(i>0) return true;
		} catch (Exception e) {

		}
		return false;
	}

}
