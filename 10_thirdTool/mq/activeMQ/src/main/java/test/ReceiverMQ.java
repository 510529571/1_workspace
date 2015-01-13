package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author 发送消息
 * 
 */
public class ReceiverMQ {

	protected String dest;//

	protected static ConnectionFactory connectionFactory;// 连接工厂，JMS 用它创建连接
	protected static Connection connection;// JMS 客户端到JMS Provider 的连接
	protected static Session session;// 一个发送或接收消息的线程
	protected static MessageProducer producer;// 消息发送者
	protected static MessageConsumer consumer;// 消息接收者;
	protected static Message message;
	protected static Destination destination;// 消息的目的地;消息发送给谁.
	protected static Queue resutlDestination;// 消息的目的地回复的消息
	private boolean bool;// 是否需要返回值 true需要  false 不需要
	
	private String error="";//错误信息
	private boolean select=true;

	/**
	 * @param dest
	 *            发送目的地
	 * @param flas
	 *            是否需要返回值 true需要  false 不需要
	 */
	public ReceiverMQ(String dest, boolean flas) {
		if(dest==null || "".equals(dest)){
			select=false;
			error="请指定发送到";
		}
		init(dest, flas);
	}

	/**
	 * @param dest
	 *            发送目的地
	 * @param flas
	 *            是否持久保存发送消息 true保存 false不保存 默认为false
	 */
	private void init(String dest, boolean flas) {
//		connectionFactory = new ActiveMQConnectionFactory();//"tcp://192.168.5.132:61616"
		connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.5.132:61616");//
		try {
			connection = connectionFactory.createConnection();
			connection.start();// 一般情况下，如果只是发送消息，而不接收不需要start() ;
			// 创建Session，参数解释：
			// 第一个参数是否使用事务:当消息发送者向消息提供者（即消息代理）发送消息时，消息发送者等待消息代理的确认，没有回应则抛出异常，消息发送程序负责处理这个错误。
			// 第二个参数消息的确认模式：
			// AUTO_ACKNOWLEDGE ：
			// 指定消息提供者在每次收到消息时自动发送确认。消息只向目标发送一次，但传输过程中可能因为错误而丢失消息。
			// CLIENT_ACKNOWLEDGE ：
			// 由消息接收者确认收到消息，通过调用消息的acknowledge()方法（会通知消息提供者收到了消息）
			// DUPS_OK_ACKNOWLEDGE ：
			// 指定消息提供者在消息接收者没有确认发送时重新发送消息（这种确认模式不在乎接收者收到重复的消息）。
			session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(dest);

			resutlDestination = session.createQueue(dest + "_result");

			producer = session.createProducer(destination);

			// 设置不持久化，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			producer.setTimeToLive(10000L);// 设置默认的过期时间

		} catch (JMSException e) {
			select=false;
			error="JMS 客户端到JMS Provider 的连接失败！";
			e.printStackTrace();
		}

	}

	/**
	 * 发送消息
	 * 
	 * @param emes
	 *            内容 目前传值字符串
	 * 
	 * @param back
	 *            回复信息处理
	 */
	public Message createMessage(Object mes, ICallback back) {

		Message m = null;
		try {
			if (mes instanceof Map) {
				MapMessage message = session.createMapMessage();
				Map map = (Map) mes;
				Set set = map.keySet();
				List list = new ArrayList(set);
				int size = set.size();
				for (int i = 0; i < size; i++) {
					String key = (String) list.get(i);
					message.setString(key, (String) map.get(key));
				}
				m = message;
//				message.setJMSReplyTo(resutlDestination);// 消息发送后的返回信息
				producer.send(message);
			} else if (mes instanceof String) {
				TextMessage message = session.createTextMessage();
				message.setText(mes.toString());
				message.setJMSReplyTo(resutlDestination);// 消息发送后的返回信息
				m=message;
				producer.send(message);
			}

			System.out.println("回复信息之前");
			resultMessage(back);//处理接收端返回信息
//			session.commit();
			System.out.println("回复信息之后");
			session.close();
			connection.close();

			return m;
		} catch (JMSException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * 接收回复信息 
	 */
	public void resultMessage(final ICallback back) {
		try {
			// 回复信息
			consumer = session.createConsumer(resutlDestination);
			// consumer.setMessageListener(new MessageListener() {
			//
			// @Override
			// public void onMessage(Message arg0) {
			// try {
			Message m = consumer.receive(10 * 1000);
			if (m == null) {
				System.out.println("客户端超时，无法收到客户端返回的消息；");
			} else {
//				System.out.println("客户端返回的信息是：" + m.getText());
			}

			String[] obj = back.getKey();
			if (m instanceof MapMessage) {// 判断消息类型
				String[] _result = null;
				if (obj != null) {
					_result = new String[obj.length];
					MapMessage message = (MapMessage) m;
					int len = 0;
					for (String o : obj) {
						String s = message.getString(o.toString());
						_result[len] = s;
						System.out.println("接收到：" + s);
						len++;
					}
					// message.acknowledge();
				}
				back.message(_result);// 把得到的消息交给接收者
			} else if (m instanceof TextMessage) {
				TextMessage message = (TextMessage) m;
				String result = message.getText();
				// message.acknowledge();
				back.message(result);// 把得到的消息交给接收者
			}
//			session.commit();
			// } catch (JMSException e) {
			// e.printStackTrace();
			// // }
			// }
			// });
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public Session getSession() {
		return session;
	}

	public MessageProducer getMessageProducer() {
		return producer;
	}

	public Destination getDestination() {
		return destination;
	}

	public Connection getConnection() {
		return connection;
	}

	public String getError() {
		return error;
	}

	public boolean isSelect() {
		return select;
	}

	public static void main(String[] args) throws Exception {
		// javax.jms.ConnectionFactory connectionFactory = new
		// ActiveMQConnectionFactory();
		//
		// Connection connection = connectionFactory.createConnection();
		// connection.start();
		//
		// Session session = connection.createSession(Boolean.TRUE,
		// Session.AUTO_ACKNOWLEDGE);
		// Destination destination = session.createQueue("my-queue");
		//
		// MessageProducer producer = session.createProducer(destination);
		// MapMessage message = session.createMapMessage();
		// message.setLong("count", new Date().getTime());
		// message.setString("count", "阿斯顿护法神大黄蜂");
		// //通过消息生产者发出消息
		// producer.send(message);
		//
		// session.commit();
		// session.close();
		// connection.close();
		// for (int i = 0; i < 3; i++) {
		// ReceiverMQ mq=ReceiverMQ.getReceiverMQ();
		// MapMessage message=mq.message;
		// message.setString("count", "222");
		// message.setString("mes", "哈哈哈哈哈");
		// mq.mes(message);
		// }
		// ReceiverMQ mq = new ReceiverMQ("123", new ReceiverMQ());
		// for (int i = 0; i < 3; i++) {
		// MapMessage message = mq.mapMessage;
		// message.setString("count", "777" + i);
		// mq.mes(message, 2000L);
		//
		// mq.getSession().commit();
		// System.out.println("哈哈哈" + i);
		// }
		// mq.getSession().close();
		// mq.getConnection().close();
	}

}
