package com.gffunds.sercurity.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

import static java.util.Collections.singletonList;
import static javax.xml.crypto.dsig.CanonicalizationMethod.INCLUSIVE;
import static javax.xml.crypto.dsig.SignatureMethod.RSA_SHA1;
import static javax.xml.crypto.dsig.Transform.ENVELOPED;
import static javax.xml.crypto.dsig.XMLSignature.XMLNS;

public class XmlSigner extends DomValidationOperator {
    private static final String parentNode = "Message";
    private final Pkcs12KeyProvider provider;

    public XmlSigner(PrivateKeyData keyData,String certFilePath) throws IOException, NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException, CertificateException {
        this.provider = new Pkcs12KeyProvider(factory, keyData,certFilePath);
    }

    public String sign(String xml, String id) throws ParserConfigurationException, SAXException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, MarshalException, XMLSignatureException, TransformerException {
        Document document = DocumentUtil.loadDocumentByXml(xml);

        SignedInfo signedInfo = createSignature(id);
//        KeyInfo keyInfo = provider.loadKeyInfo();
        KeyInfo keyInfo =null;
        PrivateKey privateKey = provider.loadPrivateKey();
        sign(document, privateKey, signedInfo, keyInfo);

        return DocumentUtil.getDocXml(document);
    }

//    public String sign2(String xml, String id) throws ParserConfigurationException, SAXException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, XMLException {
//        Document document = DocumentUtil.loadDocumentByXml(xml);
//
//        SignedInfo signedInfo = createSignature(id);
//        PrivateKey privateKey = provider.loadPrivateKey();
//        X509Certificate x509Certificate509Certificate=provider.getX509Certificate();
//        XMLSignatureService.softXMLSignAppendIssuerSerial(document, id,x509Certificate509Certificate , privateKey);
//
//        String requestPlainMsg = XMLUtil.node2String(document, true);
//
//        return requestPlainMsg;
//    }

    public void sign(String pathToUnsignedDocument, String pathToSignedDocument, String id) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, KeyStoreException, IOException, UnrecoverableEntryException, CertificateException, ParserConfigurationException, SAXException, MarshalException, XMLSignatureException, TransformerException {
        Document document = DocumentUtil.loadDocument(pathToUnsignedDocument);

        SignedInfo signedInfo = createSignature(id);
//        KeyInfo keyInfo = provider.loadKeyInfo();
        KeyInfo keyInfo = null;
        PrivateKey privateKey = provider.loadPrivateKey();
        sign(document, privateKey, signedInfo, keyInfo);
        DocumentUtil.writeDocument(pathToSignedDocument, document);
    }

    private void sign(Document document, PrivateKey privateKey, SignedInfo signedInfo, KeyInfo keyInfo) throws MarshalException, XMLSignatureException {
        DOMSignContext signContext = new DOMSignContext(privateKey, document.getElementsByTagName(parentNode).item(0));
        XMLSignature signature = factory.newXMLSignature(signedInfo, keyInfo);
        signature.sign(signContext);
    }

    private SignedInfo createSignature(String id) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        DigestMethod digestMethod = factory.newDigestMethod(DigestMethod.SHA1, null);
        Transform transform = factory.newTransform(ENVELOPED, (TransformParameterSpec) null);
        Reference reference = factory.newReference(id, digestMethod, singletonList(transform), null, null);
        SignatureMethod signatureMethod = factory.newSignatureMethod(RSA_SHA1, null);
        CanonicalizationMethod canonicalizationMethod = factory.newCanonicalizationMethod(INCLUSIVE, (C14NMethodParameterSpec) null);
        return factory.newSignedInfo(canonicalizationMethod, signatureMethod, singletonList(reference));
    }

    /**
     * @throws SignatureNotFound if there is not element "Signature" on the top level of the document.
     */
    public boolean isValid(String pathToDocument) throws SignatureNotFound, MarshalException, XMLSignatureException, CertificateException, IOException, SAXException, ParserConfigurationException {
        Document document = DocumentUtil.loadDocument(pathToDocument);
        return validateDocumentWithKey(document, provider.loadPublicKey());
    }

    public boolean isValidXml(String xml) throws SignatureNotFound, MarshalException, XMLSignatureException, CertificateException, IOException, SAXException, ParserConfigurationException {
        Document document = DocumentUtil.loadDocumentByXml(xml);
        return validateDocumentWithKey(document, provider.loadPublicKey());
    }

    private boolean validateDocumentWithKey(Document document, PublicKey key) throws MarshalException, XMLSignatureException {
        Node item = findSignatureElement(document);
        DOMValidateContext validateContext = new DOMValidateContext(key, item);
        XMLSignature signature = factory.unmarshalXMLSignature(validateContext);
        return signature.validate(validateContext);
    }

    private Node findSignatureElement(Document document) {
        NodeList nodeList = document.getElementsByTagNameNS(XMLNS, "Signature");
        if (nodeList.getLength() == 0) {
            throw new SignatureNotFound();
        }
        return nodeList.item(0);
    }
}