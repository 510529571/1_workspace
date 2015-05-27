package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: 下午5:05
 * 观察者模式
 */
public class Client {
    public static void main(String[] args) {
        Subject sub = new ConcreteSubject();
        sub.addObserver(new ConcreteObserver1()); //添加观察者1
        sub.addObserver(new ConcreteObserver2()); //添加观察者2
        sub.doSomething();
    }
}
