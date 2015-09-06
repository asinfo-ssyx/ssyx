package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ITriggerService {
	
	/**
	 * ��ѯ���ж�Ӧ��վ�����б�
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public String searchBsType(Map<String,String> map )throws Exception;
	
	/**
	 * ɾ����������
	 * @param map
	 * @return
	 */
	public boolean deleteSetProperty(Map<String,String> map);
	
	
	/**
	 * ��ȡһ�����͵�app�б�
	 * @return
	 */
	public Map<String,String> getTypeAppList(String typeid);
	
	/**
	 * ��ȡ����������Ϣ--���ڵ���չʾ
	 * @param activeCode
	 * @return
	 */
	public String getTriggerInfoMs(String activeCode);

	/**
	 * ��ȡapp��Ϣ
	 * @param map
	 * @return
	 */
	public Map<String,List<Map<String, String>>> getAppMessageList(Map<String,String> map);

	/**
	 * �� ��ȡapp�б�
	 * @return
	 */
	public List<Map<String,String>> getAppList();

	/**
	 * �ύapp
	 * @param map
	 * @return
	 */
	public boolean insertCheckedApp(Map<String,String> map);

	/**
	 * ��ȡ�����app�б�
	 * @param map
	 * @return
	 */
	public Map<String,String> getCheckedApp(Map<String,String> map);

	/**
	 * ��ȡ���������б�
	 * @return
	 */
	public String  getCityList();

	/**
	 * ��ȡ�����»�վ
	 * @param map
	 * @return
	 */
	public String  getCityPositionList(Map<String,String> map);

	/**
	 * �ύѡȡ��վ
	 * @param map
	 * @return
	 */
	public boolean subCheckedPosition(Map<String,String> map);

	/**
	 * ��ѯ�Ѿ�ѡ���վ
	 * @param map
	 * @return
	 */
	public Map<String,String> getCheckedPosition(Map<String,String> map);

	/**
	 * ɾ����������
	 * @param map
	 * @return
	 */
	public boolean deleteTriggerMs(Map<String,String> map);
	
	/**
	 * ��ȡ���ύ��app��
	 * @param map
	 * @return
	 */
	public int getCheckedAppCount(Map<String,String> map)throws Exception;
}
