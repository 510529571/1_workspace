package test;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

public class TrTag extends BodyTagSupport
{
	public int doStartTag()
	{
		Tag tag = getParent();
	 if (tag instanceof ForEach)
        {
		 ForEach forEach = (ForEach) tag;
        }
		return 0;
	}
}
