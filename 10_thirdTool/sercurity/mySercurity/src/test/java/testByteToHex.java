import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-24
 * Time: ÏÂÎç2:24
 * To change this template use File | Settings | File Templates.
 */
public class testByteToHex {

    private static final char[] BCD_LOOKUP = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    @Test
    public void test1(){
            byte[] b="±ò".getBytes();
        System.out.println(b[0]+","+b[1]);
        System.out.println(BCD_LOOKUP[b[0] >>> 4 & 0xF]);
        System.out.println(BCD_LOOKUP[(b[0] & 0xF)]);
        System.out.println(66>>>4&0xF);
        System.out.println(75&0xF);
    }
    public static String bytesToHexStr2(byte[] paramArrayOfByte, int len) {
        StringBuilder localStringBuilder = new StringBuilder(len * 2 + 1);
        for (int i = 0; i < len; i++) {
            localStringBuilder.append(BCD_LOOKUP[(paramArrayOfByte[i] >>> 4 & 0xF)]);
            localStringBuilder.append(BCD_LOOKUP[(paramArrayOfByte[i] & 0xF)]);
        }
        return localStringBuilder.toString();
    }
}
