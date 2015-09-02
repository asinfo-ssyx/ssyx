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
	 * ��ȡsequence
	 * @param typeid
	 * @return
	 * @throws Exception
	 */
	public String getSequence(@Param(value="typeid")String typeid)throws Exception;

	/**
	 * ��������
	 * @param map
	 * @return
	 */
	public int insertActiveMp(Map<String,Object> map);

	/**
	 * ��ȡ�����
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public String  queryActiveId(String activeCode) throws Exception;

	/**
	 * ���븴�ƻ�õĴ�����Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCopyCreateActiveTrigger(Map<String,String> map) throws Exception;

	/**
	 * ���븴�ƻ�õ��û�Ⱥ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCopyCreateActiveUserGroup(Map<String,String> map) throws Exception;

	/**
	 * ���»״̬
	 * @param acitveCode
	 * @return
	 * @throws Exception
	 */
	public int updateActiveStatus(Map<String,String> map) throws Exception;


	public Map<String,Object> getEffectPara(String activeCode) throws Exception;

	/**
	 * ��ѯ���Ҫͳ�Ƶ�ҵ�����
	 * @param productId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getEffectProduct(@Param(value="productId")String productId)throws Exception;

	public int insertEffect(Map<String,Object> map)throws Exception;

	/**
	 * ���»��Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateActiveInfo(Map<String,Object> map) throws Exception;

	/**
	 * ��ȡ����������Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryTrigger(Map<String,Object> map) throws Exception;

	/**
	 * ��ȡ�ͻ�Ⱥ��ǩ��Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryUserGroup(Map<String,Object> map) throws Exception;

	/**
	 * ��ȡ�������Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> querySendMessage(Map<String,Object> map) throws Exception;

	/**
	 * ��ȡ�����û�Ⱥ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryGlUserGroup(Map<String,Object> map) throws Exception;

	/**
	 * ��ȡ��б�
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryActiveList(Map<String,Object> map) throws Exception;

	/**
	 * ��ȡ�����
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> queryActiveCount(Map<String,Object> map) throws Exception;

	/**
	 * ��ȡ����Mq��Ϣ >>app��Ϣ
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> queryActiveMq(String activeCode) throws Exception;

	/**
	 * ��ȡ����Mq��Ϣ>>��վ��Ϣ
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> queryActiveBsMq(String activeCode) throws Exception;

	public Map<String,String> queryActiveKeyWordMq(String activeCode) throws Exception;

	/**
	 * ���ݻid �ʹ������Ͳ�ѯ
	 * @param activeCode
	 * @param triggerType
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> querySubTriggerInfoMq(Map<String,String> map)throws Exception;

	/**
	 * �޸Ļ״̬ --֪ͨmqȡ�����͸û��Ϣ
	 * @param activeCode
	 * @return
	 */
	public int updateActiveOverDate(String activeCode) ;

	/**
	 * ��ȡ���Ϣ
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> queryActiveInfo(String activeCode) throws Exception;


	public List<Map<String,Object>> queryActiveSendInfo(String nowTime)throws Exception;

	/**
	 * ��ȡ������������͡��������ж�ȡ��mq��������
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String getSendMqType(Map<String,String> map)throws Exception;

	/**
	 * ��ȡ�������Ϣ
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
