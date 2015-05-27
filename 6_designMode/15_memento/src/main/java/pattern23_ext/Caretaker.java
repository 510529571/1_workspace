package pattern23_ext;

import java.util.HashMap;
import java.util.Map;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: ÏÂÎç4:31
 */
public class Caretaker {
    private Map<String, Memento> memMap = new HashMap<String, Memento>();
    public Memento getMemento(String index){
        return memMap.get(index);
    }

    public void setMemento(String index, Memento memento){
        this.memMap.put(index, memento);
    }
}
