package own.hhw.sql.test;

import org.apache.ibatis.session.SqlSession;
import own.hhw.select.po.SelectObj;
import own.hhw.util.MybatisUtil;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-11
 * Time: 上午10:12
 * To change this template use File | Settings | File Templates.
 */
public class SessionSql {
    public static void main(String[] args) {
        sql();
    }

    /*
   查询多条数据,数据装载在对象中
    */
    public static void sql() {
        SqlSession session = MybatisUtil.getSqlSession();
//        下面直接执行有问题
//        session.update("own.hhw.sql.mapper.Sql.excSql");

        String s=session.selectOne("own.hhw.sql.mapper.Sql.seleteOne");
        System.out.println(s);


        session.commit();
        session.close();
    }
}
