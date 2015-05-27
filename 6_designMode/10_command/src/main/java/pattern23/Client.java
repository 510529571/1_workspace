package pattern23;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: ����10:57
 * ����ģʽ
 */
public class Client {
    public static void main(String[] args){
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        //�ͻ���ֱ��ִ�о������ʽ���˷�ʽ����ͼ�����
        command.execute();

        //�ͻ���ͨ����������ִ������
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.action();
    }
}