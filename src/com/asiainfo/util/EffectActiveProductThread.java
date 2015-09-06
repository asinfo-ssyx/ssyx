package com.asiainfo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.dao.EffectDAO;

@Service("effectActiveProductThread")
public class EffectActiveProductThread {
	public Logger log = Logger.getLogger(EffectActiveProductThread.class);
	@Autowired
	private EffectDAO effectDao;

	public void doWork() {
		String tableName = "aiods.no_pd_outprc_rel_"
				+ DateUtil.getNowDateMinusDayYYYYMMDD(1);
		Connection conn = DataBaseJdbc.get134DB2Connection();
		tableName="aiods.no_pd_outprc_rel";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = " select active_code,count(1) smnum from " + tableName
					+ " group by active_code ";
			sql=" select active_code,count(1) smnum from " + tableName +" where create_time=current date "
					+ " group by active_code ";
			log.info("query sql="+sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Map<String, Object> map = new HashMap<String, Object>();
			while (rs.next()) {
				map.put("activeCode", rs.getString("active_code"));
				map.put("tran_count", rs.getInt("smnum"));
				log.info("map chashu:"+map);
				updateEffectActive(map);

			}
		} catch (Exception e) {
			log.error("查询134数据库，活动短信发送量出错：" + e.getMessage());
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
	}

	public void updateEffectActive(Map<String, Object> map) {
		try {
			effectDao.updateActiveEffect(map);
		} catch (Exception e) {
			log.error("更新活动统计表出错：" + e.getMessage());
		}

	}
}
