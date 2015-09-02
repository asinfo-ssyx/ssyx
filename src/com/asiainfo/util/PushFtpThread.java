package com.asiainfo.util;

import org.springframework.stereotype.Service;

@Service("pushFtpThread")
public class PushFtpThread {

	public void shPushFtp(){
		System.out.println(" diao yong ftp xiancheng");
		ConnLinux connlinux=new ConnLinux();
		connlinux.setParams("10.109.3.234", "dptuser", "Dpt3234$");
		String sh=" cd /interface/yangsy; nohup /usr/java6/bin/java -jar ssyxftp.jar &";
		connlinux.exec(sh);
	}

}
