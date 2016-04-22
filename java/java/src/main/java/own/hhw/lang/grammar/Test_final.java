package own.hhw.lang.grammar;

import org.junit.Test;

import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-3
 * Time: 下午6:30
 * To change this template use File | Settings | File Templates.
 */
public class Test_final {
    private final static User finObj;//用户自定义对象可以不用给定默认值

    private final static String finStr = "我是固定的值";//基本类型引用，必须给定默认值

    //hhw:task final有什么用呢？
    static {
        User myUser = new User();
        myUser.setName("hanwei");
        myUser.setPassword("123456");
        finObj = myUser;
//        finObj=null;//上面已经指定了指定了值，不能重新指定
//        finStr="";//上面已经指定了指定了值，不能重新指定
        myUser.setName("scott");
        myUser.setPassword("111111");
    }

    @Test
    public void mytest() {
        System.out.println(Test_final.finObj.getName());//final对象的内容被改变了
        System.out.println(Test_final.finObj.getPassword());   //final对象的内容被改变了
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
