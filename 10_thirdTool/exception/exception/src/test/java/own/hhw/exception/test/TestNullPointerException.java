package own.hhw.exception.test;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-3-21
 * Time: обнГ3:43
 * To change this template use File | Settings | File Templates.
 */
public class TestNullPointerException {
    @Test
    public String test1()
    {
        String s=null;
       return s.indexOf("")+"";
    }

    public static void main(String[] args) {
        String s=null;
         s.indexOf("");
    }
}
