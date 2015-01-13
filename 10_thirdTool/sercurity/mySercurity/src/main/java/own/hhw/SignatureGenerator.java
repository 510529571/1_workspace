package own.hhw;

import java.util.*;

//			GenericSignTools

public class SignatureGenerator
{

	public SignatureGenerator()
	{
	}

	public static String genSignStr(Map params, String signType, String privateKey, String inputCharset)
	{
		Properties properties = new Properties();
		Iterator iter = params.keySet().iterator();
		do
		{
			if (!iter.hasNext())
				break;
			String name = (String)iter.next();
			if (name != null && !name.equalsIgnoreCase("sign") && !name.equalsIgnoreCase("signType"))
			{
				String value = (String)params.get(name);
				properties.setProperty(name, value);
			}
		} while (true);
		String content = getSignatureContent(properties);
		return GenericSignTools.genSignStr(content, privateKey, inputCharset, signType);
	}

	public static String getSignatureContent(Properties properties)
	{
		StringBuffer content = new StringBuffer();
		List keys = new ArrayList(properties.keySet());
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++)
		{
			String key = (String)keys.get(i);
			String value = properties.getProperty(key);
			content.append((new StringBuilder()).append(i != 0 ? "&" : "").append(key).append("=").append(value).toString());
		}

		return content.toString();
	}
}
