package own.hhw.threeDES;

import org.apache.commons.lang.ArrayUtils;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-12
 * Time: ÏÂÎç5:39
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        InputStream ofis = new FileInputStream("F:\\test\\1\\1.txt");


        byte[] btyes = new byte[1024];

        byte[] tbytes=new byte[0];

        tbytes= ArrayUtils.addAll(tbytes,btyes);
        tbytes= ArrayUtils.addAll(tbytes,btyes);

        System.out.println(tbytes.length);

        System.out.println(ofis.read(btyes));
        System.out.println(ofis.read(btyes));

    }
}
