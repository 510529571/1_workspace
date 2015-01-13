package own.hhw.resultMap.test;

import org.apache.ibatis.session.SqlSession;
import own.hhw.resultMap.po.Ctable1;
import own.hhw.resultMap.po.Ctable2;
import own.hhw.resultMap.po.Ptable;
import own.hhw.resultMap.po2.MoreTable;
import own.hhw.resultMap.po2.OneTable;
import own.hhw.util.MybatisUtil;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-11
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public class ResultMapTest {
    public static void main(String[] args) {
        testDiscriminator();
        testAssociation();
    }

    /**
     * 查询订单，有父订单，父订单下还有两种子订单
     * 查出的数据列表中会出现两种类型的数据
     */
    public static void testDiscriminator() {
        SqlSession session = MybatisUtil.getSqlSession();

        List<Ptable> list = session.selectList("own.hhw.resultMap.mapper.ResultMap.select");
        for (Ptable p : list) {
            if (p instanceof Ctable1) {
                Ctable1 c = (Ctable1) p;
                System.out.println("object:" + c.getPid() + "," + c.getCid1());
            } else if (p instanceof Ctable2) {
                Ctable2 c = (Ctable2) p;
                System.out.println("object:" + c.getPid() + "," + c.getCid2());
            }
        }

        session.commit();
        session.close();
    }

    /**
     * 多对一的查询和一对一的查询
     */
    public static void testAssociation() {
        SqlSession session = MybatisUtil.getSqlSession();

        List<MoreTable> list = session.selectList("own.hhw.resultMap.mapper.ResultMap.selectMoreOne");

        for (MoreTable o:list)
        {
            System.out.println(o.getId()+","+o.getName()+","+o.getOneTable().getClassName());
        }

        session.commit();
        session.close();
    }
}
