package pattern23;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ����10:01
 */
public class ConcreteElement1 extends Element {
    public void doSomething(){
        System.out.println("����Ԫ��1");
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}