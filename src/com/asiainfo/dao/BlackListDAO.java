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
	 * 把用户添加到黑名单
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertUserToBlackList(Map<String,Object> map) throws Exception;
	
	/**
	 * 把用户从黑名单中删除
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public int deleteUserFromBlackList(String phoneNumber) throws Exception;
	
	/**
	 * 根据电话号码查询黑名单
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> queryBlackList(String phoneNumber) throws Exception;
	
	/**
	 * 显示黑名单
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> showBlackList(Map<String,Object> map) throws Exception;
	
	/**
	 * 查询黑名单记录总数
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> queryBlackListCount(Map<String,Object> map)throws Exception;
}
