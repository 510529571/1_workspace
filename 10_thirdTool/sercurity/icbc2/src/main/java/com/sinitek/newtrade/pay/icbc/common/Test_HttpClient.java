package com.sinitek.newtrade.pay.icbc.common;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.contrib.ssl.AuthSSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-25
 * Time: ����11:34
 * To change this template use File | Settings | File Templates.
 */
public class Test_HttpClient {
    public static void main(String[] args) throws MalformedURLException {
        //hhw:tag ��https��ʹ��֤�����https����
//        Protocol.registerProtocol("https", new Protocol("https", new AuthSSLProtocolSocketFactory(null, null, new URL("file:" + ConfigContext.icbc_truststore), null), 443));
        //hhw:tag ��https�����Ե�֤�����https���񣬴�������Ҫ���������󷽷�֮ǰ
//        ProtocolSocketFactory myhttps = new MySecureProtocolSocketFactory();
//        Protocol.registerProtocol("https", new Protocol("https", myhttps, 443));

        HttpClient httpclient = new HttpClient();
        GetMethod httpget = new GetMethod("https://travis-ci.org/510529571/mybatis-3/");
        try {
            httpclient.executeMethod(httpget);
            System.out.println(httpget.getStatusLine());
        } catch (HttpException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            httpget.releaseConnection();
        }
    }
}
