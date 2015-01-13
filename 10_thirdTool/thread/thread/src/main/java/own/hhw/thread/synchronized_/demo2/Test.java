package own.hhw.thread.synchronized_.demo2;

/**
 * 当一个线程进入一个对象的一个synchronized方法后，其它线程是否可进入此对象的其它方法?
 * 
 * @class Test
 * @description
 * @author 胡寒伟
 * @copyRight copyright(c) 2013 广东南航易网通电子商务有限公司,Rights Reserved
 * @time Aug 31, 2013 5:26:33 PM
 */
public class Test implements Runnable
{
	public void fun1()
	{
		  System.out.println("我在运行fun1");
	}
	public synchronized void fun2()
	{
		try
        {
	        Thread.sleep(1000);
	        System.out.println("我在运行fun2");
        } catch (InterruptedException e)
        {
	        e.printStackTrace();
        }
	}
	
    public void run()
    {
	    if("1".equals(Thread.currentThread().getName()))
	    	fun2();
	    else if("2".equals(Thread.currentThread().getName()))
	    	fun1();
    }
	
	public static void main(String[] args)
    {
	    Test test=new Test();
	    new Thread(test,"1").start();
	    new Thread(test,"2").start();
    }
}
