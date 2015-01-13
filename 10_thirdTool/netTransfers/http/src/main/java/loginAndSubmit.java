import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-16
 * Time: 上午9:53
 * To change this template use File | Settings | File Templates.
 */
public class loginAndSubmit {
    private static String SESSIONID = null;
    public static String URL = "http://localhost:8081/";

    public static void main(String[] args) throws Exception {
        login("hhw", "123");
        submit();
    }

    /**
     * 用户自动登录
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public static synchronized String login(String username, String password) throws Exception {
        URL url = null;
        InputStream in = null;
        HttpURLConnection conn = null;
        String code = "";
        try {

            String loginstr = "username=" + username + "&password=" + password;
            url = new URL(URL + "Login");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*");
//			conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Length", loginstr.length() + "");
            conn.setRequestProperty("Cookie", SESSIONID);
//            conn.setInstanceFollowRedirects(false);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            OutputStream loginout = conn.getOutputStream();
            loginout.write(loginstr.getBytes());
            loginout.close();

            // System.out.println("PageSize->Set-Cookie->" + conn.getHeaderField("Set-Cookie"));
            Map<String, List<String>> map = conn.getHeaderFields();
            Set keyset = map.keySet();
            Iterator iter = keyset.iterator();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                // System.out.println("key~~~~~~~~~~~~:"+key+"-"+(String) ((List) map.get(key)).get(0));
                if (key != null && key.equals("Set-Cookie")) {
                    String sid2 = null;
                    List cookies = (List) map.get(key);
                    //这里做这么多判断就是为了保证取得cs1246643xde的值
                    for (int i = 0; i < cookies.size(); i++) {
                        String value = (String) ((List) map.get(key)).get(i);
                        if (value != null) {
                            if (value.indexOf("JSESSIONID") != -1) {
                                sid2 = value;
                            }
                        }
                    }
                    if (sid2 != null)
                        SESSIONID += "; " + sid2.substring(0, sid2.indexOf(";"));
                    System.out.println("Set-Cookie~~~~~~~~~~" + SESSIONID);
                    break;
                }
            }

            in = conn.getInputStream();
            StringBuffer sb = new StringBuffer();
            int a = 0;
            byte[] b = new byte[1024];
            a = in.read(b);
            while (a != -1) {
                sb.append(new String(b, 0, a, "utf-8"));
                a = in.read(b);
            }
            in.close();
            conn.disconnect();
            System.out.println(sb.toString());

//			JSONObject jObject = JSONObject.fromObject(sb.toString());
//			code = (String) jObject.getString("code");

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return code;
    }

    public static synchronized void submit() throws Exception {
        URL url = null;
        InputStream in = null;
        HttpURLConnection conn = null;
        String code = "";
        try {

            url = new URL(URL + "Submit");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*");
//			conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Cookie", SESSIONID);
//            conn.setInstanceFollowRedirects(false);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);

            in = conn.getInputStream();
            StringBuffer sb = new StringBuffer();
            int a = 0;
            byte[] b = new byte[1024];
            a = in.read(b);
            while (a != -1) {
                sb.append(new String(b, 0, a, "utf-8"));
                a = in.read(b);
            }
            in.close();
            conn.disconnect();
            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
