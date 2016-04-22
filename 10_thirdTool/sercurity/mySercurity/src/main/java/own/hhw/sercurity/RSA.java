package own.hhw.sercurity;

import own.hhw.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *—È«©
 */
public class RSA
{
  public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

  public static String sign(String content, String privateKey, String input_charset)
  {
    PKCS8EncodedKeySpec priPKCS8;
    try
    {
      priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));

      KeyFactory keyf = KeyFactory.getInstance("RSA");
      PrivateKey priKey = keyf.generatePrivate(priPKCS8);

      Signature signature = Signature.getInstance("SHA1WithRSA");

      signature.initSign(priKey);
      signature.update(content.getBytes(input_charset));

      byte[] signed = signature.sign();

      String signStr = Base64.encode(signed);

      return signStr;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return null;
  }

  public static boolean doCheck(String content, String sign, String public_key, String input_charset)
  {
    KeyFactory keyFactory;
    try
    {
      keyFactory = KeyFactory.getInstance("RSA");
      byte[] encodedKey = Base64.decode(public_key);
      PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

      Signature signature = Signature.getInstance("SHA1WithRSA");

      signature.initVerify(pubKey);
      signature.update(content.getBytes(input_charset));

      boolean bverify = signature.verify(Base64.decode(sign));
      return bverify;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return false;
  }

  public static PrivateKey getPrivateKey(String key)
    throws Exception {

      PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(key)));

      return privateKey;
  }
}