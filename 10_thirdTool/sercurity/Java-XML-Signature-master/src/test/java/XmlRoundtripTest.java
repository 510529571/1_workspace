import com.gffunds.sercurity.xml.XmlException;
import com.gffunds.sercurity.util.XmlSignatureUtil;
import com.gffunds.sercurity.xml.XmlSigner;
import org.xml.sax.SAXException;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class XmlRoundtripTest {

    public static void main(String[] args) throws XMLSignatureException, MarshalException, NoSuchAlgorithmException, IOException, ParserConfigurationException, SAXException, InvalidAlgorithmParameterException, CertificateException, TransformerException {
        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><SoEv>\n" +
                "\t<Message id=\"2222\">\n" +
                "\t\t<CSReq id=\"req\">\n" +
                "\t\t\t<version>1.0.1</version>\n" +
                "\t\t</CSReq>\n" +
                "\t</Message>\n" +
                "</SoEv>\n";
        System.out.println(xml);
        String encript= null;
        try {
            encript =  XmlSignatureUtil.sign(xml,"#req");
        } catch (XmlException e) {
        }
        System.out.println(encript);
        encript="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><SoEv>\n" +
                "<Message id=\"20141112105614125\">\n" +
                "<CSVReq id=\"CSVReq\">\n" +
                "<version>1.0.0</version>\n" +
                "<date>20141112 10:56:14</date>\n" +
                "<instId>003480202006443</instId>\n" +
                "<certId>0001</certId>\n" +
                "<accountName>崔敏一</accountName>\n" +
                "<bankCardNo>6225682121003851746</bankCardNo>\n" +
                "<bankCardType>D</bankCardType>\n" +
                "<certificateType>1</certificateType>\n" +
                "<certificateNo>440281198702080742</certificateNo>\n" +
                "<mobilePhone>18620156978</mobilePhone>\n" +
                "</CSVReq>\n" +
                "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"#CSVReq\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>LDbjVz4YydVH4aSNISQyoe8TKCs=</DigestValue></Reference></SignedInfo><SignatureValue>rnpGHMGpvk8zZXzKpHboPu63FRZP9p+dJhNFBapc/K2FJxvUM/2xuLkZKfL9j4jw3dgXvYpL+qXa\n" +
                "3AsTiVKTSKI3RPBAAyEUHwxCMJqRqsqLze19siAU3Hs+kUeB1eanlauzLlwqjH6Ku7zbEr8/7BVG\n" +
                "ZbpN5k0yn0K83wfqmfE=</SignatureValue></Signature></Message></SoEv>";
        try {
            System.out.println( "签约结果："+XmlSignatureUtil.isValidXml(encript));
        } catch (XmlException e) {
            e.printStackTrace();
        }
    }

//    @Rule
//    public TemporaryFolder folder = new TemporaryFolder();
//    private XmlSigner signer;
//
//    @Before
//    public void createSignerWithKeyData() throws Exception {
//        PrivateKeyData keyData = createKeyData();
//        this.signer = new XmlSigner(keyData);
//    }
//
//    @Before
//    public void createValidatorWithKeyData() throws Exception {
//        PrivateKeyData keyData = createKeyData();
//    }
//
//    private PrivateKeyData createKeyData() {
//        String pathToKeystore = getPathToFileOnClasspath("certificate.p12");
//        String passphraseForKeystore = "pass";
//        String passphraseForKey = "pass";
//        return new PrivateKeyData(pathToKeystore, passphraseForKeystore, passphraseForKey);
//    }
//
//    @Test
//    public void canValidateAFileItSignedItself() throws Exception {
//        String pathToInputFile = getPathToInputFile();
//        String pathToOutputFile = getPathToOutputFile();
//        sign(pathToInputFile, pathToOutputFile);
//        System.out.println(validate(pathToOutputFile));
//    }
//
//    private void sign(String pathToInputFile, String pathToOutputFile) throws Exception {
//        signer.sign(pathToInputFile, pathToOutputFile);
//    }
//
//    private boolean validate(String pathToOutputFile) throws Exception {
//        return signer.isValid(pathToOutputFile);
//    }
//
//    private String getPathToInputFile() {
//        return getPathToFileOnClasspath("unsignedFile.xml");
//    }
//
//    private String getPathToFileOnClasspath(String name) {
//        URL unsignedXmlUrl = getClass().getClassLoader().getResource(name);
//        return unsignedXmlUrl.getFile();
//    }
//
//    private String getPathToOutputFile() throws Exception {
//        File outputFile = folder.newFile("outputFile.xml");
//        System.out.println(outputFile.getAbsolutePath());
//        return outputFile.getAbsolutePath();
//    }
}
