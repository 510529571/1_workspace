package pattern23;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ����10:01
 */
public interface IVisitor {
    public void visit(ConcreteElement1 el1);
    public void visit(ConcreteElement2 el2);
}