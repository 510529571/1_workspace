package com.sinitek.newtrade.pay.icbc.common;

import cn.com.icbc.CMS.commontools.TranslationTool;
import cn.com.infosec.icbc.ReturnValue;
import com.sun.net.ssl.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.contrib.ssl.AuthSSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sun.misc.BASE64Encoder;
import sun.security.provider.SecureRandom;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-18
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public class ICBCUtils {
    /**
     * 银行卡信息验证接口版本号，目前取值为：1.0.0.0
     */
    public static String ICBC_VERIFY_VERSION = "1.0.0.0";

    /**
     * 在线支付接口版本号，目前取值为：1.0.0.4
     */
    public static String ICBC_PERBANK_B2C_VERSION = "1.0.0.4";

    /**
     * NC交易请求接口版本号，目前取值为：0.0.0.1
     */
    public static String ICBC_NC_TRADE_VERSION = "0.0.0.1";

    /**
     * 接口使用的文件编码字符集
     */
    public static String ICBC_ENCODING = "GBK";

    /**
     * 配置表key
     */
    public static String PAYCONFIGKEY = "icbc";

    /**
     * 配置表key
     */
    public static String CAPITALMODE = "4";


    public static String sign(String xml) throws UnsupportedEncodingException {
        String sign = null;
        byte[] data = xml.getBytes(ICBCUtils.ICBC_ENCODING);
        byte[] key = null;

        try {
            key = TranslationTool.readFile(ConfigContext.ep_key);
        } catch (IOException e2) {
            e2.printStackTrace();
            System.out.println("无法读取企业数据层私钥文件");
            return null;
        }
        byte[] signature = null;
        char[] password = ConfigContext.ep_keypass.toCharArray();
        try {
            signature = ReturnValue.sign(data, data.length, key, password);
        } catch (Exception e3) {
            System.out.println("无法生成签名");
            return null;
        }

        String length = String.valueOf(xml.length());
        if (length.length() <= 10)
            length = ("0000000000").substring(0, 10 - length.length()) + length;//长度补齐10位，左补0

        sign = length + xml + "ICBCCMP" + base64Encode(signature);

        sign = base64Encode(sign.getBytes(ICBCUtils.ICBC_ENCODING));

        System.out.println("签约数据：" + sign);

        return sign;
    }

    // Base64编码和解码方法，可以根据自己使用的库重新编写
    private static String base64Encode(String str) {
        return base64Encode(str.getBytes());
    }

    private static String base64Encode(byte[] by) {
        String result = null;
        BASE64Encoder encoder = new BASE64Encoder();
        result = encoder.encodeBuffer(by);
        return result;
    }


    /*   public static String send(String xml, String sign) throws UnsupportedEncodingException, DocumentException {
        String retStr = null;
        byte[] data = xml.getBytes(ICBCUtils.ICBC_ENCODING);

        SAXReader reader = new SAXReader();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        Document doc = reader.read(new InputStreamReader(bais, ICBC_ENCODING));
        Element CMS = doc.getRootElement();
        Element eb = CMS.element("eb");
        Element pub = eb.element("pub");

        String userID = pub.element("ID").getText(); // 证书ID
        String PackageID = pub.element("fSeqno").getText(); // 包序列ID
        String TransCode = pub.element("TransCode").getText(); //交易代码
        String BankCode = pub.element("BankCode").getText(); //客户的归属单位
        String GroupCIS = pub.element("CIS").getText();//客户的归属编码

        byte[] certificate = null;

        try {
            FileInputStream fii = new FileInputStream(new File(ConfigContext.ep_cerfile));
            certificate = new byte[fii.available()];
            fii.read(certificate);//公钥
            fii.close();
        } catch (IOException e4) {
            System.out.println("无法读取配置企业数据层公钥文件");
            return null;
        }

        PostMethod mypost = new PostMethod();
        mypost.addParameter("Version", ICBC_NC_TRADE_VERSION);
        mypost.addParameter("TransCode", TransCode);
        mypost.addParameter("BankCode", BankCode);
        mypost.addParameter("GroupCIS", GroupCIS);
        mypost.addParameter("ID", userID);
        mypost.addParameter("PackageID", PackageID);//要求永远不能重复,可以参考接口文档的说明
        mypost.addParameter("Cert", getrevFromBASE64(certificate));
        mypost.addParameter("reqData", sign);//请根据实际修改上送包xml数据


        try {
            Protocol myhttps = new Protocol("https", new AuthSSLProtocolSocketFactory(null, null, new URL("file:" + ConfigContext.icbc_truststore), null), 446);
            Protocol.registerProtocol("https", myhttps);
            HttpConnection myconn = new HttpConnection(ConfigContext.icbc_ip, Integer.parseInt(ConfigContext.icbc_port), myhttps);
            int re_code = mypost.execute(new HttpState(), myconn);
            if (re_code == 200) {
                System.out.println("已成功发送一笔 " + TransCode);
                retStr = mypost.getResponseBodyAsString();
                System.out.println("已接收到返回信息 " + retStr);
            } else {
                System.out.println("发送失败，http错误码" + re_code);
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            System.out.println("无法读取工行通讯层证书");
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("通讯异常");
        }

        return retStr;
    }*/
    public static String send(String xml, String sign) throws UnsupportedEncodingException, DocumentException {
        String retStr = null;
        byte[] data = xml.getBytes(ICBCUtils.ICBC_ENCODING);

        SAXReader reader = new SAXReader();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        Document doc = reader.read(new InputStreamReader(bais, ICBC_ENCODING));
        Element CMS = doc.getRootElement();
        Element eb = CMS.element("eb");
        Element pub = eb.element("pub");

        String userID = pub.element("ID").getText(); // 证书ID
        String PackageID = pub.element("fSeqno").getText(); // 包序列ID
        String TransCode = pub.element("TransCode").getText(); //交易代码
        String BankCode = pub.element("BankCode").getText(); //客户的归属单位
        String GroupCIS = pub.element("CIS").getText();//客户的归属编码

        byte[] certificate = null;

        try {
            FileInputStream fii = new FileInputStream(new File(ConfigContext.ep_cerfile));
            certificate = new byte[fii.available()];
            fii.read(certificate);//公钥
            fii.close();
        } catch (IOException e4) {
            System.out.println("无法读取配置企业数据层公钥文件");
            return null;
        }

        try {
//            Protocol myhttps = new Protocol("https", new AuthSSLProtocolSocketFactory(null, null, new URL("file:" + ConfigContext.icbc_truststore), null), 446);
//            Protocol.registerProtocol("https", myhttps);

            ProtocolSocketFactory myhttps = new MySecureProtocolSocketFactory();
            Protocol.registerProtocol("https", new Protocol("https", myhttps, 443));
            PostMethod mypost = new PostMethod("https://118.2.2.14:8446");
            mypost.addParameter("Version", ICBC_NC_TRADE_VERSION);
            mypost.addParameter("TransCode", TransCode);
            mypost.addParameter("BankCode", BankCode);
            mypost.addParameter("GroupCIS", GroupCIS);
            mypost.addParameter("ID", userID);
            mypost.addParameter("PackageID", PackageID);//要求永远不能重复,可以参考接口文档的说明
            mypost.addParameter("Cert", getrevFromBASE64(certificate));
            mypost.addParameter("reqData", sign);//请根据实际修改上送包xml数据
            HttpClient httpClient=new HttpClient();
            int re_code =httpClient.executeMethod(mypost);
            if (re_code == 200) {
                System.out.println("已成功发送一笔 " + TransCode);
                retStr = mypost.getResponseBodyAsString();
                System.out.println("已接收到返回信息 " + retStr);
            } else {
                System.out.println("发送失败，http错误码" + re_code);
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            System.out.println("无法读取工行通讯层证书");
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("通讯异常");
        }

        return retStr;

    }

    public static String getrevFromBASE64(byte[] s) {
        if (s == null)
            return null;
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        try {
            return encoder.encode(s);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) throws IOException, DocumentException {
        int command = 0;
        String tran_code = null;
        String file = null;
        Server s = null;
        byte[] data = null;

        System.out.println("简易ERP接口演示系统 V1.0 by ICBCSDC");
        System.out.println("命令（1send 2start 3stop 4exit）");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("COMMAND:");
            command = Integer.parseInt(br.readLine());
            switch (command) {

                case 1:
//                    String xml="<?xml version=\"1.0\" encoding = \"GBK\"?><CMS>  <eb>    <pub>      <TransCode>SZFH_ATTEST</TransCode>      <CIS>400090002873120</CIS>      <BankCode>102</BankCode>      <ID>gfjjsz.y.4000</ID>      <TranDate>20141218</TranDate>      <TranTime>163354</TranTime>      <fSeqno>201412181633541</fSeqno>    </pub>    <in>      <CorpAccNo>4000021419201337003</CorpAccNo>      <AccNo>33333333333333</AccNo>      <VfFlag>1111</VfFlag>      <AccName>工行4</AccName>      <IdType>0</IdType>      <IdCode>430601194602035153</IdCode>      <MobilePhone>13678911655</MobilePhone>      <ReqReserved1></ReqReserved1>      <ReqReserved2></ReqReserved2>      <ReqReserved3></ReqReserved3>      <ReqReserved4></ReqReserved4>    </in>  </eb></CMS>";

                    FileInputStream fi = new FileInputStream("D:\\icbc\\send.xml");
                    byte[] bytes = new byte[fi.available()];
                    fi.read(bytes);
                    fi.close();

                    String xml = new String(bytes, ICBC_ENCODING);
                    System.out.println("工行请求报文：" + xml);
                    String sign = sign(xml);
                    String retMsg = send(xml, sign);
                    System.out.println("工行返回数据：" + retMsg);

                    FileOutputStream fs = new FileOutputStream(new File(System.currentTimeMillis() + ".txt"));
                    fs.write(retMsg.getBytes(ICBC_ENCODING));
                    fs.close();

                    break;
                case 2:
                    s = Server.GetServer();
                    s.start();
                    break;
                case 3:
                    s = Server.GetServer();
                    s.stop();
                    break;
            }
        }
        while (command != 4);
        System.out.println("演示系统退出");

    }
}
