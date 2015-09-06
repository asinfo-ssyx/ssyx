package com.asiainfo.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.dao.ActiveDAO;

public class SendMsClientService implements Runnable{
	public ActiveDAO dao;

	public SendMsClientService(ActiveDAO d){
		this.dao=d;
	}

	public static Map<String,String> sendMsActiveInfo ;

	public static List<Map<String,Object>> sendMsActiveList=new ArrayList<Map<String,Object>>();

	public static void sendMsT0Server(Map<String,String> map){
		try {
			os.writeObject(map);
			os.flush();
		} catch (IOException e) {
			System.out.println("发送信息到server出错："+e.getMessage());
		}

	}

 	static Socket socket = null;
    static ObjectOutputStream os = null;
    static ObjectInputStream is = null;


    @Override
	public void run() {
		while(true){
	    	try {
				socket = new Socket("localhost", 19254);
				os = new ObjectOutputStream(socket.getOutputStream());
		        is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		        Map<String,String> sendMap=null;
				while(true){
					sendMap=new HashMap<String,String>();
					sendMsT0Server(sendMap);
					Object obj = is.readObject();
					sendMsActiveInfo = (Map<String,String>)obj;
					System.out.println(sendMsActiveInfo);
					if(sendMsActiveInfo!=null&&sendMsActiveInfo.size()>0){
						searchSendMsActiveInfo();
					}else{
						sendMsActiveList.clear();
					}
					Thread.sleep(1000L);
				}
			}catch (Exception e) {
				System.out.println("线程客户端出错:"+e.getMessage());
			}finally{
				try{
					if (is!=null)is.close();
					if (os!=null)os.close();
					if (socket!=null)socket.close();
				}catch(Exception e){
					System.out.println("客户端关闭socket 出错："+e.getMessage());
				}
			}

			try {
				Thread.sleep(1000L);
			} catch (Exception e) {
				System.out.println("ssss"+e);
			}
		}
	}

    public void searchSendMsActiveInfo(){
    	System.out.println("进入查询 方法");
    	try{
    	StringBuffer s=new StringBuffer();
    	for (String activeCode : sendMsActiveInfo.keySet()) {
    		s.append("'"+activeCode+"',");
		}
    	String al=s.substring(0, s.length()-1);
    	sendMsActiveList=dao.querySendMsActiveInfo(al);
    	String activeCode="";
    	for (Map<String,Object> map : sendMsActiveList) {
    		activeCode=(String) map.get("active_code");
    		map.put("sendStatus", sendMsActiveInfo.get(activeCode));
		}
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }

    public static void main(String[] args) {
//    	Map<String,String> m=new HashMap<String,String>();
//    	m.put("a1", "1");
//    	m.put("a2", "2");
//    	m.put("a3", "3");
//    	Set<String> s=m.keySet();
//    	searchSendMsActiveInfo(s);
	}

}
