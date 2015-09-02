package com.asiainfo.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusiCodeDAO {

	public List<Map<String,Object>>  getDataBusiBigType(@Param(value="group_id")String group_id) throws Exception;

	public List<Map<String,Object>>  getDataBusiSmallType(@Param(value="bigType")String code) throws Exception;

	public List<String> getEffectProduct()throws Exception;

	public List<Map<String,String>> getActiveUser()throws Exception;

	public List<Map<String,Object>> searchActive(String sm)throws Exception;
}
