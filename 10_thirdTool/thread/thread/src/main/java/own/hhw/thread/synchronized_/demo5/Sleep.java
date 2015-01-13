package own.hhw.thread.synchronized_.demo5;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-22
 * Time: 下午2:21
 * sleep并没有放弃资源锁
 */
public class Sleep extends Thread {

    public static void main(String[] args) {
        Sleep sleep = new Sleep();
        new Thread(sleep).start();
        new Thread(sleep).start();
    }

    public synchronized void doSomeThing() throws InterruptedException {
            System.out.println("开始");
            Thread.sleep(3000);
            System.out.println("结束");
    }

    @Override
    public void run() {
        try {
            doSomeThing();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
