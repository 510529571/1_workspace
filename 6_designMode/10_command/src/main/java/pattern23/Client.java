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
        Command command2 = new ConcreteCommand2(receiver);
        //�ͻ���ֱ��ִ�о������ʽ���˷�ʽ����ͼ�����
        command.execute();

        //�ͻ���ͨ����������ִ������
        Invoker invoker = new Invoker();
        invoker.addCommand(command);
        invoker.addCommand(command2);

        invoker.action();
    }
}