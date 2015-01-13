import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: обнГ2:46
 * To change this template use File | Settings | File Templates.
 */
public class MapWrapper {
    public static Map<String, HttpSession> sessionMap = new HashMap<String, HttpSession>();
}