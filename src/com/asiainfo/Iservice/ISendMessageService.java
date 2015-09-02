package com.asiainfo.Iservice;

import java.util.Map;

public interface ISendMessageService {


	/**
	 * ��ȡ��֤��
	 * @param activeCode
	 * @return
	 */
	public String getSendMsRondom(String activeCode);


	/**
	 * ��ѯ���������
	 * @param map
	 * @return
	 */
	public String getActiveShr(Map<String,String> map);

	/**
	 * ��֤�뷢��
	 * @param map
	 * @return
	 */
	public String randomNoSend(Map<String,Object> map) ;
	/**
	 * �����Է�
	 * @param map
	 * @return
	 */
	public String shortMessageCodeSend(Map<String,Object> map);

	/**
	 * ��ȡ�ύ��������Ϣ����
	 * @param activeCode
	 * @return
	 */
	public String getSubSendMessage(String activeCode);

	/**
	 * ������Ϣ���
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> insertSendMessage(Map<String,Object> map);

	/**
	 * �������֤
	 * @param Map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> examineSendMessage(Map<String,Object> map) throws Exception;

	/**
	 * ������֤�����
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean sendExamineRandomNo(Map<String,Object> map);

	/**
	 * �޸���������
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> updateSendMessage(Map<String,Object> map) throws Exception;


	public boolean addWebServiceSendMessage(Map<String,Object> map)throws Exception;

	/**
	 * ���Ų��Ժ���
	 * @param map
	 * @return
	 */
	public boolean addTestPhone(Map<String,Object> map);

}
