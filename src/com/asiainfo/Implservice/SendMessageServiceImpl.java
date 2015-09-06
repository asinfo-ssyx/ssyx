package com.asiainfo.Implservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.Iservice.ISendMessageService;
import com.asiainfo.dao.ActiveDAO;
import com.asiainfo.dao.RandomDAO;
import com.asiainfo.dao.SendMessageDAO;
import com.asiainfo.util.DataBaseJdbc;
import com.asiainfo.util.DateUtil;
import com.asiainfo.util.RandomUtil;

@Service
public class SendMessageServiceImpl implements ISendMessageService{
	public Logger log = Logger.getLogger(SendMessageServiceImpl.class);

	@Autowired
	public SendMessageDAO sendMessageDao;
	@Autowired
	public RandomDAO randomDao;
	@Autowired
	public ActiveDAO activeDao;

	@Override
	public Map<String, Object> insertSendMessage(Map<String,Object> map){
		Map<String,Object> rmap=new HashMap<String,Object>();

		map.put("createTime", DateUtil.getNowDateStr());
		map.put("sendCode",DateUtil.getNowDateStr2()+RandomUtil.getRandom());
//		Map<String,String> m=getExamineId((String)map.get("spr"));
//		if(m==null ||m.size()==0){
//			rmap.put("status", "N");
//			rmap.put("msg", "δ��ѯ���������Ϣ��");
//			return rmap;
//		}
//		map.put("examine_id", m.get("examine_id"));
//		map.put("phone_no",m.get("examine_phone"));
		int i = 0;
		//�ж�������Ϣ�Ƿ��ظ�
		String dxCode="";
		try {
			dxCode=sendMessageDao.queryMessageCode((String)map.get("send_ms"));
		} catch (Exception e1) {
			log.error("��ѯdxCode����"+e1.getMessage());
			rmap.put("status", "N");
			rmap.put("msg", "����������Ϣ����");
			return rmap;
		}
		if(null==dxCode||"".equals(dxCode)){
			dxCode=UUID.randomUUID().toString().replaceAll("-", "");
		}
		map.put("dxCode", dxCode);
		try {
			map.put("status", "2");
			sendMessageDao.updateAfterSendMessageStatus(map);
			i = sendMessageDao.insertSendMessage(map);
		} catch (Exception e) {
			log.info("����������Ϣ����"+e.getMessage());
		}
		if(1!=i){
			rmap.put("status", "N");
			rmap.put("msg", "����������Ϣ����");
			return rmap;
		}

//		if(sendExamineRandomNo(map)){
//			try {//�����ɹ�  �޸�Ŀǰ״̬����2�� ��֤ һ�������� ֻ��һ��������Ϣ����
//				map.put("status", "2");
//				sendMessageDao.updateAfterSendMessageStatus(map);
//			} catch (Exception e) {
//			}
//			rmap.put("status","Y");
//			rmap.put("sendCode",map.get("sendCode"));
//			rmap.put("exName", m.get("examine_name"));
//			rmap.put("exPhoneNo", m.get("examine_phone"));
//		}else{
//			rmap.put("status", "N");
//			rmap.put("msg", "�������������������ύ");
//		}
		rmap.put("status","Y");
		rmap.put("msg", "����������Ϣ�ɹ���");
		return rmap;
	}

	@Override
	public boolean sendExamineRandomNo(Map<String, Object> map){
		if("Y".equals(map.get("rsend"))){//���·��Ͷ��������
			//�޸����е������״̬
			map.put("randomStatus", "2");//��ʱ״̬
			try {
				randomDao.updateRandomNo(map);
			} catch (Exception e) {
				log.error("���������״̬��ʧЧ������="+e.getMessage());
			}
			Map<String,Object> smMap=sendMessageDao.getSendMessages(map);
			Map<String,String> m=getExamineId((String)map.get("user_id"));//��ȡ�����
			map.put("examine_id", m.get("examine_id"));
			map.put("send_ms", smMap.get("send_ms"));
		}
		String randomNo=RandomUtil.getRandom();
		map.put("random_type", "sh");
		map.put("create_time",DateUtil.getNowDateStr());
		map.put("random_no",randomNo);
		map.put("status","0");//0δʹ��

		int r=0;
		try {
			r = randomDao.insertRandomCode(map);
		} catch (Exception e) {
			log.info("����������־�����="+e.getMessage());
		}//��������
		if(1!=r){
			return false;
		}

		//���ɶ�����Ϣ
		map.put("send_time",DateUtil.getNowDateStr());
		map.put("send_ms",map.get("send_ms")+"|�����������룺"+randomNo);
		map.put("ms_type","9");//ϵͳ�ڲ�����

		//���÷�����Ϣ����
		//if�����Ƿ��ͳɹ�
		log.info("�·�������Ϣ="+map);

		//���Ͷ��������־��  ����
		try {
			sendMessageDao.insertSendMessageLog(map);
		} catch (Exception e) {
			log.info("���Ͷ��������־�����="+e.getMessage());
		}

		return sendmsdb(map);
	}
	/**
	 * ��ȡ�����˺���
	 * @param userId
	 * @return
	 */
	public Map<String,String> getExamineId(String userId){
		Map<String,String> map = new HashMap<String,String>();
		try {
			map =sendMessageDao.getExamineId(userId);
		} catch (Exception e) {
			log.error("��ȡ�����˺������="+e.getMessage());
		}
		return map;
	}


	@Override
	public Map<String, Object> examineSendMessage(Map<String, Object> map)
			throws Exception {
		int r=randomDao.queryRandomNo(map);
		if(r<1){
			map.clear();
			map.put("result", "��֤ʧ�ܣ�");
			map.put("status", "N");
			return map;
		}
		//�ɹ������־

		map.put("examine_id",map.get("spr"));
		map.put("examine_time", DateUtil.getNowDateStr());
		sendMessageDao.insertSendMessageLog(map);
		if("1".equals(map.get("exLev"))){
			map.put("status", "2");//���� -- �������״̬�޸ĳ�5  ʡ���쵼��� ״̬Ϊ2
		}else if("2".equals(map.get("exLev"))){
			map.put("status", "2");//����
		}
		int j=sendMessageDao.updateSendMessageStatus(map);//����������Ϣ״̬
		if(j==1){
			map.put("randomStatus", "1");//�Ѿ�ʹ��
			randomDao.updateRandomNo(map);
			map.clear();
			map.put("result", "���ͨ����");
			map.put("status", "S");
		}else{
			map.clear();
			map.put("result", "���ͨ����������Ϣ״̬����ʧ�ܣ�");
			map.put("status", "Y");
		}
		return map;
	}



	@Override
	public Map<String, Object> updateSendMessage(Map<String, Object> map)
			throws Exception {

		return null;
	}

	@Override
	public String getSubSendMessage(String activeCode) {
		String rms="";
		try {
			List<String> list=sendMessageDao.querySubSendMessages(activeCode);
			for (String string : list) {
				rms+=string+",";
			}
		} catch (Exception e) {
			log.error("��ѯ���ύ��������Ϣ���ͳ���="+e.getMessage());
		}
		return rms;
	}

	@Override
	public String shortMessageCodeSend(Map<String, Object> map) {
		map.put("create_time", DateUtil.getNowDateStr());
		map.put("sendCode",DateUtil.getNowDateStr2()+RandomUtil.getRandom());
		String resultms=(String) map.get("sendCode");

		String isql="���ű�";
		try {
			String r=insertShortMessageCode(map);
			if(r.equals("N")){
				resultms="F";
			}else{
				map.put("status", "2");//����ɹ����޸�Ŀǰ״̬�Ѿ�Ϊ2������
				sendMessageDao.updateAfterSendMessageStatus(map);
			}
			Connection con=DataBaseJdbc.getDB2Connection();
			if(con!=null){
				Statement stmt = con.createStatement();
				int i=stmt.executeUpdate(isql);
				if(i<1){
					resultms="F";
				}
			}else{
				resultms="F";
			}
		} catch (Exception e) {
			log.error("cc="+e.getMessage());
			resultms="E";
		}
		return resultms;
	}
	/**
	 * ���ű��뱾�����
	 * @param map
	 * @return
	 */
	public String insertShortMessageCode(Map<String,Object> map){
		int i=0;
		try {
			i = sendMessageDao.shortMessageCodeSend(map);
		} catch (Exception e) {
			log.warn("���ű������ʧ��="+e.getMessage());
		}
		if(i>0){
			return "Y";
		}
		return "N";
	}

	@Override
	public String randomNoSend(Map<String, Object> map) {
		String randomNo=RandomUtil.getRandom();
		map.put("random_type", "sh");
		map.put("create_time",DateUtil.getNowDateStr());
		map.put("random_no",randomNo);
		map.put("status","0");//0δʹ��
		int r=0;
		try {
			r = randomDao.insertRandomCode(map);
		} catch (Exception e) {
			log.info("����������־�����="+e.getMessage());
			return "E";
		}//��������
		if(1!=r){
			return "N";
		}

		Map<String,String> pm=getExamineId((String)map.get("spr"));
		if(pm!=null){
			map.put("phone_no", pm.get("examine_phone"));
		}
		//���ɶ�����Ϣ
		map.put("send_time",DateUtil.getNowDateStr());
		map.put("send_ms",map.get("smbCode")+"|�����������룺"+randomNo);
		map.put("ms_type","9");//ϵͳ�ڲ�����
		//���÷�����Ϣ����
//select * from vgop.msm_upload_his where msm_phonenum='15828295348' order by msm_date desc fetch first 10 rows only


		//if�����Ƿ��ͳɹ�
		log.info("�·�������Ϣ="+map);
		//���Ͷ��������־��  ����
		try {
			sendMessageDao.insertSendMessageLog(map);
		} catch (Exception e) {
			log.info("���Ͷ��������־�����="+e.getMessage());
			return "E";
		}

		if(!sendmsdb(map)){
			return "N";
		}
		return "Y";
	}

	@Override
	public String getActiveShr(Map<String, String> map) {
		List<Map<String,String>> list=null;
		try {
			list=sendMessageDao.queryActiveShr(map);
		} catch (Exception e) {
			log.error("��ѯ�������Ϣ����="+e.getMessage());
		}
		StringBuffer rms=new StringBuffer();
		rms.append("<select class=\"inputText\" id=\"spr\"  style=\"width:100px\">");
		for (Map<String, String> map2 : list) {
			rms.append("<option value =\""+map2.get("examine_id")+"\">"+map2.get("examine_name")+"</option>");
		}
		rms.append("</select>");
		return rms.toString();
	}


	public boolean sendmsdb(Map<String,Object> map){
		String insert="insert into vgop.msm_upload(msm_id,msm_content,msm_state,msm_date,msm_phonenum) " +
				"values('100860470009',?,0,?,?)";

		Connection conn=DataBaseJdbc.getDB2Connection();
		PreparedStatement  stmt=null;
		try {
			stmt=conn.prepareStatement(insert);
			stmt.setString(1, map.get("send_ms").toString());
			stmt.setString(2, map.get("create_time").toString());
			stmt.setString(3, map.get("phone_no").toString());
			int i = stmt.executeUpdate();
			log.info("����vgop ���ű����� ��"+i);
			if(i>0){
				return true;
			}
		} catch (Exception e) {
			log.error("�������ݿ��ѯ�������="+e.getMessage());
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public String getSendMsRondom(String activeCode) {
		try {
			return randomDao.querySendMsRandom(activeCode);
		} catch (Exception e) {
			log.error("�ֶ���ѯ��֤�����"+e.getMessage());
		}
		return null;
	}

	@Override
	public boolean addWebServiceSendMessage(Map<String, Object> map)
			throws Exception {
		sendMessageDao.updatAllSendMessageStatus(map.get("activeCode").toString());
		map.put("sendCode",DateUtil.getNowDateStr2()+RandomUtil.getRandom());
		map.put("createTime",DateUtil.getNowDateStr());
		int i=sendMessageDao.addWebServiceSendMessage(map);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean addTestPhone(Map<String, Object> map) {
		try {
			int i=sendMessageDao.addTestPhone(map);
			if(i>0) return true;
		} catch (Exception e) {

		}
		return false;
	}

}
