package com.xw.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {
	public static void main(String[] args) {

		ConnectionFactory connectionFactory;// ConnectionFactory ：连接工厂，JMS 用它创建连接

		Connection connection = null;// Connection ：JMS 客户端到JMS Provider 的连接

		Session session;// Session： 一个发送或接收消息的线程

		Destination destination;// Destination ：消息的目的地;消息发送给谁.

		MessageConsumer consumer;// 消费者，消息接收者
		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD,
				"tcp://127.0.0.1:61616");
		try {

			connection = connectionFactory.createConnection();// 构造从工厂得到连接对象

			connection.start();// 启动

			session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);// 获取操作连接

			destination = session.createQueue("test");// 获取session注意参数值test是一个服务器的queue，须在在ActiveMq的console配置
			consumer = session.createConsumer(destination);
			while (true) {
				TextMessage message = (TextMessage) consumer.receive(1000);
				if (null != message) {
					System.out.println("收到消息" + message.getText());
				} else {
					break;
				}
			}
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
}
