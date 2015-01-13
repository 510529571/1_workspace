package chapter10;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-30
 * Time: ÏÂÎç1:48
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args) {
        TestPaper1 testPaper1=new TestPaper1();
        testPaper1.testQuestion1();
        testPaper1.testQuestion2();
        testPaper1.testQuestion3();
        System.out.println("=================");
        TestPaper2 testPaper2=new TestPaper2();
        testPaper2.testQuestion1();
        testPaper2.testQuestion2();
        testPaper2.testQuestion3();
    }
}
