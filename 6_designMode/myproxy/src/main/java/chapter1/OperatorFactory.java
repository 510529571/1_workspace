package chapter1;

import chapter1.operation.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-21
 * Time: ÉÏÎç11:30
 * To change this template use File | Settings | File Templates.
 */
public class OperatorFactory {
    public static Operator getOperator(String operator) {
        if ("+".equals(operator)) {
            return new OperatorAdd();
        } else if ("/".equals(operator)) {
            return new OperatorDiv();
        } else if ("*".equals(operator)) {
            return new OperatorMul();
        } else if ("-".equals(operator)) {
            return new OperatorSub();
        } else {
            return null;
        }
    }
}
