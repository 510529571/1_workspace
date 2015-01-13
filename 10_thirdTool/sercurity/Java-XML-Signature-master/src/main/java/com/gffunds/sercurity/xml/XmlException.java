package com.gffunds.sercurity.xml;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-8-18
 * Time: обнГ4:35
 * To change this template use File | Settings | File Templates.
 */
public class XmlException extends Exception {
    public XmlException(String message) {
        super(message);
    }

    public XmlException(String message, Throwable cause) {
        super(message, cause);
    }
}
