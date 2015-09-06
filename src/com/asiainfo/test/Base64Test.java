package com.asiainfo.test;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;


public class Base64Test {
	public static void main(String[] args) {
		byte[] encoded = Base64.encodeBase64("http://dwz6.cn/9cl".getBytes());
		try {
			System.out.println(new String(encoded,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(8+8+"88"+8+8);
	}
}
