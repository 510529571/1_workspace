/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-17
 * Time: ÏÂÎç2:31
 * To change this template use File | Settings | File Templates.
 */
public class RequestEventSubject {
    private RequestEventListener listener;

    public void attach(RequestEventListener listener) {
        this.listener = listener;
    }

    public void saveSession() {
        if (listener != null)
            listener.saveSession();
    }

}
