package own.hhw.app.fileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.String;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FileUpload
{

	private java.lang.String userInfo = "";
	private String sysInfo = "";

	private HttpServletRequest request;
	private long maxSize = 1024 * 1024 * 20;
	private String rootPath = "";

	private long uploadedByte = 0;

	private HttpSession session = null;

	public FileUpload(HttpServletRequest request)
	{
		this.request = request;
		// 得到session为了下面把进度信息写入session，进度条实际上就是不停的获得此信息而生成的
		// 至于怎么不停的获得后台session里的进度信息，自己看着办吧，由于太多代码的关联就不贴出来了
		session = request.getSession(false);
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public HttpServletRequest getRequest()
	{
		return this.request;
	}

	public void setMaxSize(long maxSize)
	{
		this.maxSize = maxSize;
	}

	public long getMaxSize()
	{
		return this.maxSize;
	}

	public void setRootPath(String rootPath)
	{
		this.rootPath = rootPath;
	}

	public String getRootPath()
	{
		return this.rootPath;
	}

	public String getUserInfo()
	{
		return userInfo;
	}

	public void setUserInfo(String userInfo)
	{
		this.userInfo = userInfo;
	}

	public String getSysInfo()
	{
		return sysInfo;
	}

	private String getParam(String line, String name)
	{
		String r = "";
		if (line == null)
		{
			return "";
		}
		int i = line.indexOf(name);
		if (i != -1)
		{
			i = i + name.length() + 2;
		} else
		{
			return "";
		}

		int j = line.indexOf('"', i);
		if (j != -1)
		{
			r = line.substring(i, j);
		}
		return r;
	}

	public boolean uploade()
	{
		if (session == null)
		{
			this.userInfo = "您还没有登陆或登录已超时，请重新登陆";
			return false;
		}

		boolean bl = true;
		int len = 0;
		byte[] byteLine = new byte[1024 * 1024];
		String line = null;
		ServletInputStream in = null;

		RandomAccessFile fRandom = null;
		FileOutputStream fStream = null;

		String totalAttrib = "";
		String uploadedAttrib = "";
		String savePath = rootPath;
		String saveName = "";
		String canOverWrite = "false";
		String fileName = "";

		String contentType = request.getContentType();
		String boundary = null;
		// String end = null;

		boundary = "--" + contentType.substring(contentType.indexOf("boundary") + 9);
		// end = boundary+"--";

		userInfo = "";
		try
		{
			int l = byteLine.length;
			in = request.getInputStream();
			while ((len = in.readLine(byteLine, 0, l)) != -1)
			{
				this.uploadedByte += len;
				line = new String(byteLine, 0, len);
				if (line.startsWith("Content-Disposition: form-data"))
				{
					String name = this.getParam(line, "name");
					len = in.readLine(byteLine, 0, l);
					this.uploadedByte += len;
					len = in.readLine(byteLine, 0, l);
					this.uploadedByte += len;
					String value = new String(byteLine, 0, len);
					value = value.substring(0, value.length() - 2);

					if ("curCount".equals(name))
					{
						totalAttrib = "totalByte" + value;
						uploadedAttrib = "uploadedByte" + value;
						session.setAttribute(totalAttrib, request.getContentLength());
					} else if ("savePath".equals(name))
					{
						savePath = rootPath + value;
					} else if ("saveName".equals(name))
					{
						saveName = value;
					} else if ("canOverWrite".equals(name))
					{
						canOverWrite = "".equals(value) ? "false" : value;
					} else if ("fileData".equals(name))
					{
						fileName = this.getParam(line, "filename");
						if ("".equals(fileName))
						{
							continue;
						}

						File f = new File(savePath);
						if (!f.exists())
						{
							f.mkdirs();
						}

						f = new File(savePath + saveName);
						if (f.exists())
						{
							if (!"true".equals(canOverWrite))
							{
								userInfo += "文件" + fileName + "已存在;";
								bl = false;
								continue;
							} else
							{
								f.delete();
							}
						}

						fStream = new FileOutputStream(f);
						len = in.readLine(byteLine, 0, l);
						line = new String(byteLine, 0, len);
						this.uploadedByte += len;
						while (!line.startsWith(boundary))
						{
							fStream.write(byteLine, 0, len);
							len = in.readLine(byteLine, 0, l);
							line = new String(byteLine, 0, len);
							this.uploadedByte += len;
							session.setAttribute(uploadedAttrib, uploadedByte);
						}
						fRandom = new RandomAccessFile(f, "rw");
						long flen = fRandom.length();
						if (flen > 1)
						{
							fRandom.setLength(flen - 2);
						}
						fStream.flush();
						fStream.close();
						fRandom.close();
					}
				}
			}
		} catch (IOException e)
		{
			userInfo += fileName + "上传失败;";
			sysInfo = e.getLocalizedMessage();
			e.printStackTrace();
			bl = false;
		} finally
		{
			session.setAttribute(uploadedAttrib, uploadedByte);
			session.setAttribute(totalAttrib, 0);
			try
			{
				if (fRandom != null)
				{
					fRandom.close();
				}
				if (fStream != null)
				{
					fStream.flush();
					fStream.close();
				}
			} catch (IOException e)
			{
				sysInfo = e.getLocalizedMessage();
				bl = false;
				e.printStackTrace();
			}
		}
		return bl;
	}
}