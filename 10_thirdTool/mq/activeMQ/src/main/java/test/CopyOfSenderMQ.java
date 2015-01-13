package test;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 接收消息
 * 
 */
public class CopyOfSenderMQ {
	public static void stopMQService() {

		Process pro;
		try {
			pro = Runtime.getRuntime().exec(
					"cmd.exe   /c   tasklist   |find   \"java.exe\" ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					pro.getInputStream()));
			String str = reader.readLine();

			if (str != null) {
				
				System.out.println("已经有一个java.exe程序在运行！\n");
			} else {
				// 启动MQ服务
				String exe = "rundll32 url.dll FileProtocolHandler   " +
						"D:\\Program Files\\activemq\\bin\\activemq.bat";
				System.out.println("java.exe没有在运行 ");
				Process p = Runtime.getRuntime().exec(exe);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		stopMQService();

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();

		Connection connection = connectionFactory.createConnection();
		connection.start();

		final Session session = connection.createSession(Boolean.TRUE,
				Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("my-queue");

		MessageConsumer consumer = session.createConsumer(destination);
		/*
		 * //listener 方式 consumer.setMessageListener(new MessageListener() {
		 * 
		 * public void onMessage(Message msg) { MapMessage message =
		 * (MapMessage) msg; //TODO something.... System.out.println("收到消息：" +
		 * new Date(message.getLong("count"))); session.commit(); }
		 * 
		 * }); Thread.sleep(30000);
		 */
		int i = 0;
		int num=0;
		while (i<3) {
			num++;
			MapMessage message = (MapMessage) consumer.receive();
			session.commit();
			// TODO something....
			// System.out.println("收到消息：" + new Date(message.getLong("count")));
			System.out.println("2收到第"+num+"条消息：" + message.getString("count"));

//			System.out.println("收到第"+num+"条消息：" + message.getString("mes"));
			
		}

		session.close();
		connection.close();
	}

}
