package com.asiainfo.Implservice;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.Iservice.BlackListService;
import com.asiainfo.dao.BlackListDAO;
@Service
public class BlackListServiceImpl implements BlackListService {
	
	public Logger log = Logger.getLogger(BlackListServiceImpl.class);
	
	@Autowired
	public  BlackListDAO blackListDAO;
	
	@Override
	public void insertUserToBlackList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		blackListDAO.insertUserToBlackList(map);
	}

	@Override
	public void  deleteUserFromBlackList(String phoneNumber) throws Exception {
		// TODO Auto-generated method stub
		blackListDAO.deleteUserFromBlackList(phoneNumber);
	}

	@Override
	public Map<String, Object> queryBlackList(String phoneNumber)
			throws Exception {
		// TODO Auto-generated method stub	
		return blackListDAO.queryBlackList(phoneNumber);
	}

	@Override
	public List<Map<String, Object>> showBlackList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		int beginNum;
		int endNum ;
		int showNum=(Integer)map.get("showNum");
		int pageNum=(Integer)map.get("pageNum");
		beginNum=(pageNum-1)*showNum;
		endNum=showNum;
		map.put("beginNum", beginNum);
		map.put("endNum", endNum);
		return blackListDAO.showBlackList(map);
	}

	@Override
	public Map<String, Object> queryBlackListCount(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		Long count = 0l;
		try{
			Map<String, Object> map1=blackListDAO.queryBlackListCount(map);
			count=(Long)map1.get("pageCount");
			if(count==0){
				map.put("pageCount", 1);
				return map;
			}
		}catch(Exception e){
			log.info("blackListPageCount"+e.getMessage());
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

}
