package own.hhw.util;

import org.apache.commons.io.IOUtils;
import own.hhw.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

/**
 * 48位3des加密类
 */
public class ThreeDesUtil {
    private static final String DEFAULT_DESKEYALGORITHM = "DESede";
    private static final String DEFAULT_CIPHERALGORITHM = DEFAULT_DESKEYALGORITHM + "/" + "CBC" + "/" + "PKCS5Padding";
    private static final char[] BCD_LOOKUP = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static CipherInputStream getDesCipher(int mode, String key, InputStream inputstream) throws Exception {
        byte input[] = Base64.decode(key);
        SecretKey desKey = new SecretKeySpec(input, DEFAULT_DESKEYALGORITHM);
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHERALGORITHM);
        cipher.init(mode, desKey, new IvParameterSpec(new byte[8]));
        return new CipherInputStream(inputstream, cipher);
    }

    public static String bytesToHexStr2(byte[] paramArrayOfByte, int len) {
        StringBuilder localStringBuilder = new StringBuilder(len * 2 + 1);
        for (int i = 0; i < len; i++) {
            localStringBuilder.append(BCD_LOOKUP[(paramArrayOfByte[i] >>> 4 & 0xF)]);
            localStringBuilder.append(BCD_LOOKUP[(paramArrayOfByte[i] & 0xF)]);
        }
        return localStringBuilder.toString();
    }

    public static OutputStream getBufferedOutputStream(File paramFile) throws Exception {
        return new BufferedOutputStream(new FileOutputStream(paramFile));
    }

    public static void closeOutputStream(OutputStream paramOutputStream) {
        IOUtils.closeQuietly(paramOutputStream);
    }

    public static byte[] hexStrToBytes(String paramString) {

        byte[] arrayOfByte = new byte[paramString.length() / 2];
        int i = 0;
        int j = arrayOfByte.length;
        while (i < j) {
            arrayOfByte[i] = (byte) Integer.parseInt(paramString.substring(2 * i, 2 * i + 2), 16);
            i++;
        }
        return arrayOfByte;
    }

    /**
     * 加密
     *
     * @param origFilePath   待加密的文件的绝对路径
     * @param targetFilePath 加密文件存放的目录
     * @param fileName       加密文件的文件名
     * @param key
     * @return
     * @throws Exception
     */
    public static boolean encryptFileInHexBy3Des(String origFilePath, String targetFilePath, String fileName, String key) throws Exception {
        File origFile = new File(origFilePath);
        // 创建文件对象
        File encryptFile = new File(targetFilePath);
        if (!encryptFile.exists()) {
            if (!encryptFile.mkdirs()) {
                throw new RuntimeException("尝试创建文件夹:[" + encryptFile + "]失败！");
            }
        }
        FileInputStream fileIn = new FileInputStream(origFile);
        InputStream desInput = getDesCipher(Cipher.ENCRYPT_MODE, key, fileIn);
        byte[] buff2 = new byte[2048];
        int len = 0;
        OutputStream localOutputStream = getBufferedOutputStream(new File(targetFilePath + "\\" + fileName));
        while ((len = desInput.read(buff2)) != -1) {
            String toHexStr = bytesToHexStr2(buff2, len);
            if (toHexStr == null || toHexStr.equals(""))
                return false;
            IOUtils.write(toHexStr, localOutputStream);
        }
        fileIn.close();
        desInput.close();
        closeOutputStream(localOutputStream);

        return true;
    }

    /**
     * 先将hexStrToBytes落为文件，然后通过文件输入流进行3des解密
     *
     * @param origFilePath   待解密的文件的绝对路径
     * @param targetFilePath 解密文件存放的目录
     * @param fileName       解密文件的文件名
     * @param key
     * @return
     * @throws Exception
     */
    public static boolean decryptFileInHexBy3Des(String origFilePath, String targetFilePath, String fileName, String key) throws Exception {
        File encryptFile = new File(origFilePath);
        File origFile = new File(targetFilePath);

        if (!origFile.exists())
            origFile.mkdirs();

        File tempFile = new File(targetFilePath + "\\temp.txt");

        FileInputStream fileInput = new FileInputStream(encryptFile);
        byte[] buff = new byte[1024];
        int len = 0;

        FileOutputStream outTempFile = new FileOutputStream(tempFile);
        while ((len = fileInput.read(buff)) != -1) {
            byte[] paramArrayOfByte = hexStrToBytes(new String(buff, 0, len));
            outTempFile.write(paramArrayOfByte, 0, paramArrayOfByte.length);
        }
        outTempFile.flush();
        outTempFile.close();
        fileInput.close();

        FileInputStream fileIn = new FileInputStream(tempFile);
        FileOutputStream outFile = new FileOutputStream(new File(targetFilePath + "\\" + fileName));
        InputStream desInput = getDesCipher(Cipher.DECRYPT_MODE, key, fileIn);
        byte[] buff2 = new byte[1024];
        int len2 = 0;
        while ((len2 = desInput.read(buff2)) != -1) {
            outFile.write(buff2, 0, len2);
        }

        outFile.flush();
        outFile.close();
        desInput.close();

        tempFile.delete();

        return true;
    }



}
