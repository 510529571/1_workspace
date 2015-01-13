package test;

import java.lang.reflect.Method;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class GetProperty extends BodyTagSupport
{

	private String name;
	private String property;

	public void setName(String name)
	{
		this.name = name;
	}

	public void setProperty(String property)
	{
		this.property = property;
	}

	@SuppressWarnings("unchecked")
	public int doStartTag() throws JspException
	{
		try
		{
			Object obj = pageContext.findAttribute(name);

			if (obj == null)
				return SKIP_BODY;

			Class c = obj.getClass();
			// 构造GET方法名字 get+属性名(属性名第一个字母大写)
			String getMethodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
			Method getMethod = c.getMethod(getMethodName, new Class[]
			{});

			pageContext.getOut().print(getMethod.invoke(obj));
			System.out.print(property + ":" + getMethod.invoke(obj) + "\t");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}
}