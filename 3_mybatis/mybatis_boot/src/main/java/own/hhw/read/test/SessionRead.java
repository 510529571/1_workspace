package own.hhw.read.test;

import org.apache.ibatis.session.SqlSession;
import own.hhw.read.mapper.Read;
import own.hhw.select.mapper.Select;
import own.hhw.select.po.SelectObj;
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
public class SessionRead {
    public static void main(String[] args) {
        selectOne();
    }

    /**
     *
     */
    public static void selectOne() {
        SqlSession session = MybatisUtil.getSqlSession();

        String s = session.selectOne("own.hhw.read.mapper.Read.selectOne","1");
        System.out.println(s);

        Read select = session.getMapper(Read.class);
        String s3 = select.selectOne("1");
        System.out.println(s3);

        session.commit();
        session.close();
    }


}
