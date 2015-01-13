package test;

import java.text.SimpleDateFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class DateTimeFormat extends TagSupport
{
	private String time = null;
	private String formatString = null;

	public void setName(String time)
	{
		this.time = time;
	}

	public void setFormatString(String formatString)
	{
		this.formatString = formatString;
	}

	public int doEndTag() throws JspException
	{
		try
		{
			JspWriter out = pageContext.getOut();
			SimpleDateFormat sdf = new SimpleDateFormat(formatString);
			String timeOut = sdf.format(time);
			out.print(timeOut);
		} catch (Exception e)
		{
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}

}