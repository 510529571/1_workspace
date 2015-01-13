import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public class Login extends HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        request.getSession().invalidate();

        System.out.println("sessionId:" + request.getRequestedSessionId());

//        MapWrapper.sessionMap.put(request.getRequestedSessionId(), request.getSession());
//
//        Cookie cookie = new Cookie("hhwId", "12346");
//        cookie.setPath("/");
//        cookie.setMaxAge(-10); // 关闭浏览器即失效
//        response.addCookie(cookie);

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(new MemcachedHttpSession());
//        oos.flush();
//        String byteStr = new String(baos.toByteArray());
//        System.out.println(byteStr);
//
//        ByteArrayInputStream bs = new ByteArrayInputStream(byteStr.getBytes());
//        ObjectInputStream ois = new ObjectInputStream(bs);
//        try {
//            HttpSession session = ((MemcachedHttpSession) ois.readObject());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }

        //hhw:task, type 在page/user.jsp页面中没有值，应该是重定向后request已经不是用来的了
        //看看别人是怎么设置值的
        request.setAttribute("type","小靓仔");
        System.out.println(request.getAttribute("type"));


        request.getSession().setAttribute("user", username);
        response.setHeader("server","hhw");
        response.sendRedirect("page/user.jsp");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

}
