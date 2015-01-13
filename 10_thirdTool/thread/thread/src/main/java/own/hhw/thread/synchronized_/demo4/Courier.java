package own.hhw.thread.synchronized_.demo4;


/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-22
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
public class Courier extends Thread {
    private Carport carport;

    private static int tag = 0;

    public Courier(Carport carport) {
        this.carport = carport;
    }

    public void delivery() throws InterruptedException {
        if (tag == 0) {
            tag++;
            if (carport.getCar()) {
                Thread.sleep(100);
                System.out.println("送完货！");
            }
        } else if (tag == 1) {
            tag--;
            if (carport.getCar2()) {
                Thread.sleep(100);
                System.out.println("送完货！");
            }
        }
    }

    @Override
    public void run() {
        try {
            delivery();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Carport carport = new Carport(2);
        Courier courier1 = new Courier(carport);
        Courier courier2 = new Courier(carport);
        new Thread(courier1).start();
        Thread.sleep(10);
        new Thread(courier2).start();
    }
}
