package com.asiainfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface SendMessageDAO {

	public String queryMessageCode(String message)throws Exception;


	/**
	 * 推送信息入库
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSendMessage(Map<String,Object> map) throws Exception;


	/**
	 * 修改推送信息内容 只有在状态等于0的时候可以修改
	 * @param activeCode
	 * @param status
	 * @param sendType
	 * @return
	 * @throws Exception
	 */
	public int updateSendMessage(String activeCode,String status,String sendType) throws Exception;

	/**
	 * 获取审核人号码
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getExamineId(String userId) throws Exception;

	/**
	 * 插入审核日志
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertExamineLog(Map<String,Object> map) throws Exception;

	/**
	 * 插入短信日志表
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertSendMessageLog(Map<String,Object> map) throws Exception;

	/**
	 * 查询活动审核人
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> queryActiveShr(Map<String,String> map) throws Exception;

	/**
	 * 修改推送信息状态
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateSendMessageStatus(Map<String,Object> map) throws Exception;

	public int updateAfterSendMessageStatus(Map<String,Object> map) throws Exception;

	/**
	 *
	 * @param activeCode
	 * @param status  状态等于0
	 * @param sendType 审核的推送的渠道  短信、网厅 等等  null表示同时审核
	 * @param sendCode 推送编码
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getSendMessages(Map<String,Object> map);

	/**
	 * 查询提交的推送信息
	 * @param activeCode
	 * @return
	 */
	public List<String> querySubSendMessages(String activeCode) throws Exception;

	/**
	 * 短信code入库
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int shortMessageCodeSend(Map<String,Object> map) throws Exception;

	/**
	 * 修改发送状态   >>针对非实时活动定时发送
	 * @param nowtime
	 * @throws Exception
	 */
	public int updateSendStatus(String nowtime) throws Exception;


	public int addWebServiceSendMessage(Map<String,Object> map)throws Exception;
	public void updatAllSendMessageStatus(String activeCode)throws Exception;


	public int addTestPhone(Map<String,Object> map) throws Exception;
}
