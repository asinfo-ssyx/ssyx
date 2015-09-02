package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;


public interface IBusiCodeService {

	public String searchActive(String sm);

	/**
	 * ��ȡҵ�����
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>>  getDataBusiBigType(String group_id) throws Exception;
	/**
	 * ��ȡҵ��С��
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public String getDataBusiSmallType(String code) throws Exception;

	public List<String> getEffectProduct()throws Exception;


	public List<Map<String,String>> searchUserCount()throws Exception;

}
