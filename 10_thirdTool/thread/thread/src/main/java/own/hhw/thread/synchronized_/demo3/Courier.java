package own.hhw.thread.synchronized_.demo3;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-22
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
public class Courier extends Thread {
    private Carport carport;

    public Courier(Carport carport) {
        this.carport = carport;
    }

    public void delivery() throws InterruptedException {
        if (carport.getCar()) {
            Thread.sleep(100);
            System.out.println("送完货！");
            carport.returnCar();
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
}
