package own.hhw.lang.math;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-23
 * Time: ����2:10
 * To change this template use File | Settings | File Templates.
 */
public class Test_Math {
    /**
     * hhw:tag �����������ȡ0��10000�������
     */
    @Test
    public void test_random() {
        System.out.println(Math.round(Math.random() * 10000));
    }
}
