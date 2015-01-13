package own.hhw.lang.grammar;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-23
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
public class Test_Operator {
    @Test
    public void test1() {
        int i = 5;
        System.out.println(i++);      //先打印i的值，然后在把i的值加1
        System.out.println(++i);      //先把i的值加1，然后打印i的值
    }
}
