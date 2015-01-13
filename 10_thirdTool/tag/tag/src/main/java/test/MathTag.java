package test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.jsp.tagext.BodyTagSupport;

public class MathTag extends BodyTagSupport
{
	private String method;
	private Double value;
	private boolean angle = false;
	private String pattern = "0.00";

	public int doStartTag()
	{
		try
		{
			Method m = Math.class.getMethod(method, new Class[]
			{ double.class });
			NumberFormat nf = NumberFormat.getNumberInstance();
			DecimalFormat df = (DecimalFormat) nf;
			df.applyPattern(pattern);

			if (angle)
				value = Math.toRadians(value);

			pageContext.getOut().print(df.format(m.invoke(null, value)));
		} catch (SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public void setMethod(String method)
	{
		this.method = method;
	}

	public void setValue(Double value)
	{
		this.value = value;
	}

	public void setAngle(boolean angle)
	{
		this.angle = angle;
	}

	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}
}
