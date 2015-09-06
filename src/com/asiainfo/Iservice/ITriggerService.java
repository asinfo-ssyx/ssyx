package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ITriggerService {
	
	/**
	 * 查询地市对应基站类型列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String searchBsType(Map<String,String> map )throws Exception;
	
	/**
	 * 删除设置属性
	 * @param map
	 * @return
	 */
	public boolean deleteSetProperty(Map<String,String> map);
	
	
	/**
	 * 获取一个类型的app列表
	 * @return
	 */
	public Map<String,String> getTypeAppList(String typeid);
	
	/**
	 * 获取触发条件信息--用于导航展示
	 * @param activeCode
	 * @return
	 */
	public String getTriggerInfoMs(String activeCode);

	/**
	 * 获取app信息
	 * @param map
	 * @return
	 */
	public Map<String,List<Map<String, String>>> getAppMessageList(Map<String,String> map);

	/**
	 * 新 获取app列表
	 * @return
	 */
	public List<Map<String,String>> getAppList();

	/**
	 * 提交app
	 * @param map
	 * @return
	 */
	public boolean insertCheckedApp(Map<String,String> map);

	/**
	 * 获取活动配置app列表
	 * @param map
	 * @return
	 */
	public Map<String,String> getCheckedApp(Map<String,String> map);

	/**
	 * 获取三级地市列表
	 * @return
	 */
	public String  getCityList();

	/**
	 * 获取地区下基站
	 * @param map
	 * @return
	 */
	public String  getCityPositionList(Map<String,String> map);

	/**
	 * 提交选取基站
	 * @param map
	 * @return
	 */
	public boolean subCheckedPosition(Map<String,String> map);

	/**
	 * 查询已经选择基站
	 * @param map
	 * @return
	 */
	public Map<String,String> getCheckedPosition(Map<String,String> map);

	/**
	 * 删除触发条件
	 * @param map
	 * @return
	 */
	public boolean deleteTriggerMs(Map<String,String> map);
	
	/**
	 * 获取已提交的app数
	 * @param map
	 * @return
	 */
	public int getCheckedAppCount(Map<String,String> map)throws Exception;
}
