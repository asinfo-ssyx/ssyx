package com.asiainfo.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class PasswordUtil {
	private byte[] desKey;
	public static String key = "asia123?";

	// 解密数据
	public static String decrypt(String message) throws Exception {

		byte[] bytesrc = convertHexString(message);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));

		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

		byte[] retByte = cipher.doFinal(bytesrc);
		return java.net.URLDecoder.decode(new String(retByte), "utf-8");
	}
    //加密数据
	public static String encrypt(String message) throws Exception {
		message = java.net.URLEncoder.encode(message, "utf-8").toLowerCase();
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

		return toHexString(cipher.doFinal(message.getBytes("UTF-8"))).toUpperCase();
	}

	public static byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}

		return digest;
	}

	public static void main(String[] args) throws Exception {
		// String key = "11111111";
		//String value = "我们都是中国人1234zxcvZXCC??/@#$%^&*(!";
		//String jiami = java.net.URLEncoder.encode(value, "utf-8").toLowerCase();
		//System.out.println("加密数据:" + jiami);
		//String a = toHexString(encrypt(jiami)).toUpperCase();
		//System.out.println("加密后的数据为:" + a);
		//String b = java.net.URLDecoder.decode(decrypt(a), "utf-8");
		//System.out.println("解密后的数据:" + b);
//		String value = "1234qwer我爱你？！@#￥%";
//		System.out.println("加密数据:" + value);
//		String a = encrypt("my_zengjie");
//		System.out.println("加密后的数据为:" + a);
//		String b = decrypt("AC3A4D84888EA4D55CF4096A41D17FB9");
//		System.out.println("解密后的数据:" + b);
//		String c = decrypt("5AB34A6350BB7488");
//		System.out.println("解密后的数据:" + c);


String s="17F197DE3F52A96A572EE7A72CACDEF373BAA3713B33155CBAB082D22E9AE93A3EF2A1977A1FD10A0E3AD67610C885158FE4E0CBACE05EBCF447805174A1091A7F14A25E2C9A6E71120872C37F7FE4B01ED006044A8073A0F42363A720669D46EE5F6409E9C524053480E53D0C93105D623B3DDB479B837AAF8D0CB0372674FBE661BEF24BC08D1F";

System.out.println(PasswordUtil.decrypt(s));
		/**
		 * jdbc读取数据库
		 */

//		  Connection conn=null;
//		  Statement st=null;
//		  String sql;
//		  Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
//		  conn=DriverManager.getConnection("jdbc:db2://10.109.2.218:50000/sccrm","db2inst1","db2inst1");
//		  st=conn.createStatement();
//		  sql="select login_no,password from power_user_info where password is not null and login_no='songlj1'";
//		  ResultSet rs=st.executeQuery(sql);
//		  while(rs.next()){
//			  System.out.println(rs.getString("login_no")+"密码="+decrypt(rs.getString("password")));
//		  }

	}

	public static String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}

		return hexString.toString();
	}
}