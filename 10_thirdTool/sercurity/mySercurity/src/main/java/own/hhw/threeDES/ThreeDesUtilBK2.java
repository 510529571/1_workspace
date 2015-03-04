package own.hhw.threeDES;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 15-2-3
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */
public class ThreeDesUtilBK2 {
    private  String  Algorithm = "TripleDES";
    private String key = "HQ937PP69X8K3KRVYY2FRPHB";
    private SecretKey deskey = null;
    private byte[] miwen = null;

    public byte[] encryptMode(byte [] value) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        deskey = new SecretKeySpec(key.getBytes(), Algorithm);
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.ENCRYPT_MODE, deskey);
        return c1.doFinal(value);
    }

    public void encryFile(String fileName,String fileNameTarget) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        FileInputStream fis=new FileInputStream(new File(fileName));
        FileOutputStream fous=new FileOutputStream(new File(fileNameTarget));

        byte[] byte1=new byte[fis.available()];
        fis.read(byte1);

        byte[] byte2=encryptMode(byte1);

        System.out.println("字节长度："+byte2.length+","+byte1.length);
        fous.write(byte2);
    }

    public void decryptFile(String fileName,String fileNameTarget) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        File file=new File(fileNameTarget);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        FileInputStream fis=new FileInputStream(new File(fileName));
        FileOutputStream fous=new FileOutputStream(file);

        byte[] byte1=new byte[fis.available()];
        fis.read(byte1);

        byte[] byte2=decryptMode(byte1);

        System.out.println("字节长度："+byte2.length+","+byte1.length);
        fous.write(byte2);
    }

    public byte[] decryptMode(byte [] value) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        deskey = new SecretKeySpec(key.getBytes(), Algorithm);
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.DECRYPT_MODE, deskey);
        return c1.doFinal(value);
    }

    public String byte2hex(byte[] b) {
        String hs="";
        String stmp="";

        for (int n=0;n<b.length;n++) {
            stmp=(Integer.toHexString(b[n] & 0XFF));
            if (stmp.length()==1) hs=hs+"0"+stmp;
            else hs=hs+stmp;
            if (n<b.length-1)  hs=hs+"";
        }
        return hs.toUpperCase();
    }

    public static byte[] hex2bybytev2(String data){
        byte[] b = data.getBytes();
        byte[] result = new byte[b.length / 2];

        int flag = 0;

        int i = 0;
        while(i < b.length){
            byte[] tmp = new byte[]{b[i] , b[i+1]};
            result[flag++] = (byte)Integer.parseInt(new String(tmp), 16);
            i += 2;
        }

        return result;

    }

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
/*		String s = "0|信息提示|10|934834\r\n序号|商户号|商户名称|基金简称|基金代码|支付公司交易单号|基金公司交易单号|交易类型|基金交易账户|姓名|证件类型|证件号码|交易金额|币种|交易日|交易时间|交易说明|备注\r\n" +
				"0|201400120120121|广发基金公司|中海可转债债券C|000004|2014110500000001|OD_00239203|1|9923929323|张三|1|411302198702109820|900000|1|20141105|20141105120300|||";
		String data = "111111111111111111111111";
		byte[] result;
		TripleDESEncrypt_BK t = new TripleDESEncrypt_BK();

	    result = t.encryptMode(s.getBytes());
	    System.out.println("加密后的字符串：" + new String(result));

	    t.setMiwen(result);
	    result = t.decryptMode();
	    System.out.print(new String(result));*/

        //System.out.println("转换后的十六进制字符串："+new Test().byte2hex(result));


        //t.setMiwen(Test.hex2bybytev2(new Test().byte2hex(result)));
        //data = new String(t.decryptMode());
        //System.out.println(data);



//        new TripleDESEncrypt_BK().encryFile("D:\\sinitek\\data\\20150202\\back_check_10000005210_20150202_plain.txt", "D:\\sinitek\\data\\20150202\\1.txt");


    }
}
