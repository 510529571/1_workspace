package pattern23;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: обнГ4:31
 */
public class ConcreteAggregate implements Aggregate {
    private List list = new ArrayList();

    public void add(Object obj) {
        list.add(obj);
    }

    public Iterator iterator() {
        return new ConcreteIterator(list);
    }

    public void remove(Object obj) {
        list.remove(obj);
    }
}
