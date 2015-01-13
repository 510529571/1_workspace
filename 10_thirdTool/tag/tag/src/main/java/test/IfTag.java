package test;

import javax.servlet.jsp.tagext.BodyTagSupport;

public class IfTag extends BodyTagSupport
{
	private boolean test;

	public void setTest(boolean test)
	{
		this.test = test;
	}

	public int doStartTag()
	{
		if (test)
			return EVAL_BODY_INCLUDE;
		else
			return SKIP_BODY;
	}
}
