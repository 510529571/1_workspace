package java.lang;

import sun.misc.BASE64Encoder;

/**
 * User: hanwei
 * Date: 15-3-27
 * Time: ÏÂÎç5:06
 */
public class Base64 {
    public static void main(String[] args) {
        String m="ÄãºÃÂð";

        String m_temp=base64Encode(m.getBytes());

        String m_temp2=getrevFromBASE64(m_temp.getBytes());
        System.out.println(m_temp2);
    }

    private static String base64Encode(byte[] by) {
        String result = null;
        BASE64Encoder encoder = new BASE64Encoder();
        result = encoder.encodeBuffer(by);
        return result;
    }


    public static String getrevFromBASE64(byte[] s) {
        if (s == null)
            return null;
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            return encoder.encode(s);
        } catch (Exception e) {
            return null;
        }
    }
}
