package com.asiainfo.Implservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.Iservice.IUserGroupService;

import com.asiainfo.bean.UserGroupInfo;
import com.asiainfo.dao.UserGroupDAO;
import com.asiainfo.dao.GroupValueDAO;
import com.asiainfo.dao.LabelDAO;
import com.asiainfo.dao.UserGroupInfoDAO;

@Service
public class UserGroupServiceImpl implements IUserGroupService{
	public Logger log = Logger.getLogger(UserGroupServiceImpl.class);

	@Autowired
	private UserGroupInfoDAO userInfoGroupDAO;

	@Autowired
	private LabelDAO labelDAO;

	@Autowired
	private UserGroupDAO groupDAO;

	@Autowired
	private GroupValueDAO groupValueDAO;



	public GroupValueDAO getGroupValueDAO() {
		return groupValueDAO;
	}

	public void setGroupValueDAO(GroupValueDAO groupValueDAO) {
		this.groupValueDAO = groupValueDAO;
	}

	public UserGroupDAO getGroupDAO() {
		return groupDAO;
	}

	public void setGroupDAO(UserGroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

	public LabelDAO getLabelDAO() {
		return labelDAO;
	}

	public void setLabelDAO(LabelDAO labelDAO) {
		this.labelDAO = labelDAO;
	}

	public UserGroupInfoDAO getUserInfoGroupDAO() {
		return userInfoGroupDAO;
	}

	public void setUserInfoGroupDAO(UserGroupInfoDAO userInfoGroupDAO) {
		this.userInfoGroupDAO = userInfoGroupDAO;
	}

	@Override
	public boolean insert(List<Map<String, String>> list){
		int i=0;
		if (list != null){
			try {
				i=userInfoGroupDAO.selectedUserGroupInsert(list);
			} catch (Exception e) {

			}
		}
		if(i>0){
			return true;
		}
		return false;

	}

	@Override
	public void delete(String activeCode) {
		if (activeCode != null && !"".equals(activeCode)){
			userInfoGroupDAO.deleteAllGroup(activeCode);
		}
	}

	@Override
	public void update(UserGroupInfo group) {
		if (group != null){
			userInfoGroupDAO.updateGroup(group);
		}
	}

	@Override
	public List<UserGroupInfo> select(String activeCode) {
		if (activeCode != null && !"".equals(activeCode)){
			List<UserGroupInfo> list = userInfoGroupDAO.selectGroupById(activeCode);
			return list;
		}
		return null;
	}

	public List<String> putData(String str){
		log.info("para=" + str);
		List<String> list=null;
		if(!"".equals(str)){
			list=new ArrayList<String>();
			String[] s=str.split("&");
			for (String string : s) {
				log.info("stringstringstringstringstring="+string);
				list.add(string);
			}
		}
		return list;
	}

	@Override
	public Map<String, List<String>> selectLabel() {
		List<Map<String, String>> list=null;
		try {
			list = labelDAO.selectLabel();
		} catch (Exception e) {
			log.info("查询label列表出错="+ e.getMessage());
		}
		Map<String, List<String>> resultMap=new HashMap<String, List<String>>();
		for (Map<String, String> map2 : list) {
			resultMap.put(map2.get("type"), putData(map2.get("label")));
		}

		log.info("查询结果="+resultMap);
		return resultMap;
	}

	@Override
	public void insertGroup(Map<String, String> map) {
		groupDAO.insertGoup(map);

	}

	@Override
	public void insertGroupValue(List<Map<String,String>> list) {
		groupValueDAO.insertGroupValue(list);

	}

	@Override
	public List<String> selectGroup() {
		return groupDAO.selectGroup();

	}

	@Override
	public boolean isSubUserGroup(String activeCode) {
		int i=userInfoGroupDAO.isSubUserGroup(activeCode);
		if (i==0) return false;
		return true;
	}

	@Override
	public Map<String, String> getBqkUserInfo(String vgopUser) {
		try {
			return userInfoGroupDAO.getBqkUserInfo(vgopUser);
		} catch (Exception e) {
			return null;
		}

	}



}
