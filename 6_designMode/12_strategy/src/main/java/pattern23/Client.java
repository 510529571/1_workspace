package pattern23;

/**
 * User: hanwei
 * Date: 15-5-14
 * Time: 上午10:40
 * 策略模式
 */
public class Client {
    public static void main(String[] args){
        Context context;
        System.out.println("-----执行策略1-----");
        context = new Context(new ConcreteStrategy1());
        context.execute();

        System.out.println("-----执行策略2-----");
        context = new Context(new ConcreteStrategy2());
        context.execute();
    }
}
