package own.hhw.lang.AI.demo1;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-22
 * Time: ����3:41
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(java.lang.String[] args) {
        Person person = new Child();
        person.eat();          //�������෽��

        Father father= (Father)person;
        father.eat();         //�������෽��
    }
}
