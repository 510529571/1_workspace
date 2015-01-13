package own.hhw.listener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: ÏÂÎç4:37
 * To change this template use File | Settings | File Templates.
 */
public class ListenerServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mhd = req.getParameter("mhd");
        if ("set".equals(mhd)) {
            set(req);
        } else if ("get".equals(mhd)) {
            get(req);
        }
    }

    private void set(HttpServletRequest req) {
//        req.getSession().setAttribute("name", "hanwei");
    }

    private void get(HttpServletRequest req) {
        MySessionContext mySessionContext = MySessionContext.getInstance();
        HttpSession session = mySessionContext.getSession(req.getRequestedSessionId());
        System.out.println(session.getAttribute("name"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
