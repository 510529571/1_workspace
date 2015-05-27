package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: 下午5:04
 */
public class ConcreteObserver1 implements Observer {
    public void update() {
        System.out.println("观察者1收到信息，并进行处理。");
    }
}