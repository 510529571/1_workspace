package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: ����5:05
 * �۲���ģʽ
 */
public class Client {
    public static void main(String[] args) {
        Subject sub = new ConcreteSubject();
        sub.addObserver(new ConcreteObserver1()); //��ӹ۲���1
        sub.addObserver(new ConcreteObserver2()); //��ӹ۲���2
        sub.doSomething();
    }
}
