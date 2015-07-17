package own.hhw.lang.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-24
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */
public class Test_Stream {
    @Test
    public void test1() throws Exception {
        java.lang.String s1="你们好吗";
        byte[] bytes=s1.getBytes("utf-8");
        System.out.println("字节长度："+bytes.length);

        ByteArrayInputStream bi=new ByteArrayInputStream(bytes);

        //hhw:tag 下面的方式读取流的内容是有问题的
        byte[] buffer=new byte[10];
        int len;
        StringBuffer sb=new StringBuffer();
        while ((len=bi.read(buffer))!=-1)
            sb.append(new String(buffer,0,len,"utf-8"));

        System.out.println("得到的支付串："+sb.toString());//得到的字符串是：你们好???

    }
}
