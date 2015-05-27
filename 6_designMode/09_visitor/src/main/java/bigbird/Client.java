package bigbird;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ионГ10:26
 */
public class Client {
    public static void main(String[] args) {
        ObjectStructure objectStructure=new ObjectStructure();
        objectStructure.add(new Woman());
        objectStructure.add(new Man());

        objectStructure.display(new Success());
        objectStructure.display(new Failed());
    }
}
