package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 *
 */
@Repository
public interface BlackListDAO {
	
	/**
	 * ���û���ӵ�������
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertUserToBlackList(Map<String,Object> map) throws Exception;
	
	/**
	 * ���û��Ӻ�������ɾ��
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public int deleteUserFromBlackList(String phoneNumber) throws Exception;
	
	/**
	 * ���ݵ绰�����ѯ������
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> queryBlackList(String phoneNumber) throws Exception;
	
	/**
	 * ��ʾ������
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> showBlackList(Map<String,Object> map) throws Exception;
	
	/**
	 * ��ѯ��������¼����
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> queryBlackListCount(Map<String,Object> map)throws Exception;
}
