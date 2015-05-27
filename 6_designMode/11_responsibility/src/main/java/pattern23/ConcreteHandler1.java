package pattern23;

import pattern23.client.Request;
import pattern23.client.Response;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: 下午3:03
 */
public class ConcreteHandler1 extends Handler {
    public Response response(Request request) {
        System.out.println("-----请求由处理器1进行处理-----");
        return null;
    }

    protected String getServiceName() {
        return "service1";
    }
}
