package pattern23;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: ����2:19
 */
public class Director {
    private Builder builder = new ConcreteBuilder();
    public Product getAProduct(){
        builder.setPart("��������","X7");
        return builder.getProduct();
    }
    public Product getBProduct(){
        builder.setPart("�µ�����","Q5");
        return builder.getProduct();
    }
}
