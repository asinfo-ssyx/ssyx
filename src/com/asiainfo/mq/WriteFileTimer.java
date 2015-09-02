package com.asiainfo.mq;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class WriteFileTimer {

	Timer timer;
	private static Logger logger = Logger.getLogger(WriteFileTimer.class);

	public WriteFileTimer() {
		timer = new Timer();
		timer.schedule(new GenrateTask(), 0, 1000 * 10);
	}

	class GenrateTask extends TimerTask {

		@Override
		public void run() {
			//System.out.println("app run");
			logger.info("app run");
			MessgeInfoList mtList = MessgeInfoList.getInstance();
			//wirte to hdfs
			//MsgExcuteService.readDataToFile(mtList);
			//when get mq message,and then according to then phone_no query hbase database
			//ControlService.selectUserViewInfo();
		}
	}

}
