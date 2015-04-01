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
 * TODO ģ����ҵ��NC��ʽ����������ǰ�÷�������gtcg�����ͱ���
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SendNotNCPackage implements Runnable{
	
	private static final String ip = "118.6.13.101"; //��������ǰ�÷�������gtcg����������Ҫ����ʵ������޸�
	private static final int port = 8108; //��������ǰ�÷�������gtcg��-��NC����˿ڣ�������Ҫ����ʵ������޸�
	public SendNotNCPackage(String transCode,String bankCode,String groupCIS,String ID,String packageId,String xml){
			this.TransCode=transCode;
			this.BankCode=bankCode;
			this.GroupCIS=groupCIS;
			this.ID=ID;
			this.Cert=Cert;
			this.xml=xml;
			this.PackageID=packageId;
		}
 
	public String Version = ""; //�ӿڰ汾�ţ�һ��Ϊ0.0.0.1��0.0.1.0
	public String TransCode = "";//���״��룬һ��ΪSZFH_��ͷ��һ���ַ�
	public String BankCode = ""; //�̶�ֵ��Ϊ102
	public String GroupCIS = "";//���ű��룬���ݲ��Ի����ṩ����¼�룬���磺400090001229524
	public String ID = "";//�ͻ�֤��ID�������ݲ��Ի����ṩ����¼�룬���磺test.d.4000��test.y.4000
	public String PackageID = "";//�����кţ���ҵ������ɣ�һ�ʱ��ĵ�Ψһ��ʶ�������ظ�
	private static String Cert = null;//��ҵ���ݲ㹫Կ֤�飨���磺test.cer��ͨ�������Ʒ�ʽ��ȡ�����ɵ�ֵ
	private static String reqData = null;
	public String xml="";

	public static void main(String []args){
		try {
			URL url=new URL("file:D:\\workspace\\DirectLinkWeb\\WebContent\\WEB-INF\\classes\\com\\szicbc\\presstest\\certificate\\shenzhen2trust");
			url.openConnection();
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
								   
	}
	
	/*
	 * ����������ǰ�÷�������gtcg�����ͱ���
	 */
	public void testNC() throws Exception{
	    
		// ��ȡ˽Կ������
		byte[] key = null;
		try {
			
			key = TranslationTool.readFile("D:\\workspace\\DirectLinkWeb\\WebContent\\WEB-INF\\classes\\com\\szicbc\\presstest\\certificate\\test.key");//��ȡ��ҵ˽Կ�ļ�
		} catch (IOException e2) {
			System.out.println("�޷���ȡ��ҵ���ݲ�˽Կ�ļ�:" + e2);
			return;
		}
		char[] password = "12345678".toCharArray(); //12345678Ϊ���Ի�����ҵ˽Կ�ļ�����

		// ��XML����ǩ��
		byte[] signature = null, xmlByte = xml.getBytes();
		try {
			signature = ReturnValue.sign(xmlByte, xmlByte.length, key, password);//��˽Կ�����Ľ���ǩ��
		} catch (Exception e3) {
			System.out.println("�޷�����ǩ��:" + e3);
			return;
		}
		
		// ��ȡ��Կ
		byte[] certificate = null;
		try {
			certificate = TranslationTool.readFile("D:\\workspace\\DirectLinkWeb\\WebContent\\WEB-INF\\classes\\com\\szicbc\\presstest\\certificate\\test.cer");//��ȡ��ҵ��Կ��Ϣ
		} catch (IOException e4) {
			System.out.println("�޷���ȡ��ҵ���ݲ㹫Կ�ļ�:" + e4);
			return;
		}
		Cert = base64Encode(certificate);//�Թ�Կ��Ϣ����base64����

		// ��reqData
		String lenStr = "" + xml.length();//����������ݳ���
		while (lenStr.length() < 10) {
			lenStr = "0" + lenStr;
		}
		reqData = base64Encode(lenStr + xml + "ICBCCMP" + base64Encode(signature));
		
		// ��ӡ������Ϣ
		System.out.println("Version=" + Version);
		System.out.println("TransCode=" + TransCode);
		System.out.println("BankCode=" + BankCode);
		System.out.println("GroupCIS=" + GroupCIS);
		System.out.println("ID=" + ID);
		System.out.println("PackageID=" + PackageID);
		System.out.println("Cert=" + Cert);
		System.out.println();
		System.out.println();
		System.out.println("Base64����ǰ��reqData=");
		System.out.println(lenStr + xml + "ICBCCMP" + base64Encode(signature));
		System.out.println();
		System.out.println("reqData=");
		System.out.println(reqData);
		System.out.println();
		System.out.println();
		System.out.println();

		// ��HTTP����
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
		// ����HTTPS����
		try {
			Protocol myhttps = new Protocol("https",
					new AuthSSLProtocolSocketFactory(null, null, new URL(
							"file:D:\\workspace\\DirectLinkWeb\\WebContent\\WEB-INF\\classes\\com\\szicbc\\presstest\\certificate\\shenzhen2trust"), null), 443); // 443�˿�ʼ�ղ���Ҫ�޸�
			HttpConnection myconn = new HttpConnection(ip, port, myhttps);
    	myconn.open();
      myconn.setSoTimeout(timeout);       
            
			
			int re_code = mypost.execute(new HttpState(), myconn);
			
			
			
			if (re_code == 200) {
				System.out.println("�ɹ�����");
			} else {
				System.out.println("����ʧ�ܣ�http������" + re_code);
			}
		} catch (MalformedURLException e1) {
			System.out.println("�޷���ȡ����ͨѶ��֤��:" + e1);
		} catch (Exception e1) {
			System.out.println("ͨѶ�쳣" + e1);
		}
		
		// ��ӡ���ر���
		String result = mypost.getResponseBodyAsString();
		System.out.println("���ر���=");
		System.out.println(result);
		System.out.println();
		if (result.substring(0, 8).equals("reqData=")) {
			System.out.println("���ر���Base64�����=");
			System.out.println(base64Decode(result.substring(8)));
		}
	 
	}
	
	// Base64����ͽ��뷽�������Ը����Լ�ʹ�õĿ����±�д
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

	/* ���� Javadoc��
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO �Զ����ɷ������
		try {
			testNC();
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
}
