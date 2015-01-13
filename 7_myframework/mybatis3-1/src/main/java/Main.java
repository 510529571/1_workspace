import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.dom4j.DocumentException;
import own.hhw.User;

import java.io.StringWriter;
import java.net.MalformedURLException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-11-26
 * Time: ÏÂÎç4:28
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InstantiationException, DocumentException, IllegalAccessException {
        XMLreader.readConfig();

        System.out.println(MyContext.mappers.toString());

        User b = new User();
        b.setUserName("aaa");
        b.setPassWord("123456");

        MyContext.insert("insert.insertUser", b);



     /*   try {
            Configuration cfg = new Configuration();
            StringTemplateLoader stl =  new StringTemplateLoader();
            stl.putTemplate("", "hello£º${userName}");
            cfg.setTemplateLoader(stl);
            Template template = cfg.getTemplate("");

            User b = new User();
            b.setUserName("aaa");

            StringWriter writer = new StringWriter();
            template.process(b, writer);
            System.out.println(writer.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
}
