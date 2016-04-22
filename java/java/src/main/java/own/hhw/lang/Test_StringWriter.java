package own.hhw.lang;

import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class Test_StringWriter
{
	@Test
	public void test1()
	{
        System.out.println(Math.random());
		StringWriter sw=new StringWriter();
		sw.append("m");
		System.out.println(sw.toString());
        sw.write("123213");
		System.out.println(sw.toString());

	}
}
