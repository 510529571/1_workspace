package com.xw.activemq;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

/**
 * Author: XW
 * Date Time: 2014/6/25 17:08
 * Desc：
 */
public class MessageSender {
	private final Logger LOG = Logger.getLogger(MessageSender.class);
	private JmsTemplate jmsTemplate;
	private Destination destination;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public void sendMsg(Object msg) {
		LOG.info("正在发送消息：" + msg);
		jmsTemplate.convertAndSend(destination, msg);
	}

}
