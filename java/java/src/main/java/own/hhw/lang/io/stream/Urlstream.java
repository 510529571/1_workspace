package own.hhw.lang.io.stream;

import java.lang.String;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class Urlstream extends TestCase {
    public static void main(java.lang.String[] args) {
        try {
            URL url=new URL("ftp://hao123.com.hao123.com.cn/123");
            System.out.println(url.getHost());
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        testUrl();
    }

    public static void testUrl() {
        String url = "http://www.chinajavaworld.com/sdf.com/4545/0/";
        String url1 = "https://wefs.chinajavaworld.com/sdf.com/4545/0/";
        String url2 = "ftps://anotherbug.blog.chinajavaworld.com/entry/4545/0/";
        String url3 = "ftp://chinajavaworld.com/sdf.com/4545/0/";
        String url4="www.baidu.com";
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        matcher.find();
        System.out.println(matcher.group());
    }
}
