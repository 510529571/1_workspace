package pattern23;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: ÏÂÎç2:18
 */
public class Product {
    private String name;
    private String type;

    public void showProduct() {
        System.out.println("Ãû³Æ£º" + name);
        System.out.println("ÐÍºÅ£º" + type);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}