package com.sample2;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
 
import java.util.Date;
import java.util.Random;
 
public class TestService {
	//TODO:(karl) 不太明白的地方
	//单getLooger不传参数的时候效果和传TestService.class.getName()这个值是一样的，但是如果
	//传入一个自定义的字符串就会导致日志输出有问题，那这样为啥非要提供一个参数可以设置logger的名字呢？
    private Logger logger = LogManager.getLogger(TestService.class.getName());
 
    private String[] messages = new String[] {
        "Hello, World",
        "Goodbye Cruel World",
        "You had me at hello"
    };
    private Random rand = new Random(1);
 
    public String retrieveMessage() {
        logger.entry();
        
        logger.debug("Logging in user %s with birthday %s", "12", "2011");
        String testMsg = getMessage(getKey());
 
        return logger.exit(testMsg);
    }
 
    public void exampleException() {
        logger.entry();
        try {
            String msg = messages[messages.length];
            logger.error("An exception should have been thrown");
        } catch (Exception ex) {
            logger.catching(ex);
        }
        logger.exit();
    }
 
    public String getMessage(int key) {
        logger.entry(key);
 
        String value = messages[key];
 
        return logger.exit(value);
    }
 
    private int getKey() {
        logger.entry();
        int key = rand.nextInt(messages.length);
        return logger.exit(key);
    }
    
	 
    public static void main( String[] args ) {
    	
    	
        TestService service = new TestService();
        service.retrieveMessage();
//        service.retrieveMessage();
        service.exampleException();
    }
}