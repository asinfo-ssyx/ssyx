package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveDAO {

	public void updateFtpActiveLogStatus(@Param(value="activeCode")String activeCode)throws Exception;

	public List<Map<String,Object>> getFtpActiveInfo(Map<String,String> para)throws Exception;

	public int insertFtpActiveLog(@Param(value="activeCode")String activeCode);


	public Map<String,String> querySendMsType(@Param(value="activeCode")String activeCode);

	public List<Map<String,Object>> querySendMsActiveInfo(@Param(value="activeList")String activeList);

	public int addAdviceMessage(Map<String,String> map)throws Exception;


	public List<Map<String,String>> queryExamineUser()throws Exception;

	public int isActiveSendMessage(Map<String,Object> map);

	public int insertActiveSendLog(Map<String,Object> map);

	public List<Map<String,Object>> ActiveSetTest(String activeCode)throws Exception;

	public List<Map<String,String>> queryOverDataActive(String nowTime) throws Exception;

	/**
	 * 获取sequence
	 * @param typeid
	 * @return
	 * @throws Exception
	 */
	public String getSequence(@Param(value="typeid")String typeid)throws Exception;

	/**
	 * 插入活动数据
	 * @param map
	 * @return
	 */
	public int insertActiveMp(Map<String,Object> map);

	/**
	 * 获取活动主键
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public String  queryActiveId(String activeCode) throws Exception;

	/**
	 * 插入复制获得的触发信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCopyCreateActiveTrigger(Map<String,String> map) throws Exception;

	/**
	 * 插入复制获得的用户群
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCopyCreateActiveUserGroup(Map<String,String> map) throws Exception;

	/**
	 * 更新活动状态
	 * @param acitveCode
	 * @return
	 * @throws Exception
	 */
	public int updateActiveStatus(Map<String,String> map) throws Exception;


	public Map<String,Object> getEffectPara(String activeCode) throws Exception;

	/**
	 * 查询活动需要统计的业务编码
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getEffectProduct(@Param(value="productId")String productId)throws Exception;

	public int insertEffect(Map<String,Object> map)throws Exception;

	/**
	 * 更新活动信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateActiveInfo(Map<String,Object> map) throws Exception;

	/**
	 * 获取触发条件信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryTrigger(Map<String,Object> map) throws Exception;

	/**
	 * 获取客户群标签信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryUserGroup(Map<String,Object> map) throws Exception;

	/**
	 * 获取活动推送信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> querySendMessage(Map<String,Object> map) throws Exception;

	/**
	 * 获取过滤用户群
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryGlUserGroup(Map<String,Object> map) throws Exception;

	/**
	 * 获取活动列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryActiveList(Map<String,Object> map) throws Exception;

	/**
	 * 获取活动条数
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> queryActiveCount(Map<String,Object> map) throws Exception;

	/**
	 * 获取发送Mq信息 >>app信息
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> queryActiveMq(String activeCode) throws Exception;

	/**
	 * 获取发送Mq信息>>基站信息
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> queryActiveBsMq(String activeCode) throws Exception;

	public Map<String,String> queryActiveKeyWordMq(String activeCode) throws Exception;

	/**
	 * 根据活动id 和触发类型查询
	 * @param activeCode
	 * @param triggerType
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> querySubTriggerInfoMq(Map<String,String> map)throws Exception;

	/**
	 * 修改活动状态 --通知mq取消发送该活动信息
	 * @param activeCode
	 * @return
	 */
	public int updateActiveOverDate(String activeCode) ;

	/**
	 * 获取活动信息
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> queryActiveInfo(String activeCode) throws Exception;


	public List<Map<String,Object>> queryActiveSendInfo(String nowTime)throws Exception;

	/**
	 * 获取活动出发条件类型》》用于判断取消mq发送类型
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String getSendMqType(Map<String,String> map)throws Exception;

	/**
	 * 获取活动基本信息
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getActiveInfo(String activeCode) throws Exception;

	public List<String> getNowRunActiveCode(@Param(value="nowTime")String nowTime)throws Exception;

	public List<String> getTestPhoneList(String activeCode)throws Exception;


	public Map<String,String> getActiveUserGroup(String activeCode)throws Exception;

	public int insertActiveMpBass(Map<String, Object> map);
}
