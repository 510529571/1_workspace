package com.sample3.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HelloWorld {

    static Logger logger = LogManager.getLogger(HelloWorld.class.getName());

    public static void main(String[] args) {
        logger.trace("Entering application.");

        logger.debug("main debug");
        logger.info("maininfo ");
        logger.warn("main warn ");
        logger.error("main error");
        logger.fatal("main fatal");

        logger.trace("Exiting application.");

    }

}
