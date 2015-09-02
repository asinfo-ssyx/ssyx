package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

public interface ActiveEventService {
	/**
	 * 获取小类型列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getEventList(String type) throws Exception;

	/**
	 * 获取事件列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String getEventNameList(Map<String,String> map)throws Exception;

	/**
	 * 提交插入
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String subCheckedEvent(Map<String,String> map)throws Exception;

	/**
	 * 查询事件类型对应的客户群
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String queryUserGroup(String code)throws Exception;

	/**
	 * 获取提交的数据
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String queryCheckedEventType(String activeCode)throws Exception;

	/**
	 * 删除事件类型对应的用户群
	 * @param acticeCode
	 * @return
	 * @throws Exception
	 */
	public String deleteActiveEventTypeUserGroup(String acticeCode)throws Exception;

	/**
	 * 查询网站大类
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryWebClassList()throws Exception;

	/**
	 * 根据class id 查询网站列表
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	public String queryWebListByCid(String classId)throws Exception;

	/**
	 * 插入选中web
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean insertCheckedWeb(Map<String,String> map)throws Exception;

	/**
	 * 查询选择提交的web
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String queryCheckedWebList(Map<String,String> map) throws Exception;


	public int getCheckedWebCount(Map<String,String> map)throws Exception;

}
