package pattern23;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: 上午10:01
 */
public class ConcreteElement2 extends Element {
    public void doSomething(){
        System.out.println("这是元素2");
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}