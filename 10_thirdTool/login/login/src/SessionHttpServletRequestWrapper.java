import listener1.MySessionContext;

import javax.servlet.http.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: ÏÂÎç2:35
 * To change this template use File | Settings | File Templates.
 */
public class SessionHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private javax.servlet.http.HttpSession httpSession = null;
    private HttpServletResponse response;
    MemcachedSessionManager memcachedSessionManager = null;
    private String s;

    public SessionHttpServletRequestWrapper(HttpServletRequest request, HttpServletResponse response, MemcachedSessionManager memcachedSessionManager) {
        super(request);
        this.response = response;
        this.memcachedSessionManager = memcachedSessionManager;
    }

    @Override
    public HttpSession getSession() {
//        MySessionContext mySessionContext = MySessionContext.getInstance();
//        httpSession = mySessionContext.getSession((request.getRequestedSessionId()));

        if (httpSession != null) return httpSession;

        httpSession = memcachedSessionManager.createSession(this, response, true);

        System.out.println("session:" + httpSession);

        return httpSession;
    }

}
