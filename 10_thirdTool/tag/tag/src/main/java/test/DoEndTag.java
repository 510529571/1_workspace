package test;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class DoEndTag extends TagSupport
{
	public int doEndTag() throws JspException
	{
		// 获得客户端的IP地址
		String remoteAddr = pageContext.getRequest().getRemoteAddr();
		// 如果客户端的IP地址是127.0.0.1，则返回EVAL_PAGE
		if (remoteAddr.equals("127.0.0.1"))
		{
			return this.EVAL_PAGE;
		}
		// 如果是其他的客户端IP地址，则返回SKIP_PAGE
		else
		{
			return this.SKIP_PAGE;
		}
	}
}