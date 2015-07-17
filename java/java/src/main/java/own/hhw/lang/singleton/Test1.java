package own.hhw.lang.singleton;

public class Test1
{
	public static void main(java.lang.String[] args)
    {
		Singleton1 singleton1 = Singleton1.getInstance();
		Singleton1 singleton2 = Singleton1.getInstance();
		System.out.println(singleton1.hashCode());
		System.out.println(singleton2.hashCode());
		//hashCode相同，说明singleton1和singleton2为同一个对象
    }
}
