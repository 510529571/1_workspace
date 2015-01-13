package chapter1.operation;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-21
 * Time: ионГ11:12
 * To change this template use File | Settings | File Templates.
 */
public class OperatorAdd extends Operator {
    @Override
    public String getResult() {
        return Double.parseDouble(numberA)+Double.parseDouble(numberB)+""; //To change body of implemented methods use File | Settings | File Templates.
    }
}
