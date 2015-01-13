package test;

/**
 * activeMQ发送消息格式
 * @author 19850326
 *
 */
public enum EMessage {
	/**
	 * MapMessage
	 */
	map,
	/**
	 * TextMessage
	 */
	text;
	
	private String key;

	public String getKey() {
		return key;
	}
	
}
