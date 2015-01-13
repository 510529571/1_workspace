package use;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import own.hhw.util.FundSecurityUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-2-17
 * Time: 下午3:15
 * To change this template use File | Settings | File Templates.
 */
public class AESUtils {
    private static final String MERCHID = "";//商户号
    private static final String VERSION = "V1.0";//接口版本
    private static final String AESKEY = "aD98j4Jd8iYSsrqBDh+ZvQ==";//AES
    private static final String PUBKEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqtsY2w7jjbV4hVoQ+8EHt8eiS" +
                    "5Aa499tmLDAXTjTSiw3r9lR7/rq845901jxC4n+x7P+xwE6lmYiS4ae0Bw+bTSBy" +
                    "/qLJdqY4zd9pQa21ZaT2tz8J0mGw4Nl4Jmb2wjT4BNlSx07sNrA2JHMc+UrdFoMM" +
                    "yKZVVX815qzE4aUuTwIDAQAB";//公钥
    private static final String PRIVATEKEY =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKq2xjbDuONtXiFWhD7w" +
                    "Qe3x6JLkBrj322YsMBdONNKLDev2VHv+urzjn3TWPELif7Hs/7HATqWZiJLhp" +
                    "7QHD5tNIHL+osl2pjjN32lBrbVlpPa3PwnSYbDg2XgmZvbCNPgE2VLHTuw2sD" +
                    "Ykcxz5St0WgwzIplVVfzXmrMThpS5PAgMBAAECgYEAjH1qVjtwdsnUw37dyqYS" +
                    "u+1vRyOdXK+twUHwOd3EntiNFnlcQcb6iuSQn6iHY0vfZt/x9aibkLDvsmE+kG" +
                    "uXLRECCZugQSurN6rdJBAgEa3Y6QuR9g7O7lWVwhvNvuIkmPXYNHBKu+pdUgyj" +
                    "pB47n1yTZDKh5jUgLfa8M0bJb0ECQQDdQkQfVE9QxDQuoCZdsphx7I8jba4c7B" +
                    "ovNSnTmnPhf6QYhNhyckZYSBrgXCgOE5BaDvFnFsOoECzwfMf8AaSfAkEAxYTN" +
                    "g0f/w3TtOxARF+NyoKS/6vM2XABVill5Mj9uZtar+poglN5CwXmx+nZ499yuQf" +
                    "XwsMolmnSJK8GS4hDoUQJAMgbjpDnJ3TcfnVM0mYbiFRAppRcNgRiZKwdYN7H6" +
                    "dYgIsEqJLgdMhHV5LjVoERa3UsWCK47oGj3eC1oLVZ2BfQJAT0zqVfLqNMcVF8l" +
                    "zk2u18dkzVFkhUf6wWUNi7VGaOZ+mnI6U1jAGDTeWxUAbgSgQrlrXr6L5RfHTwM" +
                    "WO+0RegQJAFv40P2hgH2ZeBwcIvWF7XcttfcPvABlA0urY0nnZ9H4V0+lFNJj5V" +
                    "XXmwFLJ9+R85mj+qexHs0wqf7rWO7vZxA==";


    /**
     * 处理
     *
     * @param sPara
     * @return
     */
    public static Map<String, String> deal(Map<String, String> sPara) {

        String reqMsg = signAndEncrypt(sPara);
        Map<String, String> retMap = decryptAndVerify(reqMsg);

        return retMap;
    }

    /**
     * 签名和加密
     *
     * @param sPara
     * @return
     */
    public static String signAndEncrypt(Map<String, String> sPara) {
        String reqMsg = "";
        try {
            String sign = FundSecurityUtil.getSign(sPara, PRIVATEKEY);
            System.out.println("请求sign：" + sign);

            StringBuffer xmlBuffer = new StringBuffer();
            xmlBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xmlBuffer.append("<request>");
            xmlBuffer.append("<name>").append(sPara.get("name")).append("</name>");
            xmlBuffer.append("<password>").append(sPara.get("password")).append("</password>");
            xmlBuffer.append("<sign>"+sign+"</sign>");
            xmlBuffer.append("</request>");
            String xml = xmlBuffer.toString();

            System.out.println("请求xml明文:" + xml);
            reqMsg = FundSecurityUtil.encryptMsg(xml, AESKEY);
            System.out.println("请求xml密文:" + reqMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reqMsg;
    }

    /**
     * 解密和验签
     *
     * @param msg
     * @return
     */
    public static Map<String, String> decryptAndVerify(String msg) {
        System.out.println("返回xml密文:" + msg);
        Map<String, String> retMap = new HashMap<String, String>();
        try {
            String retXml = FundSecurityUtil.decryptMsg(msg, AESKEY);
            System.out.println("返回xml明文:" + retXml);

            Document doc = getDom(retXml);
            retMap.put("name", doc.getElementsByTagName("name").item(0).getTextContent());
            retMap.put("password", doc.getElementsByTagName("password").item(0).getTextContent());
            String sign = doc.getElementsByTagName("sign").item(0).getTextContent();

            boolean verifySign = FundSecurityUtil.verifySign(retMap, sign, PUBKEY);
            System.out.println("验签结果：" + verifySign);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retMap;
    }

    /**
     * 返回一个dom
     *
     * @param xmlstr
     * @return
     */
    public static Document getDom(String xmlstr) {//返回一个dom
        Document doc = null;
        try {
            DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder dombuilder = domfac.newDocumentBuilder();
            doc = dombuilder.parse(new InputSource(new StringReader(xmlstr)));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
