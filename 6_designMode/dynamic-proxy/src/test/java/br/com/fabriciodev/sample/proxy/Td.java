package br.com.fabriciodev.sample.proxy;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-8-13
 * Time: ����5:24
 * To change this template use File | Settings | File Templates.
 */
public class Td<T,P,F> {
    private T t;
    private HashMap<P,F> map;

    public static void main(String[] args) {
        Class<?> m;
        m=String.class;
        m=Double.class;

        Td td=new Td();

        System.out.println(td.getmm1("1234"));

        String s=getmm(String.class);
        System.out.println(s);
    }
    /**
     * �������P������ͣ����ص�Ҳ��P�������
     * @param pp
     * @return
     */
    public static  <P> P getmm(Class<P> pp){
        String s="123";
        return (P) s;
    }

    /**
     * �������P���Ͷ��󣬷��ص�Ҳ��P���Ͷ���
     * @param pp
     * @return
     */
    public  P getmm1(P pp){
        return (P) pp;
    }
}
