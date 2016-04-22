package own.hhw.app.encoding;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-1-15
 * Time: ����9:45
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
        //���������ʶ��ֻ��1��0���ֽ������������ڼ�¼��ʱ������ö����ƣ��˽��ƣ�ʮ���ƣ�ʮ�����Ƶ�ȥ��ʾ�ֽ���
        //ע��java��ֻ����ʮ���ƣ��˽��ƣ�ʮ�����Ʊ�ʾһ����
        // 1011 1110 0110 =be6
        // 101 111 100 110=5746
        byte b4=(byte)-129;//С��-128����Ҫbyteǿ��ת��
        byte b1=-128;   //ʮ���Ʊ�ʾ��,�����ƣ�1000 0000 hhw:task ����û������-128�Ķ�������ô�������ӵ�
        byte b2=127;   //ʮ���Ʊ�ʾ���������ƣ�0111 1111
        byte b5=(byte)128;//����127������Ҫ��byteǿ��ת���ˣ�����֪��Ϊʲô
        byte b3=0x7F; //16���Ʊ�ʾ��
        byte b6=0177; //8���Ʊ�ʾ��

        byte[] bs = "��".getBytes("gbk");
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
        System.out.println("��".getBytes("utf-8")[1]);
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

    //hhw:question ���룬��������
    /*
    ���ĵ��ı���ΪGBK��GB2312��ʱ���ڳ����в���UFT-8��GBK��GB2312�����Ľ���ͱ���
    �����ĵ���UTF-8�����ʱ���ڳ����о��ܲ���UFT-8��GBK��GB2312��������ͽ���
    */

    /*
    ���ڱ������⣬�����ֲ���1.reload 2.convert ����һ�֣��ǲ��ò�ͬ�ı��뷽ʽȥװ����ͬ�Ķ������ַ�
    �ڶ����أ��һ�ûŪ��������ô���£����ǿ϶�������ת�룬Ҳ����˵�ı������ݵĴ洢��ʽ
    */

    public static void main(String[] args) throws UnsupportedEncodingException {

//        try {

     /*   byte[] bs= new byte[0];
        try {
            bs = "��".getBytes("gbk");
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
