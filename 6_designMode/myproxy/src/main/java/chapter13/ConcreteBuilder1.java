package chapter13;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-30
 * Time: ����3:56
 * To change this template use File | Settings | File Templates.
 */
public class ConcreteBuilder1 implements Builder {
    private Product product = new Product();

    @Override
    public void buildPartA() {
        product.add("����A1");
    }

    @Override
    public void buildPartB() {
        product.add("����B1");
    }

    @Override
    public Product getResult() {
        return product;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
