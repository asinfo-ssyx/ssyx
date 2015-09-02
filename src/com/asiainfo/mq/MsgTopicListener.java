package com.asiainfo.mq;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Jason(3412)
 * @date 2010-9-14
 */
@Service("msgTopicListener")
public class MsgTopicListener implements InitializingBean {
	public Logger log = Logger.getLogger(MsgTopicListener.class);
	@Autowired
	private ActiveExcuteService service1;

	@Autowired
	private ActiveExcuteService service2;

	/**
	 *
	 * @param
	 * @author Jason(3412)
	 * @date 2010-9-14
	 * @return void
	 */
	public void  mqLitener()  throws Exception{
		// FIXME Auto-generated method stub
		log.info("进入监听程序==1111111111111");
		String url = SendMq.url;
		//返回新增活动消息
		String topic1 = "com.ailk.fba.notice.rule.response.network";
		//返回删除活动消息
		String topic2 = "com.ailk.flowmanagement.51_mcd.rule.cancel.success";


			TopicMsgListener myLitener = new TopicMsgListener(url);
			myLitener.setMessageHandle(service1);
			myLitener.listenTopic(topic1);
		//////////////////////////
			TopicMsgListener myLitener1 = new TopicMsgListener(url);
			myLitener1.setMessageHandle(service2);
			myLitener1.listenTopic(topic2);

		log.info("进入监听程序==endendendendendend");

	}
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("initinitinitinitinitinitinitinitinitinit");

		//mqLitener();
	}

}
