package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupDAO {
	public void insertGoup(Map<String,String> map);
	public List<String> selectGroup();

}
