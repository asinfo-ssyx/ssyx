package com.asiainfo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class DataMarketIm134DB implements Runnable{

	private Map<String,String> map;

	public DataMarketIm134DB(Map<String,String> m){
		this.map=m;
	}

	@Override
	public void run() {
		DataBaseJdbc db=new DataBaseJdbc();
		db.getJdbcParam();
		String dbType=map.get("rule_code").trim();
		Connection sjjs=null;
		if(dbType.equals("68")){
			sjjs=db.get68DB2Connection();
		}else if(dbType.equals("72")){
			sjjs=db.get72DB2Connection();
		}else if(dbType.equals("73")){
			sjjs=db.get73DB2Connection();
		}

		Connection c134=db.get134DB2Connection();
		PreparedStatement pstmt = null;
		Statement stmt=null;
		ResultSet set=null;
		String tableName=map.get("search_sql");
		String createTable="create table aiapp.ssyx_"+map.get("active_code")+" (phone_no varchar(20) ) ";
		try{
			stmt = c134.createStatement();
			stmt.executeUpdate(createTable);

			String sql="select * from "+tableName+" WITH UR";
			pstmt=sjjs.prepareStatement(sql);
			set=pstmt.executeQuery();
			StringBuffer insertSql=new StringBuffer(" insert into aiapp.ssyx_"+map.get("active_code")+ " values ");
			int i=0;
			while (set.next()) {
				insertSql.append(" ("+set.getString("phone_no")+") ");
				++i;
				if(set.isLast()|| i==200){
					stmt.executeUpdate(insertSql.toString());
					i=0;
					insertSql=new StringBuffer(" insert into aiapp.ssyx_"+map.get("active_code")+ " values ");
					continue;
				}
				insertSql.append(",");
			}
		}catch (Exception e) {
			e.getStackTrace();
		}finally{
			try{
				if(set!=null) set.close();
				if(pstmt!=null)pstmt.close();
				if(sjjs!=null) sjjs.close();
				if(stmt!=null) stmt.close();
				if(c134!=null) c134.close();
			}catch(Exception e){
				e.getStackTrace();
			}
		}
	}

}
