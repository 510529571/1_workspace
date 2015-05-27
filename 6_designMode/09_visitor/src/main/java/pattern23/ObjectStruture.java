package pattern23;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ÉÏÎç10:07
 */
public class ObjectStruture {
    public static List<Element> getList() {
        List<Element> list = new ArrayList<Element>();
        list.add(new ConcreteElement1());
        list.add(new ConcreteElement2());

        return list;
    }
}
