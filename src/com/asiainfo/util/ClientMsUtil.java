package com.asiainfo.util;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientMsUtil {
	
	public static Map<String,Integer> citySend=new HashMap<String,Integer>();
	
	public static Map<String,Integer> cityInit=new HashMap<String,Integer>();
	
	public static String activeCode="";
	
	public void getSmsSendNum(){
		Map<String,String> map=new HashMap<String,String>();
		map.put("method", "citySend");
	}
	
	
	
	public void startClient(Map<String,String> map){
		Socket socket = null;
        ObjectOutputStream os = null;
        ObjectInputStream is = null;

        try {
            socket = new Socket("localhost", 19254);
            os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(map);
            os.flush();

            is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            Object obj = is.readObject();
            if (obj != null) {
            	Map<String,Object> ma = (Map<String,Object>)obj;
                System.out.println("Server result Ms :"+ma);
                if("citySend".equals(map.get("method"))){
                	citySend=(Map<String, Integer>) ma.get("citySendNum");
                	cityInit=(Map<String, Integer>) ma.get("cityParamNum");
                	activeCode=(String) ma.get("activeCode");
                }
                      
            }
        } catch(Exception ex) {

        } finally{
			try{
				is.close();
				os.close();
				socket.close();
			}catch(Exception e){

			}
		}

	}
}
