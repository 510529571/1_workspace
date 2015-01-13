import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.MalformedURLException;
import java.util.*;

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

        Document doc = reader.read("F:\\work\\3_project\\1_workspace\\framework\\mybatis3-1\\src\\main\\java\\configuration.xml");
        Element root = doc.getRootElement();

        Element mappers = root.element("mappers");
        List<String> mapperList = new ArrayList<String>();
        for (Iterator iter = mappers.elementIterator("mapper"); iter.hasNext(); ) {
            Element msgEle = (Element) iter.next();
            mapperList.add(msgEle.attributeValue("resource"));
        }

        MyContext.mappers=new HashMap<String, SqlMapper>();
        for (String mapperUrl : mapperList) {
            Map<String, SqlMapper> map = MapperReader.readConfig(mapperUrl);
            for (String key : map.keySet()) {
                MyContext.mappers.put(key, map.get(key));
            }
        }
    }
}
