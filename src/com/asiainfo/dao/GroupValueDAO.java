package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface GroupValueDAO {
	public void insertGroupValue(List<Map<String,String>> list);
}
