package step1;

import java.io.UnsupportedEncodingException;
import java.lang.String;import java.lang.System; /**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-10
 * Time: 下午7:16
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] v="好".getBytes("utf-8");

        for(int i=0;i<v.length;i++){
            System.out.println(v[i]);
        }
    }
}
