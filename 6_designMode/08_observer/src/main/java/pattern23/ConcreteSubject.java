package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: ����5:04
 */
public class ConcreteSubject extends Subject {
    public void doSomething(){
        System.out.println("���۲����¼�����");
        this.notifyObserver();
    }
}