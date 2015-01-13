package own.hhw.spring_servlet_proxy.service;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-5
 * Time: обнГ5:24
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImpl implements UserService {
    public String sayHello(String hello) {
        return hello+",userService";
    }
}
