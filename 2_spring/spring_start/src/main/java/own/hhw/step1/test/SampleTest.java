package own.hhw.step1.test;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import own.hhw.step1.dao.PrintDaoImpl;
import own.hhw.step1.service.PrintServiceImpl;

import java.io.FileNotFoundException;
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
//        String path=new URI(SampleTest.class.getResource("/")+"spring.xml").getPath();
        String path = "F:\\work\\3_project\\1_workspace\\2_spring\\spring_boot\\src\\main\\resources\\spring.xml";
        XmlBeanFactory factory = new XmlBeanFactory(new FileSystemResource((new URI(SampleTest.class.getResource("/") + "spring.xml")).getPath()));
        ApplicationContext ctx = new GenericXmlApplicationContext("spring.xml");
        PrintServiceImpl print = (PrintServiceImpl) ctx.getBean("printServiceImpl");
//        PrintDaoImpl print= (PrintDaoImpl) factory.getBean("printDaoImpl");
        print.print();

    }
}
