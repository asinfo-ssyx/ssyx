package com.asiainfo.mq;

import java.util.Map;

import javax.jms.DeliveryMode;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.asiainfo.mq.TopicMsgPublisher;


@Service
public class SendMq {
	static final String url = "tcp://10.95.66.118:61616";

	private static Logger log = Logger.getLogger(SendMq.class);
	public void sendStartMq(Map<String,String> activeMap){
		String mqCode=activeMap.get("mqCode");
		String beginTime=activeMap.get("beginTime");
		String endTime=activeMap.get("endTime");
		String activeCode=activeMap.get("activeCode");

		if(activeMap.get("mqType").equals("kw")){
			log.info("-----------------send start MQ KW-----------------");
			String topic1 = "com.ailk.flowmanagement.nbs.flux_action";
			TopicMsgPublisher myPublisher = new TopicMsgPublisher(url);
			myPublisher.registTopic(topic1, DeliveryMode.NON_PERSISTENT);
			myPublisher.publish("com.ailk.flowmanagement.nbs.flux_action",
					"{'control_field':274877906944,'keyword':'"+mqCode+"'," +
					"'event_end_time':'"+endTime+"'," +
					"'event_type':2,'event_start_time':'"+beginTime+"'," +
					"'event_id':110,'control_file':0," +
					"'activity_code':'"+activeCode+"'}");
			myPublisher.close();
		}else if(activeMap.get("mqType").equals("app")){
			log.info("-----------------send start MQ APP-----------------");
			String topic1 = "com.ailk.flowmanagement.nbs.flux_action";
			TopicMsgPublisher myPublisher = new TopicMsgPublisher(url);
			myPublisher.registTopic(topic1, DeliveryMode.NON_PERSISTENT);
			myPublisher.publish("com.ailk.flowmanagement.nbs.flux_action",
						"{'control_field':536870912,'business_id':'"+mqCode+"'," +
						"'event_end_time':'"+endTime+"'," +
						"'event_type':2,'event_start_time':'"+beginTime+"'," +
						"'event_id':791,'control_file':0," +
						"'activity_code':'"+activeCode+"'}");
			myPublisher.close();

		}else if(activeMap.get("mqType").equals("web")){
			log.info("-----------------send start MQ WEB-----------------");
			String topic1 = "com.ailk.flowmanagement.nbs.flux_action";
			TopicMsgPublisher myPublisher = new TopicMsgPublisher(url);
			myPublisher.registTopic(topic1, DeliveryMode.NON_PERSISTENT);//1,2,5
			myPublisher.publish("com.ailk.flowmanagement.nbs.flux_action",
						"{'control_field':4294967296,'domain_id':'"+mqCode+"'," +
						"'event_end_time':'"+endTime+"'," +
						"'event_type':2,'event_start_time':'"+beginTime+"'," +
						"'event_id':1110,'control_file':0," +
						"'activity_code':'"+activeCode+"'}");
			myPublisher.close();
		}else if(activeMap.get("mqType").equals("bs")){
			log.info("-----------------send start MQ BS-----------------");
			String topic1 = "com.ailk.flowmanagement.ccc.ret.signal.aline";
			TopicMsgPublisher myPublisher = new TopicMsgPublisher(url);
			myPublisher.registTopic(topic1, DeliveryMode.NON_PERSISTENT);

			myPublisher.publish("com.ailk.flowmanagement.ccc.ret.signal.aline",
						"{'control_field':256," +
						" 'site_id':'"+mqCode+"'," +
						" 'event_end_time':'"+endTime+"'," +
						" 'event_type':5," +
						" 'event_start_time':'"+beginTime+"'," +
						" 'event_id':110," +
						" 'control_file':0," +
						" 'activity_code':'"+activeCode+"'}");
			myPublisher.close();
		}

	}


	public void sendEndMq(Map<String,String> activeMap){
		//»î¶¯É¾³ý
		if(activeMap.get("mqType").equals("GN")){
			log.info("-----------------send end MQ GN-----------------");
			String topic1 = "com.ailk.flowmanagement.51_mcd.rule.cancel";
			TopicMsgPublisher myPublisher = new TopicMsgPublisher(url);
			myPublisher.registTopic(topic1, DeliveryMode.NON_PERSISTENT);
			myPublisher.publish("com.ailk.flowmanagement.51_mcd.rule.cancel",
						"{'activity_code':'"+activeMap.get("activeCode")+"'," +
						"'activity_status':4,'event_type':2,'event_id':'0','flag':1}");
			myPublisher.close();
		}else if(activeMap.get("mqType").equals("A")){
			log.info("-----------------send end MQ A-----------------");
			String topic1 = "com.ailk.flowmanagement.51_mcd.rule.cancel";
			TopicMsgPublisher myPublisher = new TopicMsgPublisher(url);
			myPublisher.registTopic(topic1, DeliveryMode.NON_PERSISTENT);
			myPublisher.publish("com.ailk.flowmanagement.51_mcd.rule.cancel",
						"{'activity_code':'"+activeMap.get("activeCode")+"'," +
						"'activity_status':4,'event_type':5,'event_id':'0','flag':1}");
			myPublisher.close();
		}
	}

}
