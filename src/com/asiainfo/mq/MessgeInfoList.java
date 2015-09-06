package com.asiainfo.mq;

import java.util.*;

/**
 * @Title: MessageBeanList
 * @Copyright�?Copyright(c) 2010
 * @Company: 亚信联创科技(中国)有限公司
 * @author wangbq
 * @date 2011-9-29
 * @version 1.0.0
 */
public class MessgeInfoList {
	/**
	 * SendMT消息模块实例 *
	 */
	static private MessgeInfoList _instance;

	/**
	 * SendMT消息队列 
	 */
	private Vector<MessageBean> list = null;

	/**
	 * 线程同步控制确保模块仅有�?��实例 
	 */
	static synchronized public MessgeInfoList getInstance() {
		if (_instance == null) {
			_instance = new MessgeInfoList();
		}
		return _instance;
	}

	private MessgeInfoList() {
		list = new Vector<MessageBean>();
	}

	public synchronized void add(MessageBean msg) {
		list.addElement(msg);
	}

	public synchronized void insert(MessageBean msg) {
		list.add(0, msg);
	}

	public synchronized MessageBean remove() {
		if (list.size() == 0)
			return null;
		return (MessageBean) list.remove(0);
	}

	public int getSize() {
		return list.size();
	}
	
	public synchronized MessageBean getElementAt(int index){
		return list.elementAt(index);
	}
}