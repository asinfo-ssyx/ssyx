package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

public interface ActiveEventService {
	/**
	 * ��ȡС�����б�
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getEventList(String type) throws Exception;

	/**
	 * ��ȡ�¼��б�
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String getEventNameList(Map<String,String> map)throws Exception;

	/**
	 * �ύ����
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String subCheckedEvent(Map<String,String> map)throws Exception;

	/**
	 * ��ѯ�¼����Ͷ�Ӧ�Ŀͻ�Ⱥ
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String queryUserGroup(String code)throws Exception;

	/**
	 * ��ȡ�ύ������
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String queryCheckedEventType(String activeCode)throws Exception;

	/**
	 * ɾ���¼����Ͷ�Ӧ���û�Ⱥ
	 * @param acticeCode
	 * @return
	 * @throws Exception
	 */
	public String deleteActiveEventTypeUserGroup(String acticeCode)throws Exception;

	/**
	 * ��ѯ��վ����
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryWebClassList()throws Exception;

	/**
	 * ����class id ��ѯ��վ�б�
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	public String queryWebListByCid(String classId)throws Exception;

	/**
	 * ����ѡ��web
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean insertCheckedWeb(Map<String,String> map)throws Exception;

	/**
	 * ��ѯѡ���ύ��web
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String queryCheckedWebList(Map<String,String> map) throws Exception;


	public int getCheckedWebCount(Map<String,String> map)throws Exception;

}
