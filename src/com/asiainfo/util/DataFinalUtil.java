package com.asiainfo.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
/**
 * 监听Mq返回状态存储数据类
 * @author Hero
 *
 */
public class DataFinalUtil {

	public static Logger log = Logger.getLogger(DataFinalUtil.class);

	public static Map<String,String> examineUser=new HashMap<String,String>();

	public static Map<String,String> createActiveMqStatus=new HashMap<String,String>();

	public static Map<String,String> deleteActiveMqStatus=new HashMap<String,String>();

	public static void putCreateActiveMqStatus(String active,String status){
		createActiveMqStatus.put(active, status);
		log.info("add 活动状态集合："+active+"|"+status);
	}
	public static String getCreateActiveMqStatus(String active){
		log.info("返回 add 活动状态集合："+active+"|"+active);
		return createActiveMqStatus.get(active);
	}

	public static void putDeleteActiveMqStatus(String active,String status){
		deleteActiveMqStatus.put(active, status);
		log.info("delete 活动状态集合："+active+"|"+status);
	}
	public static String getDeleteActiveMqStatus(String active){
		log.info("返回 add 活动状态集合："+active+"|"+active);
		return deleteActiveMqStatus.get(active);
	}

	public static void setExamineUser(String userId,String value){
		log.info("添加 审核人 信息："+userId+"|"+value);
		examineUser.put(userId, value);
	}

	public static String getExamineUser(String userId){
		return examineUser.get(userId);
	}

}
