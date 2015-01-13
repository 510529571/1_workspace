package chapter7;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-28
 * Time: ����9:56
 * To change this template use File | Settings | File Templates.
 */
public class Proxy implements Subject {
    private Subject subject;

    public Proxy() {
        this.subject = new RealSubject();
    }

    @Override
    public void request() {
        System.out.println("��������ʼ");
        subject.request();
        System.out.println("�����������");
    }
}
