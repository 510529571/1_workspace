package test;

/**
 * 消息发送
 * @author 19850326
 *
 */
public class ReceiverText {
	public static void main(String[] args) {
		ReceiverMQ mq = new ReceiverMQ("Text",false);
		if (mq.isSelect()) {
			mq.createMessage("测试", new ICallback() {

				@Override
				public void message(Object obj) {
					if (obj instanceof String[]) {// 返回Stirng数组

					} else if (obj instanceof String) {// 返回字符串
						System.out.println("我发送消息后取得的返回值：" + obj.toString());
					}
				}

				@Override
				public String[] getKey() {

					return null;
				}
			});
		}else{
			System.out.println(mq.getError());
		}
	}
}
