package chapter7;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-28
 * Time: ионГ9:57
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args) {
        Subject proxy=new Proxy();

        proxy.request();
    }
}
