import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-20
 * Time: 下午3:20
 * To change this template use File | Settings | File Templates.
 */
public class CipherTest {
    @Test
    public void generateKey() {
        byte[] key = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            SecretKey skey = kgen.generateKey();
            key = skey.getEncoded();
            System.out.println(Base64.encode(key));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("执行AES密钥生成时发生异常！", e);
        }
    }
}