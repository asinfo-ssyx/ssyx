package com.asiainfo.util;

public class SingletonF {

	private  static  RandomThread random;

	public  static RandomThread getRandomInstance(){
		if(random==null){
			random=new RandomThread();
		}
		return random;
	}

}
