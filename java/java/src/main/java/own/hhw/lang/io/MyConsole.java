package own.hhw.lang.io;

import java.io.Console;

public class MyConsole
{
	//hhw:task 了解java是如何和控制台交互信息的
	public static void main(String[] args)
	{
		Console console = System.console();
		String m=console.readLine();
		System.out.println(m);
	}
}
