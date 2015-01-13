package chapter1.control;


import chapter1.OperatorFactory;
import chapter1.operation.Operator;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-21
 * Time: 下午1:37
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {

        Operator operator= OperatorFactory.getOperator("*");
        operator.setNumberA("1");
        operator.setNumberB("5");
        System.out.println("结果是:"+operator.getResult());
    }
}
