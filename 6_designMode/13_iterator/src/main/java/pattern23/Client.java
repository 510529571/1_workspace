package pattern23;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: ����4:31
 * ������ģʽ
 */
public class Client {
    public static void main(String[] args) {
        Aggregate ag = new ConcreteAggregate();
        ag.add("С��");
        ag.add("С��");
        ag.add("С��");
        Iterator it = ag.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            System.out.println(str);
        }
    }
}