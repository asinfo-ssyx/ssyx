package com.asiainfo.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
@Service("connlinux")
public class ConnLinux {
	public Logger log = Logger.getLogger(ConnLinux.class);
	private Connection conn;
	private String ipAddr="10.95.66.60";
	private String charset = Charset.defaultCharset().toString();
	private String userName="ocdc";
	private String password="llixsx15@)";
//	public ConnLinux(String ipAddr, String userName, String password, String charset) {
//	   this.ipAddr = ipAddr;
//	   this.userName = userName;
//	   this.password = password;
//	   if(charset != null) {
//	    this.charset = charset;
//	   }
//	}

	public void setParams(String ipAddr, String userName, String password){
		   this.ipAddr = ipAddr;
		   this.userName = userName;
		   this.password = password;
	}

	/**
	* 登录远程Linux主机
	*
	* @return
	* @throws IOException
	*/
	public boolean login() throws IOException {
		log.info(" 登入 服务器 信息："+ipAddr+"|"+userName+"|"+password);
	   conn = new Connection(ipAddr);
	   conn.connect(); // 连接
	   return conn.authenticateWithPassword(userName, password); // 认证
	}

	/**
	* 执行Shell脚本或命令
	*
	* @param cmds 命令行序列
	* @return
	*/
	public String exec(String cmds) {
	   log.info("-----------执行shell脚本方法---------"+cmds+"-----------");
	   InputStream in = null;
	   String result = "";
	   try {
	    if (this.login()) {
	     Session session = conn.openSession(); // 打开一个会话
	     session.execCommand(cmds);
	     in = session.getStdout();
	     result = this.processStdout(in, this.charset);
	     conn.close();
	    }else{
	    	log.info("ssh登入失败");
	    }
	   } catch (Exception e1) {
	    e1.printStackTrace();
	    log.error("shell执行出错="+e1.getMessage());
	   }
	   return result;
	}
	/**
	* 解析流获取字符串信息
	*
	* @param in 输入流对象
	* @param charset 字符集
	* @return
	*/
	public String processStdout(InputStream in, String charset) {
	   byte[] buf = new byte[1024];

	   StringBuffer sb = new StringBuffer();
	   try {
	    while (in.read(buf) != -1) {
	     sb.append(new String(buf, charset));
	    }
	   } catch (Exception e) {
	    log.error("解析返回出错:"+e.getMessage());
	   }
	   log.info("返回解析："+sb.toString());
	   return sb.toString();
	}




}
