package com.xw.activemq.listener;

import com.xw.activemq.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;

/**
 * Author: XW
 * Date Time: 2014/6/25 15:20
 * Desc：接收jms消息的监听器
 */
public class MessageReceiveListener implements MessageListener {
	private static final Logger LOG = Logger.getLogger(MessageReceiveListener.class);
	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				LOG.info("收到文本消息:" + textMessage.getText());
//				jmsTemplate.send(message.getJMSDestination(), new MessageCreator() {
//					@Override
//					public Message createMessage(Session session) throws JMSException {
//						return session.createTextMessage("我收到了你发的消息");
//					}
//				});
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else {
			ObjectMessage om = (ObjectMessage) message;
			LOG.info("收到对象消息：" + om);
			try {
				LOG.info("obj is :" + om.getObject());
				if (om.getObject() instanceof User) {
					User user = (User) om.getObject();
					LOG.info(user.getUserName());
					LOG.info(user.getGender());

				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}
}
