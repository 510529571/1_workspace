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
 * Time: 下午3:08
 * 用来测试idea中ctrl+shitf+F9的操作的
 * 这个快捷键进行了两个操作，1.编译类，2加载类到jvm
 *
 * 启动servlet项目，然后使用快捷键编译这个类，接着请求此servlet，发现
 * 请求所使用的是这个servlet类的内容
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
