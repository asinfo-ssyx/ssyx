package com.asiainfo.mq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class MsgExcuteService implements ITopicMessageHandle {

	private static Logger logger = Logger.getLogger(MsgExcuteService.class);

	private MessgeInfoList mtList = MessgeInfoList.getInstance();
	/**
	 * @param <V>
	 * @param args
	 */
	public static <V> void main(String[] args) {
		// TODO Auto-generated method stub
		MessgeInfoList mtList = MessgeInfoList.getInstance();
		String acctMsg = "{\"application_layer_protocol_id\":2,\"month_accum_down_flux\":0,\"business_type_id\":106,\"business_start_time\":20140421172951,\"event_type\":2,\"event_id\":791,\"terminal_brand\":0,\"terminal_price_range\":0,\"day_accum_down_flux\":0,\"month_accum_flux\":0,\"channel_domain_id\":0,\"network_access_type\":1,\"day_accum_during_time\":3643,\"user_account\":\"13880766413\",\"event_end_time\":20141212000000,\"business_operator_id\":0,\"event_start_time\":20140421000000,\"business_id\":23011,\"site_logic_id\":0,\"content_type\":\"5519:5519000001173,1.0\",\"month_accum_during_time\":145241,\"resources_province\":0,\"keyword\":\"K1:;K2:;K3:\",\"domain_id\":84271415,\"day_accum_flux\":0,\"site_id\":\"61706-9769\",\"site_logic_name\":\"\",\"terminal_type\":2,\"control_file\":0,\"activity_code\":\"2014040704048982D01\",\"site_logic_type_id\":0,\"control_field\":268435456,\"market_keyword_id\":0,\"bearing_layer_protocol\":1,\"site_logic_subtype_id\":0,\"terminal_screen_size\":\"\",\"end_time\":20140421172951,\"network_access_point\":\"cmwap\",\"terminal_mode\":0,\"resources_operator\":7,\"month_accum_up_flux\":0,\"start_time\":20140421172951,\"terminal_opersys\":\"0\",\"network_link_type\":1,\"software_name_id\":38040,\"day_accum_up_flux\":0,\"terminal_factory\":0}";
		mtList.add(getMqMsgToList(acctMsg));
		//readDataToFile(mtList);
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.ssyx.jms.ITopicMessageHandle#handTopicMessage(java.lang.String, java.lang.String)
	 */
	@Override
	public void handTopicMessage(String _topic, String _message) {
		mtList.add(getMqMsgToList(_message));
		logger.info("add message:" + _message);
	}

	/**
	 *
	 * @param acctMsg
	 * @return
	 */
	public static MessageBean getMqMsgToList(String acctMsg) {
		String[] msgStr = acctMsg.split(",\"");

		Map<String, String> strMap = new HashMap<String, String>();

		for (int i = 0; i < msgStr.length; i++) {
			String key = msgStr[i].split("\":")[0].replace("{", "");
			String val = msgStr[i].split("\":")[1].replace("\"", "");
			strMap.put(key, val);
		}

		MessageBean msgBean = new MessageBean();
		msgBean.setBusiness_id(strMap.get("business_id"));
		msgBean.setUser_account(strMap.get("user_account"));
		msgBean.setBusiness_start_time(strMap.get("business_start_time"));
		msgBean.setContent_type(strMap.get("content_type"));
		msgBean.setKeyword(strMap.get("keyword"));
		msgBean.setSite_id(strMap.get("site_id"));
		msgBean.setActivity_code(strMap.get("activity_code"));
		return msgBean;
	}

	/**
	 * write data to hdfs
	 * @param mtList
	 */
	public static void writeDataToFile(MessgeInfoList mtList) {

		// according Main list's active code to distribute several sub list

		String strFileName = "//home//ocdc//fuse-dfs//steaming//input//app//music//music_user.txt";
		//String strFileName = "d://music_user.txt";

		try {
			PrintWriter pw = null;

			File exportfile = new File(strFileName);

			if (exportfile.exists()) {
				FileWriter fw = new FileWriter(exportfile);
				fw.write("");
				fw.close();
			}

			pw = new PrintWriter(new FileOutputStream(new File(strFileName)));

			while (mtList.getSize()>0) {
				MessageBean mtInfo = mtList.remove();
				pw.println(mtInfo.getUser_account());
			}

			pw.close();
			pw = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


}
