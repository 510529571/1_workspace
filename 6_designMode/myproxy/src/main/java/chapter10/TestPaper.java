package chapter10;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-30
 * Time: ����1:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class TestPaper {
    public void testQuestion1() {
        System.out.println("ѡ����1���� a.a b.b c.c d.d");
        System.out.println("��:" + answer1());
    }

    public void testQuestion2() {
        System.out.println("ѡ����2���� a.a b.b c.c d.d");
        System.out.println("��:" + answer2());
    }

    public void testQuestion3() {
        System.out.println("ѡ����3���� a.a b.b c.c d.d");
        System.out.println("��:" + answer3());
    }

    public abstract String answer1();

    public abstract String answer2();

    public abstract String answer3();
}
