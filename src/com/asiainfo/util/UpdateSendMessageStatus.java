package com.asiainfo.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.dao.SendMessageDAO;

@Service("updateSendMessageStatus")
public class UpdateSendMessageStatus {

	public Logger log = Logger.getLogger(QueryActiveThread.class);

	@Autowired
	public SendMessageDAO dao;

	public void doUpdate(){
		log.info("---------------update SendStatus----------------");
		try {
			int i=dao.updateSendStatus(DateUtil.getNowDateStr());
			log.info("�޸ķ���״̬������"+i);
		} catch (Exception e) {
			log.error("�޸ķ���״̬����"+e.getMessage());
		}
	}

}
