package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository
public interface ManageDAO {


	public int updateCityCount(Map<String,String> map)throws Exception;

	/**
	 * 获取产品编码
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> getProductCode()throws Exception;

	/**
	 * 新增产品编码信息
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int addProductInfo(Map<String,String> map) throws Exception;

	/**
	 * 修改产品编码信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateProductInfo(Map<String,Object> map)throws Exception;

	public List<Map<String,Object>> getSendCityNum()throws Exception;
}
