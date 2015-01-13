package session;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import junit.framework.Assert;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * function description
 *
 * @author huzl
 * @version 1.0.0
 */
public class MemcachedSessionTests {
    MemcachedSessionManager sessionManager;
    MemCachedClient memcachedClient;
    String sessionId;
    HttpSession session;
    Map map = new HashMap();

    @Before
    public void setup() throws Exception {
        sessionManager = new MemcachedSessionManager();

        BasicConfigurator.configure();
        String[] servers = {"localhost:11211", "localhost:11220"};
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
        memcachedClient = new MemCachedClient();
        sessionManager.setMemcachedClient(memcachedClient);
    }

    public static HttpServletRequestInitial emptyRequest() {
        return new HttpServletRequestInitial() {
            public void init(MockHttpServletRequest request) {

            }
        };
    }

    public static HttpServletRequestInitial sessionRequest(final String sessionId) {
        return new HttpServletRequestInitial() {
            public void init(MockHttpServletRequest request) {
                request.setCookies(new Cookie[]{new Cookie(MemcachedSessionManager.SESSION_ID_COOKIE, sessionId)});
                request.setRequestedSessionId(sessionId);
            }
        };
    }

    @Test
    public void testMemcachedClient() throws Exception {
        memcachedClient.set("111", "value 1111111");
        Assert.assertEquals(memcachedClient.get("111"), "value 1111111");
        Assert.assertNull(memcachedClient.get("1112"));
    }

    public void testSession(HttpServletRequestInitial requestInitial, FilterChain filterChain, SessionVerify verifier) throws Exception {
        final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test/a.do");
        final MockHttpServletResponse response = new MockHttpServletResponse();
        requestInitial.init(request);
        MemcachedSessionFilter sessionFilter = new MemcachedSessionFilter();
        sessionFilter.setSessionManager(sessionManager);
        sessionFilter.doFilter(request, response, filterChain);
        verifier.verify(request, response);
    }

    public void testEmptySession() throws Exception {
        testSession(emptyRequest(), new MockFilterChain() {
                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse response) {
                        HttpServletRequest request = (HttpServletRequest) servletRequest;
                        session = request.getSession(false);
                        Assert.assertNull(session);
                    }
                }, new SessionVerify() {
                    public void verify(MockHttpServletRequest request, MockHttpServletResponse response) {

                    }
                }
        );

        testSession(sessionRequest("1234"), new MockFilterChain() {
                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse response) {
                        HttpServletRequest request = (HttpServletRequest) servletRequest;
                        session = request.getSession(false);
                        Assert.assertNull(session);
                    }
                }, new SessionVerify() {
                    public void verify(MockHttpServletRequest request, MockHttpServletResponse response) {

                    }
                }
        );
    }

    @Test
    public void testNewSession() throws Exception {

        testSession(sessionRequest("1234"), new MockFilterChain() {
                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse response) {
                        HttpServletRequest request = (HttpServletRequest) servletRequest;
                        session = request.getSession();
                        session.setAttribute("aaa", "v_aaaa");
                    }
                }, new SessionVerify() {
                    public void verify(MockHttpServletRequest request, MockHttpServletResponse response) {
                        Cookie cookie = response.getCookie("JSESSIONID");
                        Assert.assertNotNull(cookie);
                        sessionId = cookie.getValue();
                        System.out.println("new session id=" + sessionId);
//                        Assert.assertEquals(map.size(), 1);
                    }
                }
        );
        final HttpSession lastSession = this.session;
        testSession(sessionRequest(sessionId), new MockFilterChain() {
                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse response) {
                        HttpServletRequest request = (HttpServletRequest) servletRequest;
                        session = request.getSession();
                        session.setAttribute("bbb", "v_bbb");

                    }
                }, new SessionVerify() {
                    public void verify(MockHttpServletRequest request, MockHttpServletResponse response) {
                        Cookie cookie = response.getCookie("JSESSIONID");
                        Assert.assertNull(cookie);
                        Assert.assertEquals(session.getAttribute("aaa"), "v_aaaa");
                        Assert.assertEquals(lastSession, session);
                    }
                }
        );
    }

    @Test
    public void testInvalidSession() throws Exception {

        //createAndInvalid
        testSession(sessionRequest("1234"), new MockFilterChain() {
                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse response) {
                        HttpServletRequest request = (HttpServletRequest) servletRequest;
                        session = request.getSession();
                        session.setAttribute("aaa", "v_aaaa");
                        session.invalidate();
                    }
                }, new SessionVerify() {
                    public void verify(MockHttpServletRequest request, MockHttpServletResponse response) {
                        Cookie cookie = response.getCookie("JSESSIONID");
//                        Assert.assertNull(cookie);
                        Assert.assertEquals(map.size(), 0);
                    }
                }
        );

        //createSession
        testSession(sessionRequest("1234"), new MockFilterChain() {
                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse response) {
                        HttpServletRequest request = (HttpServletRequest) servletRequest;
                        session = request.getSession();
                        session.setAttribute("aaa", "v_aaaa");
                    }
                }, new SessionVerify() {
                    public void verify(MockHttpServletRequest request, MockHttpServletResponse response) {
                        Cookie cookie = response.getCookie("JSESSIONID");
                        Assert.assertNotNull(cookie);
                        sessionId = cookie.getValue();
//                        Assert.assertEquals(map.size(), 1);
                    }
                }
        );

        //invalidate
        testSession(sessionRequest(sessionId), new MockFilterChain() {
                    @Override
                    public void doFilter(ServletRequest servletRequest, ServletResponse response) {
                        HttpServletRequest request = (HttpServletRequest) servletRequest;
                        session = request.getSession();
                        session.setAttribute("bbb", "v_aaaa");
                        session.invalidate();
                    }
                }, new SessionVerify() {
                    public void verify(MockHttpServletRequest request, MockHttpServletResponse response) {
                        Cookie cookie = response.getCookie("JSESSIONID");
                        Assert.assertNotNull(cookie);
                        Assert.assertTrue(StringUtils.isEmpty(cookie.getValue()));
                        Assert.assertEquals(0, map.size());
                    }
                }
        );
    }
}

interface SessionVerify {
    void verify(MockHttpServletRequest request, MockHttpServletResponse response);
}

interface HttpServletRequestInitial {

    void init(MockHttpServletRequest request);
}
