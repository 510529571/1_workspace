package own.hhw;

import org.apache.log4j.*;
import own.hhw.sercurity.*;

//			Md5Encrypt, RSA

public class GenericSignTools
{

	private static final Logger logger = Logger.getLogger(GenericSignTools.class);

	public GenericSignTools()
	{
	}

	public static String genSignStr(String content, String privateKey, String inputCharset, String signType)
	{
		if (signType.equals("MD5"))
		{
			String signBefore = (new StringBuilder()).append(content).append(privateKey).toString();
			if (logger.isDebugEnabled())
				logger.debug((new StringBuilder()).append("signBefore = ").append(signBefore).toString());
			return Md5Encrypt.md5(signBefore, inputCharset);
		}
		if (signType.equals("RSA"))
		{
			if (logger.isDebugEnabled())
				logger.debug((new StringBuilder()).append("signBefore = ").append(content).toString());
			return RSA.sign(content, privateKey, inputCharset);
		} else
		{
			return null;
		}
	}

}
