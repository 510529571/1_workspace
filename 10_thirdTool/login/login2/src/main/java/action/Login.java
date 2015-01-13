package action;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: ÉÏÎç10:32
 * To change this template use File | Settings | File Templates.
 */
public class Login extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        System.out.println("sessionId:" + request.getRequestedSessionId());


        request.getSession().setAttribute("user", username + ":" + password);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

}
