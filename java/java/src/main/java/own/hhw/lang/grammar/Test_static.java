package own.hhw.lang.grammar;

import org.junit.Test;

public class Test_static
{
	static String static_str="���Ǿ�̬����";
	String normal_str="�Ҳ���̬����";

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
