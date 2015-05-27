package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: 下午5:05
 */
public class ConcreteObserver2 implements Observer {
    public void update() {
        System.out.println("观察者2收到信息，并进行处理。");
    }
}