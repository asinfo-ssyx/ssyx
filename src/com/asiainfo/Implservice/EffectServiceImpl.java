package com.asiainfo.Implservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.Iservice.EffectService;
import com.asiainfo.dao.EffectDAO;

@Service
public class EffectServiceImpl implements EffectService{
	@Autowired
	private EffectDAO dao;

	@Override
	public List<Map<String,Object>> queryEffectList(Map<String, Object> map)
			throws Exception {
		int beginNum;
		int endNum;
		int showNum=(Integer)map.get("showNum");
		int pageNum=(Integer)map.get("pageNum");
		beginNum=(pageNum-1)*showNum;
		endNum=showNum;
		map.put("beginNum", beginNum);
		map.put("endNum", endNum);
		List<Map<String, Object>> list=dao.queryEffectActiveList(map);
		return list;
	}


	@Override
	public Map<String, Object> getEffectCount(Map<String,Object> map)
			throws Exception {
		Long count = 0l;
		try{
			Map<String, Object> map1=dao.queryEffectCount(map);
			count=(Long)map1.get("pageCount");
			if(count==0){
				map.put("pageCount", 1);
				return map;
			}
		}catch(Exception e){
		}

		int showNum=(Integer)map.get("showNum");
		Long pageCount=0l;
		if( count%showNum != 0){
			pageCount=(count/showNum)+1;
		}else{
			pageCount=count/showNum;
		}
		map.put("pageCount", pageCount);

		return map;
	}


	@Override
	public Map<String,List<Map<String,Object>>> queryActiveCountList(
			Map<String, Object> map) throws Exception {
		List<Map<String,Object>> scenes=dao.queryActiveScene();
		List<Map<String,Object>> coutns=dao.queryActiveInfo(map);

		List<Map<String,Object>> countList=new ArrayList<Map<String,Object>>();
		Map<String,Map<String,Object>> cityMap=new HashMap<String,Map<String,Object>>();
		Map<String,Object> city=null;

		String cityName="";
		String sceneId="";
		String cSceneId="";
		long aCount=0;
		for (Map<String, Object> scene : scenes) {
			sceneId=scene.get("scene_id").toString();
			for (Map<String, Object> count : coutns) {
				cityName=count.get("city_name").toString().trim();
				city=cityMap.get(cityName);
				if(city==null){
					city=new HashMap<String,Object>();//创建一个地市map
					cityMap.put(cityName, city);
				}
				if(count.get("acitve_scene")!=null){
					cSceneId=count.get("acitve_scene").toString();
				}else{
					cSceneId="0";
				}
				if(count.get("con")==null){//没有创建过活动的地市 con是null
					aCount=0l;
				}else{
					aCount =(Long) count.get("con");
				}
				if(sceneId.equals(cSceneId)){
					city.put(sceneId, aCount);
				}else{
					if(city.get(sceneId)==null)
					city.put(sceneId, 0l);
				}

			}
		}


		Set<String> cityKey=cityMap.keySet();
		Map<String,Object> qs=null;
		Map<String,Object> m=null;
		for (String string : cityKey) {
			m=cityMap.get(string);
			//添加合计
			activeCount(m,scenes);
			m.put("cityName", string);
			if(string.equals("全省")){
				qs=m;
				continue;
			}
			countList.add(m);
		}
		if(qs!=null){
			countList.add(qs);
		}

		long c=0;
		Map<String,Object> cMap=new HashMap<String,Object>();
		cMap.put("cityName", "合计");
		for (Map<String,Object> sm: scenes) {
			c=getScenceCount(sm.get("scene_id").toString(),countList);
			cMap.put(sm.get("scene_id").toString(), c);
		}
		cMap.put("cityCount", getScenceCount("cityCount",countList));
		countList.add(cMap);

		Map<String,List<Map<String,Object>>> rMap=new HashMap<String, List<Map<String,Object>>>();
		rMap.put("titleList", scenes);
		rMap.put("countList", countList);
		return rMap;
	}

	private void activeCount(Map<String,Object> m,List<Map<String,Object>> s){
		long count=0;
		for (Map<String, Object> map : s) {
			long cc=(Long)m.get(map.get("scene_id").toString());
			count+=cc;
		}
		m.put("cityCount", count);
	}

	private long getScenceCount(String key,List<Map<String,Object>> sList){
		long i=0;
		for (Map<String, Object> map : sList) {
			i+=(Long)map.get(key);
		}//m387
		return i;
	}


	@Override
	public Map<String, String> searchActiveSendInfo(String activeCode) {
		List<Map<String,Object>> list=null;
		try {
			list =dao.searchActiveSendInfo(activeCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> map=null;
		if(list!=null && list.size()>0){
			StringBuffer info=new StringBuffer("");
			for (Map<String, Object> m : list) {
				info.append("活动发送时间："+m.get("send_time")+",");
				info.append("当天重复数："+m.get("today_rnum")+",");
				info.append("7天重复数："+m.get("sevday_rnum")+",");
				info.append("超过地市配置数："+m.get("city_rnum")+" \n ");
			//	info.append("实际推送数："+m.get("real_send_count")+" ");
			}
			map=new HashMap<String,String>();
			map.put("infoMs", info.toString());
			return map;
		}

		return map;
	}

}


