package own.hhw;

import java.io.UnsupportedEncodingException;

/**
 * User: hanwei
 * Date: 15-11-11
 * Time: обнГ5:48
 */
public class Test {
    public static void main(String[] args) {

        try {
            java.net.URLEncoder.encode(null, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
