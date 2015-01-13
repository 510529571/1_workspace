import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-12
 * Time: ÏÂÎç4:39
 * To change this template use File | Settings | File Templates.
 */
public class TestClient {
    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        client.getHttpConnectionManager().getParams().setSoTimeout(2000);
        PostMethod method = new PostMethod("http://localhost:8071/netbank/CMBMainServlet");
        int returnCode = 0;
        try {
            returnCode = client.executeMethod(method);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(returnCode);
    }
}
