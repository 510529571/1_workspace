package own.hhw.util;

import java.util.Map;

import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.UrlBase64;
import own.hhw.Base64;
import own.hhw.sercurity.AES;
import own.hhw.sercurity.RSA;


public class FundSecurityUtil {

    private static final Logger logger = Logger.getLogger(FundSecurityUtil.class);
    public static final String inputCharset = "UTF-8";

    public FundSecurityUtil() {
    }

    public static String getSign(Map sPara, String privateKey)
            throws Exception {
        logger.info("FUNDPAY >>> getSign start ...");
        String params = FundPayUtil.consGenRsaSignParams(sPara);
        String sign = RSA.sign(params, privateKey, inputCharset);
        logger.info((new StringBuilder()).append("FUNDPAY >>> getSign end and sign is:").append(sign).toString());
        return sign;
    }

    public static boolean verifySign(Map sPara, String sign, String pubKey)
            throws Exception {
        logger.info("FUNDPAY >>> verifySign start ...");
        String params = FundPayUtil.consGenRsaSignParams(sPara);
        logger.info((new StringBuilder()).append("FUNDPAY >>> params is:").append(params).toString());
        boolean verifySignFlag = RSA.doCheck(params, sign, pubKey, inputCharset);
        logger.info((new StringBuilder()).append("FUNDPAY >>> verifySign end result is:").append(verifySignFlag).toString());
        return verifySignFlag;
    }


    /**
     * º”√‹
     *
     * @param xmlStr
     * @param aesKey
     * @return
     * @throws Exception
     */
    public static String encryptMsg(String xmlStr, String aesKey) throws Exception {
        byte encryptStr[] = AES.encrypt(xmlStr.getBytes(inputCharset), Base64.decode(aesKey));
//        byte encryptStr[] = AES.encrypt(xmlStr.getBytes(inputCharset), aesKey.getBytes());
        return new String(UrlBase64.encode(encryptStr));
    }

    /**
     * Ω‚√‹
     *
     * @param xmlStr
     * @param aesKey
     * @return
     * @throws Exception
     */
    public static String decryptMsg(String xmlStr, String aesKey) throws Exception {
        byte decodeStr[] = UrlBase64.decode(xmlStr);
        byte decryptStr[] = AES.decrypt(decodeStr, Base64.decode(aesKey));
//        byte decryptStr[] = AES.decrypt(decodeStr, aesKey.getBytes());
        return new String(decryptStr, inputCharset);
    }

}
