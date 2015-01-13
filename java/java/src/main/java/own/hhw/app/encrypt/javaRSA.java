package own.hhw.app.encrypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import java.security.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-2-17
 * Time: 下午2:02
 * To change this template use File | Settings | File Templates.
 */
public class javaRSA {
    // 非对称加密密钥算法
    private static final String KEY_ALGORTHM = "RSA";
    // RSA密钥长度
    private static final int KEY_SIZE = 512;
    // 公钥
    private static final String PUBLIC_KEY = "RSAPublicKey";
    // 私钥
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {

        //实例化密钥对生成器
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORTHM);
        //初始化密钥对的生成器
        keyPairGen.initialize(KEY_SIZE);
        // 生成密钥对
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * 获取私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static byte[] getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);

        return key.getEncoded();
    }

    /**
     * 获取公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static byte[] getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return key.getEncoded();
    }

}
