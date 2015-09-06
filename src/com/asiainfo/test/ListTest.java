package com.asiainfo.test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			list.add("list"+i);
		}
		list.remove(0);
		list.remove(0);
		System.out.println(list.get(0));
		System.out.println(list);
	}
}
