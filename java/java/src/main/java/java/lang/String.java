package java.lang;

import org.apache.commons.lang.StringUtils;

/**
 * User: hanwei
 * Date: 15-3-10
 * Time: ионГ10:54
 */
public class String {
    public void trim(){
        System.out.println("trim");
    }

    public String(String s) {
    }

    public static void main(String[] args) {
        new String("123").trim();

    }
}
