package com.asiainfo.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface MqMessageDAO {

	public void saveReturnMessage(Map<String,String> map)throws Exception;


	public void saveWebServiceLog(Map<String,Object> map)throws Exception;

	public String queryWebServiceCodeName(Map<String,Object> map)throws Exception;
}
