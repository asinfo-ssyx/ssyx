package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface EffectService {

	public Map<String,String> searchActiveSendInfo(String activeCode);

	/**
	 * ��ѯЧ�������б�
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryEffectList(Map<String,Object> map) throws Exception;

	public Map<String,Object> getEffectCount(Map<String,Object> map) throws Exception;


	/**
	 * ��ѯ�ͳ���б�
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,List<Map<String,Object>>> queryActiveCountList(Map<String,Object> map) throws Exception;
}
