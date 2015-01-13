package chapter10;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-30
 * Time: 下午1:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class TestPaper {
    public void testQuestion1() {
        System.out.println("选择题1【】 a.a b.b c.c d.d");
        System.out.println("答案:" + answer1());
    }

    public void testQuestion2() {
        System.out.println("选择题2【】 a.a b.b c.c d.d");
        System.out.println("答案:" + answer2());
    }

    public void testQuestion3() {
        System.out.println("选择题3【】 a.a b.b c.c d.d");
        System.out.println("答案:" + answer3());
    }

    public abstract String answer1();

    public abstract String answer2();

    public abstract String answer3();
}
