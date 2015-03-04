package own.hhw.sercurity;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.lang.ArrayUtils;
import own.hhw.util.PublicTool;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte data[], byte key[])
            throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(2, k, new IvParameterSpec(new byte[8]));
        return cipher.doFinal(data);
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte data[], byte key[])
            throws Exception {
        Key k = toKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(1, k, new IvParameterSpec(new byte[8]));
        return cipher.doFinal(data);
    }

    private static String bytesToHexStr(byte[] paramArrayOfByte) {
        int len = paramArrayOfByte.length;
        StringBuilder localStringBuilder = new StringBuilder(len * 2 + 1);
        for (int i = 0; i < len; i++) {
            localStringBuilder.append(Integer.toHexString(paramArrayOfByte[i] >>> 4 & 0xF));
            localStringBuilder.append(Integer.toHexString(paramArrayOfByte[i] & 0xF));
        }
        return localStringBuilder.toString();
    }

    private static byte[] hexStrToBytes(String paramString) {
        byte[] arrayOfByte = new byte[paramString.length() / 2];
        int j = arrayOfByte.length;
        for (int i=0;i < j;i++) {
            arrayOfByte[i] = (byte) Integer.parseInt(paramString.substring(2 * i, 2 * i + 2), 16);
        }
        return arrayOfByte;
    }

    public static String encryptStr(String origStr, String key) throws Exception {
        return bytesToHexStr(encrypt(origStr.getBytes("gbk"), hexStrToBytes(key)));
    }

    public static String decryptStr(String origStr, String key) throws Exception {
        return new String(decrypt(hexStrToBytes(origStr), hexStrToBytes(key)), "gbk");
    }

    private static CipherInputStream getDesCipher(int mode, String key, InputStream inputstream) throws Exception {
        byte input[] = hexStrToBytes(key);
        SecretKey desKey = new SecretKeySpec(input, KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(mode, desKey, new IvParameterSpec(new byte[8]));
        return new CipherInputStream(inputstream, cipher);
    }

    /**
     * 加密文件，将字节码以十六进制字符串保存
     *
     * @param origFilePath
     * @param targetFilePath
     * @param key
     * @return
     * @throws Exception
     */
    public static boolean encryptFileInHex(String origFilePath, String targetFilePath, String key) throws Exception {
        InputStream ofis = new FileInputStream(origFilePath);
        OutputStream tfos = new FileOutputStream(PublicTool.makeFile(targetFilePath));

//        InputStream cis = getDesCipher(1, key, ofis);
        int len = 0;
        int len_total = 0;
        byte[] btyes = new byte[1024];
        byte[] bytes_total = new byte[0];

        while ((len = ofis.read(btyes)) != -1) {
            len_total += len;
            bytes_total = ArrayUtils.addAll(bytes_total, btyes);
        }
        bytes_total = ArrayUtils.subarray(bytes_total, 0, len_total);

        byte[] tbytes = bytesToHexStr(encrypt(bytes_total, hexStrToBytes(key))).getBytes();
        tfos.write(tbytes);

        return true;
    }

    /**
     * 与encryptFileInHex加密方式对应，进行解密
     *
     * @param origFilePath
     * @param targetFilePath
     * @param key
     * @return
     * @throws Exception
     */
    public static boolean decryptFileInHex(String origFilePath, String targetFilePath, String key) throws Exception {
        InputStream ofis = new FileInputStream(origFilePath);
        OutputStream tfos = new FileOutputStream(PublicTool.makeFile(targetFilePath));

        int len = 0;
        int len_total = 0;
        byte[] btyes = new byte[1024];
        byte[] bytes_total = new byte[0];

        while ((len = ofis.read(btyes)) != -1) {
            len_total += len;
            bytes_total = ArrayUtils.addAll(bytes_total, btyes);
        }
        bytes_total = ArrayUtils.subarray(bytes_total, 0, len_total);

        byte[] tbytes = decrypt(hexStrToBytes(new String(bytes_total)), hexStrToBytes(key));
        tfos.write(tbytes);

        return true;
    }

    /**
     * 加密文件
     *
     * @param origFilePath
     * @param targetFilePath
     * @param key
     * @return
     * @throws Exception
     */
    public static boolean encryptFile(String origFilePath, String targetFilePath, String key) throws Exception {
        InputStream ofis = new FileInputStream(origFilePath);
        OutputStream tfos = new FileOutputStream(PublicTool.makeFile(targetFilePath));

        InputStream cis = getDesCipher(1, key, ofis);
        byte[] btyes = new byte[1024];
        int len = 0;

        int i = 0;

        while ((len = cis.read(btyes)) != -1) {
            i++;
            tfos.write(btyes, 0, len);
        }
        System.out.println("次数" + i);

        return true;
    }

    /**
     * 解密一个加密文件
     *
     * @param origFilePath
     * @param targetFilePath
     * @param key
     * @return
     * @throws Exception
     */
    public static boolean decryptFile(String origFilePath, String targetFilePath, String key) throws Exception {
        InputStream ofis = new FileInputStream(origFilePath);
        OutputStream tfos = new FileOutputStream(PublicTool.makeFile(targetFilePath));

        InputStream cis = getDesCipher(2, key, ofis);
        byte[] btyes = new byte[1024];
        int len = 0;

        while ((len = cis.read(btyes)) != -1) {
            tfos.write(btyes, 0, len);
        }
        return true;
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

    /**
     * @param keySize
     * @return
     */
    public static String generateKey2Base64Str(int keySize) {
        return Base64.encode(generateKey(keySize));
    }

    /**
     * 生成3des密钥，将密钥的字节码以十六进制字符保存
     *
     * @param keySize
     * @return
     */
    public static String generateKey2HexStr(int keySize) {
        return bytesToHexStr(generateKey(keySize));
    }

    public static void main(String[] args) throws Exception {
        String s = "测试开始";
        System.out.println(s.getBytes("GBK")[0] + "," + s.getBytes("GBK")[1]);

        System.out.println("字节码的十六进制表达：" + bytesToHexStr(s.getBytes("gbk")));
        System.out.println(new String(hexStrToBytes("BAC3"), "gbk"));

        String s2 = own.hhw.Base64.encode(s.getBytes("gbk"));
        System.out.println(s2);
        System.out.println(new String(own.hhw.Base64.decode(s2), "gbk"));

        String s3 = URLEncoder.encode(s, "gbk");
        System.out.println(s3);
        System.out.println(URLDecoder.decode(s3, "gbk"));

        String key = generateKey2HexStr(168);
        String s4 = encryptStr(s, key);
        System.out.println("encryptStr:" + s4);
        System.out.println("decryptStr:" + decryptStr(s4, key));


        InputStream is = new ByteArrayInputStream(s.getBytes("GBK"));

        InputStream is2 = getDesCipher(2, key, getDesCipher(1, key, is));

        byte[] bytes = new byte[1024];

        is2.read(bytes);

        System.out.println(new String(bytes, "gbk"));

        encryptFile("F:\\test\\1\\1.txt", "F:\\test\\2\\1.txt", key);
        decryptFile("F:\\test\\2\\1.txt", "F:\\test\\3\\1.txt", key);

        encryptFileInHex("F:\\test2\\1\\1.txt", "F:\\test2\\2\\1.txt", key);
        decryptFileInHex("F:\\test2\\2\\1.txt", "F:\\test2\\3\\1.txt", key);

//        System.out.println((byte) Integer.parseInt("BA", 16));
    }
}
