package own.hhw.lang;

import org.junit.Test;
import own.hhw.util.Public;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class Test_String {


    @Test
    public void main() {


//		String str1="Dell戴尔Dimension 2010 R-206M台式电脑含18.5寸宽屏";
//		System.out.println(str1.length());
//		
//		for(int i =2013;i<2050;i++)
//		{
//			System.out.print(i+",");
//		}

        String callbackurl_temp = "http://www.hao123.com";
        byte[] c_temp = callbackurl_temp.getBytes();
        StringBuffer callbackurl = new StringBuffer();
        for (int i = 0; i < c_temp.length; i++) {
            callbackurl.append((int) c_temp[i] + "aaa");
        }

        String p_orderno = "123";
        String p_paymoney = "0.1";
        String p_customerId = "hanwei";
        String p_callbackurl = callbackurl.toString();
        String p_paymode = "1|2|3";
        String p_signature = "";
        try {
            p_signature = Public.getMD5(p_orderno + p_paymoney + p_customerId + p_callbackurl);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String payUrl = "http://10.102.125.249:8087/PayStage/paystage?" + "orderno=" + p_orderno + "&paymoney=" + p_paymoney + "&customerId=" + p_customerId + "&callbackurl=" + p_callbackurl + "&paymode=" + p_paymode + "&signature=" + p_signature;

        System.out.println(payUrl);
    }

    @Test
    public void test_subStr() {
        String string = "123456";
        System.out.println(string.length());
        System.out.println(string.substring(0, 3));
        System.out.println(string.substring(3));
        System.out.println(string.substring(3, 4));
    }

    public static void main(String[] args) {
        ArrayList<String> as = new ArrayList<String>();
        as.add("1");
        as.add("2");
        as.add("3");
        System.out.println(as.get(1));


        if ("".compareTo("20140704") < 0)
            System.out.println("我答对了");
    }

    @Test
    public void testSplit() {
        String s = "1|23|4";
        System.out.println(s.split("\\|").length); //结果3

        System.out.println(s.split("|").length); //结果为7

        String s2 = "1,23,4";
        System.out.println(s2.split(",").length); //结果3
    }

    /**
     * hhw:tag 测试split
     * <p/>
     * 看下面的效果注意区别
     */
    @Test
    public void testSplit2() {
        String s = ",,,1";
        String[] a = s.split(",");
        System.out.println(a.length);//结果4

        s = ",,,1,,";
        a = s.split(",");
        System.out.println(a.length);//结果还是4
    }


}
