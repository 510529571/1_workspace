package own.hhw;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 13-12-27
 * Time: ����10:56
 * To change this template use File | Settings | File Templates.
 */
public class User {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;

}
