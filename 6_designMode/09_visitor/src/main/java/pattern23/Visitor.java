package pattern23;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ионГ10:02
 */
public class Visitor implements IVisitor {

    public void visit(ConcreteElement1 el1) {
        el1.doSomething();
    }

    public void visit(ConcreteElement2 el2) {
        el2.doSomething();
    }
}