package own.hhw.spring_servlet_proxy;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-5
 * Time: ����5:21
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
/**
 * �����Լ�ʵ�ֵ�һ�����������ڽ�ServletתΪSpring�����Servlet Bean
 */
public class ServletToBeanProxy extends GenericServlet {

    private String targetBean;//��ǰ�ͻ��������Servlet����
    private Servlet proxy;//����Servlet

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()); //��ʼ��Spring����
        this.targetBean = getServletName();
        this.proxy = (Servlet) wac.getBean(targetBean);//����ServletBean
        proxy.init(getServletConfig());//���ó�ʼ��������ServletConfig����Bean
    }

    @Override
    public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
        proxy.service(arg0, arg1);//��service�����е���bean��service������servlet����ݿͻ�������ȥ������Ӧ�����󷽷���Get/Post��
    }
}
