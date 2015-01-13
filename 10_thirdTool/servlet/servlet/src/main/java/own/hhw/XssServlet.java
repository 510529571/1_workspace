package own.hhw;


import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-17
 * Time: 下午3:58
 * To change this template use File | Settings | File Templates.
 */
public class XssServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");

//        name= StringEscapeUtils.escapeHtml(name);  //将字符串中的特殊字字符进行了htmlEncode，解决xxs的问题
//        name=StringEscapeUtils.escapeJavaScript(name);
        request.getSession().setAttribute("name", name);

        System.out.println(name);

        response.sendRedirect("module/testXSS.jsp");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
