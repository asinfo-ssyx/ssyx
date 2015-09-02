package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

import com.asiainfo.bean.UserGroupInfo;

public interface IUserGroupService {

	public Map<String,String> getBqkUserInfo(String vgopUser);

	public boolean insert(List<Map<String, String>> list);
	public void delete(String activeCode);
	public void update(UserGroupInfo group);
	public List<UserGroupInfo> select(String activeCode);
	//��ѯ��ǩ�����б�ǩ
	public Map<String, List<String>> selectLabel();
	//���������û�Ⱥ
	public void insertGroup(Map<String, String> map);
	//���������û�Ⱥ������ֵ
	public void insertGroupValue(List<Map<String,String>> list);
	//��ѯ�û�Ⱥ����
	public List<String> selectGroup();

	public boolean isSubUserGroup(String activeCode);

}
