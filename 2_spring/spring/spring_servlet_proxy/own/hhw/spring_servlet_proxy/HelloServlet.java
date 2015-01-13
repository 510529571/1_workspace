package own.hhw.spring_servlet_proxy;

import own.hhw.spring_servlet_proxy.service.UserService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-5
 * Time: ÏÂÎç5:23
 * To change this template use File | Settings | File Templates.
 */
public class HelloServlet extends HttpServlet {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Get");
        PrintWriter out = response.getWriter();
        out.println(userService.sayHello("Hello,Spring.Servlet"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Post");
        PrintWriter out = response.getWriter();
        out.println(userService.sayHello("Hello,Spring.Servlet"));
    }

}