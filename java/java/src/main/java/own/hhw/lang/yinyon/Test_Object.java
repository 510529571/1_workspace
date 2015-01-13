package own.hhw.lang.yinyon;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author huhanwei
 */
public class Test_Object {
    /**
     * java 都是通过引用对实体操作
     */
    @Test
    public void test_object() {
        User user = new User();
        user.setUsername("huhanwei");
        user.setPassword("123");

        Map<String, User> map = new HashMap<String, User>();
        map.put("1", user);
        System.out.println(map.get("1").getUsername());
        User u = map.get("1");
        u.setPassword("12345");
        u.setUsername("hanwei");

        System.out.println(map.get("1").getUsername());
    }

    public void changeStr(String str) {
        str = "改后的数据";
    }

    /**
     * 但是基础类型的封装对象会不一样
     */
    @Test
    public void test_str() {
        String str = "原始数据";

        Test_Object test_str = new Test_Object();
        test_str.changeStr(str);

        System.out.println(str);
    }

    @Test
    public void test_null(){
        String s=(String) null;//这里将null强转型不会抛出异常
    }


    static class User {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

}

