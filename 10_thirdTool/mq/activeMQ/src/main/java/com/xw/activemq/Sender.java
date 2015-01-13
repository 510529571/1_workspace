package com.xw.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Sender {
	private static final int SEND_NUMBER = 5;

	public static void main(String[] args) {

		ConnectionFactory connectionFactory;// ConnectionFactory ：连接工厂，JMS 用它创建连接

		Connection connection = null;// Connection ：JMS 客户端到JMS Provider 的连接

		Session session;// Session： 一个发送或接收消息的线程

		Destination destination;// Destination ：消息的目的地;消息发送给谁.

		MessageProducer producer;// MessageProducer：消息发送者

		// TextMessage message;
		// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD,
				"tcp://127.0.0.1:61616");
		try {

			connection = connectionFactory.createConnection();// 构造从工厂得到连接对象

			connection.start();// 启动

			session = connection.createSession(Boolean.TRUE,
					Session.AUTO_ACKNOWLEDGE);// 获取操作连接

			destination = session.createQueue("test");// 获取session注意参数值test是一个服务器的queue，须在在ActiveMq的console配置

			producer = session.createProducer(destination);// 得到消息生成者【发送者】

			producer.setDeliveryMode(DeliveryMode.PERSISTENT);// 设置持久化，此处学习，实际根据项目决定

			sendMessage(session, producer);// 构造消息，此处写死
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}
	}

	public static void sendMessage(Session session, MessageProducer producer)
			throws Exception {
		for (int i = 1; i <= SEND_NUMBER; i++) {
			TextMessage message = session
					.createTextMessage("学习ActiveMq 发送的消息" + i);
			// 发送消息到目的地方
			System.out.println("发送消息：" + "学习ActiveMq 发送的消息" + i);
			producer.send(message);
		}
	}
}
