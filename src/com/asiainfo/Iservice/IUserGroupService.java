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
	//查询标签库所有标签
	public Map<String, List<String>> selectLabel();
	//插入新增用户群
	public void insertGroup(Map<String, String> map);
	//插入新增用户群各属性值
	public void insertGroupValue(List<Map<String,String>> list);
	//查询用户群名称
	public List<String> selectGroup();

	public boolean isSubUserGroup(String activeCode);

}
