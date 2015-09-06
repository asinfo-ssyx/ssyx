package com.asiainfo.shorturl;


public class Test {
	public static void main(String[] args) {
		String longUrl="http://a.10086.cn/pams2/m/s.do?gId=300000008459&c=172&j=l&p=72&src=90.510007.001";
		System.out.println("生成段地址："+getShort(longUrl.trim()));

	}

	/**
	 * 获取短网址
	 * @param longUrl
	 * @return
	 */
	public static String getShort(String longUrl){
		//根据长地址为key查询之前是否有生成过短地址 ，有直接返回
		String shotUrl=RedisDBConn.getString(longUrl);
		if(shotUrl!=null)return ShortUrlGenerator.urlPrefix+shotUrl;
		System.out.println("----地址第一次使用----："+longUrl);
		String[] aResult = ShortUrlGenerator.shortUrl (longUrl);

	    for (int i = 0; i < aResult. length ; i++) {
	          System. out .println( "[" + i + "]:::" + aResult[i]);
	          String s=RedisDBConn.getString(aResult[i]);//判断key是否被使用过，如果被使用过取下一个值
	          if(s==null || "".equals(s)){
	        	  RedisDBConn.setString(aResult[i], longUrl);
	        	  RedisDBConn.setString(longUrl, aResult[i]);//重复长地址直接获取
	        	  shotUrl=ShortUrlGenerator.urlPrefix+aResult[i];
	        	  System.out.println("短地址："+shotUrl);
	        	  System.out.println("长地址："+longUrl);
	        	  break;
	          }
	    }
	    return shotUrl;
	}
}
