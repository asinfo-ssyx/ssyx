package com.asiainfo.Implservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.Iservice.IActiveService;
import com.asiainfo.dao.ActiveDAO;
import com.asiainfo.mq.SendMq;
import com.asiainfo.util.ConnLinux;
import com.asiainfo.util.DataBaseJdbc;
import com.asiainfo.util.DataMarketIm134DB;
import com.asiainfo.util.DateUtil;
import com.asiainfo.util.RandomUtil;


@Service
public class ActiveServiceImpl implements IActiveService{

	public Logger log = Logger.getLogger(ActiveServiceImpl.class);

	@Autowired
	public ActiveDAO dao;

	
	
	public Map<String,Object> createFromBass(Map<String, Object> map) {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sf1=new SimpleDateFormat("yyyyMMddHHmmss");
		String  s=sf.format(new Date());//活动创建时间
		String  acticeSq=sf1.format(new Date());
		String active_code="active_"+acticeSq+RandomUtil.getRandom10();
		map.put("active_code",active_code);
		map.put("create_time", s);
		int a=dao.insertActiveMpBass(map);
		if(a==1){
			return map;
		}else{
			return null;
		}
	}
	
	@Override
	public Map<String,Object> insertActive(Map<String, Object> map) {
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sf1=new SimpleDateFormat("yyyyMMddHHmmss");
		String  s=sf.format(new Date());//活动创建时间
		String  acticeSq=sf1.format(new Date());
		String active_code="active_"+acticeSq+RandomUtil.getRandom10();
		map.put("active_code",active_code);
		map.put("create_time", s);
		int a=dao.insertActiveMp(map);
		if(a==1){
			return map;
		}else{
			return null;
		}
	}
	@Autowired
	private ConnLinux connlinux;
	@Override
	public Map<String, Object> updateActiveInfo(Map<String, Object> map) {
		//是否审核通过 前台传参
		int i = 0;
		try {
			i = dao.updateActiveInfo(map);
		} catch (Exception e) {
			log.info("更新活动信息出错="+e.getMessage());
		}
		if(i>0){
			 try {
				Map<String,String> userGroup=dao.getActiveUserGroup(map.get("activeCode").toString());
				if(userGroup.size()>0){
					new Thread(new DataMarketIm134DB(userGroup)).start();
				}
			} catch (Exception e) {
				log.error("获取用户群类型出错:"+e);
			}
			map.put("result", "Y");
		 }else{
			 map.put("result", "N");
		 }
		return map;
	}




	@Override
	public Map<String, Object> getActiveChooseInfo(Map<String, Object> map)
			throws Exception {
		List<Map<String,Object>> list3=dao.queryGlUserGroup(map);
		List<Map<String,Object>> list=dao.querySendMessage(map);
		List<Map<String,Object>> list1=dao.queryTrigger(map);
		List<Map<String,Object>> list2=dao.queryUserGroup(map);


		map.clear();
		map.put("sendMessage", list);
		map.put("trigger", list1);
		map.put("userGroup", list2);
		map.put("gluserGroup", list3);
		return map;
	}

	@Override
	public List<Map<String, Object>> getActiveList(Map<String, Object> map)
			throws Exception {
		int beginNum;
		int endNum ;
		int showNum=(Integer)map.get("showNum");
		int pageNum=(Integer)map.get("pageNum");
		beginNum=(pageNum-1)*showNum;
		endNum=showNum;
		map.put("beginNum", beginNum);
		map.put("endNum", endNum);

		return dao.queryActiveList(map);
	}

	@Override
	public Map<String, Object> getActiveCount(Map<String, Object> map)
			throws Exception {
		Long count = 0l;
		try{
			Map<String, Object> map1=dao.queryActiveCount(map);
			count=(Long)map1.get("pageCount");
			if(count==0){
				map.put("pageCount", 1);
				return map;
			}
		}catch(Exception e){
			log.info("bbbbbbbbbbbbbbbbbb"+e.getMessage());
		}

		int showNum=(Integer)map.get("showNum");
		Long pageCount=0l;
		if( count%showNum != 0){
			pageCount=(count/showNum)+1;
		}else{
			pageCount=count/showNum;
		}
		map.put("pageCount", pageCount);
		return map;
	}

	@Override
	public boolean copuCreateActive(Map<String, String> map) throws Exception {
		map.put("createTime", DateUtil.getNowDateStr());
		int t=dao.insertCopyCreateActiveTrigger(map);
		int g=dao.insertCopyCreateActiveUserGroup(map);
		if(t>0 && g>0){
			return true;
		}
		return false;

	}

	@Override
	public Map<String,Object> getActiveInfo(String activeCode) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("activeCode", activeCode);
		List<Map<String,Object>> list3=dao.queryGlUserGroup(map);
		List<Map<String,Object>> list=dao.querySendMessage(map);
		List<Map<String,Object>> list1=dao.queryTrigger(map);
		List<Map<String,Object>> list2=dao.queryUserGroup(map);
		Map<String,Object> m=dao.queryActiveInfo(activeCode);
		map.put("sendMessage", list);
		map.put("trigger", list1);
		map.put("userGroup", list2);
		map.put("gluserGroup", list3);
		map.put("activeInfo", m);
		return map;
	}
	@Autowired
	private SendMq mq;
	@Override
	public String sendMq(Map<String,String> map) throws Exception {
		Map<String,String> appcode=null;
		appcode=dao.queryActiveMq(map.get("activeCode"));
		map.put("mqType", "app");
		if(appcode==null||appcode.get("tmcode")==null){
			log.info("sendmq= ---------BS------信息：");
			appcode=dao.queryActiveBsMq(map.get("activeCode"));
			map.put("mqType", "bs");
			if(appcode==null||appcode.get("tmcode")==null){
				appcode=dao.queryActiveKeyWordMq(map.get("activeCode"));
				map.put("mqType", "kw");
				if(appcode==null||appcode.get("tmcode")==null){
					map.put("triggerType", "5");
					appcode=dao.querySubTriggerInfoMq(map);
					map.put("mqType", "web");
				}

			}
		}

		String bt=map.get("beginTime");
		String et=map.get("endTime");
		bt=bt.replaceAll("-", "").replaceAll(":", "").replaceFirst(" ", "");
		et=et.replaceAll("-", "").replaceAll(":", "").replaceFirst(" ", "");
		map.remove("beginTime");
		map.remove("endTime");
		map.put("beginTime", bt);
		map.put("endTime", et);
		map.put("mqCode", appcode.get("tmcode"));
		mq.sendStartMq(map);
		log.info("发送mq=参数"+map);
		return null;
	}

	@Override
	public String updateActiceStatus(Map<String,String> map) throws Exception {
		if("2".equals(map.get("status"))){
			Map<String,String> sendType=dao.querySendMsType(map.get("activeCode"));
			if(sendType!=null){
				if(sendType.get("send_type").equals("10")){//10代表熟客系统
					int i=dao.insertFtpActiveLog(map.get("activeCode"));
					if ( i<1 ) return "N";
				}else{
					Map<String,Object> m=dao.getEffectPara(map.get("activeCode"));
					try{
						dao.insertEffect(m);
					}catch(Exception e){
						log.error("插入统计表出错 ："+e.getMessage());
					}
					if(null!=m.get("product_id")&&!"".equals(m.get("product_id").toString())&&!"null".equals(m.get("product_id").toString())&&!"NULL".equals(m.get("product_id").toString())){
						try{
							List<Map<String,String>> list=dao.getEffectProduct(m.get("product_id").toString());

							Connection conn=DataBaseJdbc.get134DB2Connection();
							Statement stmt = conn.createStatement();
							for (Map<String, String> map2 : list) {
								String insert="insert into aiapp.active_product_code(active_code,begin_time," +
										"end_time,price_code,busi_code,product_code) " +
										"values('"+m.get("active_code")+"'," +
												"'"+m.get("begin_time")+"',"+
												"'"+m.get("end_time")+"',"+
												"'"+map2.get("price_code")+"',"+
												"'"+map2.get("busi_code")+"',"+
												"'"+map2.get("product_code")+"')";


								stmt.executeUpdate(insert);
							}
							if(stmt!=null)stmt.close();
							if(conn!=null)conn.close();
						}catch(Exception e){
							log.error("订购统计134库出错："+e.getMessage());
						}
					}
				}
			}

		}else if("6".equals(map.get("status"))){
			int i=dao.addAdviceMessage(map);
			if(i==0){
				return "N";
			}
		}
		int i=dao.updateActiveStatus(map);
		if (i>0) return "Y";
		return "N";
	}

	@Override
	public String sendMqDelete(Map<String, String> map) throws Exception {

		String type=dao.getSendMqType(map);
		if("3".equals(type)){
			map.put("mqType", "A");
		}else if("2".equals(type)||"1".equals(type)||"5".equals(type)){
			map.put("mqType", "GN");
		}
		mq.sendEndMq(map);
		return null;
	}

	@Override
	public void getUserGroupHdfs(String activeCode) throws Exception {
		String sh="nohup sh /home/ocdc/bin/tools/bin/d2c_data_hbase_new.sh "+DateUtil.getNowDateYYYY_MM_DD()+" "+activeCode+"  & ";
		connlinux.exec(sh);
	}

	@Override
	public String ActiveSetTest(String activeCode,String activeType) throws Exception {
		List<Map<String,Object>> list=dao.ActiveSetTest(activeCode);

		String result="请设置:";
		for (Map<String, Object> map : list) {
			String c=Long.toString((Long)map.get("con"));
			if(c.equals("0")){
				if("1".equals(Long.toString((Long)map.get("ty"))) && activeType.equals("1")){//实时才判断
					result+="触发条件.";
				}else if("2".equals(Long.toString((Long)map.get("ty")))){
					result+="客户群.";
				}else if("3".equals(Long.toString((Long)map.get("ty")))){
					result+="推送信息.";
				}
			}
		}
		if("请设置:".equals(result)){
			result="Y";
		}
		return result;
	}

	@Override
	public Map<String, Object> getActiveInfo1(String activeCode)
			throws Exception {
		return dao.getActiveInfo(activeCode);
	}

	@Override
	public boolean isMqInertDB(String activeCode){
		Connection conn=DataBaseJdbc.get118MysqlConnection();
		ResultSet set=null;
		Statement stmt=null;
		String sql=" select * from tr_event_rule where rule_id like '%"+activeCode+"%'";
		try {
			stmt = conn.createStatement();
			set=stmt.executeQuery(sql);
			while(set.next()) {
				return true;
			}
		} catch (Exception e) {
			log.error("查询 118 数据库出错 ："+e.getMessage());
		}finally {
			try {
				if(set!=null)set.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e1) {
				log.error("关闭118连接出错："+e1.getMessage());
			}
	}
		return false;
	}

	@Override
	public String cityExamineActive(Map<String, String> map) throws Exception {
		int i=dao.updateActiveStatus(map);
		if (i>0) return "Y";
		return "N";
	}

	@Override
	public List<Map<String, String>> getDownLoadPhone(String activeCode)
			throws Exception {
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		Connection conn = DataBaseJdbc.get134DB2Connection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = " select phone_no from aiods.no_pd_outprc_rel where active_code='"+activeCode+"' group by phone_no";
			log.info("query sql="+sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Map<String, String> map = null;
			while (rs.next()) {
				map = new HashMap<String, String>();
				map.put("phoneNo", rs.getString("phone_no"));
				list.add(map);
			}
		} catch (Exception e) {
			log.error("查询134数据库，用户订购表出错：" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				log.error("关闭134连接出错：" + e.getMessage());
			}
		}

		return list;
	}

}
