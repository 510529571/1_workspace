import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-17
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
public class MemcachedSessionManager {
    public static final String SESSION_ID_COOKIE = "WebId";
    /*如果session没有变化，则5分钟更新一次memcached*/
    private int expirationUpdateInterval = 5 * 60;
    private int maxInactiveInterval = 30 * 60;
    private static MemcachedSessionManager memcachedSessionManager = null;
    private static MemCachedClient memCachedClient;

    private MemcachedSessionManager() {
        super();
    }

    public static MemcachedSessionManager getInstance() {
        if (memcachedSessionManager == null) {
//            BasicConfigurator.configure();
            String[] servers = {"localhost:11211", "localhost:11213"};
            SockIOPool pool = SockIOPool.getInstance();
            pool.setServers(servers);
            pool.setFailover(true);
            pool.setInitConn(10);
            pool.setMinConn(5);
            pool.setMaxConn(250);
            pool.setMaintSleep(30);
            pool.setNagle(false);
            pool.setSocketTO(3000);
            pool.setAliveCheck(true);
            pool.initialize();
            memCachedClient = new MemCachedClient();
            memcachedSessionManager = new MemcachedSessionManager();
        }

        return memcachedSessionManager;
    }

    public MemcachedHttpSession createSession(SessionHttpServletRequestWrapper request, HttpServletResponse response, boolean create) {
        String sessionId = getRequestedSessionId(request);

        if (StringUtils.isEmpty(sessionId) && create == false) return null;

        MemcachedHttpSession session = null;

        if (StringUtils.isNotEmpty(sessionId)) {
            session = (MemcachedHttpSession) MapWrapper.sessionMap.get(sessionId);

            if (session == null) {
                session = (MemcachedHttpSession) memCachedClient.get(sessionId);
                if (session != null) {
                    MapWrapper.sessionMap.put(sessionId, session);
                    System.out.println("从memcached取的session:" + session);
                }
            } else {
                String memUpdateTime = (String) memCachedClient.get(sessionId + "_version");

                if (session.getUpdateTime() != null && !session.getUpdateTime().equals(memUpdateTime)) {
                    session = (MemcachedHttpSession) memCachedClient.get(sessionId);
                    if (session != null) {
                        MapWrapper.sessionMap.put(sessionId,session);
                        System.out.println("从memcached取的session:" + session);
                    }
                } else {
                    System.out.println("从内存取的session:" + session);
                }
            }
        }

        if (session == null && create) {
            session = createEmptySession(request, response);
            System.out.println("新建session:" + session);
        }
//        if (session != null)
//            attachEvent(session, request, response, requestEventSubject);
        return session;
    }

    /**
     * not use method <code>request.getRequestedSessionId()</code> ,because some problem with websphere implements
     *
     * @param request
     * @return
     */
    private String getRequestedSessionId(HttpServletRequestWrapper request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) return null;
        String sessionId = null;
        for (Cookie cookie : cookies) {
            if (SESSION_ID_COOKIE.equals(cookie.getName())) sessionId = cookie.getValue();
        }
        return sessionId;
    }

    private MemcachedHttpSession createEmptySession(SessionHttpServletRequestWrapper request, HttpServletResponse response) {
        MemcachedHttpSession session = new MemcachedHttpSession();
        session.id = createSessionId();
        session.creationTime = System.currentTimeMillis();
        session.maxInactiveInterval = maxInactiveInterval;
        session.isNew = true;
        saveCookie(session, request, response);
        return session;
    }

    private String createSessionId() {
        return System.currentTimeMillis() + "-" + UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(10);
    }

    private void saveCookie(MemcachedHttpSession session, HttpServletRequestWrapper request, HttpServletResponse response) {
        if (session.isNew == false && session.expired == false) return;

        Cookie cookie = new Cookie(SESSION_ID_COOKIE, null);
        cookie.setPath(request.getContextPath());
        if (session.expired) {
            cookie.setMaxAge(0);
        } else if (session.isNew) {
            cookie.setValue(session.getId());
        }
        response.addCookie(cookie);

    }

    public void saveSession(MemcachedHttpSession session) {
        if (session.isNew || session.isDirty) {
            session.updateTime();
            MapWrapper.sessionMap.put(session.id, session);
            memCachedClient.set(session.id, session);
            memCachedClient.set(session.id + "_version", session.getUpdateTime());
        }
    }
}
