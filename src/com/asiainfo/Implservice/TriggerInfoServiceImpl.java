package com.asiainfo.Implservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.Iservice.ITriggerInfoService;
import com.asiainfo.bean.KeyWord;
import com.asiainfo.bean.TriggerInfo;
import com.asiainfo.dao.KeyWordDAO;
import com.asiainfo.dao.TriggerInfoDAO;

@Service
public class TriggerInfoServiceImpl implements ITriggerInfoService {

	@Autowired
	private TriggerInfoDAO triggerDAO;
	@Autowired
	private KeyWordDAO keyWordDAO;


	public KeyWordDAO getKeyWordDAO() {
		return keyWordDAO;
	}

	public void setKeyWordDAO(KeyWordDAO keyWordDAO) {
		this.keyWordDAO = keyWordDAO;
	}


	public TriggerInfoDAO getTriggerDAO() {
		return triggerDAO;
	}


	public void setTriggerDAO(TriggerInfoDAO triggerDAO) {
		this.triggerDAO = triggerDAO;
	}

	/**
	 * 调用DAO方法，实现数据插入
	 */
	@Override
	public void triggerInsert(TriggerInfo trigger) {
		if(trigger != null){
			triggerDAO.insertTrigger(trigger);
		}

	}

	@Override
	public void triggerDelete(String activeCode) {
		if(activeCode != null && !"".equals(activeCode)){
			triggerDAO.deleteTrigger(activeCode);
		}

	}

	@Override
	public void triggerUpdate(TriggerInfo trigger) {
		if (trigger != null) {
			triggerDAO.updateTrigger(trigger);
		}

	}

	@Override
	public List<TriggerInfo> triggerSelect(String activeCode) {
		if(activeCode != null && !"".equals(activeCode)){
			List<TriggerInfo> list = triggerDAO.selectTriggerById(activeCode);
			return list;
		}
		return null;
	}



	@Override
	public List<KeyWord> keyWordSelect(String keyName) {
		if(keyName != null && !"".equals(keyName)){
			System.out.println(keyWordDAO.selectKeyWordByWord(keyName));
			return keyWordDAO.selectKeyWordByWord(keyName);
		}
		return null;
	}

}
