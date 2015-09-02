package com.asiainfo.util;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.asiainfo.action.SendMessageAction;

@Service
public class RandomUtil {
	public static String[] arr;

	public static Logger log = Logger.getLogger(SendMessageAction.class);

	private static int i=0;//用于创建唯一活动id

	static{
		StringBuffer buf = new StringBuffer();
		//buf.append("A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z");
		buf.append(",1,2,3,4,5,6,7,8,9,0");
		arr=buf.toString().split(",");
	}

	/**
	 * 获取随机码
	 * @return
	 */
	public static String getRandom(){
		StringBuilder randomNo=new StringBuilder();
		for(int i=0;i<6;i++){
			int ran=(int)(Math.random()*9)+1;
			randomNo.append(arr[ran]);
		}
		return randomNo.toString();
	}


	public static  synchronized  String getRandom10(){
		if(i==10) i=0;
		return ++i+"";
	}

	public static void main(String[] args) {
		System.out.println((int)(Math.random()*999999));
	}
}
