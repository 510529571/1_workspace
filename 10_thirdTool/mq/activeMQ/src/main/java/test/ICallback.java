package test;


public interface ICallback {

	/**
	 * 获取消息的键  
	 * @return String[]
	 */
	public String[] getKey();
	
	/**
	 * @param obj 返回获取的消息
	 */
	public void message(Object obj);

}
