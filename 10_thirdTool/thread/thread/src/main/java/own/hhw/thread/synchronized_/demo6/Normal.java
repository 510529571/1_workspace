package own.hhw.thread.synchronized_.demo6;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-22
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
public class Normal extends Thread {
    private int tag = 0;

    public static void main(String[] args) {
        Normal normal1 = new Normal();
        Normal normal2= new Normal();
        new Thread(normal1).start();
        new Thread(normal2).start();
    }

    public synchronized void normalMhd() throws InterruptedException {
        System.out.println("方法开始");
        Thread.sleep(1000);
        System.out.println("方法结束");
    }

    @Override
    public void run() {
        try {
            normalMhd();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
