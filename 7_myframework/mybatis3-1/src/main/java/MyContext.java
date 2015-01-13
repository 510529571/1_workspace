import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import own.hhw.User;

import java.io.StringWriter;
import java.sql.ResultSet;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-11-26
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
public class MyContext {
    public static Map<String, SqlMapper> mappers;

    /**
     * 插入数据的操作
     */
    public static void insert(String key, Object object) {
        String sqlTemplate = mappers.get(key).getSql();
        String sql = renderTemplate(sqlTemplate, object);

        JDBConnection jdbConnection = new JDBConnection();
        int i = jdbConnection.executeUpdate(sql);

        System.out.println("执行的sql"+sql);
        System.out.println("插入数据条数："+i);

    }

    /**
     * 查询数据的操作
     */
    public Object select(String key, Object object) {


        return null;
    }

    /**
     * 渲染模板
     */
    private static String renderTemplate(String sqlTemplate, Object object) {
        String retStr = "";
        try {
            Configuration cfg = new Configuration();
            StringTemplateLoader stl = new StringTemplateLoader();
            stl.putTemplate("", sqlTemplate);
            cfg.setTemplateLoader(stl);
            Template template = cfg.getTemplate("");

            StringWriter writer = new StringWriter();
            template.process(object, writer);
            retStr = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(retStr);
        return retStr;
    }

    /**
     * 装载返回的数据
     */
    public Object boxingObj(ResultSet rs, String objectType) {

        return null;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUserName("hanwei");
        user.setPassWord("123456");
        String sql = "INSERT INTO LINEITEM (userName, passWord)\n" +
                "        VALUES (${userName}, ${passWord})";
        new MyContext().renderTemplate(sql, user);
    }
}
