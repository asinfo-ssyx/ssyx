package com.asiainfo.shorturl;


public class Test {
	public static void main(String[] args) {
		String longUrl="http://a.10086.cn/pams2/m/s.do?gId=300000008459&c=172&j=l&p=72&src=90.510007.001";
		System.out.println("���ɶε�ַ��"+getShort(longUrl.trim()));

	}

	/**
	 * ��ȡ����ַ
	 * @param longUrl
	 * @return
	 */
	public static String getShort(String longUrl){
		//���ݳ���ַΪkey��ѯ֮ǰ�Ƿ������ɹ��̵�ַ ����ֱ�ӷ���
		String shotUrl=RedisDBConn.getString(longUrl);
		if(shotUrl!=null)return ShortUrlGenerator.urlPrefix+shotUrl;
		System.out.println("----��ַ��һ��ʹ��----��"+longUrl);
		String[] aResult = ShortUrlGenerator.shortUrl (longUrl);

	    for (int i = 0; i < aResult. length ; i++) {
	          System. out .println( "[" + i + "]:::" + aResult[i]);
	          String s=RedisDBConn.getString(aResult[i]);//�ж�key�Ƿ�ʹ�ù��������ʹ�ù�ȡ��һ��ֵ
	          if(s==null || "".equals(s)){
	        	  RedisDBConn.setString(aResult[i], longUrl);
	        	  RedisDBConn.setString(longUrl, aResult[i]);//�ظ�����ֱַ�ӻ�ȡ
	        	  shotUrl=ShortUrlGenerator.urlPrefix+aResult[i];
	        	  System.out.println("�̵�ַ��"+shotUrl);
	        	  System.out.println("����ַ��"+longUrl);
	        	  break;
	          }
	    }
	    return shotUrl;
	}
}
