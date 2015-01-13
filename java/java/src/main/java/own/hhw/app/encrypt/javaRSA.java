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
 * Time: ����2:02
 * To change this template use File | Settings | File Templates.
 */
public class javaRSA {
    // �ǶԳƼ�����Կ�㷨
    private static final String KEY_ALGORTHM = "RSA";
    // RSA��Կ����
    private static final int KEY_SIZE = 512;
    // ��Կ
    private static final String PUBLIC_KEY = "RSAPublicKey";
    // ˽Կ
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * ��ʼ����Կ
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {

        //ʵ������Կ��������
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORTHM);
        //��ʼ����Կ�Ե�������
        keyPairGen.initialize(KEY_SIZE);
        // ������Կ��
        KeyPair keyPair = keyPairGen.generateKeyPair();
        //��Կ
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //˽Կ
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * ��ȡ˽Կ
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
     * ��ȡ��Կ
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
