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
 * Time: ����6:20
 * ͨ��session���������ݿ�
 * select��ʹ��
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
     * �����configuration��������ã��������������ӳص�
     * ��һ���������������ӳص����������
     * �ڶ����������������ӳ�ʱʱ�䣬�������ʱ�䳬����20�룬�����ӽ��ᱻ�رգ���������ã���Ĭ��ʱ�䣩
     * ע�����ӳغ����ݿ�ĻỰһֱ��������״̬
     * ���ӵĿ��������ӳط���һ���Ѿ����ڵ����Ӹ�������
     * ���ӵĹرգ����ӳ��ջ������ߵ����ӣ�������������ʹ��
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
     * Ĭ������£�ͬһ�������е����β�ѯ���ڶ��β�ѯ��˳�򣺶�������->һ������->���ݿ�
     * ��һ����������󣬲����ö�������
     * <p/>
     * ���ԣ���Cache.xml�еĻ�������ע���
     */
    public static void selectListObj1() throws InterruptedException {
        SqlSession session = MybatisUtil.getSqlSession();

        List<MoreTable> list = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println(list.size());

        Thread.sleep(1000);
        System.out.println("��Ϣ���");
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
     * Ĭ������£���ͬ�������е����β�ѯ���ڶ��β�ѯ�ǲ����ݿ��
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
        System.out.println("�����һ������ǰ��" + list.size());

        DataTable data = new DataTable();
        data.setId(01 + "");
        data.setName("name" + 01);
        session.insert("own.hhw.cache.mapper.insertData", data);

        list = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println("�����һ�����ݺ�" + list.size());
        session.commit();
        session.close();

        session.startManagedSession(false);

        DataTable data2 = new DataTable();
        data.setId(01 + "");
        data.setName("name" + 01);
        session.insert("own.hhw.cache.mapper.insertData", data);

        list = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println("����ڶ������ݺ�" + list.size());

        session.commit();
        session.close();
    }
}
