package pattern23;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: ����2:18
 */
public class Product {
    private String name;
    private String type;

    public void showProduct() {
        System.out.println("���ƣ�" + name);
        System.out.println("�ͺţ�" + type);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}