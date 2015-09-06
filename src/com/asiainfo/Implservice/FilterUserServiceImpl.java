package com.asiainfo.Implservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.Iservice.IFilterUserService;
import com.asiainfo.dao.FilterDAO;
@Service
public class FilterUserServiceImpl implements IFilterUserService{

	public Logger log = Logger.getLogger(FilterUserServiceImpl.class);
	@Autowired
	public FilterDAO dao;

	@Override
	public List<Map<String, String>> searchFilterUserGroup(
			Map<String, String> map) {
		try {
			return dao.queryFilterUserGroup(map);
		} catch (Exception e) {
			log.error("查询过滤客户群出错="+e.getMessage());
			return null;
		}
	}

	@Override
	public Boolean subFilterUserGroup(Map<String, String> map) {
		// TODO Auto-generated method stub
		String s=map.get("fuserGroup");
		String activeCode=map.get("activeCode");
		String userId=map.get("userId");
		String [] ug=s.split(",");
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for (String string : ug) {
			Map<String,String> m=new HashMap<String,String>();
			m.put("activeCode", activeCode);
			m.put("checked", string);
			m.put("userId", userId);
			list.add(m);
		}

		return true;
	}

	@Override
	public String getSubUserGroup(String activeCode) {
		String rms="";
		try {
			List<String> list=dao.querySubUserGroup(activeCode);
			for (String string : list) {
				rms+=string+",";
			}
		} catch (Exception e) {
			log.error("查询以选择的用户群出错="+e.getMessage());
		}
		return rms;
	}

	@Override
	public String getSubFilterUserGroup(String acitveCode) {
		String rms="";
		try {
			List<String> list =dao.querySubFilterUserGroup(acitveCode);
			log.info("querylist="+list);
			for (String string : list) {
				rms+=string+",";
			}
		} catch (Exception e) {
			log.error("查询以选择的过滤用户群出错="+e.getMessage());
		}
		log.info("rmsrmsrmsrmsrmsrmsrmsrmsrms="+rms);
		return rms;
	}

}
