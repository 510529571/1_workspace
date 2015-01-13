package own.hhw.spring;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-5
 * Time: ионГ11:11
 * To change this template use File | Settings | File Templates.
 */
public class UpperAction implements Action {
    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String string) {
        message = string;
    }
    public String execute(String str) {
        return (getMessage() + str).toUpperCase();
    }
}