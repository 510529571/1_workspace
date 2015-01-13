package own.hhw;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-22
 * Time: ÉÏÎç9:54
 * To change this template use File | Settings | File Templates.
 */
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("·ÀÖ¹xss¹¥»÷..");
        filterChain.doFilter(new XssServletRequest((HttpServletRequest)servletRequest),servletResponse);

    }

    @Override
    public void destroy() {
    }
}
