package com.asiainfo.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.dao.UtilDAO;
@Service
public class ControlSchedul {
	private Logger log = Logger.getLogger(ControlSchedul.class);

	//���������ȿ���
	public static boolean schedulStatus=false;

	//���Ʒ�ֵ
	public final int fz = 30;

	@Autowired
	public UtilDAO dao;

	public void controlSchedulThread(){
		log.info("ִ��");
		//���ݿ�ʱ��   ����  ��ǰʱ��-30����


	}
}
