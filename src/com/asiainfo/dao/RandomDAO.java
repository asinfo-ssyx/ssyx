package com.asiainfo.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface RandomDAO {
	/**
	 * 手动查询验证码
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String querySendMsRandom(String code)throws Exception;

	/**
	 * 随机码入库
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertRandomCode(Map<String,Object> map) throws Exception;

	/**
	 * 验证随机码  查询未使用的状态随机码
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int queryRandomNo(Map<String,Object> map) throws Exception;

	/**
	 * 更新随机码状态
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateRandomNo(Map<String,Object> map) throws Exception;
}
