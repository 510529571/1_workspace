
package own.hhw.util;


import java.io.*;
import java.net.*;
import java.security.CodeSource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.ProtectionDomain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

/**
 * 1.1
 *
 * @author
 * @class Public
 * @description
 * @copyRight copyright(c) 2013 广东南航易网通电子商务有限公司,Rights Reserved
 * @time Mar 21, 2013 11:41:12 AM
 */
public class Public {

    private static int iTmpNo = 0;


    public static String GetIpAdd() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {// TODO 自动生成 catch 块
            return e.getMessage();// e.printStackTrace();
        }
    }

    /**
     * 获取当前时间，而且是加上了时区设置的
     *
     * @return
     */
    public static Date getNowDate() {
        String fromFormat = "yyyy-MM-dd HH:mm:ss:ms";
        SimpleDateFormat format = new SimpleDateFormat(fromFormat);
        TimeZone zone = TimeZone.getTimeZone("GMT+8");
        format.setTimeZone(zone);
        Date myDate = new Date();
        try {
            myDate = format.parse(format.format(myDate));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return myDate;
    }

    public static Date StringToDate(String str) {
        SimpleDateFormat sdfIn;
        sdfIn = new SimpleDateFormat("yyyy-MM-dd");
        Date dataTmp = null;
        try {
            dataTmp = sdfIn.parse(str);
        } catch (ParseException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return dataTmp;
    }

    /**
     * 日期增减天数k,正数为增,负数为减
     */
    public static Date addDate(int k) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, k);
        Date d = cal.getTime();
        return d;
    }


    /**
     * @param date
     * @param format 格式 yyyyMMdd HHmmssms 自己任意去选
     * @return
     */
    public static String date2String(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * @param dateStr
     * @param format  格式 yyyyMMdd HHmmssms 自己任意去选
     * @return
     */
    public static Date string2Date(String dateStr, String format) {
        SimpleDateFormat dformat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dformat.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取文件的绝对路径
     *
     * @param fileName /file.txt  这种格式
     * @return
     */
    public static String getSourcePath(String fileName) {
        return URLDecoder.decode(Public.class.getResource(fileName).getFile());
    }

    public void testGetIpAdd() throws IOException {
//		System.out.println(Public.date2String(Public.getNowDate(), "【yyyy年MM月dd日 HH时mm分ss秒ms毫秒】"));

        //TODO(karl) 为什么URL对象的文件路径是null
        //System.out.println(URL.class.getResource(""));

        System.out.println(this.getClass().getResource("../Thread").getPath());
//		System.out.println(Public.getProVal("sitename", "../../../prop.properties"));
    }

    /**
     * 从properties文件读取数据
     *
     * @param key
     * @param proPath
     * @return
     * @tag properties
     */
    public static String getProVal(String key, String proPath) {
        proPath = Public.getSourcePath(proPath);
        FileInputStream in = null;
        Properties properties = new Properties();
        try {
            in = new FileInputStream(proPath);
            if (in != null) {
                properties.load(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return properties.getProperty(key);
    }

    public static boolean setProVal(Properties prop, String proPath) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(proPath);
            prop.store(fos, "里程密码");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
        return true;
    }

    public static String getRandomNub(int Nub) {
        String num = "";
        for (int i = 0; i < Nub; i++) {
            num += String.valueOf((int) (10 * Math.random()));
        }
        return num;
    }

    public static String dec(String str) {
        String[] strarr = str.split("\\'");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strarr.length; i++) {
            sb.append((char) Integer.parseInt(strarr[i]));
        }
        return sb + "";
    }

    public static void writeLog(String userid, String logStr) {
        // System.out.println("【"+userid+"】【"+Public.DateTOString(Public.GetNewDate())+"】"+logStr);
    }

    /**
     * 比较指定时间与当前时间相差多少分钟
     */
    public static long compareTime(Date time) {
        return (getNowDate().getTime() - time.getTime()) / (1000 * 60);
    }

    /**
     * 确定是否今天的日期
     */
    public static boolean isToday(Date date) {
        if (date == null) return false;
        return date2String(getNowDate(), "yyyyMMdd HHmmss").equals(date2String(date, "yyyyMMdd HHmmss"));
    }


    /**
     * 将整数的个位四舍五入，如525要变成530
     *
     * @return
     */
    public static String numF(String integerstr) {
        int lastnum = Integer.parseInt(integerstr.substring(integerstr.length() - 1));
        if (lastnum >= 5) {
            return (Integer.parseInt(integerstr) - lastnum + 10) + "";
        } else {
            return (Integer.parseInt(integerstr) - lastnum) + "";
        }
    }

    /**
     * @param cls 一个对象的Class属性
     * @return 这个类的class文件位置的绝对路径。 如果没有这个类的定义，则返回null。
     */
    public String getPathFromClass(Class cls) throws IOException {
        String path = null;
        if (cls == null) {
            throw new NullPointerException();
        }
        URL url = getClassLocationURL(cls);
        if (url != null) {
            path = url.getPath();
            if ("jar".equalsIgnoreCase(url.getProtocol())) {
                try {
                    path = new URL(path).getPath();
                } catch (MalformedURLException e) {
                }
                int location = path.indexOf("!/");
                if (location != -1) {
                    path = path.substring(0, location);
                }
            }

            File file = new File(path.replaceAll("%20", " "));
            path = file.getCanonicalPath();
        }
        return path;
    }

    /**
     * 获取类的class文件位置的URL。这个方法是本类最基础的方法，供其它方法调用。
     */
    public URL getClassLocationURL(final Class cls) {
        if (cls == null) {
            throw new IllegalArgumentException("class that input is null");
        }
        URL result = null;
        final String clsAsResource = cls.getName().replace('.', '/')
                .concat(".class");
        final ProtectionDomain pd = cls.getProtectionDomain();
        if (pd != null) {
            final CodeSource cs = pd.getCodeSource();
            if (cs != null) {
                result = cs.getLocation();

            }
            if (result != null) {
                if ("file".equals(result.getProtocol())) {
                    try {
                        if (result.toExternalForm().endsWith(".jar")
                                || result.toExternalForm().endsWith(".zip")) {
                            result = new URL("jar:"
                                    .concat(result.toExternalForm())
                                    .concat("!/").concat(clsAsResource));
                        } else if (new File(result.getFile()).isDirectory()) {
                            result = new URL(result, clsAsResource);
                        }
                    } catch (MalformedURLException ignore) {
                    }
                }
            }
        }

        if (result == null) {
            final ClassLoader clsLoader = cls.getClassLoader();
            result = clsLoader != null ? clsLoader.getResource(clsAsResource)
                    : ClassLoader.getSystemResource(clsAsResource);
        }
        return result;
    }

    /**
     * 将字符串用MD5进行编码,得到32位字符串
     *
     * @param str
     * @return
     */
    public static String getMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(str.getBytes("UTF-8"));

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    /**
     * 将字符串用MD5进行编码,得到16位字符串
     *
     * @param str
     * @return
     */
    public static String getMD5_16(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return getMD5(str).toString().substring(8,24);
    }

    public static void main(String[] args) throws Exception {
        Public p = new Public();
        System.out.println(p.numF("159"));

        System.out.println(Public.getMD5("123"));
        //System.out.println(compareTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-07-23 15:42:00")));
    }
}
