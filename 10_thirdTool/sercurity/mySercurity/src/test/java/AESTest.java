import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.Test;
import own.hhw.sercurity.AES;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-18
 * Time: ����4:29
 * To change this template use File | Settings | File Templates.
 */
public class AESTest {
    @Test
    public void test() throws Exception {
        String key=Base64.encode(AES.generateKey(128));
        System.out.println("AES_key:"+key);
        String srcString = "ʹ��AES�ԳƼ�����ʾ�ı�";

        System.out.println("����(����ǰ) �� " + srcString);
        byte[] ciphertArray = AES.encrypt(srcString.getBytes(), Base64.decode(key));

        String ciphertTxt = Base64.encode(ciphertArray);
        System.out.println("���� :" + ciphertTxt);

        byte[] plaintext = AES.decrypt(Base64.decode(ciphertTxt),Base64.decode(key));
        String desString = new String(plaintext);
        System.out.println("���� (���ܺ�):" + desString);
    }

}
