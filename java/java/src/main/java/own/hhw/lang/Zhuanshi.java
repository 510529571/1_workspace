package own.hhw.lang;

import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-11
 * Time: ����9:57
 * To change this template use File | Settings | File Templates.
 */
public class Zhuanshi {
    public void excutor(People p) {
        System.out.println("���������");
        p.work();
        System.out.println("�Ѿ������");
    }


    public static void main(java.lang.String[] args) {
        final String s = "ץС͵";
        new Zhuanshi().excutor(new People() {
            @Override
            public void work() {
                System.out.println(s);
            }
        });
    }
}

interface People {
    public void work();
}