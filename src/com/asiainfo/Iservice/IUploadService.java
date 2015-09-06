package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

public interface IUploadService {

	public String isRuserGroupName(Map<String,String> map);

	/**
	 * 上传用户群
	 * @param map
	 * @return
	 */
	public boolean uploadUsersService(Map<String,Object> map);

	/**
	 * 查询过滤客户群
	 * @return
	 */
	public List<Map<String,String>> getGlUserGroup(String cityId);

	/**
	 * 插入过滤客户群
	 * @param map
	 * @return
	 */
	public boolean insertGlUserGroup(Map<String,String> map);

	/**
	 * 插入上传用户群路径
	 * @param map
	 * @return
	 */
	public boolean insertUploadFilePath(Map<String,Object> map);

	/**
	 * 导入号码入库操作(客户群)
	 * @param phoneList
	 * @param city_id
	 */
	public void   insertGroupBatch(List<String> phoneList,String city_id,String tableName);

	/**
	 * 导入号码入库操作(渠道)
	 * @param phoneList
	 * @param city_id
	 */
	public void   insertChannelBatch(List<String> phoneList,String city_id,String uniqueCode,String tableName);

	/**
	 * 删除uniqueCode对应的电话号码
	 * @param uniqueCode
	 * @param tableName
	 */
	public void  deleteChannelBatch(String uniqueCode,String tableName);
	void insertGroupBatchBass(String cityId, String tableName,
			String othertablename);
	/**
	 * 删除过滤用户群名单
	 * @param map
	 * @return
	 */
	public String deleteGlGroupName(Map<String,Object> map);
}
