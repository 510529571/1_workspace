import org.dom4j.DocumentException;

import java.net.MalformedURLException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-11-26
 * Time: обнГ4:28
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InstantiationException, DocumentException, IllegalAccessException {
        MyContext myContext=XMLreader.readConfig();
        System.out.println(myContext.isCacheEnabled());

    }
}
