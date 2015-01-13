package own.hhw.spring_servlet_proxy;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import own.hhw.spring_servlet_proxy.service.UserService;

import javax.servlet.Servlet;
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
 * Time: 下午5:36
 * To change this template use File | Settings | File Templates.
 */
public class SayServlet extends HttpServlet {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Get");
        response.getWriter().println(userService.sayHello("say,Spring.Servlet"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Post");
        response.getWriter().println(userService.sayHello("say,Spring.Servlet"));
    }

    public void init() throws ServletException {
        super.init();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); //初始化Spring容器
        userService= (UserService) wac.getBean("userService");//调用ServletBean
    }

}
