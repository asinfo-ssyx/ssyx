package com.asiainfo.Implservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.thrift.generated.Hbase.createTable_args;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.Iservice.IUploadService;
import com.asiainfo.dao.UploadDAO;
import com.asiainfo.util.DataBaseJdbc;

@Service
public class UploadServiceImpl implements IUploadService{
	@Autowired
	public UploadDAO dao;
	public Logger log = Logger.getLogger(UploadServiceImpl.class);
	@Override
	public boolean uploadUsersService(Map<String, Object> map) {
		try {
			dao.insertUserGroup(map);
		} catch (Exception e) {
			log.error("插入用户群出错："+e);
			return false;
		}
//		try {
//			InputStream is = new FileInputStream((File) map.get("userFile"));
//			InputStreamReader isr = new InputStreamReader(is);// 字符流
//			BufferedReader br = new BufferedReader(isr);// 缓冲流
//			String str = null;
//			List<String> phoneList=new ArrayList<String>();
//			int i=0;
//			while((str = br.readLine()) != null) {
//				if(str.trim().equals("")) continue;
//				phoneList.add(str);
//				i++;
//				if(i==100){
//					int c=dao.insertUsers(phoneList, (String)map.get("activeCode"),(String)map.get("upType"));
//					i=0;
//					phoneList.clear();
//				}
//			}
//			if(phoneList.size()>0){//插入最后一批
//				int c=dao.insertUsers(phoneList, (String)map.get("activeCode"),(String)map.get("upType"));
//			}
//		} catch (Exception e) {
//			return false;
//		}
		return true;
	}


	public void createTable(String tableName){
		System.out.println("createTable invoked...");
		Connection conn=null;
		try {
			conn=DataBaseJdbc.get134DB2Connection();
			conn.setAutoCommit(false);
			//online
//			String createSql="CREATE TABLE AIAPP."+tableName+"  (CITY_ID  VARCHAR(12) ,PHONE_NO VARCHAR(50) ) " +
//			 			     "COMPRESS YES  DISTRIBUTE BY HASH(PHONE_NO)   IN TBS_DM ";
			//test
			String createSql="CREATE TABLE AIAPP."+tableName+"  (CITY_ID  VARCHAR(12) ,PHONE_NO VARCHAR(50) ) " +
	 			     "COMPRESS YES  DISTRIBUTE BY HASH(PHONE_NO)    ";
			System.out.println("createSql:"+createSql);
			Statement s= conn.createStatement();
			s.executeUpdate(createSql);
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			log.error("创建表134 aiapp.active_code 表出错："+e.getMessage());
		}finally{
			try {
				if (conn!=null && !conn.isClosed())
				    conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void  insertGroupBatchBass(String city_id,String tableName,String othertablename){
		this.createTable(tableName);
		Connection conn=null;
		try {
			conn=DataBaseJdbc.get134DB2Connection();
			conn.setAutoCommit(false);
			String sql="insert into aiapp."+tableName+"(city_id,phone_no) select '"+city_id+"',phone_no from aiapp.wqy_"+othertablename;
			PreparedStatement prest = conn.prepareStatement(sql);
			prest.execute();
            conn.commit();
            conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if (conn!=null && !conn.isClosed())
				    conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void  insertGroupBatch(List<String> phoneList,String city_id,String tableName){
		System.out.println("insertGroupBatch invoked....");
		this.createTable(tableName);
		//连接134
		Connection conn=null;
		try {
			conn=DataBaseJdbc.get134DB2Connection();
			System.out.println("insertGroupBatch===>conn:"+conn);
			conn.setAutoCommit(false);
		//	int count =1;
			String sql="insert into aiapp."+tableName+"(city_id,phone_no) values(?,?)";
			PreparedStatement prest = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for(int i = 0; i < phoneList.size(); i++){
                String phone_no=phoneList.get(i);
                prest.setString(1, city_id);
                prest.setString(2, phone_no);
                prest.addBatch();
//                if(i>0){
//                	if(i%2000==0){
//                		count++;
//                		 System.out.println("count "+count);
//                		 prest.executeBatch();
//                		 conn.commit();
//                	}
//                }
             }
			int[] i=prest.executeBatch();
	     	System.out.println("自定义上传用户群插入数据库返回值 num："+i[0]+" ,int [] size="+i.length);
			System.out.println("phoneList.size()num："+phoneList.size());
//			for (int j : i) {
//				System.out.println("i="+j);
//			}
            conn.commit();
            conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("插入134数据库active_code表错误："+e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				if (conn!=null && !conn.isClosed())
				    conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	@Override
	public void  insertChannelBatch(List<String> phoneList,String city_id,String uniqueCode,String tableName){
		//连接134
		Connection conn=null;
		try {
			conn=DataBaseJdbc.get134DB2Connection();
			System.out.println("insertChannelBatch===>conn:"+conn);
			conn.setAutoCommit(false);
			String sql="insert into aiapp."+tableName+"(city_id,phone_no,gl_code) values(?,?,?)";
			PreparedStatement prest = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for(int i = 0; i < phoneList.size(); i++){
                String phone_no=phoneList.get(i);
                prest.setString(1, city_id);
                prest.setString(2, phone_no);
                prest.setString(3, uniqueCode);
                prest.addBatch();
             }
			int[] i=prest.executeBatch();
			System.out.println("自定义上传用户群插入数据库返回值 num："+i[0]+" ,int [] size="+i.length);
			System.out.println("phoneList.size()num："+phoneList.size());
            conn.commit();
            conn.close();
            if(i[0]==phoneList.size()){
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			 try {
				if (conn!=null && !conn.isClosed())
				    conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Map<String, String>> getGlUserGroup(String cityId) {
		List<Map<String, String>> list =null;
		try {
			list=dao.queryGlUserGroup(cityId);
		} catch (Exception e) {
			log.error("查询过滤名单出差="+e.getMessage());
		}
		log.info("listlistlistlist="+list);
		return list;
	}
	@Override
	public boolean insertGlUserGroup(Map<String, String> map) {
		String ug=map.get("userGroup");
		String[] ss=ug.split(",");
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Map<String,String> m=null;
		for (String string : ss) {
			m=new HashMap<String,String>();
			m.put("ug", string);
			m.put("activeCode", map.get("activeCode"));
			list.add(m);
		}
		int i=0;
		try {
			i=dao.insertGlUserGroup(list);
		} catch (Exception e) {
			log.error("插入过滤客户群出错="+e.getMessage());
			return false;
		}
		if(i>0){
			return true;
		}
 		return false;
	}
	@Override
	public boolean insertUploadFilePath(Map<String, Object> map) {

		return false;
	}
	@Override
	public String isRuserGroupName(Map<String, String> map) {
		try {
			if(dao.isRuserGroupName(map)==0){
				return "N";
			}
		} catch (Exception e) {
			log.error("查询用户群名称重复错误："+e);
		}
		return "R";
	}


	@Override
	public String deleteGlGroupName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		try {
			if(dao.deleteGlGroupName(map)>0){
				return "Y";
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
		  log.error("删除用户群名称错误："+e);
		}
		return "N";
	}


	@Override
	public void deleteChannelBatch(String uniqueCode, String tableName) {
		// TODO Auto-generated method stub
		//连接134
		Connection conn=null;
		try {
			conn=DataBaseJdbc.get134DB2Connection();
			System.out.println("insertChannelBatch===>conn:"+conn);
			conn.setAutoCommit(false);
			String sql="delete from aiapp."+tableName+"where gl_code='"+uniqueCode+"'";
			PreparedStatement prest = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    int  i=prest.executeUpdate();
		    log.info("delete result : "+i);
		    conn.commit();
            conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			 try {
				if (conn!=null && !conn.isClosed())
				    conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
