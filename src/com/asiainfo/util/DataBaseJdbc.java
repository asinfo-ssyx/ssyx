package com.asiainfo.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
@Service
public class DataBaseJdbc {
	public static Logger log = Logger.getLogger(DataBaseJdbc.class);
	public static Map<String,String> db2map=new HashMap<String,String>();


	public void getJdbcParam(){
		Properties prop = new Properties();
        InputStream in = null;
			in = this.getClass().getClassLoader().getResourceAsStream("dataBase.properties");
        try {
            prop.load(in);
            log.info("propproppropprop="+prop);
            db2map.put("url", prop.getProperty("db2Url").trim());
            db2map.put("driver", prop.getProperty("db2Driver").trim());
            db2map.put("user", prop.getProperty("db2User").trim());
            db2map.put("password", prop.getProperty("db2PassWord").trim());

            db2map.put("134url", prop.getProperty("134db2Url").trim());
            db2map.put("134driver", prop.getProperty("134db2Driver").trim());
            db2map.put("134user", prop.getProperty("134db2User").trim());
            db2map.put("134password", prop.getProperty("134db2PassWord").trim());

            db2map.put("228url", prop.getProperty("228db2Url").trim());
            db2map.put("228driver", prop.getProperty("228db2Driver").trim());
            db2map.put("228user", prop.getProperty("228db2User").trim());
            db2map.put("228password", prop.getProperty("228db2PassWord").trim());
            
            db2map.put("53url", prop.getProperty("53db2Url").trim());
            db2map.put("53driver", prop.getProperty("53db2Driver").trim());
            db2map.put("53user", prop.getProperty("53db2User").trim());
            db2map.put("53password", prop.getProperty("53db2PassWord").trim());

            db2map.put("118url", prop.getProperty("118mysqlUrl").trim());
            db2map.put("118driver", prop.getProperty("118mysqlDriver").trim());
            db2map.put("118user", prop.getProperty("118mysqlUser").trim());
            db2map.put("118password", prop.getProperty("118mysqlPassWord").trim());
            /*s数据集市数据库*/
            db2map.put("68url", prop.getProperty("68db2Url").trim());
            db2map.put("68driver", prop.getProperty("68db2Driver").trim());
            db2map.put("68user", prop.getProperty("68db2User").trim());
            db2map.put("68password", prop.getProperty("68db2PassWord").trim());

            db2map.put("72url", prop.getProperty("72db2Url").trim());
            db2map.put("72driver", prop.getProperty("72db2Driver").trim());
            db2map.put("72user", prop.getProperty("72db2User").trim());
            db2map.put("72password", prop.getProperty("72db2PassWord").trim());

            db2map.put("73url", prop.getProperty("73db2Url").trim());
            db2map.put("73driver", prop.getProperty("73db2Driver").trim());
            db2map.put("73user", prop.getProperty("73db2User").trim());
            db2map.put("73password", prop.getProperty("73db2PassWord").trim());
            //成都分公司数据导入
            db2map.put("68cfurl", prop.getProperty("68cfdb2Url").trim());
            db2map.put("68cfdriver", prop.getProperty("68cfdb2Driver").trim());
            db2map.put("68cfuser", prop.getProperty("68cfdb2User").trim());
            db2map.put("68cfpassword", prop.getProperty("68cfdb2PassWord").trim());
        } catch (Exception e) {
        	log.error("读取数据库配置文件出错="+e.getMessage());
        }
        log.info("sss"+db2map);
	}
	public static  Connection getDB2Connection(){
		log.info("jintufangf get“vgop”DB2Connection");
		new DataBaseJdbc().getJdbcParam();
		Connection conn=null;
		try {
			Class.forName(db2map.get("driver")).newInstance();
			conn = DriverManager.getConnection(db2map.get("url"),db2map.get("user"),db2map.get("password"));
		} catch (Exception e) {
			log.info("连接db2数据库出错="+e.getMessage());
		}
		return conn;
	}

	public static  Connection get134DB2Connection(){
		log.info("jintufangf get134DB2Connection");
		new DataBaseJdbc().getJdbcParam();
		Connection conn=null;
		try {
			Class.forName(db2map.get("134driver")).newInstance();
			conn = DriverManager.getConnection(db2map.get("134url"),db2map.get("134user"),db2map.get("134password"));
		} catch (Exception e) {
			log.info("连接228 db2数据库出错="+e.getMessage());
		}
		return conn;
	}
	public static  Connection get53DB2Connection(){
		log.info("jintufangf get53DB2Connection");
		new DataBaseJdbc().getJdbcParam();
		Connection conn=null;
		try {
			Class.forName(db2map.get("53driver")).newInstance();
			conn = DriverManager.getConnection(db2map.get("53url"),db2map.get("53user"),db2map.get("53password"));
		} catch (Exception e) {
			log.info("连接53 db2数据库出错="+e.getMessage());
		}
		return conn;
	}
	public static  Connection get228DB2Connection(){
		Connection conn=null;
		try {
		log.info("jintufangf get228DB2Connection");
		log.info("db2mapdb2map:"+db2map);
		new DataBaseJdbc().getJdbcParam();
			Class.forName(db2map.get("228driver")).newInstance();
			conn = DriverManager.getConnection(db2map.get("228url"),db2map.get("228user"),db2map.get("228password"));
		} catch (Exception e) {
			log.error("连接228 db2数据库出错="+e.getMessage());
		}
		System.out.println("connnnnnnnnnnnnnnnnnnnnnnn:"+conn);
		return conn;
	}

	public static  Connection get118MysqlConnection(){
		log.info("获取 118 mq配置数据库 连接");
		new DataBaseJdbc().getJdbcParam();
		Connection conn=null;
		try {
			Class.forName(db2map.get("118driver")).newInstance();
			conn = DriverManager.getConnection(db2map.get("118url"),db2map.get("118user"),db2map.get("118password"));
		} catch (Exception e) {
			log.info("连接118  mysql数据库出错="+e.getMessage());
		}
		return conn;
	}


	public static  Connection get68DB2Connection(){
		log.info("jintufangf get68DB2Connection");
		new DataBaseJdbc().getJdbcParam();
		Connection conn=null;
		try {
			Class.forName(db2map.get("68driver")).newInstance();
			conn = DriverManager.getConnection(db2map.get("68url"),db2map.get("68user"),db2map.get("68password"));
		} catch (Exception e) {
			log.info("连接68 db2数据库出错="+e.getMessage());
		}
		return conn;
	}

	public static  Connection get72DB2Connection(){
		log.info("jintufangf get72DB2Connection");
		new DataBaseJdbc().getJdbcParam();
		Connection conn=null;
		try {
			Class.forName(db2map.get("72driver")).newInstance();
			conn = DriverManager.getConnection(db2map.get("72url"),db2map.get("72user"),db2map.get("72password"));
		} catch (Exception e) {
			log.info("连接72 db2数据库出错="+e.getMessage());
		}
		return conn;
	}

	public static  Connection get73DB2Connection(){
		log.info("jintufangf get73DB2Connection");
		new DataBaseJdbc().getJdbcParam();
		Connection conn=null;
		try {
			Class.forName(db2map.get("73driver")).newInstance();
			conn = DriverManager.getConnection(db2map.get("73url"),db2map.get("73user"),db2map.get("73password"));
		} catch (Exception e) {
			log.info("连接73 db2数据库出错="+e.getMessage());
		}
		return conn;
	}
	//成都分公司
	public static  Connection get68cfDB2Connection(){
		log.info("jintufangf get68DB2Connection");
		new DataBaseJdbc().getJdbcParam();
		Connection conn=null;
		try {
			Class.forName(db2map.get("68cfdriver")).newInstance();
			conn = DriverManager.getConnection(db2map.get("68cfurl"),db2map.get("68cfuser"),db2map.get("68cfpassword"));
		} catch (Exception e) {
			log.info("连接68 db2数据库出错="+e.getMessage());
		}
		return conn;
	}
	public static Map<String,Object> getPermissionUserData(String userId){
		Map<String,Object> map=null;
		
/* !!!!!!!!!!!!!!  生产上线   !!!!!!!!!!!!!!!!!!!!!!!*/
//		Connection conn=null;
//		Statement stmt=null;
//		ResultSet set=null;
//		try {
//			String sql=" select * from suite.user_user where USERID='"+userId+"' fetch first 1 rows only";
//			conn=DataBaseJdbc.get228DB2Connection();
//			log.info("con00000000000000000000000n:"+conn);
//			stmt=conn.createStatement();
//			set = stmt.executeQuery(sql);
//
//			while(set.next()) {
//				map=new HashMap<String,Object>();
//				log.info("set.getString(CITYID):"+set.getString("CITYID"));
//				map.put("cityId", set.getString("CITYID").replaceAll("s",""));
//				map.put("userId", userId);
//			}
//		} catch (Exception e) {
//			log.error("查询28用户报错:"+e.getMessage());
//		}finally{
//			try {
//				if(set!=null)set.close();
//				if(stmt!=null)stmt.close();
//				if(conn!=null)conn.close();
//			} catch (Exception e) {
//			}
//		}
		
// !!!!!!!!!!!!!!!开发调试!!!!!!!!!!!!!!!!!!!!!!!
		if(map==null){
			map=new HashMap<String,Object>();

			map.put("userId", userId);
			if("yangsy".equals(userId)){
				map.put("cityId", "999");
			}else {
				map.put("cityId", "3");
			}
		}
		return map;
	}

}
