package com.sinitek.newtrade.pay.icbc.common;

import cn.com.icbc.CMS.commontools.TranslationTool;
import cn.com.icbc.CMS.commontools.XMLIO;
import cn.com.icbc.CMS.commontools.XpathOperater;
import org.jdom.Document;
import org.jdom.JDOMException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-18
 * Time: 上午11:23
 * To change this template use File | Settings | File Templates.
 */
public class ConfigContext {
    public static String name = null;
    public static String bankcode = null;
    public static String version = null;
    public static String cis = null;

    public static String ep_port = null;
    public static String ep_store = null;
    public static String ep_storepass = null;
    public static String ep_cerfile = null;
    public static String ep_key = null;
    public static String ep_keypass = null;

    public static String icbc_ip = null;
    public static String icbc_port = null;
    public static String icbc_truststore = null;
    public static String icbc_cerfile = null;

    static {
        byte[] data = null;
        XMLIO reader = new XMLIO();
        try {
            data = TranslationTool.readFile("para.xml");
        } catch (IOException e) {
           System.out.println("无法读取参数配置文件 para.xml");
        }
        try {
            reader.build(data);
        } catch (Exception e1) {
           System.out.println("参数文件不正确");
        }

        Document jdom = reader.getJdom();
        XpathOperater xo = new XpathOperater();
        try {
            xo.setDom(jdom);
            xo.setXpath("/paras/name");
            name = xo.getNodeValue();
            xo.setXpath("/paras/bankcode");
            bankcode = xo.getNodeValue();
            xo.setXpath("/paras/cis");
            cis = xo.getNodeValue();
            xo.setXpath("/paras/enterprise/port");
            ep_port = xo.getNodeValue();
            xo.setXpath("/paras/enterprise/commlevel/store");
            ep_store = xo.getNodeValue();
            xo.setXpath("/paras/enterprise/commlevel/storepass");
            ep_storepass = xo.getNodeValue();
            xo.setXpath("/paras/enterprise/datalevel/cerfile");
            ep_cerfile = xo.getNodeValue();
            xo.setXpath("/paras/enterprise/datalevel/key");
            ep_key = xo.getNodeValue();
            xo.setXpath("/paras/enterprise/datalevel/keypass");
            ep_keypass = xo.getNodeValue();

            xo.setXpath("/paras/icbc/ip");
            icbc_ip = xo.getNodeValue();
            xo.setXpath("/paras/icbc/port");
            icbc_port = xo.getNodeValue();
            xo.setXpath("/paras/icbc/commlevel/truststore");
            icbc_truststore = xo.getNodeValue();
            xo.setXpath("/paras/icbc/datalevel/cerfile");
            icbc_cerfile = xo.getNodeValue();
        } catch (JDOMException e2) {
            e2.printStackTrace();
           System.out.println("参数不存在");
        }
    }

}
