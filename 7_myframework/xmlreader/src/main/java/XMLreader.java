import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import own.hhw.IPayStarter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-11-26
 * Time: ÏÂÎç3:52
 * To change this template use File | Settings | File Templates.
 */
public class XMLreader {
    public static MyContext readConfig() throws MalformedURLException, DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyContext myContext = new MyContext();

        SAXReader reader = new SAXReader();

        Document doc = reader.read("F:\\work\\3_project\\1_workspace\\framework\\xmlreader\\src\\main\\java\\configuration.xml");
        Element root = doc.getRootElement();

        Element setting = root.element("settings");
        myContext.setCacheEnabled("true".equalsIgnoreCase(root.elementTextTrim("cacheEnabled")));
        myContext.setLazyLoadingEnabled("true".equalsIgnoreCase(root.elementTextTrim("lazyLoadingEnabled")));

        Element api = root.element("api");
        Class paystarter = Class.forName(api.elementTextTrim("paystarter"));
        myContext.setPayStarter((IPayStarter) paystarter.newInstance());

        Element returnMessages = root.element("returnMessages");
        Map retMsgMap = new HashMap();
        for (Iterator iter = returnMessages.elementIterator("message"); iter.hasNext(); ) {
            Element msgEle = (Element) iter.next();
            retMsgMap.put(msgEle.attributeValue("code"), msgEle.getTextTrim());
        }
        myContext.setErrorMsgMap(retMsgMap);

        return myContext;
    }

}
