package own.hhw.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import org.apache.log4j.Logger;
import own.hhw.Md5Encrypt;

public class FundPayUtil
{

	private static final Logger logger = Logger.getLogger(FundPayUtil.class);

	public FundPayUtil()
	{
	}

	public static Map paraFilter(Map sArray)
	{
		Map result = new HashMap();
		if (sArray == null || sArray.size() <= 0)
			return result;
		Iterator i$ = sArray.keySet().iterator();
		do
		{
			if (!i$.hasNext())
				break;
			String key = (String)i$.next();
			String value = (String)sArray.get(key);
			if (value != null && !value.equals("") && !key.equalsIgnoreCase("sign") && !key.equalsIgnoreCase("signType"))
				result.put(key, value);
		} while (true);
		return result;
	}

	public static String createLinkString(Map params)
	{
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++)
		{
			String key = (String)keys.get(i);
			String value = (String)params.get(key);
			if (i == keys.size() - 1)
				prestr = (new StringBuilder()).append(prestr).append(key).append("=").append(value).toString();
			else
				prestr = (new StringBuilder()).append(prestr).append(key).append("=").append(value).append("&").toString();
		}

		return prestr;
	}

	public static String createLinkStringForCharset(Map params, String inputCharset)
		throws UnsupportedEncodingException
	{
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++)
		{
			String key = (String)keys.get(i);
			String value = (String)params.get(key);
			if (i == keys.size() - 1)
				prestr = (new StringBuilder()).append(prestr).append(key).append("=").append(URLEncoder.encode(value, inputCharset)).toString();
			else
				prestr = (new StringBuilder()).append(prestr).append(key).append("=").append(URLEncoder.encode(value, inputCharset)).append("&").toString();
		}

		return prestr;
	}

	public static String createLinkStr(Map params, String inputCharset)
		throws UnsupportedEncodingException
	{
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++)
		{
			String key = (String)keys.get(i);
			String value = (String)params.get(key);
			if (i == keys.size() - 1)
				prestr = (new StringBuilder()).append(prestr).append(key).append("=").append(value).toString();
			else
				prestr = (new StringBuilder()).append(prestr).append(key).append("=").append(value).append("&").toString();
		}

		return prestr;
	}

	public static String buildMd5Sign(Map sArray, String key)
	{
		Map params = paraFilter(sArray);
		String prestr = createLinkString(params);
		prestr = (new StringBuilder()).append(prestr).append(key).toString();
		String inputCharset = (String)params.get("inputCharset");
		if (logger.isInfoEnabled())
		{
			logger.info((new StringBuilder()).append("MD5 ÑéÇ©  inputCharset >>> ").append((String)params.get("inputCharset")).toString());
			logger.info((new StringBuilder()).append("MD5 ÑéÇ©´® >>> ").append(prestr).toString());
		}
		String localSign = Md5Encrypt.md5(prestr, inputCharset);
		return localSign;
	}

	public static String consGenRsaSignParams(Map sArray)
	{
		Map params = paraFilter(sArray);
		String prestr = createLinkString(params);
		return prestr;
	}

}
