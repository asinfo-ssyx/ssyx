package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

public interface IFilterUserService {

	/**
	 * ��ѯ�����û�Ⱥ
	 * @param map
	 * @return
	 */
	public List<Map<String,String>> searchFilterUserGroup(Map<String,String> map);

	/**
	 * �ύѡ��Ĺ����û�Ⱥ
	 * @param map
	 * @return
	 */
	public Boolean subFilterUserGroup(Map<String,String> map);

	/**
	 * ��ѯ�ύ�Ŀͻ�Ⱥ
	 * @param activeCode
	 * @return
	 */
	public String getSubUserGroup(String activeCode);

	/**
	 * ��ѯ�ύ�Ĺ��˿ͻ�Ⱥ
	 * @param acitveCode
	 * @return
	 */
	public String getSubFilterUserGroup(String acitveCode);
}
