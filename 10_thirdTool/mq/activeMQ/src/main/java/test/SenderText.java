package test;

/**
 * 消息返回
 * @author 19850326
 *
 */
public class SenderText {
	public static void main(String[] args) {
		SenderMQ mq =new SenderMQ("Text",true);
		if(mq.isSelect()){
			mq.mess(new IProductback() {
				
				@Override
				public void message(Object obj) {
					System.out.println("我接收到的消息："+obj.toString());
					
				}
				
				@Override
				public Object getResult() {
					return "生成者，我接收到了你的消息";
				}
			});
		}else{
			System.out.println(mq.getError());
		}
		
	}
}
