package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface ActiveEventDAO {

	/**
	 * ��ȡС�����б�
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryEventList(String type)throws Exception;

	/**
	 * ��ȡ�¼��б�
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryEventNameList(Map<String,String> map) throws Exception;

	/**
	 * ��ѯ��ǰ��Ƿ��Ѿ��ύ��
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int queryEventType(Map<String,String> map) throws Exception;

	/**
	 * ����ѡ�еĻ�¼�����
	 * @param map
	 * @throws Exception
	 */
	public void insertEventType(Map<String,String> map)throws Exception;

	/**
	 * ��ѯ���ύ���¼�����
	 * @param map
	 * @throws Exception
	 */
	public String queryCheckedEventType(String activeCode)throws Exception;


	/**
	 * ��ѯ�¼���Ӧ���û�Ⱥ
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String queryUserGroup(String code)throws Exception;

	/**
	 * ɾ���¼����Ͷ�Ӧ���û�Ⱥ
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public void deleteActiveEventTypeUserGroup(Map<String,String> map)throws Exception;

	/**
	 * ��ѯ��վ�����б�
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryWebClassList()throws Exception;

	/**
	 * ���� ����id��ѯweb��վ
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryWebListByCid(String classId)throws Exception;

	/**
	 * ����ѡ��web����
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertCheckedWebs(List<Map<String,String>> list)throws Exception;

	/**
	 * ���ݴ������Ͳ�ѯ�ύ������
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String querySubTriggerMsListByType(Map<String,String> map) throws Exception;

	public int getCheckedWebCount(Map<String,String> map)throws Exception;

}
