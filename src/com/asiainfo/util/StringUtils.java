package com.asiainfo.util;

public class StringUtils {

	public static String convertToUTF(String s){
        String result="";
        byte[] temp ;
        try{
            temp = s.getBytes("iso-8859-1");
            result =  new String(temp,"UTF-8");
        }catch(Exception e){
        	//log.debug("ת��ʧ��"+e);
        }
        //log.debug("ת���"+result);
        return result;
    }
	public static String convertToISO(String s){
        String result="";
        byte[] temp ;
        try{
            temp = s.getBytes("UTF-8");
            result =  new String(temp,"iso-8859-1");
        }catch(Exception e){
        	//log.debug("ת��ʧ��"+e);
        }
        //log.debug("ת���"+result);
        return result;
    }

}
