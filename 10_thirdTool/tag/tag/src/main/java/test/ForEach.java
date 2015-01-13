package test;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ForEach extends BodyTagSupport
{
	private String id;
	private String collection;
	private Iterator iter;

	public void setCollection(String collection)
	{
		this.collection = collection;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	// 遇到开始标签执行
	public int doStartTag() throws JspException
	{
		Collection coll = (Collection) pageContext.findAttribute(collection);
		// 表示如果未找到指定集合，则不用处理标签体，直接调用doEndTag()方法。
		if (coll == null || coll.isEmpty())
			return SKIP_BODY;

		iter = coll.iterator();
		pageContext.setAttribute(id, iter.next());
		// 表示在现有的输出流对象中处理标签体，但绕过setBodyContent()和doInitBody()方法
		// 这里一定要返回EVAL_BODY_INCLUDE，否则标签体的内容不会在网页上输出显示
		return EVAL_BODY_INCLUDE;
	}

	// 在doInitBody方法之前执行，在这里被绕过不执行
	@Override
	public void setBodyContent(BodyContent arg0)
	{
		System.out.println("setBodyContent...");
		super.setBodyContent(arg0);
	}

	// 此方法被绕过不会被执行
	@Override
	public void doInitBody() throws JspException
	{
		System.out.println("doInitBody...");
		super.doInitBody();
	}

	// 遇到标签体执行
	public int doAfterBody() throws JspException
	{
		if (iter.hasNext())
		{
			pageContext.setAttribute(id, iter.next());
			return EVAL_BODY_AGAIN;// 如果集合中还有对像，则循环执行标签体
		}
		return SKIP_BODY;// 迭代完集合后，跳过标签体，调用doEndTag()方法。
	}

	// 遇到结束标签执行
	public int doEndTag() throws JspException
	{
		System.out.println("doEndTag...");
		return SKIP_PAGE;
	}

}