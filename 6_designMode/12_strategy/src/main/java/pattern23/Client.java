package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: ����10:40
 * ����ģʽ
 */
public class Client {
    public static void main(String[] args){
        Context context;
        System.out.println("-----ִ�в���1-----");
        context = new Context(new ConcreteStrategy1());
        context.execute();

        System.out.println("-----ִ�в���2-----");
        context = new Context(new ConcreteStrategy2());
        context.execute();
    }
}
