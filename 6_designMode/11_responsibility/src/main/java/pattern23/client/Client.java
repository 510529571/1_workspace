package pattern23.client;

import pattern23.ConcreteHandler1;
import pattern23.ConcreteHandler2;
import pattern23.ConcreteHandler3;
import pattern23.Handler;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: 下午3:04
 * 责任链模式
 */
public class Client {
    public static void main(String[] args){
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();

        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        Response response = handler1.handleRequest(new Request("service3"));
    }
}