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
            System.out.println("服务端关闭");
		}
		catch (InterruptedException e)
		{
            System.out.println("关闭服务端异常");
		}
	}
	/**
	 * 
	 ********************************************
	 * 方法名称：start<br>
	 * 方法功能：服务端起动<br>
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
            System.out.println(ConfigContext.ep_port + "端口打开");

			HttpContext context = new HttpContext();
			context.setContextPath("/");
			server.addContext(context);
			ServletHandler servlets = new ServletHandler();
			context.addHandler(servlets);

			servlets.addServlet("/*", "com.sinitek.newtrade.pay.icbc.common.Eservlet");
			server.start();
            System.out.println("服务端启动完成");
		}
		catch (Exception e1)
		{
            e1.printStackTrace();
            System.out.println("服务端启动异常");
		}
	}

}
