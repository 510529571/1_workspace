package own.hhw.lang.grammar;

import org.junit.Test;

import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-3
 * Time: ����6:30
 * To change this template use File | Settings | File Templates.
 */
public class Test_final {
    private final static User finObj;//�û��Զ��������Բ��ø���Ĭ��ֵ

    private final static String finStr = "���ǹ̶���ֵ";//�����������ã��������Ĭ��ֵ

    //hhw:task final��ʲô���أ�
    static {
        User myUser = new User();
        myUser.setName("hanwei");
        myUser.setPassword("123456");
        finObj = myUser;
//        finObj=null;//�����Ѿ�ָ����ָ����ֵ����������ָ��
//        finStr="";//�����Ѿ�ָ����ָ����ֵ����������ָ��
        myUser.setName("scott");
        myUser.setPassword("111111");
    }

    @Test
    public void mytest() {
        System.out.println(Test_final.finObj.getName());//final��������ݱ��ı���
        System.out.println(Test_final.finObj.getPassword());   //final��������ݱ��ı���
        System.out.println(finStr);
    }

    static class User {
        private String name;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}
