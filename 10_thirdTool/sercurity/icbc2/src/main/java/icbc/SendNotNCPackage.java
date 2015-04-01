package icbc;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.contrib.ssl.AuthSSLProtocolSocketFactory;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import cn.com.icbc.CMS.commontools.TranslationTool;
import cn.com.infosec.icbc.ReturnValue;


/**
 * @author 
 *
 * TODO 模拟企业非NC方式向银行外联前置服务器（gtcg）发送报文
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SendNotNCPackage implements Runnable{
	
	private static final String ip = "118.6.13.101"; //银行外联前置服务器（gtcg），可能需要根据实际情况修改
	private static final int port = 8108; //银行外联前置服务器（gtcg）-非NC接入端口，可能需要根据实际情况修改
	public SendNotNCPackage(String transCode,String bankCode,String groupCIS,String ID,String packageId,String xml){
			this.TransCode=transCode;
			this.BankCode=bankCode;
			this.GroupCIS=groupCIS;
			this.ID=ID;
			this.Cert=Cert;
			this.xml=xml;
			this.PackageID=packageId;
		}
 
	public String Version = ""; //接口版本号，一般为0.0.0.1或0.0.1.0
	public String TransCode = "";//交易代码，一般为SZFH_开头的一串字符
	public String BankCode = ""; //固定值，为102
	public String GroupCIS = "";//集团编码，根据测试环境提供数据录入，形如：400090001229524
	public String ID = "";//客户证书ID名，根据测试环境提供数据录入，形如：test.d.4000或test.y.4000
	public String PackageID = "";//包序列号，企业随机生成，一笔报文的唯一标识，不可重复
	private static String Cert = null;//企业数据层公钥证书（形如：test.cer）通过二进制方式读取后，生成的值
	private static String reqData = null;
	public String xml="";

	public static void main(String []args){
		try {
			URL url=new URL("file:D:\\workspace\\DirectLinkWeb\\WebContent\\WEB-INF\\classes\\com\\szicbc\\presstest\\certificate\\shenzhen2trust");
			url.openConnection();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
								   
	}
	
	/*
	 * 向银行外联前置服务器（gtcg）发送报文
	 */
	public void testNC() throws Exception{
	    
		// 读取私钥和密码
		byte[] key = null;
		try {
			
			key = TranslationTool.readFile("D:\\workspace\\DirectLinkWeb\\WebContent\\WEB-INF\\classes\\com\\szicbc\\presstest\\certificate\\test.key");//读取企业私钥文件
		} catch (IOException e2) {
			System.out.println("无法读取企业数据层私钥文件:" + e2);
			return;
		}
		char[] password = "12345678".toCharArray(); //12345678为测试环境企业私钥文件密码

		// 对XML报文签名
		byte[] signature = null, xmlByte = xml.getBytes();
		try {
			signature = ReturnValue.sign(xmlByte, xmlByte.length, key, password);//用私钥对明文进行签名
		} catch (Exception e3) {
			System.out.println("无法生成签名:" + e3);
			return;
		}
		
		// 读取公钥
		byte[] certificate = null;
		try {
			certificate = TranslationTool.readFile("D:\\workspace\\DirectLinkWeb\\WebContent\\WEB-INF\\classes\\com\\szicbc\\presstest\\certificate\\test.cer");//读取企业公钥信息
		} catch (IOException e4) {
			System.out.println("无法读取企业数据层公钥文件:" + e4);
			return;
		}
		Cert = base64Encode(certificate);//对公钥信息进行base64编码

		// 组reqData
		String lenStr = "" + xml.length();//获得明文数据长度
		while (lenStr.length() < 10) {
			lenStr = "0" + lenStr;
		}
		reqData = base64Encode(lenStr + xml + "ICBCCMP" + base64Encode(signature));
		
		// 打印调试信息
		System.out.println("Version=" + Version);
		System.out.println("TransCode=" + TransCode);
		System.out.println("BankCode=" + BankCode);
		System.out.println("GroupCIS=" + GroupCIS);
		System.out.println("ID=" + ID);
		System.out.println("PackageID=" + PackageID);
		System.out.println("Cert=" + Cert);
		System.out.println();
		System.out.println();
		System.out.println("Base64编码前的reqData=");
		System.out.println(lenStr + xml + "ICBCCMP" + base64Encode(signature));
		System.out.println();
		System.out.println("reqData=");
		System.out.println(reqData);
		System.out.println();
		System.out.println();
		System.out.println();

		// 组HTTP报文
		PostMethod mypost = new PostMethod();
		mypost.addParameter("Version", Version);
		mypost.addParameter("TransCode", TransCode);
		mypost.addParameter("BankCode", BankCode);
		mypost.addParameter("GroupCIS", GroupCIS);
		mypost.addParameter("ID", ID);
		mypost.addParameter("PackageID", PackageID);
		mypost.addParameter("Cert", Cert);
		mypost.addParameter("reqData", reqData);

        int timeout=10000;
		// 发送HTTPS报文
		try {
			Protocol myhttps = new Protocol("https",
					new AuthSSLProtocolSocketFactory(null, null, new URL(
							"file:D:\\workspace\\DirectLinkWeb\\WebContent\\WEB-INF\\classes\\com\\szicbc\\presstest\\certificate\\shenzhen2trust"), null), 443); // 443端口始终不需要修改
			HttpConnection myconn = new HttpConnection(ip, port, myhttps);
    	myconn.open();
      myconn.setSoTimeout(timeout);       
            
			
			int re_code = mypost.execute(new HttpState(), myconn);
			
			
			
			if (re_code == 200) {
				System.out.println("成功发送");
			} else {
				System.out.println("发送失败，http错误码" + re_code);
			}
		} catch (MalformedURLException e1) {
			System.out.println("无法读取工行通讯层证书:" + e1);
		} catch (Exception e1) {
			System.out.println("通讯异常" + e1);
		}
		
		// 打印返回报文
		String result = mypost.getResponseBodyAsString();
		System.out.println("返回报文=");
		System.out.println(result);
		System.out.println();
		if (result.substring(0, 8).equals("reqData=")) {
			System.out.println("返回报文Base64解码后=");
			System.out.println(base64Decode(result.substring(8)));
		}
	 
	}
	
	// Base64编码和解码方法，可以根据自己使用的库重新编写
	private static String base64Encode(String str) {
		return base64Encode(str.getBytes());
	}
	
	private static String base64Encode(byte[] by) {
		String result = null;
		BASE64Encoder encoder = new BASE64Encoder();
		result = encoder.encodeBuffer(by);
		return result;
	}
	
	private static String base64Decode(String str) {
		String result = null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			result = new String(decoder.decodeBuffer(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/* （非 Javadoc）
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO 自动生成方法存根
		try {
			testNC();
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
}
