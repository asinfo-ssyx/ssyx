package com.asiainfo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.asiainfo.bean.TriggerInfo;

@Repository
public interface TriggerInfoDAO {
	
	public void insertTrigger(TriggerInfo trigger);
	public void deleteTrigger(String activeCode);
	public void updateTrigger(TriggerInfo trigger);
	public List<TriggerInfo> selectTriggerById(String activeCode);

}
