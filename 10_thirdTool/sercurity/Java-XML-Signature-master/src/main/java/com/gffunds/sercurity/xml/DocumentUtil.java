package com.gffunds.sercurity.xml;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class DocumentUtil {

    public DocumentUtil() {
    }

    public static Document loadDocument(String pathToFile) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        return documentBuilderFactory.newDocumentBuilder().parse(new FileInputStream(pathToFile));
    }

    public static Document loadDocumentByXml(String xml) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        return documentBuilderFactory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
    }

    public static String getDocXml(Document doc) throws TransformerException, UnsupportedEncodingException {
        TransformerFactory  tf  =  TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.setOutputProperty("encoding","gb2312");//
        ByteArrayOutputStream  bos  =  new  ByteArrayOutputStream();
        t.transform(new DOMSource(doc), new StreamResult(bos));
        return  bos.toString();
    }

    public static void writeDocument(String pathToFile,Document document) throws FileNotFoundException, TransformerException {
        OutputStream stream = new FileOutputStream(pathToFile);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(stream));
    }
}