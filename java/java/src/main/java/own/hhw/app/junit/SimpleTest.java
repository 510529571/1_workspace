package own.hhw.app.junit;

import java.util.*;

import junit.framework.*;

public class SimpleTest extends TestCase {
    public SimpleTest(String name) {
        super(name);
    }

    // 2、写一个测试方法断言期望的结果
    public void testEmptyCollection() {
        Collection collection = new ArrayList();
        assertTrue(collection.isEmpty());
    }

    // 注 junit
    // 意：JUnit推荐的做法是以test作为待测试的方法的开头，这样这些方法可以被自动找到并被测试。
// 3、写一个suite()方法，它会使用反射动态的创建一个包含所有的testXxxx方法的测试套件
    public static Test suite() {
        return new TestSuite(SimpleTest.class);
    }

    // 4、写一个main()方法以文本运行器的方式方便的运行测试
    public static void main(String args[]) {
        junit.textui.TestRunner.run(suite());
    }

}