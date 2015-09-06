package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;


public interface IActiveService {

	public List<Map<String,String>> getDownLoadPhone(String activeCode)throws Exception;

	public String cityExamineActive(Map<String,String> map)throws Exception;

	/**
	 * �鿴�������Ϣ�Ƿ�����
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String ActiveSetTest(String activeCode,String activeType)throws Exception;

	public String updateActiceStatus(Map<String,String> map) throws Exception;

	public void getUserGroupHdfs(String activeCode)throws Exception;

	/**
	 * ����֪ͨ��MQ
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public String sendMq(Map<String,String> map) throws Exception;
	/**
	 * mqɾ����Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String sendMqDelete(Map<String,String> map)throws Exception;
	/**
	 * ����
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> insertActive(Map<String,Object> map) throws Exception;

	/**
	 * �������л����
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean copuCreateActive(Map<String,String> map) throws Exception;

	/**
	 * ���»��Ϣ����ȫ��
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> updateActiveInfo(Map<String,Object> map);

	/**
	 * ��ȡ�ѡ����Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getActiveChooseInfo(Map<String,Object> map) throws Exception;

	/**
	 * ��ȡ��б�
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getActiveList(Map<String,Object> map) throws Exception;

	/**
	 * ��ȡ�������
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getActiveCount(Map<String,Object> map) throws Exception;

	/**
	 * ��ȡ�������Ϣ
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getActiveInfo(String activeCode) throws Exception;

	/**
	 * ���ݻ�����ȡ�������Ϣ����
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getActiveInfo1(String activeCode) throws Exception;

	public boolean isMqInertDB(String activeCode);
	
	public Map<String,Object> createFromBass(Map<String, Object> map);
}
