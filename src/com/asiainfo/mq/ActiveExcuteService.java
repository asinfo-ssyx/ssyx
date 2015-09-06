package com.asiainfo.mq;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asiainfo.dao.MqMessageDAO;
import com.asiainfo.util.DataFinalUtil;

@Service
public class ActiveExcuteService implements ITopicMessageHandle {
	private static Logger log = Logger.getLogger(MsgExcuteService.class);

	@Autowired
	private MqMessageDAO dao;

	@Override
	public void handTopicMessage(String _topic, String _message) {

		log.info("add message:" + _message+"_topic:"+_topic);
		if("com.ailk.fba.notice.rule.response.network".equals(_topic)){//新增
			saveAddMqReturnMessages(_message);
		}else if("com.ailk.flowmanagement.51_mcd.rule.cancel.success".equals(_topic)){//删除
			saveDeleteMqReturnMessages(_message);
		}
	}

	//处理活动状态
	public  void saveAddMqReturnMessages(String acctMsg) {
		log.info("c3返回的信息acctMsg="+acctMsg);
		Map<String,String> sqlmap=new HashMap<String,String>();
		sqlmap.put("mqMessage", acctMsg);
		sqlmap.put("mqType", "1");
		try {
			log.info("MqMessageDAOMqMessageDAOMqMessageDAO:"+dao);
			dao.saveReturnMessage(sqlmap);
		} catch (Exception e) {
			log.error("mq日志数据记录出错="+e.getMessage());
		}
		try{
			String[] msgStr = acctMsg.split(",\"");

			Map<String, String> strMap = new HashMap<String, String>();

			for (int i = 0; i < msgStr.length; i++) {
				String key = msgStr[i].split("\":")[0].replace("{", "");
				String val = msgStr[i].split("\":")[1].replace("\"", "");
				val=val.replace("}", "");
				strMap.put(key, val);
			}

			if("1".equals(strMap.get("result"))){//状态需确认
				log.info("mq返回添加活动信息状态：");
				DataFinalUtil.putCreateActiveMqStatus(strMap.get("activity_code"), "2");
			}
		}catch(Exception e) {
			log.error("mq返回解析出错="+e.getMessage());
		}
		//判断代码
		//数据库代码
	}


	public void saveDeleteMqReturnMessages(String acctMsg){
		log.info("c3返回的信息acctMsg="+acctMsg);
		Map<String,String> sqlmap=new HashMap<String,String>();
		sqlmap.put("mqMessage", acctMsg);
		sqlmap.put("mqType", "2");
		try {
			dao.saveReturnMessage(sqlmap);
		} catch (Exception e) {
			log.error("mq日志数据记录出错="+e.getMessage());
		}

		try{
			acctMsg=acctMsg.replace("{", "").replace("}", "").replace("'", "");
			String[] msgStr = acctMsg.split(",");

			Map<String, String> strMap = new HashMap<String, String>();

			for (int i = 0; i < msgStr.length; i++) {
				String [] sp=msgStr[i].split(":");
				if(sp.length<2)continue;
				String key = sp[0];
				String val = sp[1];
				strMap.put(key, val);
			}

			if("1".equals(strMap.get("result"))){//状态需确认
				log.info("delete active put返回mq状态:"+strMap.get("activity_code"));
				DataFinalUtil.putDeleteActiveMqStatus(strMap.get("activity_code"), "2");
			}
		}catch(Exception e) {
			log.error(" 删除 mq返回解析出错="+e.getMessage());
		}
		//判断代码
		//数据库代码
	}

}


