package com.asiainfo.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface UtilDAO {

	/**
	 * 获取当前最新的服务器时间
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getNewServerTime()throws Exception;
}
