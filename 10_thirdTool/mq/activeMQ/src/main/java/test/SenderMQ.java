package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author 接收消息
 * 
 */
public class SenderMQ {
	private ActiveMQConnectionFactory connectionFactory = null;
	private Connection connection = null;
	protected static MessageProducer producer;// 消息发送者
	private Session session = null;
	private Queue destination = null;
	private MessageConsumer consumer = null;
	private boolean bool;// true无限接收 false接收一次
	private String error="";//错误信息
	private boolean select=true;

	/**
	 * @param dest
	 *            接收目的地
	 * @param bool
	 *            true无限接收 false接收一次 默认true
	 */
	public SenderMQ(String dest, boolean bool) {
		if(dest==null || "".equals(dest)){
			select=false;
			error="请指定接收点";
		}
		this.bool = bool;
		init(dest);
	}

	/**
	 * @param dest
	 *            接收目的地
	 * @param obj
	 *            消息键
	 */
	public void init(String dest) {
//		stopMQService();
		try {
//			connectionFactory = new ActiveMQConnectionFactory();
			connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.5.132:61616");
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE,
					Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(dest);
		}catch (JMSException e) {
			select=false;
			error="JMS 客户端到JMS Provider 的连接失败！";
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @param back
	 *            消息处理及返回消息
	 * @return
	 */
	private Object result = null;

	public Object mess(final IProductback back) {
		// try {
		try {
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {

				@Override
				public void onMessage(Message arg0) {
					Message m;
					try {
						m = arg0;
						TextMessage textM = (TextMessage) m;
						m.acknowledge();
						result = textM.getText();
						back.message(result);// 把得到的消息交给接收者

						// 回复消息
						System.out.println("准备回复消息：");
						MessageProducer producer = session.createProducer(m
								.getJMSReplyTo());
						producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
						producer.setTimeToLive(10000L);
						Object o = back.getResult();
						if (o instanceof Map) {// 判断回复信息的类型
							MapMessage message = session.createMapMessage();
							Map map = (Map) o;
							Set set = map.keySet();
							List list = new ArrayList(set);
							int size = set.size();
							for (int i = 0; i < size; i++) {
								String key = (String) list.get(i);
								message.setString(key, (String) map.get(key));
							}

							producer.send(message);
						} else if (o instanceof String) {
							TextMessage message = session.createTextMessage();
							message.setText(o.toString());
							// m = message;
							producer.send(message);
						}
						session.commit();
						System.out.println("回复消息完成！");
						if (!bool) {
							session.close();
							connection.close();
						}

					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Message m = consumer.receive();
		// TextMessage textM = (TextMessage) m;
		// result = textM.getText();
		// session.commit();
		// m.acknowledge();
		// back.message(result);// 把得到的消息交给接收者
		//
		// // 回复消息
		// MessageProducer producer = session
		// .createProducer(m.getJMSReplyTo());
		// producer.setTimeToLive(5000L);
		// Object o = back.getResult();
		// if (o instanceof Map) {// 判断回复信息的类型
		// MapMessage message = session.createMapMessage();
		// Map map = (Map) o;
		// Set set = map.keySet();
		// List list = new ArrayList(set);
		// int size = set.size();
		// for (int i = 0; i < size; i++) {
		// String key = (String) list.get(i);
		// message.setString(key, (String) map.get(key));
		// }
		//
		// producer.send(message);
		// } else if (o instanceof String) {
		// TextMessage message = session.createTextMessage();
		// message.setText(o.toString());
		// // m = message;
		// producer.send(message);
		// }
		// if (!bool) {
		// session.close();
		// connection.close();
		// }
		// return result;
		// } catch (JMSException e) {
		// e.printStackTrace();
		// }
		return null;
	}

	/**
	 * @param back
	 *            消息处理及返回消息
	 * @param obj
	 * @return
	 */
//	public Object mess(IProductback back, Object... obj) {
//		Object result = null;
//		try {
//			// try {
//			// consumer.setMessageListener(new MessageListener() {
//			//
//			// @Override
//			// public void onMessage(Message arg0) {
//			// MapMessage message=(MapMessage) arg0;
//			// mess(message, obj);
//			// System.out.println("啊哈哈");
//			//
//			// }
//			// });
//			// } catch (Exception e) {
//			//
//			// }
//			Message m = consumer.receive();
//			if (m instanceof MapMessage) {// 判断消息类型
//				MapMessage message = (MapMessage) m;
//				String[] _result = new String[obj.length];
//				int len = 0;
//				for (Object o : obj) {
//					String s = message.getString(o.toString());
//					_result[len] = s;
//					System.out.println("接收到：" + s);
//					len++;
//				}
//				result = _result;
//			} else if (m instanceof TextMessage) {
//				TextMessage message = (TextMessage) m;
//				result = message.getText();
//			}
//			session.commit();
//			back.message(result);// 把得到的消息交给接收者
//
//			System.out.println("准备回复消息：");
//			// 回复消息
//			MessageProducer producer = session
//					.createProducer(m.getJMSReplyTo());
//			Object o = back.getResult();
//			if (o instanceof Map) {// 判断回复信息的类型
//				MapMessage message = session.createMapMessage();
//				Map map = (Map) o;
//				Set set = map.keySet();
//				List list = new ArrayList(set);
//				int size = set.size();
//				for (int i = 0; i < size; i++) {
//					String key = (String) list.get(i);
//					message.setString(key, (String) map.get(key));
//				}
//				producer.send(message);
//				message.acknowledge();
//			} else if (o instanceof String) {
//				TextMessage message = session.createTextMessage();
//				message.setText(o.toString());
//				m = message;
//				producer.send(message);
//				message.acknowledge();
//			}
//			System.out.println("回复消息完成！");
//			if (!bool) {
//				session.close();
//				connection.close();
//			}
//			return result;
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

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
				String exe = "rundll32 url.dll FileProtocolHandler   "
						+ "D:\\Program Files\\activemq\\bin\\activemq.bat";
				System.out.println("java.exe没有在运行 ");
				Process p = Runtime.getRuntime().exec(exe);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isSelect() {
		return select;
	}

	public String getError() {
		return error;
	}

	public static void main(String[] args) throws Exception {

		stopMQService();

		// ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory();
		//
		// Connection connection = connectionFactory.createConnection();
		// connection.start();
		//
		// final Session session = connection.createSession(Boolean.TRUE,
		// Session.AUTO_ACKNOWLEDGE);
		// Destination destination = session.createQueue("123");
		//
		// MessageConsumer consumer = session.createConsumer(destination);
		/*
		 * //listener 方式 consumer.setMessageListener(new MessageListener() {
		 * 
		 * public void onMessage(Message msg) { MapMessage message =
		 * (MapMessage) msg; //TODO something.... System.out.println("收到消息：" +
		 * new Date(message.getLong("count"))); session.commit(); }
		 * 
		 * }); Thread.sleep(30000);
		 */
		// int i = 0;
		// int num=0;
		// while (i<3) {
		// num++;
		// MapMessage message = (MapMessage) consumer.receive();
		// session.commit();
		// TODO something....
		// System.out.println("收到消息：" + new Date(message.getLong("count")));
		// System.out.println("1收到第条消息：" + message.getString("count"));
		//
		// System.out.println("收到第"+num+"条消息：" + message.getString("mes"));
		//
		// }
		//
		// session.close();
		// connection.close();
	}

}
