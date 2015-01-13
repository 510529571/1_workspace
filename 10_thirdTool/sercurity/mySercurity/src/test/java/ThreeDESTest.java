import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.Test;
import own.hhw.sercurity.ThreeDes;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-18
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public class ThreeDESTest {


    @Test
    public void test() throws Exception {
        String key=Base64.encode(ThreeDes.generateKey(168));
        System.out.println("key:"+key);
        String srcString = "3des加密演示文本";

        System.out.println("明文(加密前) ： " + srcString);
        byte[] ciphertArray = ThreeDes.encrypt(srcString.getBytes(), Base64.decode(key));

        String ciphertTxt = Base64.encode(ciphertArray);
        System.out.println("密文 :" + ciphertTxt);

        byte[] plaintext = ThreeDes.decrypt(Base64.decode(ciphertTxt),Base64.decode(key));
        String desString = new String(plaintext);
        System.out.println("解密 (解密后):" + desString);
    }

}
