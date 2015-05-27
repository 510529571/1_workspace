package pattern23;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: обнГ2:19
 */
public class Client {
    public static void main(String[] args){
        Director director = new Director();
        Product product1 = director.getAProduct();
        product1.showProduct();

        Product product2 = director.getBProduct();
        product2.showProduct();
    }
}