package com.gffunds.sercurity.util;

import com.gffunds.sercurity.xml.PrivateKeyData;
import com.gffunds.sercurity.xml.XmlException;
import com.gffunds.sercurity.xml.XmlSigner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-8-15
 * Time: ÏÂÎç5:21
 * To change this template use File | Settings | File Templates.
 */
public class XmlSignatureUtil {
    static Log logger = LogFactory.getLog(XmlSignatureUtil.class);
    private static XmlSigner signer;

    static  {
        String keyFilePath="D:\\sinitek\\tradeapp\\key\\cgb\\GFBankDPClient.pfx";
        String username="q1w2e3";
        String password="q1w2e3";

        String publicKeyFilePath="D:\\sinitek\\tradeapp\\key\\cgb\\dpmerkey003480202006443.cer";

        PrivateKeyData keyData = new PrivateKeyData(keyFilePath, username, password);
        try {
            signer = new XmlSigner(keyData,publicKeyFilePath);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (KeyStoreException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (CertificateException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static String sign(String xml,String id) throws XmlException {
        logger.debug("123");
        try {
            return signer.sign(xml, id);
        } catch (Exception e) {
            throw new XmlException("Ç©ÃûÊ§°Ü",e);
        }
    }

    public static boolean isValidXml(String xml) throws XmlException {
        try {
            return signer.isValidXml(xml);
        } catch (Exception e) {
            throw new XmlException("ÑéÇ©Ê§°Ü",e);
        }
    }


}
