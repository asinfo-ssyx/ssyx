package com.asiainfo.mq;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

public class TopicMsgPublisher {

	private static Logger log = Logger.getLogger(TopicMsgPublisher.class);
	private String url;

	private Connection connection;

	private Session session;

	private Map<String, MessageProducer> producerMap;

	public TopicMsgPublisher(String _url) {
		url = _url;
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		try {
			connection = factory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			log.error(e.getMessage(), e);
			System.out.println("-----------------------------------e:" + e);
			e.printStackTrace();
		}
		producerMap = new ConcurrentHashMap<String, MessageProducer>();
	}

	public void registTopic(String strTopic, int deliveryMode) {
		log.debug(strTopic);
		Topic topic = null;
		MessageProducer publisher = null;
		try {
			topic = session.createTopic(strTopic);
			publisher = session.createProducer(topic);
			publisher.setDeliveryMode(deliveryMode);
			connection.start();
			producerMap.put(strTopic, publisher);
		} catch (JMSException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void publish(String _topic, String _message) {
		log.debug(_topic);
		log.info("_message_message_message:"+_message);
		if (null == _topic || "".equals(_topic)) {
			log.debug("err: topic is empty");
		}
		TextMessage msg = null;
		MessageProducer publisher = null;
		try {
			msg = session.createTextMessage();
			msg.setText(_message);
			publisher = producerMap.get(_topic);
			publisher.send(msg);
		} catch (JMSException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void close() {
		log.debug("close()");
		try {
			if(null !=session){
				session.close();
			}
			if (null != connection) {
				connection.stop();
				connection.close();
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
