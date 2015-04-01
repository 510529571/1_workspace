import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * User: hanwei
 * Date: 15-3-27
 * Time: ÏÂÎç5:06
 */
public class Base64 {
    public static void main(String[] args) throws IOException {
        String m="ÄãºÃÂð";

        String m_temp=base64Encode(m.getBytes());

        String m_temp2=base64Decode(m_temp);
        System.out.println(m_temp2);

        String s="123ÄãºÃÂð345";
        System.out.println(s.substring(3,s.indexOf("345")));
    }

    private static String base64Encode(byte[] by) {
        String result = null;
        BASE64Encoder encoder = new BASE64Encoder();
        result = encoder.encodeBuffer(by);
        return result;
    }

    private static String base64Decode(String by) throws IOException {
        String result = null;
        BASE64Decoder decoder=new BASE64Decoder();
        result=new String(decoder.decodeBuffer(by));
        return result;
    }

}
