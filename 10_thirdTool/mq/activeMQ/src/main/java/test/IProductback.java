package test;

public interface IProductback {

	/**
	 * 获取传递的字符串
	 * @param obj
	 */
	public void message(Object obj);
	
	/**
	 * 返回给发送者的消息 
	 * @return 目前只支持返回Map和String
	 */
	public Object getResult();
}
