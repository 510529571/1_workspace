package com.sinitek.newtrade.pay.icbc.common;


import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * hhw:tag httpsµÄÇëÇó
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-25
 * Time: ÏÂÎç2:25
 * To change this template use File | Settings | File Templates.
 */
public class Test_HttpsUrlConnection {
    public static void main(String[] args) throws Exception {
        igoreVerify();
        URL url=new URL("https://travis-ci.org/510529571/mybatis-3/");
        HttpsURLConnection connection= (HttpsURLConnection) url.openConnection();

        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.getOutputStream().write("ºÃ°¡".getBytes());
        InputStream is= connection.getInputStream();

        System.out.println(connection.getResponseCode());
        byte[] bytes=new byte[is.available()];
        is.read(bytes);

        System.out.println(new String(bytes));
    }

    /**
     * ºöÂÔòž×Chttps
     */
    public static void igoreVerify() throws Exception
    {

        ignoreVerifyHttpsTrustManager();
        ignoreVerifyHttpsHostName();
    }

    /**
     * ºöÂÔòž×Chttps
     */
    public static void ignoreVerifyHttpsHostName()
    {
        javax.net.ssl.HostnameVerifier hv = new javax.net.ssl.HostnameVerifier()

        {

            public boolean verify(String urlHostName, SSLSession session)

            {

                System.out.println("Warning: URL Host: "+urlHostName+" vs. "+session.getPeerHost());

                return true;

            }

        };

        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

    /**
     * ºöÂÔòž×Chttps
     */
    public static void ignoreVerifyHttpsTrustManager() throws Exception
    {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[] {
                new javax.net.ssl.X509TrustManager(){
                    public java.security.cert.X509Certificate[] getAcceptedIssuers()
                    {
                        return null;
                    }

                    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                    }

                    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                    {
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
}
