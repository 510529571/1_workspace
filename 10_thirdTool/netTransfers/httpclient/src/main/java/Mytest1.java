import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-8-15
 * Time: ÏÂÎç2:58
 * To change this template use File | Settings | File Templates.
 */
public class Mytest1 {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            String s="<?xml version=\"1.0\" encoding=\"GB2312\"?>\n" +
                    "<SoEv>\n" +
                    "\t<Message id=\"20140822022549207\">\n" +
                    "\t\t<CPReq id=\"CPReq\">\n" +
                    "\t\t\t<version>1.0.0</version>\n" +
                    "\t\t\t<date>20140822 02:25:49</date>\n" +
                    "\t\t\t<instId>003480203009975</instId>\n" +
                    "\t\t\t<certId>0001</certId>\n" +
                    "\t\t\t<serialNo>20140605033083</serialNo>\n" +
                    "\t\t\t<signNo>6225682141004705590</signNo>\n" +
                    "\t\t\t<charge>0</charge>\n" +
                    "\t\t\t<amount>33300</amount>\n" +
                    "\t\t\t<currency>156</currency>\n" +
                    "\t\t</CPReq>\n" +
                    "\t\t<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">\n" +
                    "\t\t\t<SignedInfo>\n" +
                    "\t\t\t\t<CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/>\n" +
                    "\t\t\t\t<SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/>\n" +
                    "\t\t\t\t<Reference URI=\"#CPReq\">\n" +
                    "\t\t\t\t\t<Transforms>\n" +
                    "\t\t\t\t\t\t<Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/>\n" +
                    "\t\t\t\t\t</Transforms>\n" +
                    "\t\t\t\t\t<DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/>\n" +
                    "\t\t\t\t\t<DigestValue>kA47/BryBiVJBuL0QURl8QaiY08=</DigestValue>\n" +
                    "\t\t\t\t</Reference>\n" +
                    "\t\t\t</SignedInfo>\n" +
                    "\t\t\t<SignatureValue>lhnNb3QxicHDFxCk7B3/jJWT+iP1v1bcGxcOi3H0pT7gDCA+r66mAz16z/m1gGYKGv1HDRafgho7\n" +
                    "GVw16rpueaocltTVVWeAHy74gMtmDu5pRlWXnoe+KZ0tXhySV8Qi1mlwtcnXHkrPw/HE2eqQjv5j\n" +
                    "pzyK2bRVNU2mpqsibY4=</SignatureValue>\n" +
                    "\t\t</Signature>\n" +
                    "\t</Message>\n" +
                    "</SoEv>\n";
            System.out.println(s);
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };

            HttpPost httpPost = new HttpPost("http://113.108.207.154:8087/spayment/servlet/gfbank.portal.GFPortalTrade");
            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            nvps.add(new BasicNameValuePair("CSVReq", s));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            httpPost.setHeader("Content-Type","application/xml; charset=utf-8");
//            httpPost.setHeader("Content-Length",s.getBytes().length+"");
            String responseBody  = httpclient.execute(httpPost,responseHandler);
            System.out.println(responseBody);

        } finally {
            httpclient.close();
        }
    }
}
