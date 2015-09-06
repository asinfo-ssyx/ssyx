package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.asiainfo.bean.UserGroupInfo;

@Repository
public interface UserGroupInfoDAO {

	public Map<String,String> getBqkUserInfo(String user)throws Exception;

	public int selectedUserGroupInsert(List<Map<String, String>> list) throws Exception;
	public void deleteAllGroup(String activeCode);
	public void updateGroup(UserGroupInfo group);
	public List<UserGroupInfo> selectGroupById(String activeCode);

	public int isSubUserGroup(String activeCode);

	/**
	 * ��ȡ��ǰ����ִ�е������Ƽ�� �û�Ⱥ����
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryNetServiceActive(Map<String,String> map)throws Exception;
}