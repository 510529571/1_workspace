package own.hhw.cache.test;

import org.apache.ibatis.session.SqlSession;
import own.hhw.cache.po.DataTable;
import own.hhw.cache.po.MoreTable;
import own.hhw.util.MybatisUtil;

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
    public static void main(String[] args) throws InterruptedException {
//        insertData();
        selectListObj1();
    }

    public static void insertData() {
        SqlSession session = MybatisUtil.getSqlSession();

        DataTable data = null;
        for (int i = 1; i < 10000; i++) {
            data = new DataTable();
            data.setId(i + "");
            data.setName("name" + i);
            session.insert("own.hhw.cache.mapper.insertData", data);
        }

        session.commit();
        session.close();
    }

    /**
     * 默认情况下，同一个事务中的两次查询，第二次查询是查缓存的
     * <p/>
     * 测试：将Cache.xml中的缓存配置注解掉
     */
    public static void selectListObj1() throws InterruptedException {
        SqlSession session = MybatisUtil.getSqlSession();

        List<MoreTable> list = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println(list.size());

        Thread.sleep(1000);
        System.out.println("休息完成");

        DataTable data = new DataTable();
        data.setId(01 + "");
        data.setName("name" + 01);
        session.insert("own.hhw.cache.mapper.insertData", data);
//        session.insert("own.hhw.cache.mapper.insertOneTable");

         list= session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println(list.size());

        session.commit();
        session.close();
    }

    /**
     * 默认情况下，不同的事务中的两次查询，第二次查询是查数据库的
     */
    public static void selectListObj2() throws InterruptedException {
        SqlSession session = MybatisUtil.getSqlSession();

        //step:1
        List<MoreTable> list = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println(list.get(0).getName());
        session.commit();

        Thread.sleep(10000);
        //step:2
        List<MoreTable> list2 = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println(list2.get(0).getName());
        session.commit();

        session.close();
    }
}
