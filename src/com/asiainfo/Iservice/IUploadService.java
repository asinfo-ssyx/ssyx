package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

public interface IUploadService {

	public String isRuserGroupName(Map<String,String> map);

	/**
	 * �ϴ��û�Ⱥ
	 * @param map
	 * @return
	 */
	public boolean uploadUsersService(Map<String,Object> map);

	/**
	 * ��ѯ���˿ͻ�Ⱥ
	 * @return
	 */
	public List<Map<String,String>> getGlUserGroup(String cityId);

	/**
	 * ������˿ͻ�Ⱥ
	 * @param map
	 * @return
	 */
	public boolean insertGlUserGroup(Map<String,String> map);

	/**
	 * �����ϴ��û�Ⱥ·��
	 * @param map
	 * @return
	 */
	public boolean insertUploadFilePath(Map<String,Object> map);

	/**
	 * �������������(�ͻ�Ⱥ)
	 * @param phoneList
	 * @param city_id
	 */
	public void   insertGroupBatch(List<String> phoneList,String city_id,String tableName);

	/**
	 * �������������(����)
	 * @param phoneList
	 * @param city_id
	 */
	public void   insertChannelBatch(List<String> phoneList,String city_id,String uniqueCode,String tableName);

	/**
	 * ɾ��uniqueCode��Ӧ�ĵ绰����
	 * @param uniqueCode
	 * @param tableName
	 */
	public void  deleteChannelBatch(String uniqueCode,String tableName);
	void insertGroupBatchBass(String cityId, String tableName,
			String othertablename);
	/**
	 * ɾ�������û�Ⱥ����
	 * @param map
	 * @return
	 */
	public String deleteGlGroupName(Map<String,Object> map);
}
