package com.asiainfo.mq;

import org.springframework.stereotype.Service;

@Service
public interface ITopicMessageHandle {

	public void handTopicMessage(String _topic, String _message);

}
