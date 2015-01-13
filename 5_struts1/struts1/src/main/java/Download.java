import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Download extends DispatchAction
{
	public ActionForward download(ActionMapping mapping, ActionForm webform, HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			String path = request.getParameter("path");
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。
			String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader(" Content-Disposition ", " attachment;filename= " + new String(filename.getBytes()));
			response.addHeader(" Content-Length ", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType(" application/octet-stream ");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	public ActionForward downloadFile(ActionMapping mapping, ActionForm webform, HttpServletRequest request, HttpServletResponse response)
	{

		String fileName = ""; // 文件名，输出到用户的下载对话框
		// 从文件完整路径中提取文件名，并进行编码转换，防止不能正确显示中文名
		String filePath = request.getParameter("path");
		try
		{
			if (filePath.lastIndexOf("/") > 0)
			{
				fileName = new String(filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length()).getBytes("GB2312"), "ISO8859_1");
			} else if (filePath.lastIndexOf("\\") > 0)
			{
				fileName = new String(filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length()).getBytes("GB2312"), "ISO8859_1");
			}

		} catch (Exception e)
		{
		}
		// 打开指定文件的流信息
		FileInputStream fs = null;
		try
		{
			fs = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// 设置响应头和保存文件名
		response.reset();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		// 写出流信息
		int b = 0;
		try
		{
			PrintWriter out = response.getWriter();
			response.getOutputStream();
			response.getOutputStream();
			while ((b = fs.read()) != -1)
			{
				out.write(b);
			}
			fs.close();
			out.close();
			System.out.println("文件下载完毕.");
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("下载文件失败!");
		}
		return null;
	}

	public void downloadLocal(HttpServletResponse response) throws FileNotFoundException
	{
		// 下载本地文件
		String fileName = " Operator.doc ".toString(); // 文件的默认保存名
		// 读到流中
		InputStream inStream = new FileInputStream(" c:/Operator.doc "); // 文件的存放路径
		// 设置输出的格式
		response.reset();
		response.setContentType(" bin ");
		response.addHeader(" Content-Disposition ", " attachment; filename=\"" + fileName + " \"");
		// 循环取出流中的数 据
		byte[] b = new byte[100];
		int len;
		try
		{
			while ((len = inStream.read(b)) > 0)
				response.getOutputStream().write(b, 0, len);
			inStream.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void downloadNet(HttpServletResponse response) throws MalformedURLException
	{
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;

		URL url = new URL(" windine.blogdriver.com/logo.gif ");

		try
		{
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			FileOutputStream fs = new FileOutputStream(" c:/abc.gif ");

			byte[] buffer = new byte[1204];
			int length;
			while ((byteread = inStream.read(buffer)) != -1)
			{
				bytesum += byteread;
				System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	// 支持在线打开文件的一种方式
	public void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception
	{
		File f = new File(filePath);
		if (!f.exists())
		{
			response.sendError(404, " File not found! ");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;

		response.reset(); // 非常重要
		if (isOnLine)
		{ // 在线打开方式
			URL u = new URL(" file:/// " + filePath);
			response.setContentType(u.openConnection().getContentType());
			response.setHeader(" Content-Disposition ", " inline; filename= " + f.getName());
			// 文件名应该编码成UTF-8
		} else
		{ // 纯下载方式
			response.setContentType(" application/x-msdownload ");
			response.setHeader(" Content-Disposition ", " attachment; filename= " + f.getName());
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		br.close();
		out.close();
	}
}
