package own.hhw;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      /*  Map map = servletRequest.getParameterMap();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            Object obj = map.get(key);
        }
        filterChain.doFilter(servletRequest, servletResponse);
        //To change body of implemented methods use File | Settings | File Templates.*/
        System.out.println("name"+servletRequest.getParameter("name"));
        filterChain.doFilter(new XssServletRequest((HttpServletRequest)servletRequest),servletResponse);

    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
