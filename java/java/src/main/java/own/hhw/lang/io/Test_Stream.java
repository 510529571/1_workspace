package own.hhw.lang.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-12-24
 * Time: ����3:08
 * To change this template use File | Settings | File Templates.
 */
public class Test_Stream {
    @Test
    public void test1() throws Exception {
        java.lang.String s1="���Ǻ���";
        byte[] bytes=s1.getBytes("utf-8");
        System.out.println("�ֽڳ��ȣ�"+bytes.length);

        ByteArrayInputStream bi=new ByteArrayInputStream(bytes);

        //hhw:tag ����ķ�ʽ��ȡ�����������������
        byte[] buffer=new byte[10];
        int len;
        StringBuffer sb=new StringBuffer();
        while ((len=bi.read(buffer))!=-1)
            sb.append(new String(buffer,0,len,"utf-8"));

        System.out.println("�õ���֧������"+sb.toString());//�õ����ַ����ǣ����Ǻ�???

    }
}
