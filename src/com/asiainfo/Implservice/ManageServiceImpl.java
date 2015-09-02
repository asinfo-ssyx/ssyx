package com.asiainfo.Implservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.Iservice.IManageService;
import com.asiainfo.dao.ManageDAO;
import com.asiainfo.util.DataBaseJdbc;

@Service
public class ManageServiceImpl implements IManageService{
	public Logger log = Logger.getLogger(ManageServiceImpl.class);

	@Autowired
	private ManageDAO dao;

	@Override
	public Map<String,Map<String,Object>> queryCitySendCOunt(Map<String, Object> map) throws Exception {
		//List<Map<String, Object>> list=dao.getSendCityNum();
		List<Map<String, Object>>  list=getSendCityNum(null);
		Map<String,Map<String,Object>> cityMap=new HashMap<String,Map<String,Object>>();
		Map<String,Object> cm=null;
		for (Map<String, Object> city : list) {
			if(cityMap.get(city.get("city_name").toString())!=null){
				cityMap.get(city.get("city_name").toString()).put(city.get("send_day").toString(), city.get("send_count"));
				cityMap.get(city.get("city_name").toString()).put(city.get("send_day").toString()+"cid", city.get("cid").toString());
			}else{
				cm=new HashMap<String,Object>();
				cm.put(city.get("send_day").toString(), city.get("send_count"));
				cm.put(city.get("send_day").toString()+"cid", city.get("cid").toString());
				cityMap.put(city.get("city_name").toString(), cm);
			}
		}
		return cityMap;
	}
	@Override
	public Map<String, Object> queryCitySendCountByCityId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		if(map !=null){
		 List<Map<String, Object>>  list=getSendCityNum(map);
		 if(list.size()==0||list==null)
		     return null;
		 else return list.get(0);
		}
		else return null;
	}
	private List<Map<String,Object>> getSendCityNum(Map<String,Object> map1){
		String sql="";
		if(map1!=null){
			sql="SELECT b.city_name,a.* FROM " +
					" aiapp.city_sendms_scale a LEFT JOIN aiapp.vgop_city_info b ON a.city_id=b.city_id WHERE a.status=1 and a.city_id='"+map1.get("cityId")+"' ";
		}
		else sql="SELECT b.city_name,a.* FROM " +
				" aiapp.city_sendms_scale a LEFT JOIN aiapp.vgop_city_info b ON a.city_id=b.city_id WHERE a.status=1 ";
		Connection conn=DataBaseJdbc.get134DB2Connection();;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);		
			rs = pstmt.executeQuery();			
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			Map<String,Object> map=null;
			while(rs.next()){
				map=new HashMap<String,Object>();
				map.put("city_name", rs.getString("city_name"));
				map.put("send_day", rs.getString("send_day"));
				map.put("send_count", rs.getString("send_count"));
				map.put("cid", rs.getString("cid"));
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			System.out.println("查询134数据库，地市短信配置查询出错："+e.getMessage());
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				log.error("关闭134连接出错："+e.getMessage());
			}
		}
		return null;

	}

	@Override
	public boolean addCitySendPara(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getProductCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addProductInfo(Map<String,String> map) {
		try {
			log.info("priceCode :"+map.get("priceCode"));
			int i=dao.addProductInfo(map);
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			log.error("add 产品资费代码出错："+e.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateProductInfo(Map<String, Object> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String updateCityCount(Map<String, String> map) throws Exception {
//		int i=dao.updateCityCount(map);
		int i=updateCityScale(map);
		if(i==1){
			return "Y";
		}else{
			return "N";
		}
	}

	private int updateCityScale(Map<String,String> map){
		String sql="update	aiapp.city_sendms_scale set send_count="+map.get("sendCount")+
				   "	where cid="+map.get("scaleId");
		Connection conn=DataBaseJdbc.get134DB2Connection();;
		Statement pstmt = null;
		try {
			pstmt = conn.createStatement();
			return pstmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("更新134数据库，地市短信配置查询出错："+e.getMessage());
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				log.error("关闭134连接出错："+e.getMessage());
			}
		}
		return 0;
	}

	



}
