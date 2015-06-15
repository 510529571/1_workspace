package pattern23;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: 上午10:57
 * 命令模式
 */
public class Client {
    public static void main(String[] args){
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Command command2 = new ConcreteCommand2(receiver);
        //客户端直接执行具体命令方式（此方式与类图相符）
        command.execute();

        //客户端通过调用者来执行命令
        Invoker invoker = new Invoker();
        invoker.addCommand(command);
        invoker.addCommand(command2);

        invoker.action();
    }
}