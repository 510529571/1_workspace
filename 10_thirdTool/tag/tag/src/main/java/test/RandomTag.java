package test;

import java.io.IOException;
import java.util.Random;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class RandomTag extends TagSupport
{
	// 封装random标签的两个属性的JavaBean属性
	private int min = 0;
	private int max = Integer.MAX_VALUE;

	// min属性的setter方法
	public void setMin(int min)
	{
		this.min = min;
	}

	// max属性的setter方法
	public void setMax(int max)
	{
		this.max = max;
	}

	// 覆盖TagSupport类的doStartTag方法
	// 当遇到标签（也就是<ct:random>）的开始标记时调用该方法
	@Override
	public int doStartTag() throws JspException
	{
		try
		{
			Random random = new Random();
			// 生成一个在min和max之间的随机数
			int result = min + random.nextInt(max - min);
			// 将生成的随机数输出到客户端
			pageContext.getOut().write(String.valueOf(result));
		} catch (IOException e)
		{
		}
		// TagSupport类的doStartTag方法默认返回SKIP_BODY，表示忽略自定义标签体
		return super.doStartTag();
	}

}