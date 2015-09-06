package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;


public interface IActiveService {

	public List<Map<String,String>> getDownLoadPhone(String activeCode)throws Exception;

	public String cityExamineActive(Map<String,String> map)throws Exception;

	/**
	 * 查看活动配置信息是否完整
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String ActiveSetTest(String activeCode,String activeType)throws Exception;

	public String updateActiceStatus(Map<String,String> map) throws Exception;

	public void getUserGroupHdfs(String activeCode)throws Exception;

	/**
	 * 发送通知到MQ
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public String sendMq(Map<String,String> map) throws Exception;
	/**
	 * mq删除消息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String sendMqDelete(Map<String,String> map)throws Exception;
	/**
	 * 插入活动
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> insertActive(Map<String,Object> map) throws Exception;

	/**
	 * 复制现有活动规则
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean copuCreateActive(Map<String,String> map) throws Exception;

	/**
	 * 更新活动信息（补全）
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> updateActiveInfo(Map<String,Object> map);

	/**
	 * 获取活动选择信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getActiveChooseInfo(Map<String,Object> map) throws Exception;

	/**
	 * 获取活动列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getActiveList(Map<String,Object> map) throws Exception;

	/**
	 * 获取活动总条数
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getActiveCount(Map<String,Object> map) throws Exception;

	/**
	 * 获取活动设置信息
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getActiveInfo(String activeCode) throws Exception;

	/**
	 * 根据活动编码获取活动基本信息方法
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getActiveInfo1(String activeCode) throws Exception;

	public boolean isMqInertDB(String activeCode);
	
	public Map<String,Object> createFromBass(Map<String, Object> map);
}
