import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: ����10:32
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
//        cookie.setMaxAge(-10); // �ر��������ʧЧ
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

        //hhw:task, type ��page/user.jspҳ����û��ֵ��Ӧ�����ض����request�Ѿ�������������
        //������������ô����ֵ��
        request.setAttribute("type","С����");
        System.out.println(request.getAttribute("type"));


        request.getSession().setAttribute("user", username);
        response.setHeader("server","hhw");
        response.sendRedirect("page/user.jsp");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

}
