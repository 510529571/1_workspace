package own.hhw.lang.grammar;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-16
 * Time: 下午1:44
 * To change this template use File | Settings | File Templates.
 */
public class Test_Break {
    /**
     * 有test
     */
    @Test
    public void test1() {
        {
            back:
            for (int i = 0; i < 10; i++) {
                if (i == 9) {
                    System.out.println("break");
                    break back;
                }
            }
            System.out.println("test");
        }
    }

    /**
     * 没有test了！！
     */
    @Test
    public void test2() {
        back:
        {
            for (int j = 0; j < 10; j++) {
                for (int i = 0; i < 10; i++) {
                    if (i == 9) {
                        System.out.println("break");
                        break back;
                    }
                    System.out.println("test1");
                }
            }
            System.out.println("test2");
        }
        System.out.println("test3");
    }

    @Test
    public void test3() {
        back:
        {
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                if (i == 9) {
                    System.out.println("break");
                    break back;
                }
                return;
            }
            System.out.println("test2");
        }
        }
        System.out.println("test3");
    }
}
