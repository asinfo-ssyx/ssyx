package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

public interface IManageService {

	public  String updateCityCount(Map<String,String> map)throws Exception;

	/**
	 * ��ȡ���з�����������Ϣ
	 * @param map
	 * @return
	 */
	public Map<String,Map<String,Object>> queryCitySendCOunt(Map<String,Object> map)  throws Exception ;

	/**
	 * ���õ��з���������
	 * @param map
	 * @return
	 */
	public boolean addCitySendPara(Map<String,Object> map);

	/**
	 * ��ȡ��Ʒ����
	 * ���ַ���ƴ�ӷ�ʽ���ؼ������� ajax����
	 * @return
	 */
	public String getProductCode();

	/**
	 * ��� ��Ʒ������Ϣ
	 * @param list
	 * @return
	 */
	public boolean addProductInfo(Map<String,String> map);

	/**
	 * �޸Ĳ�Ʒ������Ϣ
	 * @param list
	 * @return
	 */
	public boolean updateProductInfo(Map<String,Object> list);

    public Map<String,Object> queryCitySendCountByCityId(Map<String,Object> map);

}
