
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import cn.com.infosec.icbc.ReturnValue;
import cn.com.icbc.CMS.commontools.TranslationTool;

import java.io.IOException;

import org.mortbay.util.B64Code;

public class Test
{
    public Test()
    {
    }
    public static void main(String[] args)
    {
        byte[] plaintext = ("this is a test message to be test in cn.com.infosec.icbc").getBytes();
        char[] ciphertext=null, restoredPlaintext = null;

        try
        {
            System.out.print("test Base64 Encoding and Base64 Decoding...");
            char[] encodedMsg = B64Code.encode(plaintext);
            byte[] decodedMsg = B64Code.decode(encodedMsg);
            if( java.util.Arrays.equals(decodedMsg,plaintext) )
                System.out.println("OK");
            else
                System.out.println("Fail");

           
            System.out.print("test HextoASCII Encoding and ASCIItoHex Decoding...");
            String strHexToAscii = TranslationTool.HextoASCII(plaintext);
            decodedMsg = TranslationTool.ASCIItoHex(strHexToAscii);
            if( java.util.Arrays.equals(decodedMsg,plaintext) )
                System.out.println("OK");
            else
                System.out.println("Fail");
            
           
            byte[] privateKey = null;
    		try
    		{
    		    privateKey = TranslationTool.readFile("ceshizhang.key");
    		}
    		catch (IOException e2)
    		{
    			System.out.println("无法读取企业数据层私钥文件"+e2);
    		}    		
    		
    		byte[] cert = null;
    		try
			{

				cert = TranslationTool.readFile("ceshizhang.cer");
			}
			catch (IOException e4)
			{
			    System.out.println("无法读取工行数据层公钥文件"+e4);
			}
                                 
            System.out.print("test ReturnValue.sign() and ReturnValue.verifySign()...");
            byte[] signature = ReturnValue.sign(plaintext,plaintext.length,privateKey,"12345678".toCharArray());
            
            int verifyit = ReturnValue.verifySign(plaintext,plaintext.length,cert,signature);
            if( verifyit == 0 )
            {
                System.out.println("OK");
            }
            else
            {
                System.out.println("Fail");
            }
           
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}