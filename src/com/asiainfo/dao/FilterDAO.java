package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface FilterDAO {

	/**
	 * 查询过滤用户群
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryFilterUserGroup(Map<String,String> map) throws Exception;

	/**
	 * 查询以提交客户群
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public List<String> querySubUserGroup(String activeCode) throws Exception;

	/**
	 * 查询已提交过滤客户群
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public List<String> querySubFilterUserGroup(String activeCode) throws Exception;
}
