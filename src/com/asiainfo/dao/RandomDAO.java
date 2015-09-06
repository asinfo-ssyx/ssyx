package com.asiainfo.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface RandomDAO {
	/**
	 * �ֶ���ѯ��֤��
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String querySendMsRandom(String code)throws Exception;

	/**
	 * ��������
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertRandomCode(Map<String,Object> map) throws Exception;

	/**
	 * ��֤�����  ��ѯδʹ�õ�״̬�����
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int queryRandomNo(Map<String,Object> map) throws Exception;

	/**
	 * ���������״̬
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateRandomNo(Map<String,Object> map) throws Exception;
}
