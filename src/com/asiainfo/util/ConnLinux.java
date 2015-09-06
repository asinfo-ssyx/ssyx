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
	* ��¼Զ��Linux����
	*
	* @return
	* @throws IOException
	*/
	public boolean login() throws IOException {
		log.info(" ���� ������ ��Ϣ��"+ipAddr+"|"+userName+"|"+password);
	   conn = new Connection(ipAddr);
	   conn.connect(); // ����
	   return conn.authenticateWithPassword(userName, password); // ��֤
	}

	/**
	* ִ��Shell�ű�������
	*
	* @param cmds ����������
	* @return
	*/
	public String exec(String cmds) {
	   log.info("-----------ִ��shell�ű�����---------"+cmds+"-----------");
	   InputStream in = null;
	   String result = "";
	   try {
	    if (this.login()) {
	     Session session = conn.openSession(); // ��һ���Ự
	     session.execCommand(cmds);
	     in = session.getStdout();
	     result = this.processStdout(in, this.charset);
	     conn.close();
	    }else{
	    	log.info("ssh����ʧ��");
	    }
	   } catch (Exception e1) {
	    e1.printStackTrace();
	    log.error("shellִ�г���="+e1.getMessage());
	   }
	   return result;
	}
	/**
	* ��������ȡ�ַ�����Ϣ
	*
	* @param in ����������
	* @param charset �ַ���
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
	    log.error("�������س���:"+e.getMessage());
	   }
	   log.info("���ؽ�����"+sb.toString());
	   return sb.toString();
	}




}
