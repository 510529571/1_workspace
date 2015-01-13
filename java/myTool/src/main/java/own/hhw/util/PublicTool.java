package own.hhw.util;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.*;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @class PublicTool
 * @description 
 * @author karl
 * @copyRight copyright(c) 2013 广东南航易网通电子商务有限公司,Rights Reserved
 * @time Apr 10, 2013 5:21:52 PM
 */
public class PublicTool
{
	/**
	 * 获取本机ip
	 * 
	 * @return
	 */
	public static String GetIpAdd()
	{
		try
		{
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e)
		{// TODO 自动生成 catch 块
			return e.getMessage();// e.printStackTrace();
		}
	}


	public void testGetIpAdd()
	{
		System.out.println(GetIpAdd());
	}

	/**
	 * 加上时区后的当前时间
	 * 
	 * @return
	 */
	public static Date GetNewDate()
	{
		String fromFormat = "yyyy-MM-dd HH:mm:ss:ms";
		SimpleDateFormat format = new SimpleDateFormat(fromFormat);
		Date myDate = new Date();
		TimeZone zone = TimeZone.getTimeZone("GMT+8");
		format.setTimeZone(zone);
		return StringToDate(format.format(myDate), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @param str
	 *            要转换的日期字符串
	 * @param formatStr
	 *            格式化模板 下面是formatStr模板的列子 yyyy-MM-dd HH:mm:ss.ms yyyy-MM-dd HH:mm:ss yyyyMMddHHmmssms yyyyMMddHHmmss yyyy-MM-dd yyyyMMdd yyyyMMdd HH:mm:ss yyMMdd
	 * @return
	 */
	public static Date StringToDate(String str, String formatStr)
	{
		SimpleDateFormat sdfIn = new SimpleDateFormat(formatStr);
		Date dataTmp = null;
		try
		{
			dataTmp = sdfIn.parse(str);
		} catch (ParseException e)
		{
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return dataTmp;
	}

	/**
	 * @param str
	 *            要转换的日期字符串
	 * @param formatStr
	 *            格式化模板 下面是formatStr模板的列子 yyyy-MM-dd HH:mm:ss.ms yyyy-MM-dd HH:mm:ss yyyyMMddHHmmssms yyyyMMddHHmmss yyyy-MM-dd yyyyMMdd yyyyMMdd HH:mm:ss yyMMdd
	 * @return
	 */
	public static String stringToString(String str, String formatStr)
	{
		SimpleDateFormat sdfIn = new SimpleDateFormat(formatStr);
		Date dataTmp = null;
		try
		{
			dataTmp = sdfIn.parse(str);
		} catch (ParseException e)
		{
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return sdfIn.format(dataTmp);
	}
	
	/**
	 *            要转换的日期字符串
	 * @param formatStr
	 *            格式化模板 下面是formatStr模板的列子 yyyy-MM-dd HH:mm:ss.ms yyyy-MM-dd HH:mm:ss yyyyMMddHHmmssms yyyyMMddHHmmss yyyy-MM-dd yyyyMMdd yyyyMMdd HH:mm:ss yyMMdd
	 * @return
	 */
	public static String dateToString(Date dateStr, String formatStr)
	{
		SimpleDateFormat sdfIn = new SimpleDateFormat(formatStr);
		return sdfIn.format(dateStr);
	}

	/**
	 * 返回想要的随机数，Nub代表随机数的位数 比如 Nub为3，则返回的字符串可能为 254
	 * 
	 * @param Nub
	 * @return
	 */
	public static String getRandomNub(int Nub)
	{

		String num = "";
		for (int i = 0; i < Nub; i++)
		{
			num += String.valueOf((int) (10 * Math.random()));
		}
		return num;
	}

	public void testGetRandomNub()
	{
		System.out.println((int) (10 * Math.random()));
		System.out.println(getRandomNub(9));
	}

	/**
	 * 把用'拼接的ascci码转换为字符串
	 * @param str
	 * @return
	 */
	public static String dec(String str)
	{
		String[] strarr = str.split("\\'");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strarr.length; i++)
		{
			sb.append((char) Integer.parseInt(strarr[i]));
		}
		return sb + "";
	}

    /**
	 * 将整数的个位四舍五入，如525要变成530
	 * @return
	 */
	public static String numF(String integerstr){
		int lastnum = Integer .parseInt(integerstr.substring(integerstr.length()-1));
		if(lastnum>=5){
			return (Integer.parseInt(integerstr)-lastnum+10)+"";
		}else{
			return (Integer.parseInt(integerstr)-lastnum)+"";			
		}
	}
	
	/**
	 * 返回一个dom
	 * @param xmlstr
	 * @return
	 */
	public static Document getDom(String xmlstr){//返回一个dom
		Document doc = null;
		try {
			DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
			DocumentBuilder dombuilder = domfac.newDocumentBuilder();
			doc = dombuilder.parse(new InputSource(new StringReader(xmlstr)));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

    /**
	 * 请求xml数据
	 * @param url  
	 * @param soapAction
	 * @param xml
	 * @return
	 */
	public static String sendXMl(String url,String soapAction,String xml){
		HttpURLConnection conn = null;
		InputStream in = null;
		InputStreamReader isr = null;
		OutputStream out = null;
		StringBuffer result = null;
		try {
			byte[] sendbyte = xml.getBytes("UTF-8");
			URL u = new URL(url);
			conn = (HttpURLConnection)u.openConnection();
			conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			conn.setRequestProperty("SOAPAction", soapAction);
			conn.setRequestProperty("Content-Length", sendbyte.length+"");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			
			out = conn.getOutputStream();
			out.write(sendbyte);
			
			if(conn.getResponseCode()==200){
				result = new StringBuffer();
				in = conn.getInputStream();
				isr = new InputStreamReader(in,"UTF-8");
				char[] c = new char[1024];
				int a = isr.read(c);
				while(a!=-1){
					result.append(new String(c,0,a));
					a = isr.read(c);
				}
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				conn.disconnect();
			}
			try {
				if(in!=null){
					in.close();
				}
				if(isr!=null){
					isr.close();
				}
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result==null?null:result+"";
	}
	
	/**
	 * @param cls 一个对象的Class属性
	 * @return 这个类的class文件位置的绝对路径。 如果没有这个类的定义，则返回null。
	 */
	//TODO hhw 这个方法不太理解
	public String getPathFromClass(Class cls) throws IOException
	{
		String path = null;
		if (cls == null)
		{
			throw new NullPointerException();
		}
		URL url = getClassLocationURL(cls);
		if (url != null)
		{
			path = url.getPath();
			if ("jar".equalsIgnoreCase(url.getProtocol()))
			{
				try
				{
					path = new URL(path).getPath();
				} catch (MalformedURLException e)
				{
				}
				int location = path.indexOf("!/");
				if (location != -1)
				{
					path = path.substring(0, location);
				}
			}

			File file = new File(path.replaceAll("%20", " "));
			path = file.getCanonicalPath();
		}
		return path;
	}

    /**
     * hhw:tag 得到指定日所对应的最近日期
     * @param num
     * @return
     */
    public static Date getDate(String num) {
        try {
            int dayNum = Integer.parseInt(num);
            Calendar calendar= Calendar.getInstance();
            calendar.setTime( new Date());
            int nowNum=calendar.get(Calendar.DATE);

            if (nowNum <= dayNum) {
                calendar.set(Calendar.DATE, dayNum);
                return  calendar.getTime();
            } else {
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DATE, dayNum);
                return  calendar.getTime();
            }
        } catch (Exception e) {

        }
        return null;
    }

	/**
	 * 获取类的class文件位置的URL。这个方法是本类最基础的方法，供其它方法调用。
	 */
	public URL getClassLocationURL(final Class cls)
	{
		if (cls == null)
		{
			throw new IllegalArgumentException("class that input is null");
		}
		URL result = null;
		final String clsAsResource = cls.getName().replace('.', '/').concat(".class");
		final ProtectionDomain pd = cls.getProtectionDomain();
		if (pd != null)
		{
			final CodeSource cs = pd.getCodeSource();
			if (cs != null)
			{
				result = cs.getLocation();

			}
			if (result != null)
			{
				if ("file".equals(result.getProtocol()))
				{
					try
					{
						if (result.toExternalForm().endsWith(".jar") || result.toExternalForm().endsWith(".zip"))
						{
							result = new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
						} else if (new File(result.getFile()).isDirectory())
						{
							result = new URL(result, clsAsResource);
						}
					} catch (MalformedURLException ignore)
					{
					}
				}
			}
		}

		if (result == null)
		{
			final ClassLoader clsLoader = cls.getClassLoader();
			result = clsLoader != null ? clsLoader.getResource(clsAsResource) : ClassLoader.getSystemResource(clsAsResource);
		}
		return result;
	}
	
	public void testDec()
	{
		System.out.println(dec("97'99'"));
		System.out.println((char)98);
		
		try
        {
	       System.out.println(getPathFromClass(String.class)); 
        } catch (IOException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}

    public static File makeFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        return file;
    }
	
}
