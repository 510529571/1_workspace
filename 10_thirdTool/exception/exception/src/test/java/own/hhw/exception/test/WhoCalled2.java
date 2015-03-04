package own.hhw.exception.test;

public class WhoCalled2
{
	static void f() throws Exception {
			throw new Exception();
	}

	static void g()
	{
        try {
            f();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	static void h()
	{
		g();
	}

	public static void main(String[] args)
	{
		h();
	}
}
