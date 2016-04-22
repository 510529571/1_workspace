package own.hhw;

import org.apache.commons.lang.StringUtils;

import java.lang.String;

/**
 * User: hanwei
 * Date: 15-3-10
 * Time: ÉÏÎç10:52
 */
public class Test {
    public static void main(String[] args) {
      /*  ServletResponseWrapper servletResponseWrapper=new ServletResponseWrapper();
        servletResponseWrapper.setCharacterEncoding("123");

        String s="123";
        s.trim();*/

        System.out.println(StringUtils.isBlank(""));
        System.out.println(StringUtils.isBlank(null));
        System.out.println(StringUtils.isEmpty(""));
        System.out.println(StringUtils.isEmpty(null));

//        new ByteArrayInputStream(null);

        String s="123,234.00";
        System.out.println(new Double(s.replace(",","")));
    }
}
