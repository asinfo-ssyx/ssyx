package com.asiainfo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.dao.ActiveDAO;
import com.asiainfo.dao.EffectDAO;

@Service("effectActiveCountThread")
public class EffectActiveCountThread {
	public Logger log = Logger.getLogger(EffectActiveCountThread.class);
	@Autowired
	private ActiveDAO activeDao;
	@Autowired
	private EffectDAO effectDao;

	public static int runTime=0;

	public void doWork(){
		Connection conn=DataBaseJdbc.get134DB2Connection();;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql=" select active_code, count(1) smnum from " +
					" aiapp.send_sms_log where send_date=current date group by active_code  with ur";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Map<String,Object> map=new HashMap<String,Object>();
			while(rs.next()){
				map.put("activeCode", rs.getString("active_code"));
				map.put("send_count", rs.getLong("smnum"));
				System.out.println(map);
				updateEffectActive(map);
			}


		} catch (Exception e) {
			System.out.println("查询134数据库，活动短信发送量出错："+e.getMessage());
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				log.error("关闭134连接出错："+e.getMessage());
			}
		}
		addActiveSendInfo();
	}


	public void addActiveSendInfo(){
		//String sql="select * from aiapp.send_sms_fruit_log where  send_date<current date and active_code is not null";
		String sql="select * from aiapp.send_sms_fruit_log where send_date=current date and active_code is not null";
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
				map.put("active_code", rs.getString("active_code"));
				map.put("city_id", rs.getString("city_id"));
				map.put("sms_num", rs.getLong("sms_num"));
				map.put("today_rnum", rs.getString("today_rnum"));
				map.put("sevday_rnum", rs.getString("sevday_rnum"));
				map.put("city_rnum", rs.getString("city_rnum"));
				map.put("city_param", rs.getString("city_param"));
				map.put("now_city_send", rs.getString("now_city_send"));
				map.put("send_date", rs.getString("send_date"));
				map.put("send_time", rs.getString("send_time"));
				map.put("send_num", rs.getString("send_num"));
				list.add(map);
			}
			if(list.size()>0){
				insertActiveSendInfo(list);
			}
		} catch (Exception e) {
			System.out.println("查询134数据库，活动短信发送量出错："+e.getMessage());
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				log.error("关闭134连接出错："+e.getMessage());
			}
		}
	}


	public void insertActiveSendInfo(List<Map<String,Object>> list){
		try {
			effectDao.insertActiveSendInfo(list);
		} catch (Exception e) {
			log.error("插入活动发送信息出差："+e);
		}
	}


	public List<String> queryNowRunActive(String nowTime){
		List<String> list=new ArrayList<String>();
		try {
			list=activeDao.getNowRunActiveCode(nowTime);
		} catch (Exception e) {
			log.error("查询当前 统计 activeCode 出错："+e.getMessage());
		}
		return list;
	}

	public void insertEffectActive(Map<String,Object> map){
		try {
			effectDao.insertActiveEffect(map);
		} catch (Exception e) {
			log.error("插入活动统计表出错 :"+e.getMessage());
		}
	}

	public String isHaveEffectTable(String activeCode){
		try {
			int i= effectDao.isHaveActiveCode(activeCode);
			if(i>0){
				return "Y";
			}
		} catch (Exception e) {
			log.error("查询活动统计表 出错  activeCode 是否纯在："+e.getMessage());
			return "E";
		}
		return "N";
	}

	public void updateEffectActive(Map<String,Object> map){
		try {
			effectDao.updateActiveEffect(map);
		} catch (Exception e) {
			log.error("更新活动统计表出错："+e.getMessage());
		}

	}
}
