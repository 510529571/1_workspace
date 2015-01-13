import com.xw.activemq.MessageSender;
import com.xw.activemq.pojo.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author: XW
 * Date Time: 2014/6/26 9:54
 * Desc：
 */
public class TestActiveMQ {
	private static ApplicationContext ctx;

	@BeforeClass
	public static void init() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void testSendMsg() {
		MessageSender messageSender = ctx.getBean(MessageSender.class);
		User user = new User();

		user.setId(1);
		user.setUserName("zhangsan");
		user.setGender("M");
		messageSender.sendMsg("测试TextMsg");
//		messageSender.sendMsg(user);
	}

}
