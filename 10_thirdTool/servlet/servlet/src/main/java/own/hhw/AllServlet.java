package own.hhw;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-22
 * Time: ����3:08
 * ��������idea��ctrl+shitf+F9�Ĳ�����
 * �����ݼ�����������������1.�����࣬2�����ൽjvm
 *
 * ����servlet��Ŀ��Ȼ��ʹ�ÿ�ݼ���������࣬���������servlet������
 * ������ʹ�õ������servlet�������
 */
public class AllServlet extends HttpServlet {
    public String s="var";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet.AllServlet.doPost()");
//        super.doPost(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("servlet.AllServlet.doGet(1)");
//        super.doGet(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }

    static{
        System.out.println("AllServlet.static{1}");
    }
}
