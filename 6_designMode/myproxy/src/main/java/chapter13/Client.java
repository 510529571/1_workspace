package chapter13;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-30
 * Time: ÏÂÎç3:58
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args) {
        Director director = new Director();
        ConcreteBuilder1 concreteBuilder1 = new ConcreteBuilder1();
        director.construct(concreteBuilder1);
        concreteBuilder1.getResult().show();

        System.out.println("==================");

        ConcreteBuilder2 concreteBuilder2 = new ConcreteBuilder2();
        director.construct(concreteBuilder2);
        concreteBuilder2.getResult().show();
    }
}
