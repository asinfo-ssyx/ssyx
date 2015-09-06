package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

public interface IManageService {

	public  String updateCityCount(Map<String,String> map)throws Exception;

	/**
	 * 获取地市发送量配置信息
	 * @param map
	 * @return
	 */
	public Map<String,Map<String,Object>> queryCitySendCOunt(Map<String,Object> map)  throws Exception ;

	/**
	 * 设置地市发送量参数
	 * @param map
	 * @return
	 */
	public boolean addCitySendPara(Map<String,Object> map);

	/**
	 * 获取产品编码
	 * 以字符串拼接方式返回集合数据 ajax请求
	 * @return
	 */
	public String getProductCode();

	/**
	 * 添加 产品编码信息
	 * @param list
	 * @return
	 */
	public boolean addProductInfo(Map<String,String> map);

	/**
	 * 修改产品编码信息
	 * @param list
	 * @return
	 */
	public boolean updateProductInfo(Map<String,Object> list);

    public Map<String,Object> queryCitySendCountByCityId(Map<String,Object> map);

}
