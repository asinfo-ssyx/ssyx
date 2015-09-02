package com.asiainfo.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.dao.MqMessageDAO;
import com.asiainfo.dao.UserGroupInfoDAO;

@Service("netServiceThread")
public class NetServiceThread {
	public Logger log = Logger.getLogger(NetServiceThread.class);
	@Autowired
	private DataBaseJdbc db2;
	@Autowired
	private UserGroupInfoDAO userDao;
	@Autowired
	private MqMessageDAO dao;

	public void doWork(){
		List<Map<String,Object>> activeList =queryNetServiceActive();
		log.info(" webservice 查询到活动数："+activeList.size());
		for (Map<String, Object> map : activeList) {
			putCodeName(map);
			if(insertTable(map)){
				insertLog(map);
			}
		}
	}

	public boolean insertTable(Map<String,Object> map){
		db2.getJdbcParam();
		Connection conn=db2.get134DB2Connection();
		ResultSet set=null;
		Statement stmt=null;
		String tableCode=(String) map.get("user_group");
		String gettableName="select list_table_name from SCCOC.CI_CUSTOM_LIST_INFO " +
				"where CUSTOM_GROUP_ID ='"+map.get("user_group")+"' order by DATA_DATE desc fetch first rows only";
		String insertSql="";
		try {
			stmt = conn.createStatement();
			String tablename="";
			if(tableCode.trim().equals(map.get("active_code").toString().trim())){
				tablename="aiapp."+tableCode;
			}else if(map.get("dbs")!=null
					&&!map.get("dbs").toString().trim().equals("")){//判断数据源字段是否为空，作为判断数据集市流程
				tablename="aiapp.ssyx_"+map.get("active_code");
			}else{
				set=stmt.executeQuery(gettableName);
				while(set.next()) {
					tablename="SCCOC."+set.getString("list_table_name");
				}
			}


			insertSql="insert into  aiapp.CUSTOMER_SEND_INFO(phone_no,ACTIVE_CODE,start_time,send_no," +
					" PROD_PRIC_ID,PROD_PRIC_NAME,send_type,active_name,end_time)  select a.phone_no, " +
						 "'"+map.get("active_code")+"',"+
						 "'"+map.get("begin_time")+"',"+
						 "'"+map.get("send_no")+"'," +
						 "'"+map.get("bus_code")+"', " +
						 "'"+map.get("codeName")+"', " +
						 "'"+map.get("send_type")+"', " +
						 "'"+map.get("active_name")+"', " +
						 "'"+map.get("end_time")+"' " +
					""+" from "+tablename+" a ";
			int i=stmt.executeUpdate(insertSql);
			log.info(" 插入webservice表用户数："+i);
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			log.error("连接134 出错："+e.getMessage());
		}finally {
				try {
					if(set!=null)set.close();
					if(stmt!=null)stmt.close();
					if(conn!=null)conn.close();
				} catch (Exception e1) {
					log.error("关闭134连接出错："+e1.getMessage());
				}
		}
		return false;
	}

	public List<Map<String,Object>> queryNetServiceActive(){
		List<Map<String,Object>> activeList=null;
		Map<String,String> parm=new HashMap<String,String>();
		parm.put("nowTime", DateUtil.getNowDateStr());
		try {
			activeList=userDao.queryNetServiceActive(parm);
		} catch (Exception e) {
			log.error("查询网厅营销活动信息出错："+e.getMessage());
		}
		return activeList;
	}


	public void insertLog(Map<String,Object> map){
		map.put("create_time", DateUtil.getNowDateStr());
		try {
			dao.saveWebServiceLog(map);
		} catch (Exception e) {
			log.error(" 插入 webservice 日志表出错："+e.getMessage());
		}
	}

	public void putCodeName(Map<String,Object> map){
		String st=map.get("send_type").toString();
		if("5".equals(st)){
			map.put("code_type", "2");
		}else{
			map.put("code_type", "1");
		}
		try {
			String codeName=dao.queryWebServiceCodeName(map);
			map.put("codeName", codeName);
		} catch (Exception e) {
		}
	}


}
