package own.hhw;

import org.apache.commons.lang.StringEscapeUtils;
import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-21
 * Time: 下午5:19
 * To change this template use File | Settings | File Templates.
 */
public class DwrXssFilter implements AjaxFilter {
    public Object doFilter(Object o, Method method, Object[] objects, AjaxFilterChain ajaxFilterChain) throws Exception {
        Object obj = null;
        for (int i = 0; i < objects.length; i++) {
            obj = objects[i];
            if (obj instanceof String) {
                obj = StringEscapeUtils.escapeHtml((String) obj);
                obj = StringEscapeUtils.escapeJavaScript((String) obj);
                System.out.println("处理后的数据" + obj);
                objects[i] = obj;
            }
        }

        return ajaxFilterChain.doFilter(o, method, objects);
    }

}
