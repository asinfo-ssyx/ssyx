package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface FilterDAO {

	/**
	 * ��ѯ�����û�Ⱥ
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryFilterUserGroup(Map<String,String> map) throws Exception;

	/**
	 * ��ѯ���ύ�ͻ�Ⱥ
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public List<String> querySubUserGroup(String activeCode) throws Exception;

	/**
	 * ��ѯ���ύ���˿ͻ�Ⱥ
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public List<String> querySubFilterUserGroup(String activeCode) throws Exception;
}
