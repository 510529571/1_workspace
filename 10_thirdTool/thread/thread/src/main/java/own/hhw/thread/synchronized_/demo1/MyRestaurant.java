package own.hhw.thread.synchronized_.demo1;

/**
 * @author HuangDong 2013-3-24
 */
public class MyRestaurant {
	MyMeal meal;
	MyChef chef = new MyChef(this);
	MyChef chef2 = new MyChef(this);
	MyWaiter waiter = new MyWaiter(this);
	MyWaiter waiter2 = new MyWaiter(this);

	public MyRestaurant() {
		/** 先启动厨师生产食物 */
//		chef.start();
//		chef2.start();
		/** 再启动服务员获取食物 */
//		waiter.start();
//		waiter2.start();
		new Thread(chef,"厨师1").start();
		new Thread(waiter,"服务员1").start();
		new Thread(waiter2,"服务员2").start();
	}

	public static void main(String[] args) {
		new MyRestaurant();
	}
}

class MyMeal {
	/** 对食物进行编号，以便于打印消息 */
	public int mealNumber;

	public MyMeal(int mealNumber) {
		super();
		this.mealNumber = mealNumber;
	}
}

class MyWaiter extends Thread {
	MyRestaurant restaurant;

	public MyWaiter(MyRestaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		super.run();
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal == null) {
						/** 如果食物为空，则服务员必须等待厨师生产食物 */
						wait();
					}
					System.out.println(Thread.currentThread().getName()+"拿到食物" + restaurant.meal.mealNumber);
					synchronized (restaurant.chef) {
						/** 如果拿到了厨师的锁，说明已经接受食物，将食物置空，并提醒厨师生产 */
						restaurant.meal = null;
						// notifyAll();
						restaurant.chef.notifyAll();
					}
					Thread.sleep(10);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("MyWaiter is interrupted!");
			e.printStackTrace();
		}
	}
}

class MyChef extends Thread {
	MyRestaurant restaurant;
	/** 给食物编号 */
	int number = 1;

	public MyChef(MyRestaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	@Override
	public void run() {
		super.run();
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null) {
						wait();
					}
					synchronized (restaurant.waiter) {
						/** 拿到服务员的锁后，生产一份食物并唤醒服务员的等待 */
						// MyMeal meal = new MyMeal(number); 这么写的话，对象是不唯一的。
						restaurant.meal = new MyMeal(number);
						System.out.println(Thread.currentThread().getName()+"生产食物" + number);
						number++;
						// notifyAll();这里调用notifyAll()比notify()更安全一些
						restaurant.waiter.notifyAll();
					}
					Thread.sleep(100);
				}
			}
		} catch (InterruptedException e) {
			System.out.println("MyChef is interrupted!");
			e.printStackTrace();
		}
	}
}