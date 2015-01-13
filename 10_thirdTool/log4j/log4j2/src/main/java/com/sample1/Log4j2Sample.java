package com.sample1;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4j2Sample {

    private static Logger log;

    public Log4j2Sample() {
		/*
		 * ͨ��getFormatterLogger��ȡ��loggerʵ������ʹ��Formatting Parameters�����Ҫ��ʾ
		 * ���ַ�����Ȼ�����������������ֵ�����һ�����ָ����ʽ����ţ�ưɣ��Ժ������ʽ�͸������
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
