package com.asiainfo.mq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

public class TopicMsgListener {

	private static Logger log = Logger.getLogger(TopicMsgListener.class);

	private String url;
	private Connection connection;
	private ITopicMessageHandle messageHandle;

	public TopicMsgListener(String _url) {
		url = _url;
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		try {
			connection = factory.createConnection();
		} catch (JMSException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void listenTopic(final String _topic) {
		try {
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = session.createConsumer(session
					.createTopic(_topic));
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message m) {
					try {
						String message = null;
						if (m instanceof TextMessage) {
								message = ((TextMessage) m).getText();
						}
						messageHandle.handTopicMessage(_topic, message);
					} catch (Exception e) {
						log.info("mq½ÓÊÕ³ö´í=====:"+e.getMessage());
						e.printStackTrace();
					}
				}
			});
			connection.start();
		} catch (JMSException e) {
			log.error(e.getMessage(), e);
		}
	}

	public void setMessageHandle(ITopicMessageHandle messageHandle) {
		log.debug(messageHandle);
		this.messageHandle = messageHandle;
	}

	public void close() {
		log.debug("close()");

		try {
			if (null != connection) {
				connection.stop();
				connection.close();
			}
		} catch (JMSException e) {
			log.error(e.getMessage(), e);
		}
	}

}
