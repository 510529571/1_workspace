import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * http://skypearl.csair.com/skypearl/cn/b2clogin.action
 * 
 * @class LichengLogin
 * @description
 * @copyRight copyright(c) 2013 广东南航易网通电子商务有限公司,Rights Reserved
 * @time Jan 11, 2013 9:33:42 AM
 */
public class BaiduLogin
{
	private static String SESSIONID = null;
	public static String URL = "http://www.baidu.com/";

	/**
	 * 用户自动登录
	 * @param username
	 * @param password
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static synchronized String login(String username, String password, String type) throws Exception
	{
		URL url = null;
		InputStream in = null;
		HttpURLConnection conn = null;
		String code = "";
		try
		{
			String validator = null;

			for (int i = 0; i < 10; i++)
			{
				validator = getValidator();
				if (validator != null)
					break;
			}

			if (validator == null)
				return "-1";

			String loginstr = "username=" + username + "&password=" + password + "&validator=" + validator + "&type=" + type;
			url = new URL(URL + "loginAction.action");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Accept", "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*");
//			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Length", loginstr.length() + "");
			conn.setRequestProperty("Cookie", SESSIONID);
			conn.setInstanceFollowRedirects(false);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			OutputStream loginout = conn.getOutputStream();
			loginout.write(loginstr.getBytes());
			loginout.close();

			// System.out.println("PageSize->Set-Cookie->" + conn.getHeaderField("Set-Cookie"));
			Map<String, List<String>> map = conn.getHeaderFields();
			Set keyset = map.keySet();
			Iterator iter = keyset.iterator();
			while (iter.hasNext())
			{
				String key = (String) iter.next();
				// System.out.println("key~~~~~~~~~~~~:"+key+"-"+(String) ((List) map.get(key)).get(0));
				if (key != null && key.equals("Set-Cookie"))
				{
					String sid2 = null;
					List cookies = (List) map.get(key);
					//这里做这么多判断就是为了保证取得cs1246643xde的值
					for(int i=0;i<cookies.size();i++)
					{
						String value=(String) ((List) map.get(key)).get(i);
						if(value!=null)
						{
							if(value.indexOf("cs1246643xde")!=-1)
							{
								sid2 = value;
							}
						}
					}
					if(sid2!=null)
					SESSIONID += "; " + sid2.substring(0, sid2.indexOf(";"));
					System.out.println("Set-Cookie~~~~~~~~~~" + SESSIONID);
					break;
				}
			}

			in = conn.getInputStream();
			StringBuffer sb = new StringBuffer();
			int a = 0;
			byte[] b = new byte[1024];
			a = in.read(b);
			while (a != -1)
			{
				sb.append(new String(b, 0, a, "utf-8"));
				a = in.read(b);
			}
			in.close();
			conn.disconnect();
			System.out.println(sb.toString());

//			JSONObject jObject = JSONObject.fromObject(sb.toString());
//			code = (String) jObject.getString("code");

		} catch (Exception e)
		{
			throw e;
		} finally
		{
			try
			{
				if (in != null)
				{
					in.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			if (conn != null)
			{
				conn.disconnect();
			}
		}
		return code;
	}

	/**
	 * 获取验证码
	 * @return
	 * @throws java.net.MalformedURLException
	 * @throws java.io.IOException
	 */
	private static String getValidator() throws MalformedURLException, IOException
	{
		String validator = null;
		URL url = new URL(URL + "validatorAction.action?d=1357868714361");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(10000);
		System.out.println("getPict1~~");
        SESSIONID = conn.getHeaderField("Set-Cookie");
		System.out.println("getPict2~~SESSIONID");
		if(SESSIONID!=null)
		{
            SESSIONID = SESSIONID.substring(0, SESSIONID.indexOf(";"));
		System.out.println("Set-Cookie->" + conn.getHeaderField("Set-Cookie"));
//		validator = new Recognize().lichengRecognize(conn.getInputStream());
		System.out.println(validator);
		}
		conn.disconnect();
		return validator;
	}

	
	/**
	 * 获取用户可用里程
	 * @param username
	 * @param password
	 * @param type
	 * @return
	 */
	public static String getLicheng(String username, String password, String type)
	{
		String retStr = "-1";
		try
		{
			for (int i = 0; i < 10; i++)
			{
				if ("1".equals(login(username, password, type)))
				{
					System.out.println("登陆成功！~~~~");
					break;//登陆成功
				}
				if(i==9)
				{
					//登陆十次后仍然失败，则退出
					return retStr;
				}
			}
			
			
			// InetAddress iaddr= InetAddress.getByAddress(new byte[]{10,102,-98,-58});
			// SocketAddress addr=new InetSocketAddress(iaddr, 1519);
			// Proxy typeProxy = new Proxy(Proxy.Type.HTTP, addr);

			URL url = new URL("http://skypearl.csair.com/skypearl/cn/b2clogin.action");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(10000);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Cookie", SESSIONID);

			InputStream in = conn.getInputStream();
			StringBuffer sb = new StringBuffer();
			int a = 0;
			byte[] b = new byte[1024];
			a = in.read(b);
			while (a != -1)
			{
				sb.append(new String(b, 0, a, "utf-8"));
				a = in.read(b);
			}
			in.close();
			conn.disconnect();
			System.out.println("returnHtml~~" + sb.toString());

//			Parser parser = new Parser(sb.toString());
//			parser.setEncoding("utf-8");
//			NodeFilter ulFilter1 = new NodeFilter()
//			{
//				public boolean accept(Node node)
//				{
//					if (node.getText().startsWith("div class=\"myubgx\""))
//					{
//						return true;
//					} else
//					{
//						return false;
//					}
//				}
//			};
//			NodeList list1 = parser.extractAllNodesThatMatch(ulFilter1);
//			String content1 = list1.elementAt(0).toHtml();
//
//			parser = new Parser(content1);
//			parser.setEncoding("utf-8");
//			NodeFilter ulFilter2 = new NodeFilter()
//			{
//				public boolean accept(Node node)
//				{
//					if (node.getText().startsWith("p"))
//					{
//						return true;
//					} else
//					{
//						return false;
//					}
//				}
//			};
//			NodeList list2 = parser.extractAllNodesThatMatch(ulFilter2);
//			String content2 = list2.elementAt(0).toHtml();
//
//			content2 = content2.replace("<p>可用里程： ", "");
//			content2 = content2.replace("公里</p>", "");
//			content2 = content2.replace(",", "");
//
//			retStr = content2;
//			System.out.println(content2);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retStr;
	}

	public static void main(String[] args)
	{
		try
		{
			BaiduLogin.getLicheng("280008300083", "123456", "1");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
