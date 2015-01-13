import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-11-27
 * Time: ÉÏÎç11:03
 * To change this template use File | Settings | File Templates.
 */
public class MapperReader {
    public static Map<String, SqlMapper> readConfig(String url) throws DocumentException {
        Map<String, SqlMapper> map = new HashMap<String, SqlMapper>();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(url);
        Element root = doc.getRootElement();

        String namespace = root.attributeValue("namespace");

        for (Iterator iter = root.elementIterator("insert"); iter.hasNext(); ) {
            Element msgEle = (Element) iter.next();
            SqlMapper sqlMapper = new SqlMapper();

            String key = namespace + "." + msgEle.attributeValue("id");
            sqlMapper.setKey(key);
            sqlMapper.setReqType(msgEle.attributeValue("reqType"));
            sqlMapper.setRespType(msgEle.attributeValue("respType"));
            sqlMapper.setSql(msgEle.getText());
            sqlMapper.setType("0");

            map.put(key, sqlMapper);
        }

        for (Iterator iter = root.elementIterator("select"); iter.hasNext(); ) {
            Element msgEle = (Element) iter.next();
            SqlMapper sqlMapper = new SqlMapper();

            String key = namespace + "." + msgEle.attributeValue("id");
            sqlMapper.setKey(key);
            sqlMapper.setReqType(msgEle.attributeValue("reqType"));
            sqlMapper.setRespType(msgEle.attributeValue("respType"));
            sqlMapper.setSql(msgEle.getText());
            sqlMapper.setType("1");

            map.put(key, sqlMapper);
        }

        return map;
    }
}
