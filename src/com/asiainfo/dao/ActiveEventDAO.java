package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface ActiveEventDAO {

	/**
	 * 获取小类型列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryEventList(String type)throws Exception;

	/**
	 * 获取事件列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryEventNameList(Map<String,String> map) throws Exception;

	/**
	 * 查询当前活动是否已经提交过
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int queryEventType(Map<String,String> map) throws Exception;

	/**
	 * 插入选中的活动事件类型
	 * @param map
	 * @throws Exception
	 */
	public void insertEventType(Map<String,String> map)throws Exception;

	/**
	 * 查询以提交的事件类型
	 * @param map
	 * @throws Exception
	 */
	public String queryCheckedEventType(String activeCode)throws Exception;


	/**
	 * 查询事件对应的用户群
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String queryUserGroup(String code)throws Exception;

	/**
	 * 删除事件类型对应的用户群
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public void deleteActiveEventTypeUserGroup(Map<String,String> map)throws Exception;

	/**
	 * 查询网站类型列表
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryWebClassList()throws Exception;

	/**
	 * 根据 类型id查询web网站
	 * @param classId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryWebListByCid(String classId)throws Exception;

	/**
	 * 插入选中web数据
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertCheckedWebs(List<Map<String,String>> list)throws Exception;

	/**
	 * 根据触发类型查询提交的数据
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String querySubTriggerMsListByType(Map<String,String> map) throws Exception;

	public int getCheckedWebCount(Map<String,String> map)throws Exception;

}
