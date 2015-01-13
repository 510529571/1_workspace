package com.sample1;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Log4j2Sample {

    private static Log log = LogFactory.getLog(Log4j2Sample.class);


    public static void main(String[] args) {
        log.error("This is error messages");
        log.trace("This is trace messages");
        log.debug("Long.MAX_VALUE =  %s");
        log.debug("Logging in user %2$s with birthday %1$s");
    }

}
