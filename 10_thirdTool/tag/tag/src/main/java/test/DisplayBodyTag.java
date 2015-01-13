package test;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class DisplayBodyTag extends TagSupport
{
	public int doStartTag() throws JspException
	{
		// 获得body请求参数的值

		String body = pageContext.getRequest().getParameter("body");
		// 存在body请求参数，返回EVAL_BODY_INCLUDE
		if (body != null)
		{
			return this.EVAL_BODY_INCLUDE;
		}
		// 不存在body请求参数，返回SKIP_BODY
		else

		{
			return this.SKIP_BODY;
		}
	}
}