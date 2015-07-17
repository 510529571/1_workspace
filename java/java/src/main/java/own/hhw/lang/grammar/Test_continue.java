package own.hhw.lang.grammar;

public class Test_continue
{
	public static void main(java.lang.String[] args)
	{
		//下面的列子，i为4的倍数，则不打印后面的语句
		for (int i = 0; i < 20; i++)
	    {
			if(i%4==0)
				continue;
		    System.out.println("i:"+i);
	    }
	}
}
