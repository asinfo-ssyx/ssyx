package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TriggerDAO {
	
	/**
	 * 查询地市对应基站类型列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<String> searchBsType(Map<String,String> map) throws Exception;
	
	/**
	 * 查询触发条件信息--导航展示
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryTriggerInfoMs(String activeCode) throws Exception;

	/**
	 * 获取app列表 新
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryAppList() throws Exception;

	/**
	 * 查询app信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryAppMessageList(Map<String,String> map) throws Exception;

	/**
	 * 插入选中信息
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertCheckedApps(List<Map<String,String>> list) throws Exception;

	/**
	 * 插入位置信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertCheckedPosition(Map<String,String> map) throws Exception;
	//插入位置信息  基站类型
	public int insertCheckedPositionType(List<Map<String,String>> list) throws Exception;

	/**
	 * 获取选取的app
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryCheckedAppList(Map<String,String> map) throws Exception;

	/**
	 * 查询三级地市数据
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryCityList() throws Exception;

	/**
	 * 查询地区下基站
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryCityPositionList(Map<String,String> map) throws Exception;

	/**
	 * 查询已经选取的基站
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> queryCheckedPosition(Map<String,String> map) throws Exception;

	/**
	 * 删除触发条件信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteTriggerMs(Map<String,String> map) throws Exception;
	public int deleteUserGroup(Map<String,String> map) throws Exception;
	public int deleteSendMs(Map<String,String> map) throws Exception;
	
	/**
	 * 删除基站详细信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int deleteTriggerBsInfo(Map<String,String> map)throws Exception;
	
	public void testMapper(@Param(value="code")String code,@Param(value="name")String name) throws Exception;

	/**
	 * 获取一个类型的app列表
	 * @param typeid
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> getTypeAppList(String typeid) throws Exception;

	public void insertBsTrigger (String activeCode) throws Exception;
	
	
	/**
	 * 获取已提交的app数
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getCheckedAppCount(Map<String,String> map)throws Exception;
}
