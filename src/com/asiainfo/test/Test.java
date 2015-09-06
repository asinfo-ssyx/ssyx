package com.asiainfo.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.asiainfo.util.RandomUtil;

public class Test {
	public static void main(String[] args) {
//		Resource resource = new ClassPathResource("applicationContext.xml");
//	    @SuppressWarnings("deprecation")
//		BeanFactory factory = new XmlBeanFactory(resource);
//	    System.out.println(factory);
//	    RandomUtil r=(RandomUtil) factory.getBean("randomUtil");
//	    RandomUtil r1=(RandomUtil) factory.getBean("randomDao");

//	    System.out.println();
//
//
//	    Map<String,Object> map=new HashMap<String,Object>();
//	    map.put("random_type", "sh");
//	    map.put("create_time", DateUtil.getNowDateStr());
//	    map.put("random_no", r.getRandom());
//	    map.put("user_id", "123456");
//	    map.put("statue","0");
//	    System.out.println(r.insertRandomLog(map));
		//getCityList("");

		//String s="萨达,23232,";

		//System.out.println(s.split(",").length);
//		try {
//			new Test().readerFile();
//		} catch (Exception e) {
//			System.out.println(e.getLocalizedMessage());
//		}

//		String m="2014-05-28 10:54:07";
//		System.out.println(m.replaceAll("-", "").replaceAll(":", "").replaceFirst(" ", ""));
//
//		String s="大数据的分析从所周知，大数据已经不简简单单是数据大的事实了，而最重要的现进行分析，只有通过分析大数据的，只有通过分析大数据的从所周知";
//		System.out.println(s.substring(1, 2));
//		try {
//			URLDecoder.decode("", "iso-8859-1");
//		} catch (UnsupportedEncodingException e) {
//			 System.out.println( e);
//		}


		String a=null;
		System.out.println(null instanceof String);
		System.out.println("".equals(a));
		if(!"".equals(a)){
			System.out.println("111");
		}

	}


	public  void  readerFile() throws Exception{
		Properties prop = new Properties();
        InputStream in = null;
		//in = new FileInputStream(new File("DemoProject\\src\\dataBase.properties"));
		in=this.getClass().getClassLoader().getResourceAsStream("dataBase.properties");
        prop.load(in);
		System.out.println(prop.getProperty("db2Url").trim());
	}


	public static Object getCityList(String s) {
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Map<String,String> map1=new HashMap<String,String>();
		map1.put("city_name", "成都");
		map1.put("county_name", "武侯");
		map1.put("area_name", "玉林");
		Map<String,String> map2=new HashMap<String,String>();
		map2.put("city_name", "成都");
		map2.put("county_name", "武侯");
		map2.put("area_name", "肖家河");
		Map<String,String> map3=new HashMap<String,String>();
		map3.put("city_name", "成都");
		map3.put("county_name", "锦江");
		map3.put("area_name", "春熙路");
		Map<String,String> map4=new HashMap<String,String>();
		map4.put("city_name", "绵阳");
		map4.put("county_name", "绵阳大区");
		map4.put("area_name", "绵阳2春熙路");
		Map<String,String> map5=new HashMap<String,String>();
		map5.put("city_name", "绵阳");
		map5.put("county_name", "绵阳江");
		map5.put("area_name", "绵阳春熙路");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		int count=list.size();
		StringBuffer html=new StringBuffer();
		Map<String,String> map=null;
		int rel=0;
		String city_name="";
		String county_name="";

		String end_city_name="";
		String end_county_name="";
		for (int i=0;i<count;i++) {
			map=list.get(i);
			String city=map.get("city_name");
			String county=map.get("county_name");
			String area=map.get("area_name");
			if(i==0){
				end_city_name=city;
				end_county_name=county;
			}

			if(!city.equals(end_city_name)){//</ul>
				html.append("</ul></li></ul></li>");
				end_city_name=city;
			}
			if(!city.equals(city_name)){
				html.append("<li rel="+ ++rel +">");
				html.append(city);
				html.append("<ul>");
				city_name=city;
				end_county_name=county;


				if(!county.equals(county_name)){
					html.append("<li rel="+ ++rel +"> ");
					html.append(county);
					html.append("<ul>");
					county_name=county;
					html.append("<li rel="+ ++rel +">"+area+"</li>");
				}else{
					html.append("<li rel="+ ++rel +">"+area+"</li>");
				}
				if(i==count-1){
					html.append("</ul></li></ul></li>");
				}
			}else{
				if(!county.equals(end_county_name)){//</ul>
					html.append("</ul></li>");
					end_county_name=county;
				}
				if(!county.equals(county_name)){
					html.append("<li rel="+ ++rel +"> ");
					html.append(county);
					html.append("<ul>");
					county_name=county;
					html.append("<li rel="+ ++rel +">"+area+"</li>");
				}else{
					html.append("<li rel="+ ++rel +">"+area+"</li>");
				}
				if(i==count-1){
					html.append("</ul></li></ul></li>");
				}
			}
		}
		System.out.println(html.toString());
		return null;
	}

	/**
	 *
INSERT INTO trigger_info
select 'active_201409021604542','3',CONCAT(city_name,",",COUNTY_NAME),1,null,NOW(),CONCAT(lac_id,"-",ci_id)
from position_info
	 * */
}
