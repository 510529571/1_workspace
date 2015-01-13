import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-11-27
 * Time: ÏÂÎç5:38
 * To change this template use File | Settings | File Templates.
 */
public class StringTempAndDate {
    public static void main(String[] args) {
        try {
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
        }
    }


}

