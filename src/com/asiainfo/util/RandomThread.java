package com.asiainfo.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.dao.RandomDAO;

public class RandomThread implements Runnable{
	@Autowired
	public RandomDAO dao;

	@Override
	public void run() {
		System.out.println(this.toString()+"=定时修改过时随机码状态");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}

}
