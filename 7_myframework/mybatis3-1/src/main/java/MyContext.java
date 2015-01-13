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
 * Time: ����3:52
 * To change this template use File | Settings | File Templates.
 */
public class MyContext {
    public static Map<String, SqlMapper> mappers;

    /**
     * �������ݵĲ���
     */
    public static void insert(String key, Object object) {
        String sqlTemplate = mappers.get(key).getSql();
        String sql = renderTemplate(sqlTemplate, object);

        JDBConnection jdbConnection = new JDBConnection();
        int i = jdbConnection.executeUpdate(sql);

        System.out.println("ִ�е�sql"+sql);
        System.out.println("��������������"+i);

    }

    /**
     * ��ѯ���ݵĲ���
     */
    public Object select(String key, Object object) {


        return null;
    }

    /**
     * ��Ⱦģ��
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
     * װ�ط��ص�����
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
