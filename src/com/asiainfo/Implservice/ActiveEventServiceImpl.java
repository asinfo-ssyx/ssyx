package com.asiainfo.Implservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asiainfo.Iservice.ActiveEventService;
import com.asiainfo.dao.ActiveEventDAO;
import com.asiainfo.util.DateUtil;
@Service
public class ActiveEventServiceImpl implements ActiveEventService{
	public Logger log = Logger.getLogger(ActiveEventServiceImpl.class);
	@Autowired
	private ActiveEventDAO dao;
	@Override
	public List<Map<String, String>> getEventList(String type) throws Exception {
		return dao.queryEventList(type);
	}

	@Override
	public String getEventNameList(Map<String,String> map)
			throws Exception {
		List<Map<String,Object>> list=dao.queryEventNameList(map);
		StringBuffer el=new StringBuffer();
		if(list!=null&&list.size()>0){
			for (Map<String, Object> map2 : list) {
				el.append(map2.get("event_name")+"("+map2.get("cycle")+")"
						  +"#"+map2.get("event_no")
						  +"#"+map2.get("event_desc")+"Y_Y");
			}

		}
		return el.toString();
	}

	@Override
	public String subCheckedEvent(Map<String, String> map) throws Exception {
		int i=dao.queryEventType(map);
		if(i==0){
			dao.insertEventType(map);
		}else{
			return "R";
		}
		return "Y";
	}

	@Override
	public String queryCheckedEventType(String activeCode)
			throws Exception {
		return dao.queryCheckedEventType(activeCode);
	}

	@Override
	public String queryUserGroup(String code) throws Exception {
		return dao.queryUserGroup(code);
	}

	@Override
	public String deleteActiveEventTypeUserGroup(String acticeCode)
			throws Exception {
		Map<String,String> map=new HashMap<String,String>();
		map.put("createTime", DateUtil.getNowDateStr());
		map.put("activeCode", acticeCode);
		dao.deleteActiveEventTypeUserGroup(map);
		return "Y";
	}

	@Override
	public List<Map<String, Object>> queryWebClassList() throws Exception {
		return dao.queryWebClassList();
	}

	@Override
	public String queryWebListByCid(String classId) throws Exception {
		List<Map<String,Object>> list=dao.queryWebListByCid(classId);
		StringBuffer sb=new StringBuffer("");
		if(list!=null && list.size()>0){
			for (Map<String, Object> map : list) {
				sb.append(map.get("site_category_id")+","+map.get("webname")+"&");
			}
		}
		return sb.toString();
	}

	@Override
	public boolean insertCheckedWeb(Map<String, String> map) throws Exception {
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
			m.put("trigger_type", "5");//web
			m.put("trigger_code", ap[1]);
			log.info(ap[0]+"|"+ap[1]);
			list.add(m);
		}
		int i=0;
		try {
			i=dao.insertCheckedWebs(list);
		} catch (Exception e) {
			log.info("插入web信息出错="+e.getMessage());
		}
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public String queryCheckedWebList(Map<String, String> map) throws Exception {
		return dao.querySubTriggerMsListByType(map);
	}

	@Override
	public int getCheckedWebCount(Map<String, String> map) throws Exception {
		return dao.getCheckedWebCount(map);
	}

}
