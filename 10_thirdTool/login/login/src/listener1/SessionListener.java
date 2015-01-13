package listener1;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: ����4:10
 * To change this template use File | Settings | File Templates.
 */
public class SessionListener implements HttpSessionListener {
    public static Map userMap = new HashMap();
    private MySessionContext myc = MySessionContext.getInstance();

    /**
     * hhw:task session����ʲôʱ�򱻴������������ͻᱻ������
     * @param httpSessionEvent
     */
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        myc.AddSession(httpSessionEvent.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        myc.DelSession(session);
    }

}
