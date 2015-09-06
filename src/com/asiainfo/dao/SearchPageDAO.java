package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchPageDAO {

	public List<Map<String,Object>> selectSql(@Param(value="sqlStr")String sqlStr)throws Exception;


	public int ddlsql(String sql)throws Exception;
}
