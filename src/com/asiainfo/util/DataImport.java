package com.asiainfo.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dataImport")
public class DataImport {
	public static Logger log = Logger.getLogger(DataImport.class);
	@Autowired
	private DataBaseJdbc db2;

	public void Data228To134(){
		db2.getJdbcParam();
		Connection db228=db2.get228DB2Connection();
		Connection db134=db2.get134DB2Connection();
		String userTableName="VGOP_DWD.DWD_AGAIN_NETWORK_USER_"+DateUtil.getNowDateMinusDayYYYYMMDD(2);

		try {
			Statement stmt134=db134.createStatement();
			Statement stmt228=db228.createStatement();
			ResultSet set228=stmt228.executeQuery("select * from "+userTableName);
				try{
					stmt134.executeUpdate("create table "+userTableName+
						" (IMEI VARCHAR(25) ," +
						"  PHONE_NO VARCHAR(40)," +
						"  BUSI_NAME VARCHAR(50)," +
						"  PROD_NAME VARCHAR(100)," +
						"  PROD_PRCID VARCHAR(20)," +
						"  CONTENT VARCHAR(1000)," +
						"  ORDER_NUM VARCHAR(8) )");
				}catch(Exception e) {
					log.info("134 创建表出错："+e.getMessage());
				}
			String insertSql="";
			while(set228.next()) {
				  insertSql="insert into "+userTableName+" values "+
						  "('"+set228.getString("IMEI")+"'," +
							"'"+set228.getString("PHONE_NO")+"'," +
							"'"+set228.getString("BUSI_NAME")+"'," +
							"'"+set228.getString("PROD_NAME")+"'," +
							"'"+set228.getString("PROD_PRCID")+"'," +
							"'"+set228.getString("CONTENT")+"'," +
							"'"+set228.getString("ORDER_NUM")+"'" +
							")";
				  //log.info("insertSqlinsertSql:"+insertSql);
				  stmt134.executeUpdate(insertSql);
			}
			log.info("---------------------228 import 134 success--------------------");
		} catch (Exception e) {
			log.error("---------------------228 import 134 erro----------------------："+e.getMessage());
		}finally{
			try {
				db134.close();
				db228.close();
			} catch (SQLException e) {
				log.error("------------close db connection error-----------:"+e.getMessage());
			}
		}
	}

}
