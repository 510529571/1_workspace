package own.hhw.lang.grammar;

import org.junit.Test;

public class Test_static
{
	static String static_str="我是静态数据";
	String normal_str="我不静态数据";

    @Test
	public void test1()
	{
		System.out.println(Test_static.static_str);
		Test_static ts=new Test_static();
		System.out.println(ts.static_str);
	}

    @Test
    public void test()
    {
        Integer i=null;
        if(i==1){};
    }

    public class aa
    {

    }
}
