package own.hhw.lang.AI;

/**
 * Abs和Interf中都有成员变量i,Test对象应该使用哪个i呢
 * 
 * @author huhanwei
 * 
 */
public class Test extends Abs implements Interf
{
	public static void main(String[] args)
	{
		Test t = new Test();
        System.out.println("123");
//        System.out.println(t.i);


	}
}
