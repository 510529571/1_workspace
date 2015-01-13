import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.MalformedURLException;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-11-26
 * Time: ÏÂÎç3:52
 * To change this template use File | Settings | File Templates.
 */
public class XMLreader {
    public static void readConfig() throws MalformedURLException, DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        SAXReader reader = new SAXReader();

        Document doc = reader.read("F:\\work\\3_project\\1_workspace\\xml\\dom4j\\src\\main\\java\\config.xml");
        Element root = doc.getRootElement();

        Element setting = root.element("settings");
        System.out.println("true".equalsIgnoreCase(root.elementTextTrim("cacheEnabled")));
        System.out.println("true".equalsIgnoreCase(root.elementTextTrim("lazyLoadingEnabled")));

        Element api = root.element("api");
        System.out.println(api.elementTextTrim("paystarter"));

        Element returnMessages = root.element("returnMessages");
        for (Iterator iter = returnMessages.elementIterator("message"); iter.hasNext(); ) {
            Element msgEle = (Element) iter.next();
            System.out.println((msgEle.attributeValue("code")+":"+msgEle.attributeValue("type") + ":" + msgEle.getTextTrim()));
        }


        Element auto = root.element("auto");
        System.out.println(auto!=null);

        Element properties = root.element("properties");
        for (Iterator iter = properties.elementIterator("property"); iter.hasNext(); ) {
            Element msgEle = (Element) iter.next();
            System.out.println((msgEle.attributeValue("name")+":"+msgEle.attributeValue("value")));
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InstantiationException, DocumentException, IllegalAccessException {
        XMLreader.readConfig();
    }
}
