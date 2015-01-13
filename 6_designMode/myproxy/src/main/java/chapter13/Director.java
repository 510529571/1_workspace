package chapter13;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-30
 * Time: обнГ3:58
 * To change this template use File | Settings | File Templates.
 */
public class Director {
    public void construct(Builder builder) {
        builder.buildPartA();
        builder.buildPartB();
    }
}
