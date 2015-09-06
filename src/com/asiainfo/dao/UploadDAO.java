package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadDAO {

	public int insertUsers(@Param(value="list")List<String> list,
						   @Param(value="activeCode")String activeCode,
						   @Param(value="upType")String upType) throws Exception;


	public List<Map<String,String>> queryGlUserGroup(@Param(value="cityId")String cityId) throws Exception;

	public int insertGlUserGroup(List<Map<String,String>> list) throws Exception;

	public int insertUserGroup(Map<String,Object> map)throws Exception;

	public int isRuserGroupName(Map<String,String> map)throws Exception;
	
	public int deleteGlGroupName(Map<String,Object> map) throws Exception;
}
