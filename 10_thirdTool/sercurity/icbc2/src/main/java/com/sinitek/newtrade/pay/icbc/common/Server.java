package com.sinitek.newtrade.pay.icbc.common;

import org.mortbay.http.HttpContext;
import org.mortbay.http.HttpServer;
import org.mortbay.http.SunJsseListener;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.util.InetAddrPort;

public class Server
{
	private static Server HTTPSERVER = null;
	private HttpServer server = null;

	private Server()
	{
		server = new HttpServer();
	}

	public static Server GetServer()
	{
		if (HTTPSERVER == null)
		{
			HTTPSERVER = new Server();
		}
		return HTTPSERVER;
	}

	public void stop()
	{
		try
		{
			server.stop();
            System.out.println("����˹ر�");
		}
		catch (InterruptedException e)
		{
            System.out.println("�رշ�����쳣");
		}
	}
	/**
	 * 
	 ********************************************
	 * �������ƣ�start<br>
	 * �������ܣ��������<br>
	 * 
	 ********************************************
	 */
	public void start()
	{
		try
		{
			SunJsseListener safelistener = new SunJsseListener(new InetAddrPort(Integer.parseInt(ConfigContext.ep_port)));
			safelistener.setKeystore(ConfigContext.ep_store);
			safelistener.setKeyPassword(ConfigContext.ep_storepass);
			safelistener.setPassword(ConfigContext.ep_storepass);
			server.addListener(safelistener);
            System.out.println(ConfigContext.ep_port + "�˿ڴ�");

			HttpContext context = new HttpContext();
			context.setContextPath("/");
			server.addContext(context);
			ServletHandler servlets = new ServletHandler();
			context.addHandler(servlets);

			servlets.addServlet("/*", "com.sinitek.newtrade.pay.icbc.common.Eservlet");
			server.start();
            System.out.println("������������");
		}
		catch (Exception e1)
		{
            e1.printStackTrace();
            System.out.println("����������쳣");
		}
	}

}
