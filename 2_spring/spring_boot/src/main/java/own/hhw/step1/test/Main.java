package own.hhw.step1.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-11
 * Time: 下午4:34
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = URLEncoder.encode("申请平台", "utf-8");
        String s2 = URLEncoder.encode("申请平台", "gbk");
        System.out.println(s);
        System.out.println(s2);

//        String m1="%3C?xml%20version=%221.0%22%20encoding=%22UTF-8%22?%3E%3Cresponse%3E%3Chead%3E%3CmerchId%3E%20%20%20%20%3C/merchId%3E%3CtimeStamp%3E%20%20%20%3C/timeStamp%3E%3Cversion%3EV1.0%3C/version%3E%3C/head%3E%3Cbody%3E%3CretCode%3E0000%3C/retCode%3E%3CretMsg%3E%20%20%20%20%3C/retMsg%3E%3CtraceNo%3E%20%20%20%20%20%20%20%20%20%20%20%3C/traceNo%3E%3CsignNo%3E123456%3C/signNo%3E%3CsignTime%3EYYYYMMDDHH24MISS%3C/signTime%3E%3C/body%3E%3Csign%3E%3C/sign%3E%3C/response%3E";
        String m1="%E5%A5%BD";
        String m2= URLDecoder.decode(m1,"utf-8");
        String m3= URLDecoder.decode(m1,"gbk");
        System.out.println(m2);
        System.out.println(m3);
    }
}
