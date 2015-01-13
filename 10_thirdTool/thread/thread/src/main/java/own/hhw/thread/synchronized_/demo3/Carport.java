package own.hhw.thread.synchronized_.demo3;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-22
 * Time: 上午11:01
 * To change this template use File | Settings | File Templates.
 */
public class Carport {
    private Integer num;

    public Carport(Integer num) {
        this.num = num;
    }

    public boolean getCar() throws InterruptedException {
        synchronized (num) {
            while(num==2){
                wait(100);
            }
            if (num == 0) {
                System.out.println("没有车了！");
                return false;
            } else {
                System.out.println("开始拿车...");
                Thread.sleep(2000);
                System.out.println("取车一辆，还剩" + (--num));
                return true;
            }
        }
    }

    /**
     * hhw:task 为什么getCar和returnCar没有锁住同一资源呢？
     * @throws InterruptedException
     */
    public void returnCar() throws InterruptedException {
        synchronized (num) {
            System.out.println("开始还车...");
            Thread.sleep(500);
            System.out.println("还车一辆，还剩" + (++num));
        }
    }
}
