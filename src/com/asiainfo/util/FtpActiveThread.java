package com.asiainfo.util;

import com.asiainfo.dao.ActiveDAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ftpActiveThread")
public class FtpActiveThread
{
  public Logger log = Logger.getLogger(FtpActiveThread.class);
  @Autowired
  public ActiveDAO dao;
  @Autowired
  private DataBaseJdbc db2;
  
  public void doWork()
  {System.out.println("*==========*************======   doWork() invoked =====*******8==============");
    List<Map<String, Object>> list = getFtpActive();
    if ((list != null) && (list.size() > 0)) {
      for (Map<String, Object> map : list) {
        if (searchFtpData(map)) {
          updateFtpActiveLogStatus(map.get("active_code").toString().trim());
        }
      }
    }
  }
  
  public boolean searchFtpData(Map<String, Object> map)
  {System.out.println("*==========*************======   searchFtpData() invoked =====*******8==============");
    this.db2.getJdbcParam();
    Connection conn = DataBaseJdbc.get134DB2Connection();
    ResultSet set = null;
    Statement stmt = null;
    String tableCode = (String)map.get("search_sql");
    String gettableName = "select list_table_name from SCCOC.CI_CUSTOM_LIST_INFO where CUSTOM_GROUP_ID ='" + 
      map.get("search_sql") + "' order by DATA_DATE desc fetch first rows only";
    try
    {
      stmt = conn.createStatement();
      String tablename = "";
      if (tableCode.trim().equals(map.get("active_code").toString().trim()))
      {
        tablename = "aiapp." + tableCode;
      }
      else if ((map.get("rule_code") != null) && (!map.get("rule_code").toString().trim().equals("")))
      {
        tablename = "aiapp.ssyx_" + map.get("active_code");
      }
      else
      {
        set = stmt.executeQuery(gettableName);
        while (set.next()) {
          tablename = "SCCOC." + set.getString("list_table_name");
        }
        set.close();
      }
      String sql = "select phone_no from " + tablename;
      set = stmt.executeQuery(sql);
      return createFtpFile(map, set);
    }
    catch (SQLException e)
    {
      this.log.error("连接134 出错：" + e.getMessage());
    }
    finally
    {
      try
      {
        if (set != null) {
          set.close();
        }
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      }
      catch (Exception e1)
      {
        this.log.error("关闭134连接出错：" + e1.getMessage());
      }
    }
    return false;
  }
  
  public boolean createFtpFile(Map<String, Object> map, ResultSet set)
  {System.out.println("*==========*************======   createFtpFile() invoked =====*******8==============");
    String filePath = "/opt/tomcat_ssyx" + 
      File.separator + "ftpfile" + File.separator + map.get("active_code").toString().trim() + ".txt";
    System.out.println(filePath);
    OutputStreamWriter write = null;
    BufferedWriter writer = null;
    InputStream inputStream = null;
    InputStream inputStreamto134 = null;
    int count =0;//记录set个数
    try
    { 
      File f = new File(filePath);
      write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
      writer = new BufferedWriter(write);
      writer.write("活动名称：" + map.get("active_name") + "\n");
      writer.write("活动编码：" + map.get("active_code") + "\n");
//      writer.write("推荐业务名称：" + map.get("BUSI_NAME") + "\n");
//      writer.write("推荐业务资费代码：" + map.get("BUSI_CODE") + "\n");
      writer.write("活动开始时间：" + map.get("begin_time") + "\n");
      writer.write("活动结束时间：" + map.get("end_time") + "\n");
//    writer.write("推荐语：" + map.get("send_ms") + "\n");
      writer.write("区县：" + map.get("active_rule") + "\n");
      writer.write("客户号码：\n");
      while (set.next()) {
        writer.write(set.getString("phone_no") + "\n");
        count ++;
      }
      writer.flush();
      String activeCode =map.get("active_code").toString().trim();
      String s =activeCode.substring(7);
      String sql="insert into db2inst1.mktsrv_selfscreening_requestqueue (request_id,login_no,request_time,request_name,targer_flname,request_status,sum_flname) "
      		+ "select substr('"+s+"',3,12)||rid,user_code,'"+map.get("begin_time")+"','"+map.get("active_name")+"','"+activeCode+".txt',3,'records["+count+"]' "
      		+ "from  (select user_code,rownumber() over(order by user_code)+10000 as rid from (select distinct user_code from db2inst1.cd_sys_person)) with ur";
      log.info("68sql :"+sql);
      if(insertDataTo68(sql))
    	  log.info("insert 68 success");
      else log.error("insert 68 error");
      System.out.println("*==========*************======   生成文件  =====*******8==============");
      inputStream = new FileInputStream(filePath);
      inputStreamto134 = new FileInputStream(filePath);
      FtpUtil.uploadFile("10.109.215.68", -1, "db2inst1", "db2opr2010", "/m05_data0/sccrm/cdMarket/result_data", map.get("active_code").toString().trim() + ".txt", inputStream);
      return FtpUtil.uploadFile("10.112.1.134", -1, "aiapp", "as1a1nf0", "/d2_data1/aiapp/cdMarket/result_data", map.get("active_code").toString().trim() + ".txt", inputStreamto134);
    }
    catch (Exception e)
    {
      this.log.error("生成ftp文件出错:" + e);
    }
    finally
    {
      try
      {
        if (write != null) {
          write.close();
        }
        if (writer != null) {
          writer.close();
        }
      }
      catch (IOException e)
      {
        this.log.error("关闭ftp文件流出错:" + e);
      }
    }
    return false;
  }
  
  public List<Map<String, Object>> getFtpActive()
  {
    try
    {
      return this.dao.getFtpActiveInfo(null);
    }
    catch (Exception e)
    {
      this.log.error("查询ftp活动信息出错 ：" + e);
    }
    return null;
  }
  public boolean insertDataTo68(String sql){
	  Statement stmt = null;
	  Connection conn = null;
      try {
    	conn=DataBaseJdbc.get68cfDB2Connection();
		stmt = conn.createStatement();
		int i=stmt.executeUpdate(sql);
	    if(i==0)
	       return false;
	    conn.close();
	    stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		log.error("添加数据到68数据库出错 ："+e.getMessage());
		return false;
	}
	  return true;
  }
  public void updateFtpActiveLogStatus(String activeCode)
  {
    try
    {
      this.dao.updateFtpActiveLogStatus(activeCode);
    }
    catch (Exception e)
    {
      this.log.error("更新ftp活动状态出错：" + e);
    }
  }
  
  public static void main(String[] args)
  {
    System.out.println(File.separator);
  }
}
