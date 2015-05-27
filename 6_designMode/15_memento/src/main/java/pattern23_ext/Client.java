package pattern23_ext;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: ����4:31
 */
public class Client {
    public static void main(String[] args){
        Originator ori = new Originator();
        Caretaker caretaker = new Caretaker();
        ori.setState1("�й�");
        ori.setState2("ǿʢ");
        ori.setState3("����");
        System.out.println("===��ʼ��״̬===\n"+ori);

        caretaker.setMemento("001",ori.createMemento());
        ori.setState1("���");
        ori.setState2("�ܹ�");
        ori.setState3("����");
        System.out.println("===�޸ĺ�״̬===\n"+ori);

        ori.restoreMemento(caretaker.getMemento("001"));
        System.out.println("===�ָ���״̬===\n"+ori);
    }
}