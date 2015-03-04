package use;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-2-13
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public class MyTest {
    /**
     * 运行这个方法，测试加密解密，及验签
     */
    @Test
    public void testAES() {
        Map<String, String> sPara = new HashMap<String, String>();
        sPara.put("name", "hanwei");
        sPara.put("password", "12346");

        Map<String, String> sPara_resp = AESUtils.deal(sPara);
    }


    @Test
    public void testURL() {
        String retStr="";
        String urlpath="http://116.236.252.112:20153/FundTxInteract/FSIRequestReceiver";
        try {
            URL url = new URL(urlpath);
            URLConnection connection = url.openConnection();
            InputStream out=connection.getInputStream();
        } catch (ConnectException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            retStr="连接异常！可能是网络无法到达指定ip，或者是远程服务不存在，或者连接错误";
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            retStr="";
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            retStr="请求资源不存在！";
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println(retStr);
    }

}
