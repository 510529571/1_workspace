package own.hhw;

import javax.servlet.ServletResponseWrapper;
import java.lang.String;

/**
 * User: hanwei
 * Date: 15-3-10
 * Time: ионГ10:52
 */
public class Test {
    public static void main(java.lang.String[] args) {
        ServletResponseWrapper servletResponseWrapper=new ServletResponseWrapper();
        servletResponseWrapper.setCharacterEncoding("123");

        String s="123";
        s.trim();
    }
}
