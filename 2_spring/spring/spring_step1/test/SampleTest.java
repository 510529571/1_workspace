package test;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import own.hhw.step1.service.PrintServiceImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-5
 * Time: ����3:22
 * To change this template use File | Settings | File Templates.
 */
public class SampleTest {
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        System.out.println(SampleTest.class.getResource("/")); //�����·��ǰ�����һ��file:\��Ȼ�����Ͳ���ֱ��ʹ����
//        InputStream is = new FileInputStream((new URI(SampleTest.class.getResource("/")+"//spring.xml")).getPath());
        XmlBeanFactory factory = new XmlBeanFactory(new FileSystemResource((new URI(SampleTest.class.getResource("/")+"spring.xml")).getPath()));
        PrintServiceImpl printService= (PrintServiceImpl) factory.getBean("printService");
        printService.print();
    }
}
