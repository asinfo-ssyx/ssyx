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

import com.asiainfo.Iservice.IActiveService;
import com.asiainfo.dao.ActiveDAO;
@Service("queryActiveThread")
public class QueryActiveThread {

	public Logger log = Logger.getLogger(QueryActiveThread.class);

	public Map<String,List<Map<String,String>>> sendInfo=new HashMap<String,List<Map<String,String>>>();

	@Autowired
	public ActiveDAO dao;
	@Autowired
	public IActiveService service;
	@Autowired
	private DataBaseJdbc db2;

	public void doQueryActive(){
		List<Map<String,Object>> list=getNowTimeCanSendActive();
		//log.info("list==================="+list);
		if(list!=null && list.size()>0){
			for (Map<String, Object> map : list) {
				if(!isNowTimeSend(map)){
					if(sendActiveMessage(map)){
						insertSendLog(map);
					}
				}
			}
		}
	}

	//查询当前可以执行的非实时活动  >>根据send_time 判断
	public List<Map<String,Object>> getNowTimeCanSendActive(){
		try {
			return	dao.queryActiveSendInfo(DateUtil.getNowDateStr());
		} catch (Exception e) {
			log.error("查询当前可以执行非实时活动"+e.getMessage());
		}
		return null;
	}

	//查询 非实时活动当前周期 是否已经发送过短信
	public boolean isNowTimeSend(Map<String,Object> map){
		map.put("uTime", DateUtil.getNowDateMinusDay((Integer)map.get("send_cycle")));
		int i =dao.isActiveSendMessage(map);
		//log.info("uTime=="+map.get("uTime")+"|查询返回条数"+i);
		if(i>0) return true;
		return false;
	}

	public List<String> searchTestPhoneList(String activeCode){
		List<String> list=null;
		try {
			list=dao.getTestPhoneList(activeCode);
		} catch (Exception e) {
			log.error("查询活动发送测试号码出错："+e);
		}
		return list;
	}

	//获取用户群表 >>插入到发送短信表中
	public boolean sendActiveMessage(Map<String,Object> map){
		log.info("活动场景编码："+map.get("acitve_scene"));
		List<String> list=searchTestPhoneList(map.get("active_code").toString());

		if("5".equals(map.get("acitve_scene").toString())){
			return sendCrwActiveMessage(map,list);
		}
		log.info("活动场景编码：》》》》》》");

		String tableCode=(String) map.get("user_group");
		String gettableName="select list_table_name from SCCOC.CI_CUSTOM_LIST_INFO " +
				"where CUSTOM_GROUP_ID ='"+map.get("user_group")+"' order by DATA_DATE desc fetch first rows only";
		db2.getJdbcParam();
		Connection conn=db2.get134DB2Connection();
		Statement stmt=null;
		ResultSet set=null;
		try {
			stmt=conn.createStatement();
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
			if(!tablename.equals("")){
				int i;
                /*****************************插入日志表***************************************/
				String sql="insert into UPLOAD.active_msm(send_type,PHONE_NO,msm_code,active_id,CONTENT,time,city_id,msm_id)  " +
						" with phone(a,b,c,d,e,f,g,h) as (select " +
						"  'azscl' a, phone_no b, " +
						 "'"+map.get("dx_code")+"' c, "+
						 "'"+map.get("active_code")+"' d,"+
						 "'"+map.get("send_ms")+"' e,current date f," +
						 "'"+map.get("city_id")+"' g, MY_SEQ.nextval h" +
						 " from " +
						 " "+tablename+" ) " ;
				if(list!=null&&list.size()>0){
						sql+="," +
							 " test(a,b,c,d,e,f,g,h) as (";
					for (String string : list) {
							sql+=" select 'test' a,'"+string+"' b," +
								 "'"+map.get("dx_code")+"' c, "+
								 "'"+map.get("active_code")+"' d ,"+
								 "'"+map.get("send_ms")+"' e,current date f , " +
								 "'"+map.get("city_id")+"' g,0 h " +
								 " from SYSIBM.SYSDUMMY1 )";
					}
					sql+=" select * from test union select * from phone";

				}else{
					sql+=" select * from phone ";
				}

				log.info("db2插入数据日志sql=="+sql);
				i=stmt.executeUpdate(sql);
				log.info("db2 插入 短信日志条数="+i);
			}else{


			}
			return true;
		} catch (Exception e) {
			log.error("非实时活动插入短信表出差 ："+e.getMessage());
		}finally {
			try {
				if(set!=null)set.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				log.error("关闭134连接出错："+e.getMessage());
			}
		}
		return false;
	}

	public boolean sendCrwActiveMessage(Map<String,Object> map,List<String> list){
		log.info("进入到重入网流程！！！！");
		db2.getJdbcParam();
		Connection conn=db2.get134DB2Connection();
		Statement stmt=null;
		try {
			stmt = conn.createStatement();
			insertCrwMSMLog(stmt,map,list);
		} catch (Exception e) {
			log.warn("重入网 发送信息出错："+e.getMessage());
		}finally {
			try {
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				log.error("关闭134连接出错："+e.getMessage());
			}
		}
		return false;
	}

	//非实时活动发送时间日志־
	public void insertSendLog(Map<String,Object> map){
		map.put("createTime", DateUtil.getNowDateStr());
		dao.insertActiveSendLog(map);
	}

	/**重入网插入日志*/
	private void insertCrwMSMLog(Statement st,Map<String,Object> map,List<String> list) throws SQLException{
		String userTableName="VGOP_DWD.DWD_AGAIN_NETWORK_USER_"+DateUtil.getNowDateMinusDayYYYYMMDD(2);
		String sendMs=(String) map.get("send_ms");
		String sql="insert into UPLOAD.active_msm(send_type,PHONE_NO,msm_code,active_id,CONTENT,time,city_id,msm_id)  "
				+"with "
				+"w as (select l.* from (select phone_no from  "+userTableName+" group by phone_no having  count(phone_no)<6) h "
				+"left join "+userTableName+" l on h.phone_no=l.phone_no ) "
				+", "
				+"s as ( "
				+"select row_number()over(partition by phone_no order by ORDER_NUM) id1, "
				+"       row_number()over(partition by phone_no order by ORDER_NUM) id2, "
				+"       CONTENT,phone_no from w "
				+") "
				+", "
				+"t(phone_no,id1,id2,CONTENT) as "
				+"( "
				+"select phone_no,id1,id2,cast(CONTENT as varchar(1000)) from  s where id1 =1 and id2=1 "
				+"union all  "
				+"select t.phone_no,t.id1+1,t.id2,cast(s.CONTENT||';'||t.CONTENT as varchar(1000))  "
				+"from  s, t  "
				+"where   s.id2=t.id1+1 and t.phone_no = s.phone_no  "
				+") "
				+"select 'azscl',phone_no, " +
				"'"+map.get("dx_code")+"', "+
				"'"+map.get("active_code")+"' ,"+
				" '"+sendMs.split("CRM")[0]+"'||CONTENT||'"+sendMs.split("CRM")[1]+"' "
				+",current date,'"+map.get("city_id")+"', MY_SEQ.nextval  from t where t.id1= (select max(id1) from s where s.phone_no = t.phone_no) ";
//				if(list!=null&&list.size()>0){
//					for (String string : list) {
//						sql+=" union all  " +
//								 " select 'test','"+string+"'," +
//								 "'"+map.get("dx_code")+"', "+
//								 "'"+map.get("active_code")+"' ,"+
//								 "'"+sendMs+"' , current date ," +
//								 "'"+map.get("city_id")+"', MY_SEQ.nextval " +
//								 " from SYSIBM.SYSDUMMY1 ";
//					}
//				}
		int i=st.executeUpdate(sql);
				log.info("<5插入短信日志表条数crw："+i);

		String sql1="insert into UPLOAD.active_msm(send_type,PHONE_NO,msm_code,active_id,CONTENT,time,city_id,msm_id) "
				+" with "
				+" w as (select l.* from (select phone_no from  "+userTableName+" group by phone_no having  count(phone_no)>5) h "
				+" left join "+userTableName+" l on h.phone_no=l.phone_no ) "
				+" , "
				+" m as (select * from (  "
				+"     select  "
				+"         (row_number() over (partition by phone_no order by ORDER_NUM asc)) AS row_id, "
				+"         ORDER_NUM, "
				+"         phone_no, "
				+"         CONTENT "
				+"     from w) t "
				+" where row_id<=5) "
				+" , "
				+" s as ( "
				+" select row_number()over(partition by phone_no order by ORDER_NUM) id1, "
				+"        row_number()over(partition by phone_no order by ORDER_NUM) id2, "
				+"        CONTENT,phone_no from m "
				+" ) "
				+" , "
				+" t(phone_no,id1,id2,CONTENT) as "
				+" ( "
				+" select phone_no,id1,id2,cast(CONTENT as varchar(1000)) from  s where id1 =1 and id2=1 "
				+" union all  "
				+" select t.phone_no,t.id1+1,t.id2,cast(s.CONTENT||';'||t.CONTENT as varchar(1000))  "
				+" from  s, t  "
				+" where   s.id2=t.id1+1 and t.phone_no = s.phone_no  "
				+" ) "
				+"select 'azscl', phone_no, " +
				"'"+map.get("dx_code")+"', "+
				"'"+map.get("active_code")+"' ,"+
				" '"+sendMs.split("CRM")[0]+"'||CONTENT||'"+sendMs.split("CRM")[1]+"' "
				+",current date,'"+map.get("city_id")+"', MY_SEQ.nextval  from t where t.id1= (select max(id1) from s where s.phone_no = t.phone_no) ";
		i=st.executeUpdate(sql1);
		log.info(">5 插入短信日志表条数crw："+i);
	}

	public static void main(String[] args) {
		String s="尊敬的用户您好,四川移动向你推荐:CRM.快编辑相应指令到10086订购吧,体验更多精彩!";
		System.out.println(s.split("CRM")[0]+s.split("CRM")[1]);
	}

}
