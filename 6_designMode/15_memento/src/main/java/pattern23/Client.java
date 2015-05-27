package pattern23;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: ����4:29
 * ����¼ģʽ
 */
public class Client {
    public static void main(String[] args){
        Originator originator = new Originator();
        originator.setState("״̬1");
        System.out.println("��ʼ״̬:"+originator.getState());
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());
        originator.setState("״̬2");
        System.out.println("�ı��״̬:"+originator.getState());
        originator.restoreMemento(caretaker.getMemento(0));
        System.out.println("�ָ���״̬:"+originator.getState());
    }
}
