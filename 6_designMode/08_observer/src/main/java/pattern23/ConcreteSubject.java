package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: 下午5:04
 */
public class ConcreteSubject extends Subject {
    public void doSomething(){
        System.out.println("被观察者事件反生");
        this.notifyObserver();
    }
}