package own.hhw.app.encoding;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-1-15
 * Time: 上午9:45
 * To change this template use File | Settings | File Templates.
 */
public class LearnEncoding {
    private static final char[] BCD_LOOKUP = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    @Test
    public void testInteger()
    {
        System.out.println(Integer.parseInt("7E",16));
    }

    @Test
    public void testByte() throws UnsupportedEncodingException {
        //计算机能认识的只有1和0的字节流，而我们在记录的时候可以用二进制，八进制，十进制，十六进制等去表示字节流
        //注：java中只能用十进制，八进制，十六进制表示一个数
        // 1011 1110 0110 =be6
        // 101 111 100 110=5746
        byte b4=(byte)-129;//小于-128就需要byte强制转型
        byte b1=-128;   //十进制表示法,二进制：1000 0000 hhw:task 还是没闹明白-128的二进制怎么是这样子的
        byte b2=127;   //十进制表示法，二进制：0111 1111
        byte b5=(byte)128;//超过127，就需要用byte强制转型了，还不知道为什么
        byte b3=0x7F; //16进制表示法
        byte b6=0177; //8进制表示法

        byte[] bs = "汉".getBytes("gbk");
        StringBuilder sb = new StringBuilder("");
        for(byte b:bs)
        {
            sb.append(BCD_LOOKUP[(b >>> 4 & 0xF)]);
            sb.append(BCD_LOOKUP[(b & 0xF)]);
        }
        System.out.println(sb.toString());

    }

    @Test
    public void test42() throws UnsupportedEncodingException {

    }


    @Test
    public void test1() throws UnsupportedEncodingException {
        System.out.println("好".getBytes("utf-8")[1]);
    }

    @Test
    public void testByte4hex(){
        System.out.println(168>>>4&0xf);
        System.out.println(Integer.toHexString(0xf&168));
        System.out.println(Integer.toOctalString(0xff & 168));
        System.out.println("\u6c49");

        System.out.println(Integer.toHexString(169>>>4&0xf));
        System.out.println(Integer.toHexString(169&0xf));

        System.out.println(Integer.toHexString(169&0xFF));
        System.out.println(Integer.parseInt("a9",16));

        System.out.println(Integer.toBinaryString(0xa9));
    }

    //hhw:question 编码，解码问题
    /*
    当文档的编码为GBK和GB2312的时候，在程序中采用UFT-8，GBK和GB2312正常的解码和编码
    而当文档用UTF-8编码的时候，在程序中就能采用UFT-8，GBK和GB2312正常编码和解码
    */

    /*
    关于编码问题，有两种操作1.reload 2.convert ，第一种，是采用不同的编码方式去装载相同的二进制字符
    第二种呢，我还没弄明白是这么回事，但是肯定进行了转码，也就是说改变了数据的存储格式
    */

    public static void main(String[] args) throws UnsupportedEncodingException {

//        try {

     /*   byte[] bs= new byte[0];
        try {
            bs = "严".getBytes("gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        for(byte b:bs)
            {
                System.out.println(b);
            }*/
//            byte[] b = "A".getBytes("IBM277");
//            StringBuffer sb = new StringBuffer();
//            for (int i = 0; i < b.length; i++)
//                sb.append(b[i]).append(",");
//
//            System.out.println(sb.toString());
//            System.out.println(new String(b, 0, b.length, "GBK"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }
}
