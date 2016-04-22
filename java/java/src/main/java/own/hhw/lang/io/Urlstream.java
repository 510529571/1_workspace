package own.hhw.lang.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.String;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;


/**
 * 请求一个路径，并且解析数据
 *
 * @class Urlstream
 * @description
 * @copyRight copyright(c) 2012 广东南航易网通电子商务有限公司,Rights Reserved
 * @time Dec 3, 2012 3:45:02 PM
 */
public class Urlstream {
    public void testUrl1() {
        try {
            InetAddress iaddr = InetAddress.getByAddress(new byte[]{10, 102, -98, -58});
            SocketAddress addr = new InetSocketAddress(iaddr, 1519);
            Proxy typeProxy = new Proxy(Proxy.Type.HTTP, addr);

            URL url = new URL("http://skypearl.csair.com/skypearl/cn/integralquery.action?NXXS=1357873964139");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(typeProxy);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
//			 OutputStreamWriter opsw=new OutputStreamWriter(os,"utf-8");
//			 opsw.write("123");
//			 os.write(new String("?123").getBytes("utf-8"));

            StringBuffer inbuffer = new StringBuffer();
//			if (conn.getResponseCode() == 200)
//			{
            InputStream is = conn.getInputStream();
            byte[] c = new byte[1024];
            int b = is.read(c);

            while ((b = is.read(c)) != -1) {
                inbuffer.append(new String(c, 0, b, "utf-8"));
            }
//			}
            System.out.println(inbuffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testUrl2() {

        try {
            URL url = new URL("https://passport.baidu.com/v2/?login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            String cookie = conn.getHeaderField("Set-Cookie");
            System.out.println(cookie);
            StringBuffer inbuffer = new StringBuffer();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                byte[] c = new byte[1024];
                int b = is.read(c);

                while (b != -1) {
                    inbuffer.append(new String(c, 0, b, "utf-8"));
                    b = is.read(c);
                }
            }
//			System.out.println(inbuffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * http 模拟请求
     */
    public void testUrl3() {
        try {
            URL url = new URL("http://www.hao123.com");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

//			OutputStream os= conn.getOutputStream();
//			os.write(new String("123").getBytes());

            StringBuffer inbuffer = new StringBuffer();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                byte[] c = new byte[1024];
                int b = is.read(c);

                while (b != -1) {
                    inbuffer.append(new String(c, 0, b));
                    b = is.read(c);
                }
            }
            System.out.println(inbuffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testStr1() {
        System.out.println("book".indexOf("o", 3));
    }

    public static void main(String[] args) {
        Urlstream us = new Urlstream();
        us.testUrl1();
    }

}
