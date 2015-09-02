package com.asiainfo.Iservice;

import java.util.Map;

public interface ISendMessageService {


	/**
	 * 获取验证码
	 * @param activeCode
	 * @return
	 */
	public String getSendMsRondom(String activeCode);


	/**
	 * 查询短信审核人
	 * @param map
	 * @return
	 */
	public String getActiveShr(Map<String,String> map);

	/**
	 * 验证码发送
	 * @param map
	 * @return
	 */
	public String randomNoSend(Map<String,Object> map) ;
	/**
	 * 短信试发
	 * @param map
	 * @return
	 */
	public String shortMessageCodeSend(Map<String,Object> map);

	/**
	 * 获取提交的推送信息类型
	 * @param activeCode
	 * @return
	 */
	public String getSubSendMessage(String activeCode);

	/**
	 * 推送信息入库
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> insertSendMessage(Map<String,Object> map);

	/**
	 * 随机码验证
	 * @param Map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> examineSendMessage(Map<String,Object> map) throws Exception;

	/**
	 * 发送验证随机码
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean sendExamineRandomNo(Map<String,Object> map);

	/**
	 * 修改推送内容
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> updateSendMessage(Map<String,Object> map) throws Exception;


	public boolean addWebServiceSendMessage(Map<String,Object> map)throws Exception;

	/**
	 * 短信测试号码
	 * @param map
	 * @return
	 */
	public boolean addTestPhone(Map<String,Object> map);

}
