package own.hhw.thread.synchronized_.demo6;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-22
 * Time: 下午4:09
 * 静态方法的同步特征
 */
public class Static extends Thread {
    private int tag = 0;

    public static void main(String[] args) {
        Static static1 = new Static();
        Static static2 = new Static();
        new Thread(static1).start();
        new Thread(static2).start();
    }

    public synchronized static void staticMhd() throws InterruptedException {
        System.out.println("静态方法开始");
        Thread.sleep(1000);
        System.out.println("静态方法结束");
    }

    @Override
    public void run() {
        if (tag == 0) {
            tag++;
            try {
                this.staticMhd();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } else if (tag == 1) {
            try {
                Static.staticMhd();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
