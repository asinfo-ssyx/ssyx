package com.asiainfo.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.asiainfo.dao.SearchPageDAO;
import com.asiainfo.util.DataBaseJdbc;
import com.asiainfo.util.StringUtils;
import com.opensymphony.xwork2.Action;
@Controller
public class SearchPageAction implements Action{
	private Logger log = Logger.getLogger(SearchPageAction.class);
	private String sqlStr;
	private String dbSou;
	private String sqlType;
	@Autowired
	private SearchPageDAO dao;
	@Autowired
	private DataBaseJdbc dbs;

	@Override
	public String execute() throws Exception {
		log.info("sqlStr:"+sqlStr);
		log.info("sqlStr:"+URLDecoder.decode(sqlStr, "iso-8859-1"));
		log.info("dbSou:"+dbSou);
		log.info("sqlType:"+sqlType);
		HttpServletRequest  request = ServletActionContext.getRequest();
		dbs.getJdbcParam();
		List<Map<String,Object>> list=null;
		int i=-1;
		Connection conn=null;
			if(!dbSou.equals("3")){
			if(dbSou.equals("1")){
				conn=DataBaseJdbc.get134DB2Connection();
			}else if(dbSou.equals("2")){
				conn=DataBaseJdbc.get228DB2Connection();
			}

			if(sqlType.equals("1")){
				list=executeSelectSql(conn);
			}else if(sqlType.equals("2")){
				i=executeDdlSql(conn);
			}
		}else{
			if(sqlType.equals("1")){
				list=dao.selectSql(sqlStr);
				log.info(list);
			}else if(sqlType.equals("2")){
				i=dao.ddlsql(sqlStr);
			}
		}

		request.setAttribute("tableList", list);
		request.setAttribute("ddlCoutn", i);

		return "searchPageTable";
	}


	private List<Map<String,Object>> executeSelectSql(Connection conn){
		Statement stmt=null;
		ResultSet set=null;
		List<String> cloumList=new ArrayList<String>();
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			stmt=conn.createStatement();
			set = stmt.executeQuery(sqlStr);
			ResultSetMetaData metaData = set.getMetaData();
			int colum = metaData.getColumnCount();
			for (int i = 1; i <= colum; i++) {
				cloumList.add(metaData.getColumnName(i));
			}
			Map<String,Object> map=null;
			while(set.next()){
				map=new HashMap<String,Object>();
				for (String string : cloumList) {
					map.put(string, set.getObject(string));
				}
				list.add(map);

			}
		} catch (SQLException e) {
			log.error("Ö´ÐÐ²éÑ¯³ö´í£º"+e.getMessage());
		}finally{
			try {
				if(set!=null)set.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
				set=null;
				stmt=null;
				conn=null;
			} catch (Exception e) {
			}
		}

		return list;
	}

	private int executeDdlSql(Connection conn){

		return 0;
	}

	public void closeDbs(){



	}



	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		try {
			this.sqlStr = StringUtils.convertToUTF(URLDecoder.decode(sqlStr, "iso-8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getDbSou() {
		return dbSou;
	}

	public void setDbSou(String dbSou) {
		this.dbSou = dbSou;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
}
