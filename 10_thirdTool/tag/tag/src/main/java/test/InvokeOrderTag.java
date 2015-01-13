package test;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class InvokeOrderTag extends TagSupport
{
	// 定义了两个属性，用于设置和读取标签的相应属性值
	private String attr1;
	private String attr2;

	// 设置attr1属性的值
	public void setAttr1(String attr1)
	{
		System.out.println("setAttr1");
		this.attr1 = attr1;
	}

	// 设置attr2属性的值
	public void setAttr2(String attr2)
	{
		System.out.println("setAttr2");
		this.attr2 = attr2;
	}

	@Override
	public void setPageContext(PageContext pageContext)
	{
		System.out.println("pageContext");
		super.setPageContext(pageContext);
	}

	@Override
	public void setParent(Tag t)
	{
		System.out.println("setParent");
		System.out.print("父标签：");
		System.out.println(t);
		super.setParent(t);
	}

	public int doStartTag() throws JspException
	{
		System.out.println("doStartTag");
		return super.doStartTag();
	}

	public int doEndTag() throws JspException
	{
		System.out.println("doEndTag");
		return super.doEndTag();
	}

	@Override
	public void release()
	{
		System.out.println("release");
		super.release();
	}
}