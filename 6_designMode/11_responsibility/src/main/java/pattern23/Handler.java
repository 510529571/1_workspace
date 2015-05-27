package pattern23;

import pattern23.client.Request;
import pattern23.client.Response;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: ����2:35
 */
public abstract class Handler {
    private Handler nextHandler;

    public final Response handleRequest(Request request) {
        Response response = null;

        if (this.getServiceName().equals(request.getServiceName())) {
            response = this.response(request);
        } else {
            if (this.nextHandler != null) {
                this.nextHandler.handleRequest(request);
            } else {
                System.out.println("-----û�к��ʵĴ�����-----");
            }
        }
        return response;
    }

    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    protected abstract String getServiceName();

    public abstract Response response(Request request);
}  