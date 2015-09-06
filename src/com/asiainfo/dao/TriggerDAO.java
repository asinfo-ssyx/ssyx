package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TriggerDAO {
	
	/**
	 * ��ѯ���ж�Ӧ��վ�����б�
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<String> searchBsType(Map<String,String> map) throws Exception;
	
	/**
	 * ��ѯ����������Ϣ--����չʾ
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryTriggerInfoMs(String activeCode) throws Exception;

	/**
	 * ��ȡapp�б� ��
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryAppList() throws Exception;

	/**
	 * ��ѯapp��Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryAppMessageList(Map<String,String> map) throws Exception;

	/**
	 * ����ѡ����Ϣ
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertCheckedApps(List<Map<String,String>> list) throws Exception;

	/**
	 * ����λ����Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCheckedPosition(Map<String,String> map) throws Exception;
	//����λ����Ϣ  ��վ����
	public int insertCheckedPositionType(List<Map<String,String>> list) throws Exception;

	/**
	 * ��ȡѡȡ��app
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryCheckedAppList(Map<String,String> map) throws Exception;

	/**
	 * ��ѯ������������
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryCityList() throws Exception;

	/**
	 * ��ѯ�����»�վ
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryCityPositionList(Map<String,String> map) throws Exception;

	/**
	 * ��ѯ�Ѿ�ѡȡ�Ļ�վ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> queryCheckedPosition(Map<String,String> map) throws Exception;

	/**
	 * ɾ������������Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteTriggerMs(Map<String,String> map) throws Exception;
	public int deleteUserGroup(Map<String,String> map) throws Exception;
	public int deleteSendMs(Map<String,String> map) throws Exception;
	
	/**
	 * ɾ����վ��ϸ��Ϣ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteTriggerBsInfo(Map<String,String> map)throws Exception;
	
	public void testMapper(@Param(value="code")String code,@Param(value="name")String name) throws Exception;

	/**
	 * ��ȡһ�����͵�app�б�
	 * @param typeid
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getTypeAppList(String typeid) throws Exception;

	public void insertBsTrigger (String activeCode) throws Exception;
	
	
	/**
	 * ��ȡ���ύ��app��
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getCheckedAppCount(Map<String,String> map)throws Exception;
}
