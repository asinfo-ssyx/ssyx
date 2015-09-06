package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

public interface IFilterUserService {

	/**
	 * 查询过滤用户群
	 * @param map
	 * @return
	 */
	public List<Map<String,String>> searchFilterUserGroup(Map<String,String> map);

	/**
	 * 提交选择的过滤用户群
	 * @param map
	 * @return
	 */
	public Boolean subFilterUserGroup(Map<String,String> map);

	/**
	 * 查询提交的客户群
	 * @param activeCode
	 * @return
	 */
	public String getSubUserGroup(String activeCode);

	/**
	 * 查询提交的过滤客户群
	 * @param acitveCode
	 * @return
	 */
	public String getSubFilterUserGroup(String acitveCode);
}
