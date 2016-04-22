package own.hhw.lang.fangxing;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * User: hanwei
 * Date: 15-7-7
 * Time: обнГ5:14
 */
public class Test {
    public static void main(String[] args) {
        Proxy<String> proxy=new Proxy<String>(new String("123"));
        proxy.getObject();

        Proxy2 proxy2=new Proxy2();
        proxy2.<String>getObject(new String("123"));

        List l=new ArrayList();
        l.add("123");
        l.add(1);
    }
}
