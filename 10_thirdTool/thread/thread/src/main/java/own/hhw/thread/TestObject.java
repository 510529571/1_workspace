package own.hhw.thread;

import org.junit.Test;

public class TestObject implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("123");
            doSome();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void doSome() throws InterruptedException {
        synchronized (this) {
            this.wait(40000);
            System.out.println(Thread.currentThread().getName() + "我等待了40秒");
        }
    }

    //TODO hhw 为什么main函数做人口得到的结果和junit的不一样呢？
    @Test
    public void test1() throws InterruptedException {
        TestObject to = new TestObject();

        new Thread(to, "thread1").start();
        new Thread(to, "thread2").start();

        Thread.sleep(1000);
        try {
            //hhw:task 按理说，这里的程序应该拿不到to对象的锁才对
            synchronized (to) {
                to.wait(50);
                to.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestObject to = new TestObject();

        new Thread(to, "thread1").start();
        new Thread(to, "thread2").start();

        Thread.sleep(1000);
        try {
            synchronized (to) {
                to.wait(50);
                to.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
