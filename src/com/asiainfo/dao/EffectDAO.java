package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface EffectDAO {
	/**
	 * 查询活动发送信息
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> searchActiveSendInfo(String activeCode)throws Exception;

	/**
	 * 查询效果跟踪列表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryEffectActiveList(Map<String,Object> map) throws Exception;

	/**
	 * 获取总条数
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> queryEffectCount(Map<String,Object> map) throws Exception;


	/**
	 * 活动统计页面
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryActiveInfo(Map<String,Object> map) throws Exception;

	/**
	 * 查询活动场景列表
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryActiveScene()throws Exception;

	/**
	 * 判断是否在 活动code是否在统计表中
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public int isHaveActiveCode(String activeCode)throws Exception;



	/**
	 * 更新活动统计信息
	 * @param map
	 * @throws Exception
	 */
	public void updateActiveEffect(Map<String,Object> map)throws Exception;

	/**
	 * 插入活动统计信息
	 * @param map
	 * @throws Exception
	 */
	public void insertActiveEffect(Map<String,Object> map)throws Exception;


	public int insertActiveSendInfo(List<Map<String,Object>> list)throws Exception;
}
