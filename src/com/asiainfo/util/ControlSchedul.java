package com.asiainfo.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.dao.UtilDAO;
@Service
public class ControlSchedul {
	private Logger log = Logger.getLogger(ControlSchedul.class);

	//服务器调度控制
	public static boolean schedulStatus=false;

	//控制阀值
	public final int fz = 30;

	@Autowired
	public UtilDAO dao;

	public void controlSchedulThread(){
		log.info("执行");
		//数据库时间   大于  当前时间-30分钟


	}
}
