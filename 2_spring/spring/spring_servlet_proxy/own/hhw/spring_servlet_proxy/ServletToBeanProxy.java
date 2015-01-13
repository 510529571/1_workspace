package own.hhw.spring_servlet_proxy;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-5
 * Time: 下午5:21
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
/**
 * 我们自己实现的一个代理类用于将Servlet转为Spring管理的Servlet Bean
 */
public class ServletToBeanProxy extends GenericServlet {

    private String targetBean;//当前客户端请求的Servlet名字
    private Servlet proxy;//代理Servlet

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); //初始化Spring容器
        this.targetBean = getServletName();
        this.proxy = (Servlet) wac.getBean(targetBean);//调用ServletBean
        proxy.init(getServletConfig());//调用初始化方法将ServletConfig传给Bean
    }

    @Override
    public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        proxy.service(arg0, arg1);//在service方法中调用bean的service方法，servlet会根据客户的请求去调用相应的请求方法（Get/Post）
    }
}
