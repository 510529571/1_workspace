package pattern23;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: обнГ2:19
 */
public class ConcreteBuilder extends Builder {
    private Product product = new Product();

    public Product getProduct() {
        return product;
    }

    public void setPart(String arg1, String arg2) {
        product.setName(arg1);
        product.setType(arg2);
    }
}