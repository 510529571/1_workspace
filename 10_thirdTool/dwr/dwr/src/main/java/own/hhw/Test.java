package own.hhw;

import org.owasp.esapi.ESAPI;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-23
 * Time: ионГ9:48
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        String s="123";
        System.out.println(ESAPI.encoder().encodeForHTML(s));
    }
}
