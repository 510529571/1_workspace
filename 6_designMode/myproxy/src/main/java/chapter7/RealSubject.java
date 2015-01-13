package chapter7;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-28
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("真实的请求");
    }
}
