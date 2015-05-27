package pattern23;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: 下午2:19
 */
public class Director {
    private Builder builder = new ConcreteBuilder();
    public Product getAProduct(){
        builder.setPart("宝马汽车","X7");
        return builder.getProduct();
    }
    public Product getBProduct(){
        builder.setPart("奥迪汽车","Q5");
        return builder.getProduct();
    }
}
