package com.asiainfo.Iservice;

import java.util.List;
import java.util.Map;

public interface BlackListService {
	
	
	public void insertUserToBlackList(Map<String,Object> map) throws Exception;
	
    public void deleteUserFromBlackList(String phoneNumber) throws Exception;
	
	public Map<String,Object> queryBlackList(String phoneNumber) throws Exception;
	
	public List<Map<String,Object>> showBlackList(Map<String, Object> map) throws Exception;

	public Map<String,Object> queryBlackListCount(Map<String, Object> map) throws Exception;
}
