import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: …œŒÁ10:35
 * To change this template use File | Settings | File Templates.
 */
public class Submit extends HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
//        request=new SessionHttpServletRequestWrapper(request);
//        System.out.println("sessionId:"+request);
        System.out.println("Ã·Ωª£∫"+request.getSession().getAttribute("user"));
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        doPost(request, response);
    }
}
