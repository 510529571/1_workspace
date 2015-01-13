package listener1;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: ÏÂÎç4:10
 * To change this template use File | Settings | File Templates.
 */
public class MySessionContext {
    private static MySessionContext instance;
    private HashMap mymap;

    private MySessionContext() {
        mymap = new HashMap();
    }

    public static MySessionContext getInstance() {
        if (instance == null) {
            instance = new MySessionContext();
        }
        return instance;
    }

    public synchronized void AddSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session);
        }
    }

    public synchronized void DelSession(HttpSession session) {
        if (session != null) {
            mymap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String session_id) {
        if (session_id == null) return null;
        return (HttpSession) mymap.get(session_id);
    }
}
