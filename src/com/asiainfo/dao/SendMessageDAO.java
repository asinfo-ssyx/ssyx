package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface SendMessageDAO {

	public String queryMessageCode(String message)throws Exception;


	/**
	 * ������Ϣ���
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSendMessage(Map<String,Object> map) throws Exception;


	/**
	 * �޸�������Ϣ���� ֻ����״̬����0��ʱ������޸�
	 * @param activeCode
	 * @param status
	 * @param sendType
	 * @return
	 * @throws Exception
	 */
	public int updateSendMessage(String activeCode,String status,String sendType) throws Exception;

	/**
	 * ��ȡ����˺���
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getExamineId(String userId) throws Exception;

	/**
	 * ���������־
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertExamineLog(Map<String,Object> map) throws Exception;

	/**
	 * ���������־��
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSendMessageLog(Map<String,Object> map) throws Exception;

	/**
	 * ��ѯ������
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryActiveShr(Map<String,String> map) throws Exception;

	/**
	 * �޸�������Ϣ״̬
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateSendMessageStatus(Map<String,Object> map) throws Exception;

	public int updateAfterSendMessageStatus(Map<String,Object> map) throws Exception;

	/**
	 *
	 * @param activeCode
	 * @param status  ״̬����0
	 * @param sendType ��˵����͵�����  ���š����� �ȵ�  null��ʾͬʱ���
	 * @param sendCode ���ͱ���
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getSendMessages(Map<String,Object> map);

	/**
	 * ��ѯ�ύ��������Ϣ
	 * @param activeCode
	 * @return
	 */
	public List<String> querySubSendMessages(String activeCode) throws Exception;

	/**
	 * ����code���
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int shortMessageCodeSend(Map<String,Object> map) throws Exception;

	/**
	 * �޸ķ���״̬   >>��Է�ʵʱ���ʱ����
	 * @param nowtime
	 * @throws Exception
	 */
	public int updateSendStatus(String nowtime) throws Exception;


	public int addWebServiceSendMessage(Map<String,Object> map)throws Exception;
	public void updatAllSendMessageStatus(String activeCode)throws Exception;


	public int addTestPhone(Map<String,Object> map) throws Exception;
}
