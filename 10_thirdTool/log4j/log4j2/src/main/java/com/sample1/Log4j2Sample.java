package com.sample1;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4j2Sample {

    private static Logger log;

    public Log4j2Sample() {
		/*
		 * 通过getFormatterLogger获取的logger实例可以使用Formatting Parameters来替代要显示
		 * 的字符串，然后后面在来设置他的值，而且还可以指定格式，够牛逼吧，以后输出方式就更灵活了
		 *
		 */
        log = LogManager.getFormatterLogger(Log4j2Sample.this);
    }

    public static void main(String[] args) {
        new Log4j2Sample();
        log.error("This is error messages");
        log.trace("This is trace messages");
        log.debug("Long.MAX_VALUE =  %s", Long.MAX_VALUE);
        log.debug("Logging in user %2$s with birthday %1$s", "12", "1234");
    }

}
