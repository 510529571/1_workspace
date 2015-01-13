package own.hhw.select.test;

import own.hhw.select.mapper.Select;
import own.hhw.select.po.SelectObj;
import own.hhw.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-10
 * Time: 下午6:20
 * 通过session来操作数据库
 * select的使用
 */
public class SessionSelect {
    public static void main(String[] args) {
        selectOne();
       /* selectList();
        selectListObj();*/
    }

    /*
    查询一条数据
     */
    public static void selectOne() {
        SqlSession session = MybatisUtil.getSqlSession();

        String s = session.selectOne("own.hhw.select.mapper.Select.seleteOne");
        System.out.println(s);

        String s2 = session.selectOne("own.hhw.select.mapper.Select.seleteOne2", "select '12' from dual");
        System.out.println(s2);

        Select select = session.getMapper(Select.class);

        String s3 = select.update("1");
        System.out.println(s3);

        session.commit();
        session.close();
    }


    /*
    查询多条数据
     */
    public static void selectList() {
        SqlSession session = MybatisUtil.getSqlSession();

        List<String> list = session.selectList("own.hhw.select.mapper.Select.selectList");
        System.out.println(list.get(0));

        session.commit();
        session.close();
    }

    /*
    查询多条数据,数据装载在对象中
     */
    public static void selectListObj() {
        SqlSession session = MybatisUtil.getSqlSession();

        List<SelectObj> list = session.selectList("own.hhw.select.mapper.Select.selectListObj");
        System.out.println(list.get(0).getId());

        session.commit();
        session.close();
    }
}
