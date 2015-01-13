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
 * Time: ����6:20
 * ͨ��session���������ݿ�
 * select��ʹ��
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
     * Ĭ������£�ͬһ�������е����β�ѯ���ڶ��β�ѯ�ǲ黺���
     * <p/>
     * ���ԣ���Cache.xml�еĻ�������ע���
     */
    public static void selectListObj1() throws InterruptedException {
        SqlSession session = MybatisUtil.getSqlSession();

        List<MoreTable> list = session.selectList("own.hhw.cache.mapper.seleteList");
        System.out.println(list.size());

        Thread.sleep(1000);
        System.out.println("��Ϣ���");

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
}
