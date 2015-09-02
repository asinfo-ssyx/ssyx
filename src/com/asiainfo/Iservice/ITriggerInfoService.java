package com.asiainfo.Iservice;

import java.util.List;

import com.asiainfo.bean.KeyWord;
import com.asiainfo.bean.TriggerInfo;

public interface ITriggerInfoService {
	
	public void triggerInsert(TriggerInfo trigger);
	public void triggerDelete(String activeCode);
	public void triggerUpdate(TriggerInfo trigger);
	public List<TriggerInfo> triggerSelect(String activeCode); 
	public List<KeyWord> keyWordSelect(String keyName);
}
