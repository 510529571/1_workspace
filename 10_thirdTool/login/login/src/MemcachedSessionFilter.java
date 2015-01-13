import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: 下午3:59
 * To change this template use File | Settings | File Templates.
 */
public class MemcachedSessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("tomcat接受请求！");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
        servletRequest.setCharacterEncoding("UTF-8");
        SessionHttpServletRequestWrapper sessionHttpServletRequestWrapper =null;
        try {

             sessionHttpServletRequestWrapper = new SessionHttpServletRequestWrapper((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, MemcachedSessionManager.getInstance());
            filterChain.doFilter(sessionHttpServletRequestWrapper, servletResponse);
        } catch (Exception e) {
        } finally {
            MemcachedSessionManager.getInstance().saveSession((MemcachedHttpSession) sessionHttpServletRequestWrapper.getSession());
        }
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
