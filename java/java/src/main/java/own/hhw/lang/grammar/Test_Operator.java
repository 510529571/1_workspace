package own.hhw.lang.grammar;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-23
 * Time: ����3:22
 * To change this template use File | Settings | File Templates.
 */
public class Test_Operator {
    @Test
    public void test1() {
        int i = 5;
        System.out.println(i++);      //�ȴ�ӡi��ֵ��Ȼ���ڰ�i��ֵ��1
        System.out.println(++i);      //�Ȱ�i��ֵ��1��Ȼ���ӡi��ֵ
    }
}
