package own.hhw.cache.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;
import own.hhw.cache.po.DataTable;
import own.hhw.cache.po.MoreTable;
import own.hhw.cache.po.WrapperDataTable;
import own.hhw.select.po.SelectObj;
import own.hhw.util.MybatisUtil;

import java.util.ArrayList;
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
//        testPool();
//        insertMoreDate();
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
     * <property name="poolMaximumActiveConnections" value="3"/>
     * <property name="poolTimeToWait" value="20000"/>
     * <p/>
     * 这个是configuration里面的配置，是用来配置连接池的
     * 第一个参数：设置连接池的最大连接数
     * 第二个参数：设置连接超时时间，如果连接时间超过了20秒，则连接将会被关闭（如果不设置，会默认时间）
     * 注：连接池和数据库的会话一直处于连接状态
     * 连接的开启：连接池分配一个已经存在的连接给消费者
     * 连接的关闭：连接池收回消费者的连接，供其他消费者使用
     */

    public static void testPool() {
        for (int i = 0; i < 5; i++) {
            new PoolThread().start();
        }
    }

    private static class PoolThread extends Thread {
        @Override
        public void run() {
            SqlSession session = MybatisUtil.getSqlSession();
            List<MoreTable> list = session.selectList("own.hhw.cache.mapper.seleteList");
            System.out.println(list.size());
            session.commit();
        }
    }

    /**
     * 默认情况下，同一个事务中的两次查询，第二次查询的顺序：二级缓存->一级缓存->数据库
     * 当一个事务结束后，才设置二级缓存
     * <p/>
     * 测试：将Cache.xml中的缓存配置注解掉
     */
    public static void selectListObj1() throws InterruptedException {
        SqlSession session = MybatisUtil.getSqlSession();

        List<MoreTable> list = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println(list.size());

        Thread.sleep(1000);
        System.out.println("休息完成");
        session.commit();
        session.close();

        session = MybatisUtil.getSqlSession();
        list = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println(list.size());
        session.commit();
        session.close();
    }

    public static void insertMoreDate() {
        SqlSession session = MybatisUtil.getSqlSession();

        WrapperDataTable dataTable = new WrapperDataTable();
        DataTable data = new DataTable();
        data.setId(01 + "");
        data.setName("name" + 01);
        dataTable.setDataTable(data);
        session.insert("own.hhw.cache.mapper.insertData", dataTable);

        session.commit();
        session.close();
//        session.insert("own.hhw.cache.mapper.insertOneTable");
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

    public static void selectListObj3() throws InterruptedException {
        SqlSessionManager session = (SqlSessionManager) MybatisUtil.getAutoSqlSession();

        session.startManagedSession(false);

        List<MoreTable> list = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println("插入第一条数据前：" + list.size());

        DataTable data = new DataTable();
        data.setId(01 + "");
        data.setName("name" + 01);
        session.insert("own.hhw.cache.mapper.insertData", data);

        list = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println("插入第一条数据后：" + list.size());
        session.commit();
        session.close();

        session.startManagedSession(false);

        DataTable data2 = new DataTable();
        data.setId(01 + "");
        data.setName("name" + 01);
        session.insert("own.hhw.cache.mapper.insertData", data);

        list = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println("插入第二条数据后：" + list.size());

        session.commit();
        session.close();
    }
}
