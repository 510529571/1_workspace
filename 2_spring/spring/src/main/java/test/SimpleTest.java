package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import own.hhw.spring.Action;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-5
 * Time: ÉÏÎç11:14
 * To change this template use File | Settings | File Templates.
 */
public class SimpleTest {
    public static void main(String[] args) throws URISyntaxException {
        testQuickStart();
    }
    public static void testQuickStart() throws URISyntaxException {
        ApplicationContext ctx = new FileSystemXmlApplicationContext((new URI(SimpleTest.class.getResource("/")+"bean.xml")).getPath());
        Action action = (Action) ctx.getBean("TheAction");
        System.out.println(action.execute("Rod Johnson"));
    }
}