package pattern23;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ионГ10:01
 */
public abstract class Element {
    public abstract void accept(IVisitor visitor);
    public abstract void doSomething();
}
