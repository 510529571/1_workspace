package pattern23;

import java.util.ArrayList;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: ÏÂÎç4:29
 */
public class Caretaker {
    private ArrayList<Memento> savedStates = new ArrayList<Memento>();
    public Memento getMemento(int index){
        return savedStates.get(index);
    }
    public void setMemento(Memento memento){
        savedStates.add(memento);
    }
}
