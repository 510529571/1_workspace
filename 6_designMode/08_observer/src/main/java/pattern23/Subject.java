package pattern23;

import java.util.Vector;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: обнГ5:03
 */
public abstract class Subject {
    private Vector<Observer> obs = new Vector<Observer>();

    public void addObserver(Observer obs){
        this.obs.add(obs);
    }
    public void delObserver(Observer obs){
        this.obs.remove(obs);
    }
    protected void notifyObserver(){
        for(Observer o: obs){
            o.update();
        }
    }
    public abstract void doSomething();
}