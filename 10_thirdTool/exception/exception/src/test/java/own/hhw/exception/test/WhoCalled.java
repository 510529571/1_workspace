package own.hhw.exception.test;

public class WhoCalled
{
	static void f()
	{
		try
		{
			throw new Exception();
		} catch (Exception e)
		{
			for(StackTraceElement ste:e.getStackTrace())
				System.out.println(ste.getMethodName());
			e.printStackTrace();
		}
	}

	static void g()
	{
		f();
	}
	
	static void h()
	{
		g();
	}

	public static void main(String[] args)
	{
		/*f();
		
		g();*/
		
//		h();
        System.out.println("234 345".indexOf(" "));
    }
}
