package action;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: …œŒÁ10:35
 * To change this template use File | Settings | File Templates.
 */
public class Submit extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("sessionId:"+request);
        System.out.println("Ã·Ωª£∫"+request.getSession().getAttribute("user"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }
}
