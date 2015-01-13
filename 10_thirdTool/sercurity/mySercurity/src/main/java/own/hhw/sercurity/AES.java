
package own.hhw.sercurity;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 *加密解密
 */
public class AES
{

	public static final String KEY_ALGORITHM = "AES";
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	private static Key toKey(byte key[])
		throws Exception
	{
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	public static byte[] decrypt(byte data[], byte key[])
		throws Exception
	{
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(2, k);
		return cipher.doFinal(data);
	}

	public static byte[] encrypt(byte data[], byte key[])
		throws Exception
	{
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(1, k);
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
            throw new RuntimeException("执行AES密钥生成时发生异常！", e);
        }

        return key;
    }

}
