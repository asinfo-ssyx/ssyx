package com.asiainfo.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface UtilDAO {

	/**
	 * ��ȡ��ǰ���µķ�����ʱ��
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getNewServerTime()throws Exception;
}
