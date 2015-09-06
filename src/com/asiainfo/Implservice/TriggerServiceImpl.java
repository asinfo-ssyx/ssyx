package com.asiainfo.Implservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.Iservice.ITriggerService;
import com.asiainfo.dao.TriggerDAO;
import com.asiainfo.util.DateUtil;
@Service
public class TriggerServiceImpl implements ITriggerService{
	public Logger log = Logger.getLogger(TriggerServiceImpl.class);

	@Autowired
	public TriggerDAO dao;

	@Override
	public Map<String, List<Map<String, String>>> getAppMessageList(
			Map<String, String> map) {
		List<Map<String, String>> list=null;
		try {
			list=dao.queryAppMessageList(map);
		} catch (Exception e) {
			log.info("查询app列表出错="+e.getMessage());
		}
		Map<String, List<Map<String, String>>> rmap=new HashMap<String, List<Map<String, String>>>();
		for (Map<String, String> map2 : list) {
			rmap.put(map2.get("ucode"), putData(map2.get("app")));
		}

		return rmap;
	}

	public List<Map<String,String>> putData(String str){
		List<Map<String,String>> list=null;
		if(!"".equals(str)){
			list=new ArrayList<Map<String,String>>();
			Map<String,String> m=null;
			String[] s=str.split("&");
			for (String string : s) {
				m=new HashMap<String,String>();
				m.put("name", string.split(",")[0]);
				m.put("imgurl", string.split(",")[1]);
				m.put("appid", string.split(",")[2]);
				list.add(m);
			}
		}
		return list;
	}

	@Override
	public boolean insertCheckedApp(Map<String, String> map) {
		String activeCode=map.get("activeCode");
		String userId=map.get("userId");
		String s=map.get("checked");
		String[] apps=s.split(",");
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for (String string : apps) {
			String[] ap=string.split("_");
			Map<String,String> m=new HashMap<String,String>();
			m.put("activeCode", activeCode);
			m.put("checked", ap[0]);
			m.put("userId", userId);
			m.put("trigger_type", "2");
			m.put("trigger_code", ap[1]);
			list.add(m);
		}
		int i=0;
		try {
			i=dao.insertCheckedApps(list);
		} catch (Exception e) {
			log.info("插入app信息出错="+e.getMessage());
		}
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Map<String, String> getCheckedApp(Map<String, String> map) {
		List<Map<String,String>> list=null;
		try {
			list=dao.queryCheckedAppList(map);
		} catch (Exception e) {
			log.info("查询选中app出错="+e.getMessage());
		}
		StringBuffer sb=new StringBuffer();
		if(list!=null && list.size()>0){
			for (Map<String, String> map2 : list) {
				if(map2==null) continue;
				String appms=map2.get("appms");
				sb.append(appms+"&");
			}

			map.put("appms", sb.toString());
		}else{
			map=null;
		}
		return map;
	}

	@Override
	public String getCityList() {
		List<Map<String, String>> list=null;
		try {
			list = dao.queryCityList();
		} catch (Exception e) {
			log.error("查询地区列表出错="+e.getMessage());
			return "F";
		}
		int count=list.size();

		StringBuffer html=new StringBuffer();
		Map<String,String> map=null;
		String city_name="";
		String county_name="";

		String end_city_name="";
		String end_county_name="";
		for (int i=0;i<count;i++) {
			map=list.get(i);
			String city=map.get("city_name");
			String county=map.get("county_name");
			//String area=map.get("area_name");
			if(i==0){
				end_city_name=city;
				end_county_name=county;
			}

			if(!city.equals(end_city_name)){//</ul>
				html.append("</ul></li></ul></li>");
				end_city_name=city;
			}
			if(!city.equals(city_name)){
				html.append("<li rel=\""+ city +"\">");
				html.append(city);
				html.append("<ul>");
				city_name=city;
				end_county_name=county;
				if(!county.equals(county_name)){
					html.append("<li rel=\""+ county +"\"> ");
					html.append(county);
					html.append("<ul>");
					county_name=county;
				//	html.append("<li rel=\""+ area +"\">"+area+"</li>");
				}else{
				//	html.append("<li rel=\""+ area +"\">"+area+"</li>");
				}
				if(i==count-1){
					html.append("</ul></li></ul></li>");
				}
			}else{
				if(!county.equals(end_county_name)){
					html.append("</ul></li>");
					end_county_name=county;
				}
				if(!county.equals(county_name)){
					html.append("<li rel=\""+ county +"\"> ");
					html.append(county);
					html.append("<ul>");
					county_name=county;
					//html.append("<li rel=\""+ area +"\">"+area+"</li>");
				}else{
					//html.append("<li rel=\""+ area +"\">"+area+"</li>");
				}
				if(i==count-1){
					html.append("</ul></li></ul></li>");
				}
			}
		}

		return html.toString();
	}

	@Override
	public String getCityPositionList(Map<String, String> map) {
		List<Map<String,String>> list=null;
		try {
			list=dao.queryCityPositionList(map);
		} catch (Exception e) {
			log.error("查询地区下基站列表出错="+e.getMessage());
			return "";
		}
		int s=list.size();
		StringBuffer html=new StringBuffer();
		Map<String,String> rmap=null;
		for(int i=1;i<=s;i++){
			rmap=list.get(i-1);
			if((i-1)%7==0){
				html.append("<tr>");
			}
			html.append("<td><input type=\"checkbox\" name=\"posiCheckbox\" " +
					"value=\""+rmap.get("bs_name")+"\" style=\"margin-right:5px;\"/>"+rmap.get("bs_name")+"</td>");
			if(i%7==0||i==s){
				html.append("</tr>");
			}
		}

		return html.toString();
	}

	@Override
	public boolean subCheckedPosition(Map<String, String> map) {

//		try {
//			dao.insertBsTrigger(map.get("activeCode"));
//		} catch (Exception e) {
//			return false;
//		}
//		return true;
		map.put("trigger_type", "3");//基站信息
		//////////////////////
		String bs_type=map.get("bs_type");//基站类型  拥有导航查询
		String [] bs=bs_type.split(",");
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Map<String,String> m=null;
		String city=map.get("cityName");
		if("1".equals(city))city="四川省";
		for (String string : bs) {
			m=new HashMap<String, String>();
			m.put("activeCode", map.get("activeCode"));
			m.put("bs_type", city+","+string);
			m.put("userId", map.get("userId"));
			list.add(m);
		}

		int i=0;
		int j=0;
		try {
			j=dao.insertCheckedPositionType(list);
			i=dao.insertCheckedPosition(map);
		} catch (Exception e) {
			log.info("插入基站信息出错="+e.getMessage());
		}
		if(i>0&&j>0){
			return true;
		}
		return false;
	}

	@Override
	public Map<String, String> getCheckedPosition(Map<String, String> map) {
		try {
			map=dao.queryCheckedPosition(map);
		} catch (Exception e) {
			log.info("查询以选取的基站出错="+e.getMessage());
			map=null;
		}
		return map;
	}

	@Override
	public boolean deleteTriggerMs(Map<String, String> map) {


		return false;
	}

	@Override
	public String getTriggerInfoMs(String activeCode) {
		List<Map<String,String>> list=null;
		try {
			list=dao.queryTriggerInfoMs(activeCode);
		} catch (Exception e) {
			log.error("查询触发条件信息出错="+e.getMessage());
		}
		String rems="";
		String keyWord="";
		String app="";
		String wz ="";
		for (Map<String, String> map : list) {
			String type=map.get("trigger_type");
			if(type.equals("1")){
				keyWord=map.get("trigger_ms");
			}else if(type.equals("2")){
				app=map.get("trigger_ms");
			}else if(type.equals("3")){
				wz=map.get("trigger_ms");
			}
		}
		rems=keyWord+"&"+app+"&"+wz;

		return rems;
	}

	@Override
	public List<Map<String,String>> getAppList() {
		try {
			return dao.queryAppList();
		} catch (Exception e) {
			log.error("查询app类型出错="+e.getMessage());
			return null;
		}

	}

	@Override
	public Map<String, String> getTypeAppList(String typeid) {
		List<Map<String, String>> list = null;
		try {
			list = dao.getTypeAppList(typeid);
		} catch (Exception e) {
			log.error("获取app类型"+typeid+"出错="+e.getMessage());
		}
		Map<String,String> reMap=new HashMap<String,String>();
		reMap.put("0", "");//自由app
		reMap.put("1", "");//非自由app
		for (Map<String, String> map : list) {
			reMap.put(map.get("app_type"), map.get("appinfo")+"&"+reMap.get(map.get("app_type")));
		}
		if(reMap.get("0").equals("")){
			reMap.put("0", "N");
		}
		if(reMap.get("1").equals("")){
			reMap.put("1", "N");
		}


		return reMap;
	}

	@Override
	public boolean deleteSetProperty(Map<String, String> map) {
		map.put("createTime", DateUtil.getNowDateStr());
		if(map.get("titleType").equals("1")){
			try {
				int i=dao.deleteTriggerMs(map);
				if(i>0){
					if(map.get("triggerType").equals("0")){//针对于基站信息需要删除详细基站信息
						String[] bsinfo=map.get("triggerName").split(",");
						map.put("bcityName", bsinfo[0]);
						map.put("bbsType", bsinfo[1]);
						i=dao.deleteTriggerBsInfo(map);
						if(i>0)return true;
					}
					return true;
				}
			} catch (Exception e) {
				log.error("删除触发条件出错="+e.getMessage()+"参数="+map);
			}
		}else if(map.get("titleType").equals("2")){
			try {
				int i=dao.deleteUserGroup(map);
				if(i>0){
					return true;
				}
			} catch (Exception e) {
				log.error("删除用户群/过滤用户群出错="+e.getMessage()+"参数="+map);
			}

		}else if(map.get("titleType").equals("3")){
			try {
				int i=dao.deleteSendMs(map);
				if(i>0){
					return true;
				}
			} catch (Exception e) {
				log.error("删除推送设置出错="+e.getMessage()+"参数="+map);
			}
		}
		return false;
	}

	@Override
	public String searchBsType(Map<String, String> map) throws Exception {
		StringBuffer tables=new StringBuffer("N");
		List<String> typeList=dao.searchBsType(map);
		String typs="";
		for (int i = 0; i < typeList.size(); i++) {
			typs=typeList.get(i);
			if(i==0||i%3==0){
				tables.append("<tr>");
			}
			tables.append("<td><input type=\"checkbox\" name=\"posiCheckbox\" value=\""+typs+"\" style=\"margin-right:5px;\"/>"+typs+"</td>");

			if(i==typeList.size()-1||(i+1)%3==0){
				tables.append("</tr>");
			}
		}
		return tables.toString();
	}

	
	
	@Override
	public int getCheckedAppCount(Map<String, String> map) throws Exception {
		return dao.getCheckedAppCount(map);
	}
}
