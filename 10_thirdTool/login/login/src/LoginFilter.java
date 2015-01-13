import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-18
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("登录filter被初始化了！");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println(((HttpServletRequest) request).getRequestURI());
        System.out.println(((HttpServletRequestWrapper) request).getSession().getAttribute("user"));


        if (((HttpServletRequest) request).getRequestURI().indexOf("index.jsp") != -1) {
            chain.doFilter(request, response);
        } else if (((HttpServletRequest) request).getRequestURI().indexOf("Login.do") != -1) {
            chain.doFilter(request, response);
        } else if (((HttpServletRequestWrapper) request).getSession().getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            request.setAttribute("errorMsg", "请先登录！");
            ((HttpServletResponse) response).sendRedirect("index.jsp");
        }

    }

    @Override
    public void destroy() {
        System.out.println("登录filter被销毁了！");
    }
}
