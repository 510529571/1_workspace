package own.hhw.thread;

//public class SaleTicketMutiThread implements Runnable
//{
//	int tickets = 100;
//	int temp = tickets;
//	boolean flag = true;
//
//	@Override
//	public void run()
//	{
//		while (flag)
//		{
//			if (tickets > 0)
//			{
//				try
//				{
//					Thread.sleep(30);
//				} catch (InterruptedException e)
//				{
//					e.printStackTrace();
//				}
//				sale();
//			} else
//			{
//				flag = false;
//				System.out.println(Thread.currentThread().getName() + "卖光了");
//			}
//		}
//	}
//
//	public synchronized void sale()
//	{
//		tickets--;
//		System.out.println(Thread.currentThread().getName() + " 已卖" + (temp - tickets) + "张，系统还剩" + tickets + "张票");
//	}
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args)
//	{
//		SaleTicketMutiThread st = new SaleTicketMutiThread();
//		new Thread(st, "一号窗口").start();
//		new Thread(st, "二号窗口").start();
//		new Thread(st, "三号窗口").start();
//		new Thread(st, "四号窗口").start();
//	}
//}

public class SaleTicketMutiThread implements Runnable
{
	int tickets = 30;
	final int temp = tickets;
	boolean flag = true;

	@Override
	public void run()
	{
		while (flag)
		{
//			try
//			{
//				Thread.sleep(1000);
//			} catch (InterruptedException e)
//			{
//				e.printStackTrace();
//			}
			sale();
		}
	}

	public synchronized  void sale()
	{
		System.out.println("开始卖票");
		//TODO:karl 可以将SaleTicketMutiThread替换成任意一个类，那这个还有什么意义呢？
		//synchronized(SaleTicketMutiThread.class)
		{
			if (flag)
			{
				//System.out.println(Thread.activeCount());
				try
				{
					Thread.sleep(300);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				tickets--;
				System.out.println(Thread.currentThread().getName() + "卖票1张， 已卖" + (temp - tickets) + "张，系统还剩" + tickets + "张票");

				if (tickets == 0)
				{
					flag = false;
					/*System.out.println(Thread.currentThread().getName() + "卖光了");*/
				}
			}
		}

	}

	public static void main(String[] args)
	{
		SaleTicketMutiThread st = new SaleTicketMutiThread();
//		st.start();
//		st.start();
//		st.start();
//		st.start();
		new Thread(st, "一号窗口").start();
		new Thread(st, "二号窗口").start();
		new Thread(st, "三号窗口").start();
		new Thread(st, "四号窗口").start();
	}
}