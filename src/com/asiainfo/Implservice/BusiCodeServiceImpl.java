package com.asiainfo.Implservice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asiainfo.Iservice.IBusiCodeService;
import com.asiainfo.dao.BusiCodeDAO;
import com.asiainfo.util.DataBaseJdbc;
@Service
public class BusiCodeServiceImpl implements IBusiCodeService {
	public Logger log = Logger.getLogger(BusiCodeServiceImpl.class);
	@Autowired
	private BusiCodeDAO dao;

	public List<Map<String,Object>>  getDataBusiBigType(String group_id) throws Exception{
		return dao.getDataBusiBigType(group_id);
	}

	public String getDataBusiSmallType(String code) throws Exception{
		List<Map<String,Object>> list=dao.getDataBusiSmallType(code);
		StringBuffer op=new StringBuffer("<option value=\"\">--«Î—°‘Ò--</option>");

		for (Map<String, Object> map : list) {
			op.append("<option value="+map.get("product_name")+"_"+map.get("product_id")+" >"+map.get("product_name")+"</option>");
		}
		return op.toString();
	}

	@Override
	public List<String> getEffectProduct() throws Exception {
		// TODO Auto-generated method stub
		return dao.getEffectProduct();
	}

	@Override
	public List<Map<String, String>> searchUserCount() throws Exception {
		List<Map<String,String>> list=dao.getActiveUser();
		DataBaseJdbc db=new DataBaseJdbc();
		db.getJdbcParam();
		Connection conn=null;
		Statement stmt=null;
		ResultSet set=null;
		String sql="";

		for (Map<String, String> map : list) {
			try{
			if(map.get("search_sql")==null ||"".equals(map.get("search_sql"))) continue;
			sql="select CUSTOM_NUM from SCCOC.CI_CUSTOM_LIST_INFO " +
				"where CUSTOM_GROUP_ID ='"+map.get("search_sql")+"' order by DATA_DATE desc fetch first rows only";
			conn=db.get134DB2Connection();
			stmt=conn.createStatement();
			set=stmt.executeQuery(sql);
			while(set.next()) {
				map.put("userCount", set.getInt("CUSTOM_NUM")+"");
			}
			}catch(Exception e){

			}finally {
				try {
					if (set!=null) set.close();
					if(stmt!=null) stmt.close();
					if(conn!=null) conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return list;
	}

	@Override
	public String searchActive(String sm) {
		try {
			List<Map<String,Object>> list=dao.searchActive(sm);
			StringBuffer sv=new StringBuffer("");
			for (Map<String, Object> map : list) {
				sv.append("<a href=\"javascript:showActiveInfo('"+map.get("active_code")+"');\">"+map.get("active_name")+"</a></br>");
			}
			return sv.toString();
		} catch (Exception e) {

		}
		return "";
	}

}
