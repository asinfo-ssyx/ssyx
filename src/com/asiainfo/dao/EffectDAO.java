package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface EffectDAO {
	/**
	 * ��ѯ�������Ϣ
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> searchActiveSendInfo(String activeCode)throws Exception;

	/**
	 * ��ѯЧ�������б�
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryEffectActiveList(Map<String,Object> map) throws Exception;

	/**
	 * ��ȡ������
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> queryEffectCount(Map<String,Object> map) throws Exception;


	/**
	 * �ͳ��ҳ��
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryActiveInfo(Map<String,Object> map) throws Exception;

	/**
	 * ��ѯ������б�
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryActiveScene()throws Exception;

	/**
	 * �ж��Ƿ��� �code�Ƿ���ͳ�Ʊ���
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public int isHaveActiveCode(String activeCode)throws Exception;



	/**
	 * ���»ͳ����Ϣ
	 * @param map
	 * @throws Exception
	 */
	public void updateActiveEffect(Map<String,Object> map)throws Exception;

	/**
	 * ����ͳ����Ϣ
	 * @param map
	 * @throws Exception
	 */
	public void insertActiveEffect(Map<String,Object> map)throws Exception;


	public int insertActiveSendInfo(List<Map<String,Object>> list)throws Exception;
}
