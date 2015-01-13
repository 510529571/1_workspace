package test;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class OutputTag extends TagSupport
{
	private String name = null;

	public void setName(String name)
	{
		this.name = name;
	}

	public int doEndTag() throws JspException
	{
		try
		{
			JspWriter out = pageContext.getOut();
			out.print("Hello! " + name);
		} catch (Exception e)
		{
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
}