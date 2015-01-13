package own.hhw.sercurity;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-20
 * Time: 下午2:01
 * To change this template use File | Settings | File Templates.
 */
public class ThreeDes {

    public static final String KEY_ALGORITHM = "DESede";
    public static final String CIPHER_ALGORITHM = KEY_ALGORITHM + "/CBC/PKCS5Padding";

    private static Key toKey(byte key[]) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte data[], byte key[])
            throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(2, k, new IvParameterSpec(new byte[8]));
        return cipher.doFinal(data);
    }

    /**
     * 加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte data[], byte key[])
            throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(1, k, new IvParameterSpec(new byte[8]));
        return cipher.doFinal(data);
    }


    /**
     * 生成秘钥
     *
     * @param keySize
     * @return
     */
    public static byte[] generateKey(int keySize) {
        byte[] key = null;

        try {
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
            kgen.init(keySize);
            SecretKey skey = kgen.generateKey();
            key = skey.getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("执行DES密钥生成时发生异常！", e);
        }

        return key;
    }

}
